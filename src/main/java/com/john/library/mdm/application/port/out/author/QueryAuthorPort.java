package com.john.library.mdm.application.port.out.author;

import com.john.library.mdm.application.domain.model.Author;
import java.util.Optional;

public interface QueryAuthorPort {

  Optional<Author> findById(Integer authorId);

  Optional<Author> findByName(String name);

  boolean existsByName(String name);

  boolean existsById(Integer authorId);

  boolean existsByNameAndIdNot(String name, Integer authorId);
}
