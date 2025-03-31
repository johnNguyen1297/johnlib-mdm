package com.john.library.mdm.application.port.out.publisher;

import com.john.library.mdm.application.domain.model.Publisher;

public interface UpdatePublisherPort {

  Integer update(Publisher publisher);
}
