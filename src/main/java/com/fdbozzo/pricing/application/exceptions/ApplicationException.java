package com.fdbozzo.pricing.application.exceptions;

import com.fdbozzo.pricing.domain.exceptions.BaseException;

public abstract class ApplicationException extends BaseException {

  protected ApplicationException(String code, String message) {
    super(code, message);
  }
}