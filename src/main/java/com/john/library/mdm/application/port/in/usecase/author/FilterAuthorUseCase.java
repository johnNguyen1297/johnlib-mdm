package com.john.library.mdm.application.port.in.usecase.author;

import com.john.library.mdm.application.dto.response.AppPageResponse;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface FilterAuthorUseCase extends
                                     BaseUseCase<FilterAuthorQuery, AppPageResponse<AuthorListingItem>> {

}
