package com.john.library.mdm.common.config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageProvider {

  private final MessageSource messageSource;

  public String get(String key) {
    return getMessage(key, new Object[0]);
  }

  public String get(String key, Object... objects) {
    if (CollectionUtils.isEmpty(Arrays.asList(objects))) {
      return get(key);
    }
    return getMessage(key, objects);
  }

  private String getMessage(String key, Object[] objects) {
    try {
      return messageSource.getMessage(key, objects, LocaleContextHolder.getLocale());
    } catch (NoSuchMessageException ex) {
      log.error("No message with code: {} for locale: {}", key, LocaleContextHolder.getLocale());
      return null;
    }
  }
}
