package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.usecase.publisher.DeletePublisherUseCase;
import com.john.library.mdm.application.port.out.publisher.DeletePublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeletePublisherService implements DeletePublisherUseCase {

  private final DeletePublisherPort deletePublisherPort;

  @Override
  public Result<Integer> execute(final Integer publisherId) {
    val deleteResult = deletePublisherPort.delete(publisherId);
    if (deleteResult == 0) {
      log.error("Publisher with id {} not found.", publisherId);
      throw new IllegalArgumentException("Publisher not found"); // TODO 03/04/2025:
    }

    return Result.of(deleteResult);
  }
}
