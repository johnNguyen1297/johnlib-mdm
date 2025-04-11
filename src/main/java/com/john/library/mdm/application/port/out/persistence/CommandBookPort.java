package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Book;

public interface CommandBookPort {

  Integer create(Book book);
}
