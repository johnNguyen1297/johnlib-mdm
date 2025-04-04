package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.adapter.in.rest.dto.response.AuthorListingItem;
import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorUseCase;
import com.john.library.mdm.application.port.out.author.FilterAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FilterAuthorService implements FilterAuthorUseCase {

  private final FilterAuthorPort filterAuthorPort;

  private final AuthorApplicationMapper applicationInboundMapper;

  @Override
  public Result<PageResponse<AuthorListingItem>> execute(FilterAuthorQuery query) {
    return Result.ofPage(filterAuthorPort.listByFilter(query)
                                         .map(applicationInboundMapper::mapToResponse));
  }
}
