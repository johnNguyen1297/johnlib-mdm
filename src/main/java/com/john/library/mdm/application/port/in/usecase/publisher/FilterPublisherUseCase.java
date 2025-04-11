package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface FilterPublisherUseCase extends
                                        BaseUseCase<FilterPublisherQuery, AppPageResponse<PublisherListingItem>> {

}
