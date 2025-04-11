package com.john.library.mdm.application.domain.service.tag;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.usecase.tag.DeleteTagUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandTagPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeleteTagService implements DeleteTagUseCase {

  private final CommandTagPort commandTagPort;

  @Override
  public Result<Integer> execute(final Integer id) {
    val deleteResult = commandTagPort.delete(id);
    if (deleteResult == 0) {
      log.error("Tag with id {} not found.", id);
      throw new ResourceNotFoundException("Tag", id);
    }

    return Result.of(deleteResult);
  }
}
