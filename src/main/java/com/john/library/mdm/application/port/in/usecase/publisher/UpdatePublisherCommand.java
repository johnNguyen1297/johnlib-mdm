package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;
import java.time.Year;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdatePublisherCommand extends BaseUpdateCommand {

  private String name;
  private String location;
  private String phone;
  private String email;
  private Year establishedYear;
  private String description;
}
