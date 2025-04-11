package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.usecase.author.DeleteAuthorUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandAuthorPort;
import com.john.library.mdm.common.UseCase;
import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeleteAuthorService implements DeleteAuthorUseCase {

  private final CommandAuthorPort commandAuthorPort;

  @Override
  public Result<Integer> execute(final Integer authorId) {
    val deleteResult = commandAuthorPort.delete(authorId);
    if (deleteResult == 0) {
      log.error("Author with id {} not found.", authorId);
      throw new ResourceNotFoundException("Author", authorId);
    }

    return Result.of(deleteResult, Common.DELETED);
  }
}
