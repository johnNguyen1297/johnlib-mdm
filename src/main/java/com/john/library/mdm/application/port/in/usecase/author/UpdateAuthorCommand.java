package com.john.library.mdm.application.port.in.usecase.author;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdateAuthorCommand extends BaseUpdateCommand {

  private String name;
  private String bio;
  private String nationality;
}
