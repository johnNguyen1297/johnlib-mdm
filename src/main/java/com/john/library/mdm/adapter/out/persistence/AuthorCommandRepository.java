package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.AuthorJpa;
import com.john.library.mdm.adapter.out.persistence.jpa.mapper.AuthorPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.AuthorJpaRepository;
import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.port.out.persistence.CommandAuthorPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthorCommandRepository implements CommandAuthorPort {

  private final AuthorJpaRepository authorJpaRepository;
  private final AuthorPersistenceMapper authorPersistenceMapper;

  @Override
  public Integer create(final Author author) {
    AuthorJpa authorJpa = authorPersistenceMapper.mapToJpaEntity(author);
    authorJpa = authorJpaRepository.save(authorJpa);
    return authorJpa.getId();
  }

  @Override
  public Integer delete(final Integer authorId) {
    if (authorJpaRepository.existsById(authorId)) {
      authorJpaRepository.deleteById(authorId);
      return 1;
    }

    return 0;
  }

  @Override
  public Integer update(final Author author) {
    AuthorJpa authorJpa = authorPersistenceMapper.mapToJpaEntity(author);
    authorJpa = authorJpaRepository.save(authorJpa);
    return authorJpa.getId();
  }
}
