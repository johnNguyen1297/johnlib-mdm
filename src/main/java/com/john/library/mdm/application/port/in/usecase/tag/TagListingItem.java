package com.john.library.mdm.application.port.in.usecase.tag;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListingItem {

  private Integer id;
  private String name;
  private String description;

}
