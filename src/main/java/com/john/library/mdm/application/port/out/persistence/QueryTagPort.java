package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Tag;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.tag.FilterTagQuery;
import java.util.Optional;

public interface QueryTagPort {

  AppPageResponse<Tag> listByFilter(FilterTagQuery query);

  Optional<Tag> findById(Integer id);

  Optional<Tag> findByName(String name);

  boolean existsByName(String name);

  boolean existsById(Integer id);

  boolean existsByNameAndIdNot(String name, Integer id);
}
