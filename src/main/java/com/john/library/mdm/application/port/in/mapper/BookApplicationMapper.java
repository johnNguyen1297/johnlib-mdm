package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.application.domain.model.Book;
import com.john.library.mdm.application.port.in.usecase.book.CreateBookCommand;
import com.john.library.mdm.application.port.in.usecase.book.UpdateBookCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface BookApplicationMapper extends
                                       BaseApplicationMapper<Book, CreateBookCommand, UpdateBookCommand> {

  @Override
  @Mapping(target = "id", ignore = true)
  Book mapToDomain(CreateBookCommand createCommand);
}
