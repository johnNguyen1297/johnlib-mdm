package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.usecase.author.DeleteAuthorUseCase;
import com.john.library.mdm.application.port.out.author.DeleteAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class DeleteAuthorService implements DeleteAuthorUseCase {

  private final DeleteAuthorPort deleteAuthorPort;

  @Override
  public Result<Integer> execute(final Integer authorId) {
    val deleteResult = deleteAuthorPort.delete(authorId);
    if (deleteResult == 0) {
      log.error("Author with id {} not found.", authorId);
      throw new ResourceNotFoundException("Author", authorId);
    }

    return Result.of(deleteResult);
  }
}
