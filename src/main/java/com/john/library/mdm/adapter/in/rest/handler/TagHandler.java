package com.john.library.mdm.adapter.in.rest.handler;

import com.john.library.mdm.adapter.in.rest.dto.request.SaveTagRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.mapper.TagRestMapper;
import com.john.library.mdm.application.dto.request.AppPageRequest;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.DeleteTagUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagQuery;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.GetTagDetailUseCase;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("rawtypes")
public class TagHandler {

  private final CreateTagUseCase createTagUseCase;
  private final UpdateTagUseCase updateTagUseCase;
  private final FilterTagUseCase filterTagUseCase;
  private final DeleteTagUseCase deleteTagUseCase;
  private final GetTagDetailUseCase getTagDetailUseCase;

  private final TagRestMapper restInboundMapper;

  public BaseResponse createTag(SaveTagRequest request) {
    CreateTagCommand command = restInboundMapper.mapToCommand(request);
    return BaseResponse.of(createTagUseCase.execute(command));
  }

  public BaseResponse deleteTag(Integer id) {
    return BaseResponse.of(deleteTagUseCase.execute(id));
  }

  public BaseResponse filterTags(String q, Pageable pageable) {
    val query = FilterTagQuery.builder()
                              .q(q)
                              .pageRequest(AppPageRequest.of(pageable))
                              .build();

    return BaseResponse.of(filterTagUseCase.execute(query));
  }

  public BaseResponse getTagDetails(Integer id) {
    return BaseResponse.of(getTagDetailUseCase.execute(id));
  }

  public BaseResponse updateTag(Integer id, SaveTagRequest request) {
    UpdateTagCommand command = restInboundMapper.mapToCommand(id, request);
    return BaseResponse.of(updateTagUseCase.execute(command));
  }
}
