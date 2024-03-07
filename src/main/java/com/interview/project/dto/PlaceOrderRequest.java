package com.interview.project.dto;

import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class PlaceOrderRequest {

    //The id of item when choosing item in screen
    @Size(min = 1, message = "Order Item must not be empty")
    List<Data_1> orderItem;

    @Data
    public static class Data_1 {
        Long orderItemId;
        Integer quantity;
    }
}
