package com.fdbozzo.pricing.domain.exceptions;

public abstract class DomainException extends BaseException {

  protected DomainException(String code, String message) {
    super(code, message);
  }
}