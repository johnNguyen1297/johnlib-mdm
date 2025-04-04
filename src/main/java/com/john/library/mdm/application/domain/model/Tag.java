package com.john.library.mdm.application.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Tag implements DomainEntity {

  private Integer id;
  private String name;
  private String description;

}
