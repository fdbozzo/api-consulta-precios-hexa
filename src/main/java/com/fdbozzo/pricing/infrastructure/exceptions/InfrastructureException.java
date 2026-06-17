package com.fdbozzo.pricing.infrastructure.exceptions;

import com.fdbozzo.pricing.domain.exceptions.BaseException;
import com.fdbozzo.pricing.domain.model.ErrorCode;

public class InfrastructureException extends BaseException {

  public InfrastructureException(ErrorCode code, String message) {
    super(code, message);
  }

}