package com.john.library.mdm.adapter.in.rest.dto.response;

import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.dto.response.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.val;
import org.apache.logging.log4j.util.Strings;

@Getter
@Setter(AccessLevel.MODULE)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

  private T data;
  private Metadata metadata;

  @SuppressWarnings("rawtypes")
  public static <E> BaseResponse of(Result<E> result) {
    if (result.getData() instanceof PageResponse<?> pageResponse) {
      return ofPage(pageResponse);
    }

    val response = new BaseResponse<E>();
    response.data = result.getData();
    return response;
  }

  public static <E> BaseResponse<List<E>> ofPage(PageResponse<E> page) {
    val response = new BaseResponse<List<E>>();
    response.data     = page.getContent();
    response.metadata = Metadata.builder()
                                .page(page.getNumber())
                                .totalPage(page.getTotalPages())
                                .hasNextPage(page.getHasNext())
                                .hasPreviousPage(page.getHasPrevious())
                                .size(page.getSize())
                                .totalElements(page.getTotalElements())
                                .build();
    return response;
  }

  public static <T, E> BaseResponse<List<E>> ofPage(Result<PageResponse<T>> pageResult,
                                                    Function<T, E> transformer) {
    val page = pageResult.getData();
    val response = new BaseResponse<List<E>>();
    response.data     = page.getContent().stream().map(transformer).toList();
    response.metadata = Metadata.builder()
                                .page(page.getNumber())
                                .totalPage(page.getTotalPages())
                                .hasNextPage(page.getHasNext())
                                .hasPreviousPage(page.getHasPrevious())
                                .size(page.getSize())
                                .totalElements(page.getTotalElements())
                                .build();
    return response;
  }

  public static BaseResponse<Void> fail(String message) {
    return fail(message, Strings.EMPTY);
  }

  public static BaseResponse<Void> fail(String message, String field) {
    return fail(message, field, null);
  }

  public static BaseResponse<Void> fail(List<FieldViolation> errors) {
    return fail(null, errors);
  }

  public static BaseResponse<Void> fail(String message, List<FieldViolation> errors) {
    val response = new BaseResponse<Void>();
    response.metadata = Metadata.builder()
                                .message(message)
                                .errors((errors != null) ? new ArrayList<>(errors) : null)
                                .build();
    return response;
  }

  public static BaseResponse<Void> fail(String message, String field, List<FieldViolation> errors) {
    val response = new BaseResponse<Void>();
    response.metadata = Metadata.builder()
                                .message(message)
                                .field(field)
                                .errors((errors != null) ? new ArrayList<>(errors) : null)
                                .build();
    return response;
  }

}