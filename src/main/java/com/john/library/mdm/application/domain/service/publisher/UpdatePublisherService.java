package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherUseCase;
import com.john.library.mdm.application.port.out.publisher.QueryPublisherPort;
import com.john.library.mdm.application.port.out.publisher.UpdatePublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdatePublisherService implements UpdatePublisherUseCase {

  private final UpdatePublisherPort updatePublisherPort;

  private final QueryPublisherPort queryPublisherPort;

  private final PublisherApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(UpdatePublisherCommand command) {
    if (!queryPublisherPort.existsById(command.getId())) {
      log.error("Publisher with id {} not found", command.getId());
      throw new IllegalArgumentException(
          "Publisher not found"); // TODO 03/04/2025: throw own Exception
    }

    if (queryPublisherPort.existsByNameAndIdNot(command.getName(), command.getId())) {
      log.error("Publisher with name {} already exists", command.getName());
      throw new IllegalArgumentException(
          "Publisher with name already exists"); // TODO 03/04/2025: throw own Exception
    }

    val publisher = applicationInboundMapper.mapToDomain(command);
    return Result.of(updatePublisherPort.update(publisher));
  }
}
