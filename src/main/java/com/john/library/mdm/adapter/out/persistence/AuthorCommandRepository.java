package com.john.library.mdm.adapter.out.persistence;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.port.out.author.CreateAuthorPort;
import com.john.library.mdm.application.port.out.author.DeleteAuthorPort;
import com.john.library.mdm.application.port.out.author.UpdateAuthorPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class AuthorCommandRepository implements CreateAuthorPort, UpdateAuthorPort,
                                                DeleteAuthorPort {

  @Override
  public Integer create(final Author author) {
    return 0;
  }

  @Override
  public Integer delete(final Integer authorId) {
    return 0;
  }

  @Override
  public Integer update(final Author author) {
    return 0;
  }
}
