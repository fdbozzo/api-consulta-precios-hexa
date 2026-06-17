package com.fdbozzo.pricing.domain.exceptions;

import com.fdbozzo.pricing.domain.model.ErrorCode;

public class DomainException extends BaseException {

  public DomainException(ErrorCode code, String message) {
    super(code, message);
  }

}