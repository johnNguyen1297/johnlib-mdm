package com.john.library.mdm.application.port.in.mapper;

import com.john.library.mdm.application.domain.model.Tag;
import com.john.library.mdm.application.port.in.usecase.tag.CreateTagCommand;
import com.john.library.mdm.application.port.in.usecase.tag.TagListingItem;
import com.john.library.mdm.application.port.in.usecase.tag.UpdateTagCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface TagApplicationMapper extends
                                      BaseApplicationMapper<Tag, CreateTagCommand, UpdateTagCommand> {

  @Override
  @Mapping(target = "id", ignore = true)
  Tag mapToDomain(CreateTagCommand createCommand);

  TagListingItem mapToResponse(Tag tag);
}
