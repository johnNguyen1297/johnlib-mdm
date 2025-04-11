package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Tag;

public interface CommandTagPort {

  Integer create(Tag tag);

  Integer update(Tag tag);

  Integer delete(Integer id);

}
