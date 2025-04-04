package com.john.library.mdm.application.port.out.author;

import com.john.library.mdm.application.domain.model.Author;

public interface UpdateAuthorPort {

  Integer update(Author author);
}
