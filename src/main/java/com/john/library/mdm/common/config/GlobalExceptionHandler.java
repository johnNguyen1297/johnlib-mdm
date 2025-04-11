package com.john.library.mdm.common.config;

import com.john.library.mdm.adapter.in.rest.dto.response.base.ApiErrorDetail;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.adapter.in.rest.dto.response.base.FieldViolation;
import com.john.library.mdm.application.exception.DuplicateResourceException;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.common.constant.MessageKeys.Common;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
@ResponseBody
@Slf4j
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

  private final MessageProvider messageProvider;

  @ExceptionHandler(value = ResourceNotFoundException.class)
  ResponseEntity<BaseResponse> handleResourceNotFoundException(
      final ResourceNotFoundException rnfe) {
    log.error(rnfe.getMessage());

    val response = BaseResponse.fail(
        messageProvider.get(rnfe.getI18nKey().getKey(), rnfe.getField()),
        ApiErrorDetail.from(rnfe));
    return new ResponseEntity<>(response, rnfe.getI18nKey().getStatus());
  }

  @ExceptionHandler(value = DuplicateResourceException.class)
  ResponseEntity<BaseResponse> handleDuplicateResourceException(
      final DuplicateResourceException dre) {
    log.error(dre.getMessage());

    val response = BaseResponse.fail(
        messageProvider.get(dre.getI18nKey().getKey(), dre.getField()),
        ApiErrorDetail.from(dre));
    return new ResponseEntity<>(response, dre.getI18nKey().getStatus());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  ResponseEntity<BaseResponse> handleMethodArgumentNotValidException(
      final MethodArgumentNotValidException manve) {
    log.error(manve.getMessage(), manve);

    val fieldViolations = manve.getFieldErrors()
                               .stream()
                               .map(error ->
                                        FieldViolation.builder()
                                                      .field(error.getField())
                                                      .rejectedValue(error.getRejectedValue())
                                                      .message(
                                                          Optional.ofNullable(messageProvider.get(
                                                                      error.getDefaultMessage()))
                                                                  .orElse(
                                                                      error.getDefaultMessage()))
                                                      .build())
                               .toList();

    val response = BaseResponse.fail(ApiErrorDetail.from(manve, fieldViolations));
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  ResponseEntity<BaseResponse<Void>> handleConstraintViolationException(
      final ConstraintViolationException cve) {
    log.error(cve.getMessage(), cve);
    return new ResponseEntity<>(
        BaseResponse.fail(ApiErrorDetail.from(cve, getFieldViolations(cve))),
        HttpStatus.BAD_REQUEST);
  }

  // Global for Exception
  @ExceptionHandler(value = Exception.class)
  ResponseEntity<BaseResponse> handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);
    val response = BaseResponse.fail(messageProvider.get(Common.INTERNAL_SERVER_ERROR.getKey()));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /// //////////////////////// PRIVATE FUNCTIONS ///// ////////////////////////
  @NotNull
  private List<FieldViolation> getFieldViolations(final ConstraintViolationException exception) {
    return exception.getConstraintViolations()
                    .stream()
                    .map(this::buildFieldViolation)
                    .toList();
  }

  private FieldViolation buildFieldViolation(ConstraintViolation<?> violation) {
    val propertyPath = (PathImpl) violation.getPropertyPath();
    val parentNode = propertyPath.getLeafNode().getParent();

    Integer index = parentNode.isIterable()
                    ? Integer.valueOf(parentNode.asString()
                                                .substring(parentNode.asString().indexOf("[") + 1,
                                                           parentNode.asString().indexOf("]")))
                    : null;

    return FieldViolation.builder()
                         .field(propertyPath.getLeafNode().getName())
                         .rejectedValue(violation.getInvalidValue())
                         .jsonPath(parentNode.asString())
                         .index(index)
                         .message(Optional.ofNullable(messageProvider.get(violation.getMessage()))
                                          .orElse(violation.getMessage()))
                         .build();
  }

}
