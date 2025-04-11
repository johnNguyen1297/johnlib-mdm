package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.TagJpa;
import com.john.library.mdm.adapter.out.persistence.jpa.mapper.TagPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.TagJpaRepository;
import com.john.library.mdm.application.domain.model.Tag;
import com.john.library.mdm.application.port.out.persistence.CommandTagPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TagCommandRepository implements CommandTagPort {

  private final TagJpaRepository tagJpaRepository;
  private final TagPersistenceMapper tagPersistenceMapper;

  @Override
  public Integer create(final Tag tag) {
    TagJpa tagJpa = tagPersistenceMapper.mapToJpaEntity(tag);
    tagJpa = tagJpaRepository.save(tagJpa);
    return tagJpa.getId();
  }

  @Override
  public Integer update(final Tag tag) {
    TagJpa tagJpa = tagPersistenceMapper.mapToJpaEntity(tag);
    tagJpaRepository.save(tagJpa);
    return tagJpa.getId();
  }

  @Override
  public Integer delete(final Integer id) {
    if (tagJpaRepository.existsById(id)) {
      tagJpaRepository.deleteById(id);
      return 1;
    }
    return 0;
  }
}
