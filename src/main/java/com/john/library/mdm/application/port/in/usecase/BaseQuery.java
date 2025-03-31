package com.john.library.mdm.application.port.in.usecase;

import com.john.library.mdm.application.dto.request.AppPageRequest;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class BaseQuery {

  protected String q;
  protected AppPageRequest pageRequest;
}
