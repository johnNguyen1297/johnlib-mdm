package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorUseCase;
import com.john.library.mdm.application.port.out.author.CreateAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreateAuthorService implements CreateAuthorUseCase {

  private final CreateAuthorPort createAuthorPort;

  private final AuthorApplicationMapper applicationInboundMapper;

  @Override
  public Result<Integer> execute(CreateAuthorCommand command) {
    Author author = applicationInboundMapper.mapToDomain(command);
    return Result.of(createAuthorPort.create(author));
  }
}
