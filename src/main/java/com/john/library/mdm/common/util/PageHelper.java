package com.john.library.mdm.common.util;

import com.john.library.mdm.application.dto.request.AppPageRequest;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import java.util.function.Function;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PageHelper {

  /**
   * Maps a JPA Page object to a custom PageResponse object.
   *
   * @param jpaPage the JPA Page object
   * @param transformer a function to transform the content of the page
   * @param <T> the type of the target content
   * @param <J> the type of the source content
   * @return a PageResponse object containing the transformed content
   */
  public static <T, J> AppPageResponse<T> map(final Page<J> jpaPage, Function<J, T> transformer) {
    return jpaPage == null || jpaPage.isEmpty()
           ? AppPageResponse.empty()
           : AppPageResponse.<T>builder()
                            .content(jpaPage.getContent().stream().map(transformer).toList())
                            .number(jpaPage.getNumber())
                            .size(jpaPage.getSize())
                            .totalElements((int) jpaPage.getTotalElements())
                            .totalPages(jpaPage.getTotalPages())
                            .hasNext(jpaPage.hasNext())
                            .hasPrevious(jpaPage.hasPrevious())
                            .build();
  }

  /**
   * Transforms a custom PageResponse object to another PageResponse object with a different content
   * type.
   *
   * @param appPageResponse the source PageResponse object
   * @param transformer     a function to transform the content of the page
   * @param <T>             the type of the target content
   * @param <J>             the type of the source content
   * @return a transformed PageResponse object
   */
  public static <T, J> AppPageResponse<T> transform(final AppPageResponse<J> appPageResponse,
                                                    Function<J, T> transformer) {
    return appPageResponse == null || appPageResponse.isEmpty()
           ? AppPageResponse.empty()
           : AppPageResponse.<T>builder()
                            .content(
                                appPageResponse.getContent().stream().map(transformer).toList())
                            .number(appPageResponse.getNumber())
                            .size(appPageResponse.getSize())
                            .totalElements(appPageResponse.getTotalElements())
                            .totalPages(appPageResponse.getTotalPages())
                            .hasNext(appPageResponse.isHasNext())
                            .hasPrevious(appPageResponse.isHasPrevious())
                            .build();
  }

  /**
   * Converts a custom AppPageRequest object to a Spring Pageable object.
   *
   * @param appPageRequest the custom page request
   * @return the Spring Pageable object
   */
  public static Pageable mapToPageable(final AppPageRequest appPageRequest) {
    return PageRequest.of(appPageRequest.getPage(), appPageRequest.getSize(),
                          mapToSpringSort(appPageRequest.getSort()));
  }

  /**
   * Converts a custom AppPageRequest.Sort object to a Spring Sort object.
   *
   * @param sort the application-level sorting object
   * @return the Spring Sort object
   */
  public static Sort mapToSpringSort(final AppPageRequest.Sort sort) {
    if (sort == null) {
      return org.springframework.data.domain.Sort.unsorted();
    }

    val orders = sort.getOrders().stream()
                     .map(o -> AppPageRequest.Sort.Direction.ASC == o.getDirection()
                               ? org.springframework.data.domain.Sort.Order.asc(o.getProperty())
                               : org.springframework.data.domain.Sort.Order.desc(o.getProperty()))
                     .toList();

    return org.springframework.data.domain.Sort.by(orders);
  }
}
