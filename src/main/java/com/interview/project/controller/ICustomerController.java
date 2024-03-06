package com.interview.project.controller;

import com.interview.project.dto.*;
import com.interview.project.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

public interface ICustomerController {

    @Operation(summary = "Customer Login", tags = { "Customer" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CustomerLoginResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/login", method = { RequestMethod.POST })
    ResponseEntity<CustomerLoginResponse> login(@RequestBody @Valid CustomerLoginRequest request);


    @Operation(summary = "Customer Place Order", tags = { "Customer" }, parameters = {
            @Parameter(in = ParameterIn.HEADER, name = HttpHeaders.AUTHORIZATION, description = "Jwt Token", required = true)
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = PlaceOrderResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)) })
    })
    @RequestMapping(path = "/order", method = { RequestMethod.POST })
    ResponseEntity<PlaceOrderResponse> placeOrder(@RequestBody @Valid PlaceOrderRequest request);
}
