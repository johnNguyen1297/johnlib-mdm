package com.john.library.mdm.adapter.in.rest.dto.response.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.common.constant.I18nKey;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.experimental.Accessors;
import lombok.val;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

  private String message;
  private T data;

  @With
  private Metadata metadata;

  @JsonIgnore
  private I18nDto i18n;

  @JsonIgnore
  private HttpStatus status = HttpStatus.OK;

  @SuppressWarnings("rawtypes")
  public static <E> BaseResponse of(Result<E> result) {
    if (result.getData() instanceof AppPageResponse<?> appPageResponse) {
      return ofPage(appPageResponse, result.getMessageKey(), result.getObjects());
    }

    val response = new BaseResponse<E>();
    response.data   = result.getData();
    response.i18n   = new I18nDto(result.getMessageKey(), result.getObjects());
    response.status = result.getMessageKey() == null
                      ? HttpStatus.OK
                      : result.getMessageKey().getStatus();
    return response;
  }

  private static <E> BaseResponse<List<E>> ofPage(AppPageResponse<E> page,
                                                  I18nKey key,
                                                  Object[] objects) {
    val response = new BaseResponse<List<E>>();
    response.data     = page.getContent();
    response.metadata = Metadata.builder()
                                .pageInfo(PageInfo.from(page))
                                .build();
    response.i18n     = new I18nDto(key, objects);
    return response;
  }

  public static BaseResponse<Void> fail(String message) {
    return fail(message, null);
  }

  public static BaseResponse<Void> fail(String message, ApiErrorDetail apiErrorDetail) {
    val response = new BaseResponse<Void>();
    response.message  = message;
    response.metadata = Metadata.builder()
                                .apiErrorDetail(apiErrorDetail)
                                .build();
    return response;
  }

  public static BaseResponse<Void> fail(ApiErrorDetail apiErrorDetail) {
    val response = new BaseResponse<Void>();
    response.message  = apiErrorDetail.getTitle();
    response.metadata = Metadata.builder()
                                .apiErrorDetail(apiErrorDetail)
                                .build();
    return response;
  }

  @Getter
  @RequiredArgsConstructor
  @EqualsAndHashCode
  @ToString
  @SuppressWarnings("ClassCanBeRecord")
  public static class I18nDto {

    private final I18nKey key;
    private final Object[] objects;
  }


}