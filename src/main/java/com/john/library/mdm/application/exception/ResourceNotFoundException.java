package com.john.library.mdm.application.exception;

import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends DomainException {

  private final transient String resourceName;
  private final transient Object resourceId;

  public ResourceNotFoundException(final String resourceName) {
    super(Common.RESOURCE_NOT_FOUND,
          "%s not found: ".formatted(resourceName), resourceName);
    this.resourceName = resourceName;
    this.resourceId   = null;
  }

  public ResourceNotFoundException(final String resourceName, Object resourceId) {
    super(Common.RESOURCE_NOT_FOUND,
          "%s with id %s not found: ".formatted(resourceName, resourceId), resourceName,
          resourceName, resourceId);
    this.resourceName = resourceName;
    this.resourceId   = resourceId;
  }

}
