package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.domain.model.Author.Fields;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandAuthorPort;
import com.john.library.mdm.application.port.out.persistence.QueryAuthorPort;
import com.john.library.mdm.common.UseCase;
import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreateAuthorService implements CreateAuthorUseCase {

  private final CommandAuthorPort commandAuthorPort;
  private final QueryAuthorPort queryAuthorPort;

  private final AuthorApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(CreateAuthorCommand command) {
    if (queryAuthorPort.existsByName(command.getName())) {
      log.error("Author with name {} already exists", command.getName());
      throw new DuplicateResourceException("Author", Fields.name);
    }

    Author author = applicationInboundMapper.mapToDomain(command);
    return Result.of(commandAuthorPort.create(author), Common.CREATED);
  }
}
