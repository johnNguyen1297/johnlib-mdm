package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.port.in.usecase.publisher.CreatePublisherCommand;
import com.john.library.mdm.application.port.in.usecase.publisher.PublisherListingItem;
import com.john.library.mdm.application.port.in.usecase.publisher.UpdatePublisherCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface PublisherApplicationMapper extends
                                            BaseApplicationMapper<Publisher, CreatePublisherCommand, UpdatePublisherCommand> {

  @Override
  @Mapping(target = "id", ignore = true)
  Publisher mapToDomain(CreatePublisherCommand createCommand);

  PublisherListingItem mapToResponse(Publisher publisher);
}
