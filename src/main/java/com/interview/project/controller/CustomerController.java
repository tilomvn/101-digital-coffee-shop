package com.interview.project.controller;

import com.interview.project.dto.CustomerLoginRequest;
import com.interview.project.dto.CustomerLoginResponse;
import com.interview.project.dto.PlaceOrderRequest;
import com.interview.project.dto.PlaceOrderResponse;
import com.interview.project.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController implements ICustomerController {

    private final CustomerService customerService;

    @Override
    public ResponseEntity<CustomerLoginResponse> login(CustomerLoginRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }

    @Override
    public ResponseEntity<PlaceOrderResponse> placeOrder(PlaceOrderRequest request) {
        return ResponseEntity.ok(customerService.placeOrder(request));
    }
}