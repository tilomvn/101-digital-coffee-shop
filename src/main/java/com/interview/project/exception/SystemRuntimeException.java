package com.interview.project.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Getter
public class SystemRuntimeException extends RuntimeException {

    Optional<HttpStatus> httpStatus;
    List<Object> errors;
    String message;

    public SystemRuntimeException(HttpStatus httpStatus, ErrorInfo error) {
        this.errors = Arrays.asList(error);
        this.httpStatus = Optional.of(httpStatus);
    }

    public SystemRuntimeException(HttpStatus httpStatus, ErrorInfo error, String message) {
        this.errors = Arrays.asList(error);
        this.httpStatus = Optional.of(httpStatus);
        this.message = message;
    }

    public ErrorResponse toErrorResponse() {
        return ErrorResponse.builder().errors(errors).message(message).build();
    }
}

