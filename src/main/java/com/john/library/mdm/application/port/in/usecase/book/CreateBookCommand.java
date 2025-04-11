package com.john.library.mdm.application.port.in.usecase.book;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateBookCommand {

  private String isbn;

  private String title;

  private Integer publisherId;

  private Integer publicationYear;

  private String format;

  private Integer printLength;

  private String description;

  private String language;

  private List<Integer> authors;

  private List<Integer> tags;
}
