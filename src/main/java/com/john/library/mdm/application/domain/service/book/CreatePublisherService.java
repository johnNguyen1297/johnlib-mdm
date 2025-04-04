//package com.john.library.mdm.application.domain.service.book;
//
//import com.john.library.mdm.application.domain.model.Publisher;
//import com.john.library.mdm.application.dto.response.Result;
//import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
//import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherCommand;
//import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherUseCase;
//import com.john.library.mdm.application.port.out.publisher.CreatePublisherPort;
//import com.john.library.mdm.common.UseCase;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@UseCase
//@RequiredArgsConstructor
//@Slf4j
//public class CreatePublisherService implements CreatePublisherUseCase {
//
//  private final CreatePublisherPort createPublisherPort;
//
//  private final PublisherApplicationMapper applicationInboundMapper;
//
//  @Override
//  public Result<Integer> execute(CreatePublisherCommand command) {
//    Publisher publisher = applicationInboundMapper.mapToDomain(command);
//    return Result.of(createPublisherPort.create(publisher));
//  }
//}
