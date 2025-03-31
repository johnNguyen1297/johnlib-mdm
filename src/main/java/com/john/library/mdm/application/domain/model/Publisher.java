package com.john.library.mdm.application.domain.model;

import java.time.Year;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Publisher implements DomainEntity {

  private Integer id;
  private String name;
  private String location;
  private String phone;
  private String email;
  private Year establishedYear;
  private String description;
}