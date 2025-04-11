package com.john.library.mdm.adapter.in.rest.mapper;

import com.john.library.mdm.adapter.in.rest.dto.request.SavePublisherRequest;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherCommand;
import java.time.Year;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR, imports = Year.class)
public interface PublisherRestMapper {

  @Mapping(target = "establishedYear", expression = "java(Year.parse(request.getEstablishedYear()))")
  CreatePublisherCommand mapToCommand(SavePublisherRequest request);

  @Mapping(target = "establishedYear", expression = "java(Year.parse(request.getEstablishedYear()))")
  UpdatePublisherCommand mapToCommand(Integer id, SavePublisherRequest request);
}
