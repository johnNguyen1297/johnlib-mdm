package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Author;

public interface CommandAuthorPort {

  Integer create(Author author);

  Integer delete(Integer authorId);

  Integer update(Author author);
}
