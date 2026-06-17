package com.fdbozzo.pricing.infrastructure.exceptions;

import com.fdbozzo.pricing.domain.exceptions.BaseException;

public abstract class InfrastructureException extends BaseException {

  protected InfrastructureException(String code, String message) {
    super(code, message);
  }
}