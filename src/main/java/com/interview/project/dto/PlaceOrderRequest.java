package com.interview.project.dto;

import lombok.Data;

@Data
public class PlaceOrderRequest {

    //The id of shop record of item when choosing item in screen
    Long shopId;

    //The id of item when choosing item in screen
    Long orderItemId;
}
