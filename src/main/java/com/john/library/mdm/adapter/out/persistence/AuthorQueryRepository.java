package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.AuthorJpa;
import com.john.library.mdm.adapter.out.persistence.jpa.mapper.AuthorPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.AuthorJpaRepository;
import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;
import com.john.library.mdm.application.port.out.persistence.QueryAuthorPort;
import com.john.library.mdm.common.util.PageHelper;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthorQueryRepository implements QueryAuthorPort {

  private final AuthorJpaRepository authorJpaRepository;

  private final AuthorPersistenceMapper authorPersistenceMapper;

  @Override
  public AppPageResponse<Author> listByFilter(final FilterAuthorQuery query) {
    Page<AuthorJpa> result = authorJpaRepository.listByFilter(query.getQ(),
                                                              PageHelper.mapToPageable(
                                                                  query.getPageRequest()));

    return PageHelper.map(result, authorPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Author> findById(final Integer authorId) {
    return authorJpaRepository.findById(authorId).map(authorPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Author> findByName(final String name) {
    return authorJpaRepository.findByName(name).map(authorPersistenceMapper::mapToDomain);
  }

  @Override
  public boolean existsByName(final String name) {
    return authorJpaRepository.findByName(name).isPresent();
  }

  @Override
  public boolean existsById(final Integer authorId) {
    return authorJpaRepository.existsById(authorId);
  }

  @Override
  public boolean existsByNameAndIdNot(final String name, final Integer authorId) {
    val foundAuthor = authorJpaRepository.findByName(name);
    return foundAuthor.filter(authorJpa -> !Objects.equals(authorJpa.getId(), authorId))
                      .isPresent();
  }
}
