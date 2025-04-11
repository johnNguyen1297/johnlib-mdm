package com.john.library.mdm.application.port.in.usecase.book;

import com.john.library.mdm.application.port.in.usecase.BaseUpdateCommand;
import java.util.List;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class UpdateBookCommand extends BaseUpdateCommand {

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
