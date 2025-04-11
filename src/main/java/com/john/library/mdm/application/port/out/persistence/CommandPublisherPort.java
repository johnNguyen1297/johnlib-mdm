package com.john.library.mdm.application.port.out.persistence;

import com.john.library.mdm.application.domain.model.Publisher;

public interface CommandPublisherPort {

  Integer create(Publisher publisher);

  Integer delete(Integer publisherId);

  Integer update(Publisher publisher);
}
