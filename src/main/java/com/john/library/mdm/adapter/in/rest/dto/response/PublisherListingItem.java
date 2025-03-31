package com.john.library.mdm.adapter.in.rest.dto.response;

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