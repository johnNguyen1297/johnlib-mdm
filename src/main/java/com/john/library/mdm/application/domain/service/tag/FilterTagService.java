package com.john.library.mdm.application.domain.service.tag;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.TagApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagQuery;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.TagListingItem;
import com.john.library.mdm.application.port.out.persistence.QueryTagPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FilterTagService implements FilterTagUseCase {

  private final QueryTagPort queryTagPort;

  private final TagApplicationMapper tagApplicationMapper;

  @Override
  public Result<AppPageResponse<TagListingItem>> execute(FilterTagQuery query) {
    return Result.ofPage(queryTagPort.listByFilter(query)
                                     .map(tagApplicationMapper::mapToResponse));
  }
}
