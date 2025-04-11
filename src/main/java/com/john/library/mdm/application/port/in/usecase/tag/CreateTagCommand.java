package com.john.library.mdm.application.port.in.usecase.tag;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTagCommand {

  private String name;
  private String description;
}
