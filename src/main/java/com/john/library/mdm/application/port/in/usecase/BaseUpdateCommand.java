package com.john.library.mdm.application.port.in.usecase;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public abstract class BaseUpdateCommand {

  protected Integer id;
}
