package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.domain.model.Author.Fields;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandAuthorPort;
import com.john.library.mdm.application.port.out.persistence.QueryAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateAuthorService implements UpdateAuthorUseCase {

  private static final String RESOURCE_NAME = "Author";
  private final CommandAuthorPort commandAuthorPort;
  private final QueryAuthorPort queryAuthorPort;
  private final AuthorApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(UpdateAuthorCommand command) {
    if (!queryAuthorPort.existsById(command.getId())) {
      log.error("Author with id {} not found", command.getId());
      throw new ResourceNotFoundException(RESOURCE_NAME);
    }

    if (queryAuthorPort.existsByNameAndIdNot(command.getName(), command.getId())) {
      log.error("Author with name {} already exists", command.getName());
      throw new DuplicateResourceException(RESOURCE_NAME, Fields.name);
    }

    val author = applicationInboundMapper.mapToDomain(command);
    return Result.of(commandAuthorPort.update(author));
  }
}
