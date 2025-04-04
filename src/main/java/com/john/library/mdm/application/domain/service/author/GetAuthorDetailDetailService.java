package com.john.library.mdm.application.domain.service.author;

import com.john.library.mdm.adapter.in.rest.dto.response.AuthorListingItem;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.mapper.AuthorApplicationMapper;
import com.john.library.mdm.application.port.in.usecase.author.GetAuthorDetailUseCase;
import com.john.library.mdm.application.port.out.author.QueryAuthorPort;
import com.john.library.mdm.common.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class GetAuthorDetailDetailService implements GetAuthorDetailUseCase {

  private final QueryAuthorPort queryAuthorPort;

  private final AuthorApplicationMapper authorApplicationMapper;

  @Override
  public Result<AuthorListingItem> execute(final Integer input) {
    val foundAuthor = queryAuthorPort.findById(input);
    return foundAuthor.map(authorApplicationMapper::mapToResponse)
                      .map(Result::of)
                      .orElseThrow(() -> new IllegalArgumentException("Author not found"));
  }
}
