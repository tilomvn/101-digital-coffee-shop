package com.interview.project.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ValidationError {

    @Builder.Default
    String code = ErrorInfo.VALIDATION_ERROR.getCode();

    String message;
}
