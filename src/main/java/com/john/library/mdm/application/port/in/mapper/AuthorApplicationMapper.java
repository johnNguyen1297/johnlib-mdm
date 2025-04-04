package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.adapter.in.rest.dto.response.AuthorListingItem;
import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface AuthorApplicationMapper extends
                                         BaseApplicationMapper<Author, CreateAuthorCommand, UpdateAuthorCommand> {

  AuthorListingItem mapToResponse(Author author);

  @Override
  @Mapping(target = "id", ignore = true)
  Author mapToDomain(CreateAuthorCommand createCommand);
}
