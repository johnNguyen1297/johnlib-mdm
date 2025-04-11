package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;
import java.util.Optional;

public interface QueryPublisherPort {

  AppPageResponse<Publisher> listByFilter(FilterPublisherQuery query);

  Optional<Publisher> findById(Integer id);

  Optional<Publisher> findByName(String name);

  boolean existsByName(String name);

  boolean existsById(Integer id);

  boolean existsByNameAndIdNot(String name, Integer id);
}
