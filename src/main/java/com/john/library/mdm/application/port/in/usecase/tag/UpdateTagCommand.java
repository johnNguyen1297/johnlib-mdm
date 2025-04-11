package com.john.library.mdm.application.port.in.usecase.tag;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdateTagCommand extends BaseUpdateCommand {

  private String name;
  private String description;
}
