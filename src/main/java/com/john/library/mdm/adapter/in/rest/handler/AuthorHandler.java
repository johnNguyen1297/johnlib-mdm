package com.john.library.mdm.adapter.in.rest.handler;

import com.john.library.mdm.adapter.in.rest.dto.request.SaveAuthorRequest;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.mapper.AuthorRestMapper;
import com.john.library.mdm.application.dto.request.AppPageRequest;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorUseCase;
import com.john.library.mdm.application.port.in.usecase.author.DeleteAuthorUseCase;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorUseCase;
import com.john.library.mdm.application.port.in.usecase.author.GetAuthorDetailUseCase;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@SuppressWarnings("rawtypes")
public class AuthorHandler {

  private final CreateAuthorUseCase createAuthorUseCase;
  private final UpdateAuthorUseCase updateAuthorUseCase;
  private final FilterAuthorUseCase filterAuthorUseCase;
  private final DeleteAuthorUseCase deleteAuthorUseCase;
  private final GetAuthorDetailUseCase getAuthorDetailUseCase;

  private final AuthorRestMapper restInboundMapper;

  public BaseResponse createAuthor(SaveAuthorRequest request) {
    CreateAuthorCommand command = restInboundMapper.mapToCommand(request);
    return BaseResponse.of(createAuthorUseCase.execute(command));
  }

  public BaseResponse deleteAuthor(Integer id) {
    return BaseResponse.of(deleteAuthorUseCase.execute(id));
  }

  public BaseResponse filterAuthors(String q, Pageable pageable) {
    val query = FilterAuthorQuery.builder()
                                 .q(q)
                                 .pageRequest(AppPageRequest.of(pageable))
                                 .build();

    return BaseResponse.of(filterAuthorUseCase.execute(query));
  }

  public BaseResponse getAuthorDetails(Integer id) {
    return BaseResponse.of(getAuthorDetailUseCase.execute(id));
  }

  public BaseResponse updateAuthor(Integer id, SaveAuthorRequest request) {
    UpdateAuthorCommand command = restInboundMapper.mapToCommand(id, request);
    return BaseResponse.of(updateAuthorUseCase.execute(command));
  }
}
