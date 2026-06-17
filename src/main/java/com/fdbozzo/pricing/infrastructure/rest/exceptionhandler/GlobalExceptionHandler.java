package com.fdbozzo.pricing.infrastructure.rest.exceptionhandler;

import com.fdbozzo.pricing.application.exceptions.ApplicationException;
import com.fdbozzo.pricing.domain.exceptions.DomainException;
import com.fdbozzo.pricing.infrastructure.exceptions.InfrastructureException;
import com.fdbozzo.pricing.infrastructure.rest.mappers.ErrorMapper;
import com.fdbozzo.pricing.infrastructure.rest.model.response.ErrorResponseImpl;
import jakarta.servlet.ServletException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DomainException.class)
  public ResponseEntity<ErrorResponseImpl> handleDomainException(DomainException ex) {
    return ResponseEntity.status(ErrorMapper.toStatus(ex.getCode()))
        .body(new ErrorResponseImpl(ex.getCode().name(), ex.getMessage()));
  }

  @ExceptionHandler(ApplicationException.class)
  public ResponseEntity<ErrorResponseImpl> handleApplicationException(ApplicationException ex) {
    return ResponseEntity.status(ErrorMapper.toStatus(ex.getCode()))
        .body(new ErrorResponseImpl(ex.getCode().name(), ex.getMessage()));
  }

  @ExceptionHandler(InfrastructureException.class)
  public ResponseEntity<ErrorResponseImpl> handleInfrastructureException(InfrastructureException ex) {
    return ResponseEntity.status(ErrorMapper.toStatus(ex.getCode()))
        .body(new ErrorResponseImpl(ex.getCode().name(), ex.getMessage()));
  }

  /**
   * Errores de validación de request (http 400)
   */
  @ExceptionHandler({
      MethodArgumentNotValidException.class,
      ConstraintViolationException.class
  })
  public ResponseEntity<ErrorResponseImpl> handleRequestValidationException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Errores de tipo / binding (http 400)
   */
  @ExceptionHandler({
      MethodArgumentTypeMismatchException.class,
      HttpMessageNotReadableException.class,
      MissingServletRequestParameterException.class
  })
  public ResponseEntity<ErrorResponseImpl> handleBindingException(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Errores de contrato HTTP (cliente)
   */
  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException1(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
        .body(new ErrorResponseImpl(HttpStatus.METHOD_NOT_ALLOWED.name(), ex.getMessage()));
  }
  @ExceptionHandler({HttpMediaTypeNotSupportedException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException2(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        .body(new ErrorResponseImpl(HttpStatus.UNSUPPORTED_MEDIA_TYPE.name(), ex.getMessage()));
  }
  @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException3(RuntimeException ex) {
    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
        .body(new ErrorResponseImpl(HttpStatus.NOT_ACCEPTABLE.name(), ex.getMessage()));
  }
  @ExceptionHandler({NoResourceFoundException.class})
  public ResponseEntity<ErrorResponseImpl> handleHttpContractException4(ServletException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(new ErrorResponseImpl(HttpStatus.BAD_REQUEST.name(), ex.getMessage()));
  }

  /**
   * Fallback (500)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseImpl> handleException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ErrorResponseImpl(HttpStatus.INTERNAL_SERVER_ERROR.name(), ex.getMessage()));
  }

}