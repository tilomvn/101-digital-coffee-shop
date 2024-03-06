package com.interview.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Object> errors = ex.getBindingResult().getFieldErrors().stream()
                .map((error) -> ValidationError.builder().message(error.getDefaultMessage()).build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().errors(errors).build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<Object> errors = ex.getConstraintViolations().stream()
                .map(error -> ValidationError.builder().message(error.getMessage()).build())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponse.builder().errors(errors).build());
    }

    @ExceptionHandler(SystemRuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleSystemException(SystemRuntimeException ex, WebRequest request) {
        return ResponseEntity.status(ex.httpStatus.orElse(HttpStatus.INTERNAL_SERVER_ERROR)).body(ex.toErrorResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        log.error("", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder().message(ex.getMessage()).build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseEntity.status(status).body(ErrorResponse.builder().message(ex.getMessage()).build());
    }
}
