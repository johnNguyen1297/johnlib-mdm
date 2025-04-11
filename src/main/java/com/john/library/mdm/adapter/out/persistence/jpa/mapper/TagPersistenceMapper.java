package com.john.library.mdm.adapter.out.persistence.jpa.mapper;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.TagJpa;
import com.john.library.mdm.application.domain.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TagPersistenceMapper extends BasePersistenceMapper<Tag, TagJpa> {

}
