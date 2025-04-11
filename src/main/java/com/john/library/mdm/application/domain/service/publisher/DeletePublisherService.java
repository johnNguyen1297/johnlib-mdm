package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.usecase.publisher.DeletePublisherUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandPublisherPort;
import com.john.library.mdm.common.UseCase;
import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeletePublisherService implements DeletePublisherUseCase {

  private final CommandPublisherPort commandPublisherPort;

  @Override
  public Result<Integer> execute(final Integer publisherId) {
    val deleteResult = commandPublisherPort.delete(publisherId);
    if (deleteResult == 0) {
      log.error("Publisher with id {} not found.", publisherId);
      throw new ResourceNotFoundException("Publisher", publisherId);
    }

    return Result.of(deleteResult, Common.DELETED);
  }
}
