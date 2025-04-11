package com.john.library.mdm.common.config;

import io.opentelemetry.api.trace.Span;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A filter that ensures each request is assigned a unique correlation ID for tracing and logging
 * purposes. If a trace ID is available from OpenTelemetry, it is used as the correlation ID;
 * otherwise, a random UUID is generated. The correlation ID is added to the response headers and
 * MDC for logging.
 */
@Slf4j
@Order(1)
@Configuration
@RequiredArgsConstructor
public class RequestCorrelationFilter extends OncePerRequestFilter {

  private static final String REQUEST_ID_HEADER = "x-correlation-id";
  private static final String REQUEST_HEALTH_CHECK = "actuator/health";

  /**
   * Filters incoming requests to assign a correlation ID and log the request details.
   *
   * @param request     the HTTP request
   * @param response    the HTTP response
   * @param filterChain the filter chain
   * @throws ServletException if an error occurs during filtering
   * @throws IOException      if an I/O error occurs during filtering
   */
  @Override
  protected void doFilterInternal(
      @NonNull final HttpServletRequest request,
      @NonNull final HttpServletResponse response,
      @NonNull final FilterChain filterChain) throws ServletException, IOException {
    MDC.put("start.time", String.valueOf(Instant.now()));

    val traceId = Span.current().getSpanContext().getTraceId();
    val requestId =
        StringUtils.isNotBlank(traceId) && !traceId.equals("00000000000000000000000000000000")
        ? traceId
        : UUID.randomUUID().toString(); // Fallback to a random UUID

    try (val ignored = MDC.putCloseable("request.id", requestId)) {
      if (!buildFullUrl(request).contains(REQUEST_HEALTH_CHECK)) {
        log.info("Receive {}: {}", request.getMethod(), buildFullUrl(request));
      }
      response.addHeader(REQUEST_ID_HEADER, requestId);
      filterChain.doFilter(request, response);
    }
  }

  /**
   * Builds the full URL of the incoming request, including the query string if present.
   *
   * @param request the HTTP request
   * @return the full URL as a string
   */
  private String buildFullUrl(final HttpServletRequest request) {
    return request.getRequestURL() +
        Optional.ofNullable(request.getQueryString()).map(q -> "?" + q).orElse(StringUtils.EMPTY);
  }
}
