package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.adapter.in.rest.dto.response.PublisherListingItem;
import com.john.library.mdm.application.dto.response.Result;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface GetPublisherDetailUseCase extends
                                           BaseUseCase<Integer, Result<PublisherListingItem>> {

}
