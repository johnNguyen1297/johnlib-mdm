package com.john.library.mdm.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageKeys {

  @Getter
  @RequiredArgsConstructor
  public enum Common implements I18nKey {
    INTERNAL_SERVER_ERROR("common.internal_server_error"),
    EMPTY_RESULT("common.empty_result"),
    ;

    private final String key;
  }
}
