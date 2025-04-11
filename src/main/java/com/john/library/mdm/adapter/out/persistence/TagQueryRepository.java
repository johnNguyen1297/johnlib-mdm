package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.mapper.TagPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.TagJpaRepository;
import com.john.library.mdm.application.domain.model.Tag;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagQuery;
import com.john.library.mdm.application.port.out.persistence.QueryTagPort;
import com.john.library.mdm.common.util.PageHelper;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TagQueryRepository implements QueryTagPort {

  private final TagJpaRepository tagJpaRepository;
  private final TagPersistenceMapper tagPersistenceMapper;

  @Override
  public AppPageResponse<Tag> listByFilter(final FilterTagQuery query) {
    val page = tagJpaRepository.listByFilter(query.getQ(),
                                             PageHelper.mapToPageable(query.getPageRequest()));
    return PageHelper.map(page, tagPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Tag> findById(final Integer id) {
    return tagJpaRepository.findById(id).map(tagPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Tag> findByName(final String name) {
    return tagJpaRepository.findByName(name).map(tagPersistenceMapper::mapToDomain);
  }

  @Override
  public boolean existsByName(final String name) {
    return findByName(name).isPresent();
  }

  @Override
  public boolean existsById(final Integer id) {
    return findById(id).isPresent();
  }

  @Override
  public boolean existsByNameAndIdNot(final String name, final Integer id) {
    val foundTag = findByName(name);
    return foundTag.filter(tag -> !Objects.equals(tag.getId(), id)).isPresent();
  }
}
