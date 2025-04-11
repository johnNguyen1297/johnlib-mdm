package com.john.library.mdm.adapter.in.rest.mapper;

import com.john.library.mdm.adapter.in.rest.dto.request.SaveTagRequest;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagCommand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface TagRestMapper {

  CreateTagCommand mapToCommand(SaveTagRequest request);

  UpdateTagCommand mapToCommand(Integer id, SaveTagRequest request);
}
