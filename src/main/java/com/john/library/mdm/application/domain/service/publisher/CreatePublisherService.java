package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandPublisherPort;
import com.john.library.mdm.application.port.out.persistence.QueryPublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreatePublisherService implements CreatePublisherUseCase {

  private final CommandPublisherPort commandPublisherPort;
  private final QueryPublisherPort queryPublisherPort;

  private final PublisherApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(CreatePublisherCommand command) {
    if (queryPublisherPort.existsByName(command.getName())) {
      log.error("Publisher with name {} already exists.", command.getName());
      throw new DuplicateResourceException("Publisher", Publisher.Fields.name);
    }

    Publisher publisher = applicationInboundMapper.mapToDomain(command);
    return Result.of(commandPublisherPort.create(publisher));
  }
}
