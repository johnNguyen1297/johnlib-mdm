package com.john.library.mdm.adapter.in.rest.dto.response.base;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Metadata {

  private Integer page;
  private Integer totalPage;
  private Boolean hasNextPage;
  private Boolean hasPreviousPage;
  private Integer size;
  private Long totalElements;
  private List<FieldViolation> errors;
  private String message;
  private String field;
}
