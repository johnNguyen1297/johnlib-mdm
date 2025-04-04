package com.john.library.mdm.application.port.out.author;

import com.john.library.mdm.application.domain.model.Author;
import com.john.library.mdm.application.dto.response.PageResponse;
import com.john.library.mdm.application.port.in.usecase.author.FilterAuthorQuery;

public interface FilterAuthorPort {

  PageResponse<Author> listByFilter(FilterAuthorQuery query);
}
