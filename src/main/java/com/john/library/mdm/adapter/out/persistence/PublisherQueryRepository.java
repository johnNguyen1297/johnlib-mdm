package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.PublisherJpa;
import com.john.library.mdm.adapter.out.persistence.jpa.mapper.PublisherPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.PublisherJpaRepository;
import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;
import com.john.library.mdm.application.port.out.persistence.QueryPublisherPort;
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
public class PublisherQueryRepository implements QueryPublisherPort {

  private final PublisherJpaRepository publisherJpaRepository;
  private final PublisherPersistenceMapper publisherPersistenceMapper;

  @Override
  public AppPageResponse<Publisher> listByFilter(FilterPublisherQuery query) {
    Page<PublisherJpa> page = publisherJpaRepository.listByFilter(query.getQ(),
                                                                  query.getEstablishedYear(),
                                                                  PageHelper.mapToPageable(
                                                                      query.getPageRequest()));
    return PageHelper.map(page, publisherPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Publisher> findById(final Integer publisherId) {
    return publisherJpaRepository.findById(publisherId)
                                 .map(publisherPersistenceMapper::mapToDomain);
  }

  @Override
  public Optional<Publisher> findByName(final String name) {
    return publisherJpaRepository.findByName(name).map(publisherPersistenceMapper::mapToDomain);
  }

  @Override
  public boolean existsByName(final String name) {
    return findByName(name).isPresent();
  }

  @Override
  public boolean existsById(final Integer publisherId) {
    return findById(publisherId).isPresent();
  }

  @Override
  public boolean existsByNameAndIdNot(final String name, final Integer publisherId) {
    val foundPublisher = findByName(name);
    return foundPublisher.filter(publisher -> !Objects.equals(publisher
                                                                  .getId(), publisherId))
                         .isPresent();
  }
}
