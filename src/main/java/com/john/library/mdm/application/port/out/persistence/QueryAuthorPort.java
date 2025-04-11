package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;
import java.util.Optional;

public interface QueryAuthorPort {

  AppPageResponse<Author> listByFilter(FilterAuthorQuery query);

  Optional<Author> findById(Integer id);

  Optional<Author> findByName(String name);

  boolean existsByName(String name);

  boolean existsById(Integer id);

  boolean existsByNameAndIdNot(String name, Integer id);
}
