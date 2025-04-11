package com.john.library.mdm.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;


@Getter
@Builder
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("ClassCanBeRecord")
public class AppPageResponse<E> {

  private final List<E> content;
  private final Integer totalElements;
  private final Integer totalPages;
  private final boolean hasPrevious;
  private final boolean hasNext;
  private final Integer number;
  private final Integer size;

  public static <T> AppPageResponse<T> empty() {
    return AppPageResponse.<T>builder()
                       .content(new LinkedList<>())
                          .totalElements(0)
                       .totalPages(0)
                       .hasPrevious(false)
                       .hasNext(false)
                       .build();
  }

  public boolean isEmpty() {
    return content == null || content.isEmpty();
  }

  public <T> AppPageResponse<T> map(final Function<E, T> mapper) {
    val newContent = content.stream().map(mapper).toList();
    return AppPageResponse.<T>builder()
                       .content(newContent)
                       .totalElements(totalElements)
                       .totalPages(totalPages)
                       .hasPrevious(hasPrevious)
                       .hasNext(hasNext)
                       .number(number)
                       .size(size)
                       .build();
  }

  public AppPageResponse<E> sort(final Comparator<E> comparator) {
    val newContent = content.stream().sorted(comparator).toList();
    return AppPageResponse.<E>builder()
                       .content(newContent)
                       .totalElements(totalElements)
                       .totalPages(totalPages)
                       .hasPrevious(hasPrevious)
                       .hasNext(hasNext)
                       .number(number)
                       .size(size)
                       .build();
  }
}
