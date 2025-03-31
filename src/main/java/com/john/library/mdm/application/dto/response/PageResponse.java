package com.john.library.mdm.application.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.val;


@Getter
@Builder
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("ClassCanBeRecord")
public class PageResponse<E> {

  private final List<E> content;
  private final Long totalElements;
  private final Integer totalPages;
  private final Boolean hasPrevious;
  private final Boolean hasNext;
  private final Integer number;
  private final Integer size;

  @JsonCreator
  public PageResponse(
      @JsonProperty("content") List<E> content,
      @JsonProperty("totalElements") Long totalElements,
      @JsonProperty("totalPages") Integer totalPages,
      @JsonProperty("hasPrevious") Boolean hasPrevious,
      @JsonProperty("hasNext") Boolean hasNext,
      @JsonProperty("number") Integer number,
      @JsonProperty("size") Integer size) {
    this.content       = content;
    this.totalElements = totalElements;
    this.totalPages    = totalPages;
    this.hasPrevious   = hasPrevious;
    this.hasNext       = hasNext;
    this.number        = number;
    this.size          = size;
  }

  public static <T> PageResponse<T> empty() {
    return PageResponse.<T>builder()
                       .content(new LinkedList<>())
                       .totalElements(0L)
                       .totalPages(0)
                       .hasPrevious(false)
                       .hasNext(false)
                       .build();
  }

  public boolean isEmpty() {
    return content == null || content.isEmpty();
  }

  public <T> PageResponse<T> map(final Function<E, T> mapper) {
    val newContent = content.stream().map(mapper).toList();
    return PageResponse.<T>builder()
                       .content(newContent)
                       .totalElements(totalElements)
                       .totalPages(totalPages)
                       .hasPrevious(hasPrevious)
                       .hasNext(hasNext)
                       .number(number)
                       .size(size)
                       .build();
  }

  public PageResponse<E> sort(final Comparator<E> comparator) {
    val newContent = content.stream().sorted(comparator).toList();
    return PageResponse.<E>builder()
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
