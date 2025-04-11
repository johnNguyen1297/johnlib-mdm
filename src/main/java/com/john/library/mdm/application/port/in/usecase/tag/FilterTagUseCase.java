package com.john.library.mdm.application.port.in.usecase.tag;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface FilterTagUseCase extends
                                  BaseUseCase<FilterTagQuery, AppPageResponse<TagListingItem>> {

}
