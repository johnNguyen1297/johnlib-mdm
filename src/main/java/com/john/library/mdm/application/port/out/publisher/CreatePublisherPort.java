package com.john.library.mdm.application.port.out.publisher;

import com.john.library.mdm.application.domain.model.Publisher;

public interface CreatePublisherPort {

  Integer create(Publisher publisher);
}
