package com.john.library.mdm.application.port.in.usecase.publisher;

import java.time.Year;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PublisherListingItem {

  private Integer id;
  private String name;
  private String location;
  private String phone;
  private String email;
  private Year establishedYear;
  private String description;
}