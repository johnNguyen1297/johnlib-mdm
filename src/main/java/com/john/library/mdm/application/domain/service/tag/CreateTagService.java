package com.john.library.mdm.application.domain.service.tag;

import com.john.library.mdm.application.domain.model.Tag;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.TagApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagUseCase;
import com.john.library.mdm.application.port.out.persistence.CommandTagPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class CreateTagService implements CreateTagUseCase {

  private final CommandTagPort commandTagPort;

  private final TagApplicationMapper tagApplicationMapper;

  @Override
  public Result<Integer> execute(CreateTagCommand command) {
    Tag tag = tagApplicationMapper.mapToDomain(command);
    return Result.of(commandTagPort.create(tag));
  }
}
