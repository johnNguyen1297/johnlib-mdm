package com.john.library.mdm.common.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.john.library.mdm.adapter.in.rest.dto.response.base.BaseResponse;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.slf4j.MDC;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * The type Rest advice.
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class RestAdvice implements ResponseBodyAdvice<Object> {

  private final MessageProvider messageProvider;
  private final ObjectMapper mapper;

  private static long calculateResponseTime() {
    val startTime = Instant.parse(MDC.get("start.time"));
    MDC.remove("start.time");
    val finishTime = Instant.now();
    return Duration.between(startTime, finishTime).toMillis();
  }

  @Override
  public boolean supports(@NonNull final MethodParameter methodParameter,
                          @NonNull final Class<? extends HttpMessageConverter<?>> converterType) {
    val returnType = Optional.of(methodParameter).map(MethodParameter::getMethod)
                             .map(Method::getGenericReturnType).orElse(null);

    if (returnType == null) {
      return false;
    }

    return returnType instanceof ParameterizedType castedReturnType
        && castedReturnType.getActualTypeArguments().length > 0;
  }

  @Override
  public Object beforeBodyWrite(final Object body, @NonNull final MethodParameter returnType,
                                @NonNull final MediaType selectedContentType,
                                @NonNull final Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                @NonNull final ServerHttpRequest request,
                                @NonNull final ServerHttpResponse response) {
    if (Objects.isNull(body)) {
      return null;
    }

    logResponse(body, request, response);

    if (body instanceof BaseResponse<?> castedBody) {
      val i18nDto = castedBody.getI18n();
      if (i18nDto == null || i18nDto.getKey() == null) {
        return body;
      }
      val key = i18nDto.getKey();
      val message = messageProvider.get(key.getKey(), i18nDto.getObjects());
      castedBody.setMessage(message);
    }
    return body;
  }

  private void logResponse(final Object body, final ServerHttpRequest request,
                           final ServerHttpResponse response) {
    val isServletRequest = request instanceof ServletServerHttpRequest;
    val isServletResponse = response instanceof ServletServerHttpResponse;
    if (!isServletRequest || !isServletResponse) {
      return;
    }
    try {
      val responseTime = calculateResponseTime();
      log.info("Response body [{}ms]: {}", responseTime, mapper.writeValueAsString(body));
    } catch (JsonProcessingException ignore) {
      // ignore
    }
  }
}

