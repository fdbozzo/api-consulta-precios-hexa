package com.fdbozzo.pricing.application.exceptions;

import com.fdbozzo.pricing.domain.exceptions.BaseException;
import com.fdbozzo.pricing.domain.model.ErrorCode;

public class ApplicationException extends BaseException {

  public ApplicationException(ErrorCode code, String message) {
    super(code, message);
  }

}