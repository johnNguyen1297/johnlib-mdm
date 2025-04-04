package com.john.library.mdm.application.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Book {

  private int id;
  private String isbn;
  private String title;
  private int publisherId;
  private int publicationYear;
  private String format;
  private int printLength;
  private String description;
  private String language;

}
