package com.john.library.mdm.application.port.in.usecase.publisher;

import com.john.library.mdm.application.port.in.usecase.BaseQuery;
import java.time.Year;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class FilterPublisherQuery extends BaseQuery {

  private Year establishedYear;
}
