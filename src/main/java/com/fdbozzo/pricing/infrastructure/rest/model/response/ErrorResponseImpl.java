package com.fdbozzo.pricing.infrastructure.rest.model.response;

import java.time.Instant;

public record ErrorResponseImpl(
    String code,
    String message,
    Instant timestamp
) {

  public ErrorResponseImpl(String code, String message) {
    this(code, message, Instant.now());
  }
}
