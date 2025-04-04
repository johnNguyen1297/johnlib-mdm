package com.john.library.mdm.adapter.in.rest.dto.response.base;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FieldViolation {

  private String field;
  private Integer index;
  private String description;
  private Object extraData;

  public static FieldViolation of(String field, String description) {
    return new FieldViolation(field, null, description, null);
  }

  public static FieldViolation of(String field, String description, Object extraData) {
    return new FieldViolation(field, null, description, extraData);
  }


  public static FieldViolation of(String field, Integer index, String description,
                                  Object extraData) {
    return new FieldViolation(field, index, description, extraData);
  }
}
