package com.john.library.mdm.application.port.in.usecase.author;

import com.john.library.mdm.adapter.in.rest.dto.response.AuthorListingItem;
import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.port.in.usecase.BaseUseCase;

public interface FilterAuthorUseCase extends
                                     BaseUseCase<FilterAuthorQuery, PageResponse<AuthorListingItem>> {

}
