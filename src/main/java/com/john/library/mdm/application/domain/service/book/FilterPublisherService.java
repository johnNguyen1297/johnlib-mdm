//package com.john.library.mdm.application.domain.service.book;
//
//import com.john.library.mdm.adapter.in.rest.dto.response.PublisherListingItem;
//import com.john.library.mdm.application.dto.response.PageResponse;
//import com.john.library.mdm.application.dto.response.Result;
//import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
//import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;
//import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherUseCase;
//import com.john.library.mdm.application.port.out.publisher.FilterPublisherPort;
//import com.john.library.mdm.common.UseCase;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@UseCase
//@RequiredArgsConstructor
//@Slf4j
//public class FilterPublisherService implements FilterPublisherUseCase {
//
//  private final FilterPublisherPort filterPublisherPort;
//
//  private final PublisherApplicationMapper applicationInboundMapper;
//
//  @Override
//  public Result<PageResponse<PublisherListingItem>> execute(FilterPublisherQuery query) {
//    return Result.ofPage(filterPublisherPort.listByFilter(query)
//                                            .map(applicationInboundMapper::mapToResponse));
//  }
//}
