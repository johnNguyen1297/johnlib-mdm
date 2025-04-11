package com.john.library.mdm.application.exception;

import com.john.library.mdm.common.constant.I18nKey;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException {

  protected final transient I18nKey i18nKey;
  protected final transient String field;
  protected final transient Object fieldValue;
  protected final transient Object[] extraData;

  public DomainException(I18nKey i18nKey) {
    this(i18nKey, null);
  }

  public DomainException(I18nKey i18nKey, String message) {
    this(i18nKey, message, (Object) null);
  }

  public DomainException(I18nKey i18nKey, String message, String field, Object fieldValue) {
    this(i18nKey, message, field, fieldValue, (Object) null);
  }

  public DomainException(I18nKey i18nKey, String message, Object... extraData) {
    this(i18nKey, message, null, extraData);
  }

  public DomainException(I18nKey i18nKey, String message, String field, Object fieldValue,
                         Object... extraData) {
    super(message);
    this.i18nKey    = i18nKey;
    this.field      = field;
    this.fieldValue = fieldValue;
    this.extraData  = extraData;
  }

}
