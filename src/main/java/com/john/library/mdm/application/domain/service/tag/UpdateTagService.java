package com.john.library.mdm.application.domain.service.tag;

import com.john.library.mdm.application.domain.model.Tag.Fields;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.mapper.TagApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandTagPort;
import com.john.library.mdm.application.port.out.persistence.QueryPublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateTagService implements UpdateTagUseCase {

  private final CommandTagPort commandTagPort;

  private final QueryPublisherPort queryPublisherPort;

  private final TagApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(UpdateTagCommand command) {
    if (!queryPublisherPort.existsById(command.getId())) {
      log.error("Publisher with id {} not found", command.getId());
      throw new ResourceNotFoundException("Tag", command.getId());
    }

    if (queryPublisherPort.existsByNameAndIdNot(command.getName(), command.getId())) {
      log.error("Publisher with name {} already exists", command.getName());
      throw new DuplicateResourceException("Tag", Fields.name);
    }

    val tag = applicationInboundMapper.mapToDomain(command);
    return Result.of(commandTagPort.update(tag));
  }
}
