package com.john.library.mdm.application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.john.library.mdm.common.constant.I18nKey;
import com.john.library.mdm.common.constant.MessageKeys;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

  private final T data;
  private final I18nKey messageKey;
  private final Object[] objects;

  public static <T> Result<T> of(final T data) {
    return new Result<>(data, null, null);
  }

  public static <T> Result<T> of(final T data, final I18nKey messageKey, final Object... objects) {
    return new Result<>(data, messageKey, objects);
  }

  public static <T> Result<T> ofPage(@NonNull final T data) {
    if (data instanceof PageResponse<?> pageResponse && pageResponse.getContent().isEmpty()) {
      return of(data, MessageKeys.Common.EMPTY_RESULT);
    }
    return of(data);
  }

  public static <T> Result<T> empty() {
    return new Result<>(null, null, null);
  }

  public <E> Result<E> map(final Function<T, E> mapper) {
    if (isEmpty()) {
      return empty();
    }
    val newContent = mapper.apply(data);
    return of(newContent, messageKey, objects);
  }

  public boolean isEmpty() {
    return data == null;
  }
}
