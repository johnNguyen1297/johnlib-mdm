package com.john.library.mdm.adapter.in.rest.dto.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FieldViolation {

  private String jsonPath;
  private Integer index;
  private String field;
  private Object rejectedValue;
  private String message;
  private Object extraData;

}
