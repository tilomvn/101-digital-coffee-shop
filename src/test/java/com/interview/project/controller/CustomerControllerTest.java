package com.interview.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.project.dto.CustomerLoginResponse;
import com.interview.project.dto.PlaceOrderResponse;
import com.interview.project.security.JWTProvider;
import com.interview.project.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private JWTProvider jwtProvider;

    @Test
    void login() throws Exception {
        var jwtToken = jwtProvider.generate("1");
        var loginResponse = CustomerLoginResponse.builder()
                .accessToken(jwtToken)
                .build();
        when(customerService.login(any())).thenReturn(loginResponse);

        var mvcResult = mockMvc.perform(post("/customer/login")
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .content("{\"userName\":\"test\", \"password\":\"testpass\"}"))
                                            .andExpect(status().isOk()).andReturn();

        var response = mapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerLoginResponse.class);

        assertEquals(loginResponse.getAccessToken(), response.getAccessToken());
    }

    @Test
    void placeOrder() throws Exception {
        var placeOrderResponse = PlaceOrderResponse.builder().orderId(1L).queueNumber(1).build();
        when(customerService.placeOrder(any())).thenReturn(placeOrderResponse);

        var mvcResult = mockMvc.perform(post("/customer/order")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, jwtProvider.generate("1"))
                .content("{\"shopId\":1, \"orderItemId\":1, \"queueNumber\":1}"))
                .andExpect(status().isOk()).andReturn();

        var response = mapper.readValue(mvcResult.getResponse().getContentAsString(), PlaceOrderResponse.class);

        assertEquals(placeOrderResponse.getIsSuccess(), response.getIsSuccess());
        assertEquals(placeOrderResponse.getOrderId(), response.getOrderId());
        assertEquals(placeOrderResponse.getQueueNumber(), response.getQueueNumber());
    }
}
