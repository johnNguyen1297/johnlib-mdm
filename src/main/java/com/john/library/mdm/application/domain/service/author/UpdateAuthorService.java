package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorUseCase;
import com.john.library.mdm.application.port.out.author.QueryAuthorPort;
import com.john.library.mdm.application.port.out.author.UpdateAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateAuthorService implements UpdateAuthorUseCase {

  private final UpdateAuthorPort updateAuthorPort;

  private final QueryAuthorPort queryAuthorPort;

  private final AuthorApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(UpdateAuthorCommand command) {
    if (!queryAuthorPort.existsById(command.getId())) {
      log.error("Author with id {} not found", command.getId());
      throw new IllegalArgumentException(
          "Author not found"); // TODO 03/04/2025: throw own Exception
    }

    if (queryAuthorPort.existsByNameAndIdNot(command.getName(), command.getId())) {
      log.error("Author with name {} already exists", command.getName());
      throw new IllegalArgumentException(
          "Author with name already exists"); // TODO 03/04/2025: throw own Exception
    }

    val author = applicationInboundMapper.mapToDomain(command);
    return Result.of(updateAuthorPort.update(author));
  }
}
