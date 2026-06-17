package com.fdbozzo.pricing.infrastructure.rest.mappers;

import com.fdbozzo.pricing.domain.model.ErrorCode;
import org.springframework.http.HttpStatus;

public class ErrorMapper {

  public static HttpStatus toStatus(ErrorCode code) {
    return switch (code) {
      case PRICE_NEGATIVE, PRICE_NULL, INVALID_CURRENCY -> HttpStatus.BAD_REQUEST;
      case PRICE_NOT_FOUND, PRODUCT_NOT_FOUND -> HttpStatus.NOT_FOUND;
    };
  }
}