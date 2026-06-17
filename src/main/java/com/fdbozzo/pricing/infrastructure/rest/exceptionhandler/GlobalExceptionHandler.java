package com.fdbozzo.pricing.infrastructure.rest.exceptionhandler;

import com.fdbozzo.pricing.domain.exceptions.PriceNotFoundException;
import com.fdbozzo.pricing.infrastructure.rest.model.response.ErrorResponseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<ErrorResponseImpl> handlePriceNotFound(PriceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(new ErrorResponseImpl("PRICE_NOT_FOUND", ex.getMessage()));
  }

}