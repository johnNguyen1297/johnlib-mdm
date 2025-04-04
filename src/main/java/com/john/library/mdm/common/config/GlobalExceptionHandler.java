package com.john.library.mdm.common.config;

import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import com.john.library.mdm.application.exception.ResourceNotFoundException;
import com.john.library.mdm.common.constant.MessageKeys.Common;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
@SuppressWarnings("rawtypes")
public class GlobalExceptionHandler {

  private final MessageProvider messageProvider;

  @ResponseBody
  @ExceptionHandler(value = ResourceNotFoundException.class)
  ResponseEntity<BaseResponse> handleResourceNotFoundException(
      final ResourceNotFoundException rnfe) {
    log.error(rnfe.getMessage());
    val response = BaseResponse.fail(
        messageProvider.get(rnfe.getI18nKey().getKey(), rnfe.getField()));
    return new ResponseEntity<>(response, rnfe.getI18nKey().getStatus());
  }

  // Global for Exception
  @ResponseBody
  @ExceptionHandler(value = Exception.class)
  ResponseEntity<BaseResponse> handleException(final Exception exception) {
    log.error(exception.getMessage(), exception);
    val response = BaseResponse.fail(messageProvider.get(Common.INTERNAL_SERVER_ERROR.getKey()));
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
