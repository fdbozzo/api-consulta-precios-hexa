package com.fdbozzo.pricing.infrastructure.rest.exceptionhandler;

import com.fdbozzo.pricing.domain.exceptions.DomainException;
import com.fdbozzo.pricing.infrastructure.rest.mappers.ErrorMapper;
import com.fdbozzo.pricing.infrastructure.rest.model.response.ErrorResponseImpl;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  private static final String CONTRACT_EXCEPTION = "Contract Exception";
  private static final String UNEXPECTED_EXCEPTION = "Unexpected Exception";
  private static final String VALIDATION_REQUEST_EXCEPTION = "Validation Request Exception";
  private static final String TYPE_BINDING_EXCEPTION = "Type/Binding Exception";

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorResponseImpl> handleDomainException(DomainException ex) {
    if (log.isDebugEnabled()) {
      log.warn("Domain Exception: ", ex);
    }
    return ResponseEntity.status(ErrorMapper.toStatus(ex.getCode()))
        .body(new ErrorResponseImpl(ex.getCode().name(), ex.getMessage()));
  }

  /**
   * Errores de validación de request (http 400)
   */
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<ErrorResponseImpl> handleRequestValidationException2(Exception ex) {
    if (log.isDebugEnabled()) {
      log.error(VALIDATION_REQUEST_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Errores de tipo / binding (http 400)
   */
  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<ErrorResponseImpl> handleBindingException(Exception ex) {
    if (log.isDebugEnabled()) {
      log.error(TYPE_BINDING_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }
  @ExceptionHandler({MissingServletRequestParameterException.class})
  public ResponseEntity<ErrorResponseImpl> handleBindingException2(Exception ex) {
    if (log.isDebugEnabled()) {
      log.error(TYPE_BINDING_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Errores de contrato HTTP (cliente)
   */
  @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException3(ServletException ex) {
    if (log.isDebugEnabled()) {
      log.error(CONTRACT_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .body(new ErrorResponseImpl(HttpStatus.NOT_ACCEPTABLE.name(), ex.getMessage()));
  }
  @ExceptionHandler({NoResourceFoundException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException4(ServletException ex) {
    if (log.isDebugEnabled()) {
      log.error(CONTRACT_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Fallback (500)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseImpl> handleException(Exception ex) {
    if (log.isDebugEnabled()) {
      log.error(UNEXPECTED_EXCEPTION + ": ", ex);
    }
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponseImpl(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage()));
  }

}