package com.john.library.mdm.application.dto.request;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.domain.Pageable;

@Getter
@AllArgsConstructor
public class AppPageRequest {

  private int page = 0;
  private int size = 10;
  private Sort sort;

  public static AppPageRequest of(final int page, final int size) {
    return new AppPageRequest(page, size, null);
  }

  public static AppPageRequest of(final int page, final int size, final Sort sort) {
    return new AppPageRequest(page, size, sort);
  }

  public static AppPageRequest of(final Pageable springPageable) {
    return new AppPageRequest(springPageable.getPageNumber(), springPageable.getPageSize(),
                              Sort.of(springPageable.getSort()));
  }

  public AppPageRequest sort(final Sort.Order... orders) {
    if (sort == null) {
      sort = new Sort();
    }
    sort.and(orders);
    return this;
  }


  public List<Sort.Order> getSortingOrders() {
    return Optional.ofNullable(sort).map(Sort::getOrders).orElse(List.of());
  }


  public boolean isUnsorted() {
    return getSortingOrders().isEmpty();
  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Sort {

    private final List<Order> orders = new LinkedList<>();

    public static Sort unsorted() {
      return new Sort();
    }

    public static Sort of(final Order order) {
      return new Sort().and(order);
    }

    public static Sort of(org.springframework.data.domain.Sort springSort) {
      if (springSort == null || springSort.isUnsorted()) {
        return unsorted();
      }

      val sorting = new Sort();
      springSort.forEach(order -> sorting.getOrders().add(new Order(order.getProperty(),
                                                                    order.isAscending()
                                                                    ? Direction.ASC
                                                                    : Direction.DESC)));
      return sorting;
    }

    public static Sort of(final List<Order> orders) {
      val sorting = new Sort();
      sorting.getOrders().addAll(orders);
      return sorting;
    }

    public Sort and(final Order... orders) {
      this.orders.addAll(List.of(orders));
      return this;
    }

    public enum Direction {
      ASC,
      DESC
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Order {

      private final String property;
      private final Direction direction;

      public static Order asc(final String property) {
        return new Order(property, Direction.ASC);
      }

      public static Order desc(final String property) {
        return new Order(property, Direction.DESC);
      }
    }
  }
}
