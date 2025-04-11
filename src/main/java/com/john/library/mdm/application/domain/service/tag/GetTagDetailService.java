package com.john.library.mdm.application.domain.service.tag;

import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.application.port.in.mapper.TagApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.tag.GetTagDetailUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.TagListingItem;
import com.john.library.mdm.application.port.out.persistence.QueryTagPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class GetTagDetailService implements GetTagDetailUseCase {

  private final QueryTagPort queryTagPort;

  private final TagApplicationMapper applicationMapper;

  @Override
  public Result<TagListingItem> execute(final Integer id) {
    val foundTag = queryTagPort.findById(id);
    return foundTag.map(applicationMapper::mapToResponse)
                   .map(Result::of)
                   .orElseThrow(() -> new ResourceNotFoundException("Tag", id));
  }
}
