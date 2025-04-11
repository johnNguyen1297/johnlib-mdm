package com.john.library.mdm.adapter.in.rest.dto.response.base;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageInfo {

  private Integer page;
  private Integer totalPage;
  private boolean hasNext;
  private Boolean hasPrevious;
  private Integer size;
  private Integer totalElements;

  public static PageInfo from(AppPageResponse<?> appPageResponse) {
    return appPageResponse.isEmpty()
           ? null
           : PageInfo.builder()
                     .page(appPageResponse.getNumber())
                     .totalPage(appPageResponse.getTotalPages())
                     .hasNext(appPageResponse.isHasNext())
                     .hasPrevious(appPageResponse.isHasPrevious())
                     .size(appPageResponse.getSize())
                     .totalElements(appPageResponse.getTotalElements())
                     .build();
  }
}
