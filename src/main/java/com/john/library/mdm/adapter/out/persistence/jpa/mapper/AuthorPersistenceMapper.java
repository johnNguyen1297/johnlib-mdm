package com.john.library.mdm.adapter.out.persistence.jpa.mapper;

import com.john.library.mdm.adapter.out.persistence.jpa.entity.AuthorJpa;
import com.john.library.mdm.application.domain.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuthorPersistenceMapper extends BasePersistenceMapper<Author, AuthorJpa> {

}
