package com.john.library.mdm.application.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
@Builder(toBuilder = true)
@FieldNameConstants
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

  private List<Integer> authors;
  private List<Integer> tags;

}
