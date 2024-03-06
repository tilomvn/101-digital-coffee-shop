package com.interview.project.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum ErrorInfo {

    VALIDATION_ERROR("VALIDATION_ERROR", "VALIDATION_ERROR"),
    PERMISSION_DENIED("PERMISSION_DENIED", "PERMISSION_DENIED"),
    INVALID_USER_NAME_PASSWORD("INVALID_USER_NAME_PASSWORD", "INVALID_USER_NAME_PASSWORD"),
    INVALID_MENU_ITEM("INVALID_MENU_ITEM", "INVALID_MENU_ITEM"),
    INVALID_SHOP("INVALID_SHOP", "INVALID_SHOP"),
    INVALID_CUSTOMER("INVALID_CUSTOMER", "INVALID_CUSTOMER"),
    SHOP_QUEUE_IS_FULL("SHOP_QUEUE_IS_FULL", "SHOP_QUEUE_IS_FULL")
    ;

    final String code;
    final String message;

    ErrorInfo(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
