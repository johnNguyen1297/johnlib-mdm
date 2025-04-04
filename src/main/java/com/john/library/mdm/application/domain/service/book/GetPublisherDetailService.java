//package com.john.library.mdm.application.domain.service.book;
//
//import com.john.library.mdm.adapter.in.rest.dto.response.PublisherListingItem;
//import com.john.library.mdm.application.dto.response.Result;
//import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
//import com.john.library.mdm.application.port.in.usecase.publisher.GetPublisherDetailUseCase;
//import com.john.library.mdm.application.port.out.publisher.QueryPublisherPort;
//import com.john.library.mdm.common.UseCase;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import lombok.val;
//
//@UseCase
//@RequiredArgsConstructor
//@Slf4j
//public class GetPublisherDetailService implements GetPublisherDetailUseCase {
//
//  private final QueryPublisherPort queryPublisherPort;
//
//  private final PublisherApplicationMapper publisherApplicationMapper;
//
//  @Override
//  public Result<PublisherListingItem> execute(final Integer input) {
//    val foundPublisher = queryPublisherPort.findById(input);
//    return foundPublisher.map(publisherApplicationMapper::mapToResponse)
//                         .map(Result::of)
//                         .orElseThrow(() -> new IllegalArgumentException("Publisher not found"));
//  }
//}
