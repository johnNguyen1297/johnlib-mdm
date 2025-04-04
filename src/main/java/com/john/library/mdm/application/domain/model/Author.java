package com.john.library.mdm.application.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Author implements DomainEntity {

  private Integer id;
  private String name;
  private String bio;
  private String nationality;
}
