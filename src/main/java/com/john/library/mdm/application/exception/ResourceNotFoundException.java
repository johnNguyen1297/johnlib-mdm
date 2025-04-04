package com.john.library.mdm.application.exception;

import com.john.library.mdm.common.constant.MessageKeys.Common;

public class ResourceNotFoundException extends DomainException {

  public ResourceNotFoundException(final String resourceName) {
    super(Common.RESOURCE_NOT_FOUND,
          "%s not found: ".formatted(resourceName), resourceName);
  }

  public ResourceNotFoundException(final String resourceName, Object resourceId) {
    super(Common.RESOURCE_NOT_FOUND,
          "%s with id %s not found: ".formatted(resourceName, resourceId), resourceName,
          resourceName, resourceId);
  }

}
