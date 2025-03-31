package com.john.library.mdm.adapter.out.persistence.jpa.mapper;

public interface BasePersistenceMapper<D, E> {

  D mapToDomain(E entity);

  E mapToJpaEntity(D domain);
}
