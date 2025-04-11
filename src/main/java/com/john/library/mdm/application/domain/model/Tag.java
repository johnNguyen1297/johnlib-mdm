package com.john.library.mdm.application.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(toBuilder = true)
@FieldNameConstants
public class Tag implements DomainEntity {

  private Integer id;
  private String name;
  private String description;

}
