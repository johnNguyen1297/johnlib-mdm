package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.PublisherJpa;
import com.john.library.mdm.adapter.out.persistence.jpa.mapper.PublisherPersistenceMapper;
import com.john.library.mdm.adapter.out.persistence.jpa.repository.PublisherJpaRepository;
import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.port.out.persistence.CommandPublisherPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class PublisherCommandRepository implements CommandPublisherPort {

  private final PublisherJpaRepository publisherJpaRepository;
  private final PublisherPersistenceMapper publisherPersistenceMapper;

  @Override
  public Integer create(Publisher publisher) {
    PublisherJpa publisherJpa = publisherPersistenceMapper.mapToJpaEntity(publisher);
    publisherJpa = publisherJpaRepository.save(publisherJpa);
    return publisherJpa.getId();
  }

  @Override
  public Integer update(Publisher publisher) {
    PublisherJpa publisherJpa = publisherPersistenceMapper.mapToJpaEntity(publisher);
    publisherJpaRepository.save(publisherJpa);
    return publisherJpa.getId();
  }

  @Override
  public Integer delete(final Integer publisherId) {
    if (publisherJpaRepository.existsById(publisherId)) {
      publisherJpaRepository.deleteById(publisherId);
      return 1;
    }

    return 0;
  }
}
