package com.interview.project.dto;

import lombok.Data;

@Data
public class PlaceOrderRequest {

    Long shopId;
    Long orderItemId;

    Integer queueNumber;
}
