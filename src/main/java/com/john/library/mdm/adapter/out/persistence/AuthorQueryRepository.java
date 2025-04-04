package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;
import com.john.library.mdm.application.port.out.author.FilterAuthorPort;
import com.john.library.mdm.application.port.out.author.QueryAuthorPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthorQueryRepository implements QueryAuthorPort, FilterAuthorPort {

  @Override
  public Optional<Author> findById(final Integer authorId) {
    return Optional.empty();
  }

  @Override
  public Optional<Author> findByName(final String name) {
    return Optional.empty();
  }

  @Override
  public boolean existsByName(final String name) {
    return false;
  }

  @Override
  public boolean existsById(final Integer authorId) {
    return false;
  }

  @Override
  public boolean existsByNameAndIdNot(final String name, final Integer authorId) {
    return false;
  }

  @Override
  public PageResponse<Author> listByFilter(final FilterAuthorQuery query) {
    return null;
  }
}
