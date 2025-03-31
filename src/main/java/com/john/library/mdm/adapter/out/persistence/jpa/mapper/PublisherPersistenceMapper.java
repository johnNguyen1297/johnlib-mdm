package com.john.library.mdm.adapter.out.persistence.jpa.mapper;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.PublisherJpa;
import com.john.library.mdm.application.domain.model.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PublisherPersistenceMapper extends BasePersistenceMapper<Publisher, PublisherJpa> {

}
