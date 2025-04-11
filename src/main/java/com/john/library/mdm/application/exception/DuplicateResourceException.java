package com.john.library.mdm.application.exception;

import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.Getter;

@Getter
public class DuplicateResourceException extends DomainException {

  private final transient String resourceName;
  private final transient String fieldError;

  public DuplicateResourceException(String resourceName, String fieldError) {
    super(Common.DUPLICATE_RESOURCE,
          "Duplicate resource: %s with %s.".formatted(resourceName, fieldError));
    this.resourceName = resourceName;
    this.fieldError   = fieldError;
  }

}
