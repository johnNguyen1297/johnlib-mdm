package com.john.library.mdm.common.util;

import com.john.library.mdm.application.dto.request.AppPageRequest;
import com.john.library.mdm.application.dto.response.PageResponse;
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
   * Map to application level jpaPage response.
   *
   * @param <T>     the type parameter
   * @param jpaPage the Spring jpaPage
   * @return the application level jpaPage response
   */
  public static <T> PageResponse<T> map(final Page<T> jpaPage) {
    return jpaPage == null || jpaPage.isEmpty()
           ? PageResponse.empty()
           : map(jpaPage, Function.identity());
  }

  public static <T, J> PageResponse<T> map(final Page<J> jpaPage, Function<J, T> transformer) {
    return jpaPage == null || jpaPage.isEmpty()
           ? PageResponse.empty()
           : PageResponse.<T>builder()
                         .content(jpaPage.getContent().stream().map(transformer).toList())
                         .number(jpaPage.getNumber())
                         .size(jpaPage.getSize())
                         .totalElements(jpaPage.getTotalElements())
                         .totalPages(jpaPage.getTotalPages())
                         .hasNext(jpaPage.hasNext())
                         .hasPrevious(jpaPage.hasPrevious())
                         .build();
  }

  public static <T, J> PageResponse<T> map(final PageResponse<J> pageResponse,
                                           Function<J, T> transformer) {
    return pageResponse == null || pageResponse.isEmpty()
           ? PageResponse.empty()
           : PageResponse.<T>builder()
                         .content(pageResponse.getContent().stream().map(transformer).toList())
                         .number(pageResponse.getNumber())
                         .size(pageResponse.getSize())
                         .totalElements(pageResponse.getTotalElements())
                         .totalPages(pageResponse.getTotalPages())
                         .hasNext(pageResponse.getHasNext())
                         .hasPrevious(pageResponse.getHasPrevious())
                         .build();
  }

  /**
   * Map to Spring Pageable.
   *
   * @param appPageRequest the custom page request
   * @return the Spring Pageable object
   */
  public static Pageable mapToPageable(final AppPageRequest appPageRequest) {
    return PageRequest.of(appPageRequest.getPage(), appPageRequest.getSize(),
                          mapToSpringSort(appPageRequest.getSort()));
  }

  /**
   * Map to Spring Sort.
   *
   * @param sort the application level sorting
   * @return the Spring optional sort
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
