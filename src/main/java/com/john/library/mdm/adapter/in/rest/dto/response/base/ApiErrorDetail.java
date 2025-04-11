package com.john.library.mdm.adapter.in.rest.dto.response.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.john.library.mdm.application.exception.DomainException;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import java.net.URI;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(Include.NON_NULL)
@EqualsAndHashCode(callSuper = true)
public class ApiErrorDetail extends ProblemDetail {

  private final Instant timestamp = Instant.now();

  private URI instance;
  private String detail;

  @With
  private transient List<FieldViolation> violations;

  private static ApiErrorDetail from(ProblemDetail problemDetail) {
    return ApiErrorDetail.builder()
                         .instance(problemDetail.getInstance())
                         .detail(problemDetail.getDetail())
                         .violations(List.of())
                         .build();
  }

  public static ApiErrorDetail from(DomainException domainException) {
    return from(ProblemDetail.forStatusAndDetail(domainException.getI18nKey().getStatus(),
                                                 domainException.getMessage()));
  }

  public static ApiErrorDetail from(ResourceNotFoundException rnfe) {
    FieldViolation violation = FieldViolation.builder()
                                             .field(rnfe.getResourceName())
                                             .rejectedValue(rnfe.getResourceId())
                                             .message(rnfe.getMessage())
                                             .build();

    return from((DomainException) rnfe).withViolations(List.of(violation));
  }

  public static ApiErrorDetail from(DuplicateResourceException dre) {
    FieldViolation violation = FieldViolation.builder()
                                             .field(dre.getFieldError())
                                             .message(dre.getMessage())
                                             .build();

    return from((DomainException) dre).withViolations(List.of(violation));
  }

  public static ApiErrorDetail from(MethodArgumentNotValidException manve,
                                    List<FieldViolation> violations) {
    return from(manve.getBody()).withViolations(violations);
  }

  public static ApiErrorDetail from(ConstraintViolationException cve,
                                    List<FieldViolation> violations) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatus.BAD_REQUEST, cve.getMessage());
    return from(problemDetail).withViolations(violations);
  }
}
