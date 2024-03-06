package com.interview.project.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaceOrderResponse {

    @Builder.Default
    Boolean isSuccess = Boolean.TRUE;

    Long orderId;

    Integer queueNumber;
}
