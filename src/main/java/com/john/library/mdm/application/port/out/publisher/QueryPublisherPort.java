package com.john.library.mdm.application.port.out.publisher;

import com.john.library.mdm.application.domain.model.Publisher;
import java.util.Optional;

public interface QueryPublisherPort {

  Optional<Publisher> findById(Integer publisherId);

  Optional<Publisher> findByName(String name);

  boolean existsByName(String name);

  boolean existsById(Integer publisherId);

  boolean existsByNameAndIdNot(String name, Integer publisherId);
}
