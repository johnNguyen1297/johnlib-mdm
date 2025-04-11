package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.domain.model.Publisher.Fields;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandPublisherPort;
import com.john.library.mdm.application.port.out.persistence.QueryPublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdatePublisherService implements UpdatePublisherUseCase {

  private final CommandPublisherPort commandPublisherPort;

  private final QueryPublisherPort queryPublisherPort;

  private final PublisherApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(UpdatePublisherCommand command) {
    if (!queryPublisherPort.existsById(command.getId())) {
      log.error("Publisher with id {} not found", command.getId());
      throw new ResourceNotFoundException("Publisher", command.getId());
    }

    if (queryPublisherPort.existsByNameAndIdNot(command.getName(), command.getId())) {
      log.error("Publisher with name {} already exists", command.getName());
      throw new DuplicateResourceException("Publisher", Fields.name);
    }

    val publisher = applicationInboundMapper.mapToDomain(command);
    return Result.of(commandPublisherPort.update(publisher));
  }
}
