package com.john.library.mdm.application.port.in.usecase.author;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorListingItem {

  private Integer id;
  private String name;
  private String bio;
  private String nationality;

}
