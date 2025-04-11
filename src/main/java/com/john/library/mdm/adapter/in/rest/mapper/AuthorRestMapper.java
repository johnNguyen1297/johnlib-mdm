package com.john.library.mdm.adapter.in.rest.mapper;

import com.john.library.mdm.adapter.in.rest.dto.request.SaveAuthorRequest;
import com.john.library.mdm.application.port.in.usecase.author.CreateAuthorCommand;
import com.john.library.mdm.application.port.in.usecase.author.UpdateAuthorCommand;
import java.time.Year;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = Year.class)
public interface AuthorRestMapper {

  CreateAuthorCommand mapToCommand(SaveAuthorRequest request);

  UpdateAuthorCommand mapToCommand(Integer id, SaveAuthorRequest request);
}
