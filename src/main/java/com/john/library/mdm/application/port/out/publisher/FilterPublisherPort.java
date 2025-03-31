package com.john.library.mdm.application.port.out.publisher;

import com.john.library.mdm.application.domain.model.Publisher;
import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.port.in.usecase.publisher.FilterPublisherQuery;

public interface FilterPublisherPort {

  PageResponse<Publisher> listByFilter(FilterPublisherQuery query);
}
