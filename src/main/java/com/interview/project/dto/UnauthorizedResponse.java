package com.interview.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UnauthorizedResponse {

    @Builder.Default
    Boolean isSuccess = Boolean.FALSE;

    String message;
}
