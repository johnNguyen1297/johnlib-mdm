package com.john.library.mdm.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class MessageKeys {

  @Getter
  @RequiredArgsConstructor
  public enum Common implements I18nKey {
    // Info
    NO_MESSAGE("common.info.no_message", HttpStatus.OK),
    CREATED("common.info.created", HttpStatus.CREATED),
    UPDATED("common.info.updated", HttpStatus.OK),
    DELETED("common.info.deleted", HttpStatus.OK),

    // Error
    INTERNAL_SERVER_ERROR("common.error.internal_server_error", HttpStatus.INTERNAL_SERVER_ERROR),
    RESOURCE_NOT_FOUND("common.error.resource_not_found", HttpStatus.BAD_REQUEST),
    DUPLICATE_RESOURCE("common.error.duplicate_resource", HttpStatus.BAD_REQUEST),
    BAD_REQUEST("common.error.bad_request", HttpStatus.BAD_REQUEST),
    ;

    private final String key;
    private final HttpStatus status;
  }
}
