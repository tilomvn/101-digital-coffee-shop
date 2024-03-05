package com.interview.project.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ErrorResponse {

    @Builder.Default
    Boolean isSuccess = Boolean.FALSE;

    List<Object> errors;
    String message;
}
