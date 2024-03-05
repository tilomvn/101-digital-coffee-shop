package com.interview.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CustomerLoginResponse {

    @Builder.Default
    Boolean isSuccess = Boolean.TRUE;


}
