package com.interview.project.service;

import com.interview.project.dto.CustomerLoginRequest;
import com.interview.project.dto.PlaceOrderRequest;
import com.interview.project.exception.SystemRuntimeException;
import com.interview.project.security.JWTProvider;
import com.interview.project.security.ProfileLocal;
import com.interview.project.security.UserIdentity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private JWTProvider jwtProvider;

    @Test
    void loginSuccess() {
        var responseToken = jwtProvider.generate("1");
        var request = new CustomerLoginRequest();
        request.setUserName("test");
        request.setPassword("testpass");

        var loginResult = customerService.login(request);

        assertEquals(loginResult.getAccessToken(), responseToken);
    }

    @Test
    void loginFailed() {
        var request = new CustomerLoginRequest();
        request.setUserName("test");
        request.setPassword("testpass1");

        assertThrows(SystemRuntimeException.class,() -> customerService.login(request));
    }

    @Test
    void placeOrderSuccess() {
        ProfileLocal.set(new UserIdentity("1"));
        var request = new PlaceOrderRequest();
        PlaceOrderRequest.Data_1 data = new PlaceOrderRequest.Data_1();
        data.setOrderItemId(1L);
        data.setQuantity(1);
        PlaceOrderRequest.Data_1 data1 = new PlaceOrderRequest.Data_1();
        data1.setOrderItemId(2L);
        data1.setQuantity(1);
        request.setOrderItem(Arrays.asList(data, data1));

        var result = customerService.placeOrder(request);

        assertEquals(result.getOrderId(), 1L);
        assertEquals(result.getQueueNumber(), 1);
    }

    @Test
    void placeOrderFailed() {
        ProfileLocal.set(new UserIdentity("1"));
        var request = new PlaceOrderRequest();
        PlaceOrderRequest.Data_1 data = new PlaceOrderRequest.Data_1();
        data.setOrderItemId(1L);
        data.setQuantity(1);
        PlaceOrderRequest.Data_1 data1 = new PlaceOrderRequest.Data_1();
        data1.setOrderItemId(3L);
        data1.setQuantity(1);
        request.setOrderItem(Arrays.asList(data, data1));

        assertThrows(SystemRuntimeException.class,() -> customerService.placeOrder(request));
    }
}
