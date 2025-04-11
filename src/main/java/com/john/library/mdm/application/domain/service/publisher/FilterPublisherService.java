package com.john.library.mdm.application.domain.service.publisher;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.PublisherApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherUseCase;
import com.john.library.mdm.application.port.in.usecase.publisher.PublisherListingItem;
import com.john.library.mdm.application.port.out.persistence.QueryPublisherPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class FilterPublisherService implements FilterPublisherUseCase {

  private final QueryPublisherPort queryPublisherPort;

  private final PublisherApplicationMapper applicationInboundMapper;

  @Override
  public Result<AppPageResponse<PublisherListingItem>> execute(FilterPublisherQuery query) {
    return Result.ofPage(queryPublisherPort.listByFilter(query)
                                           .map(applicationInboundMapper::mapToResponse));
  }
}
