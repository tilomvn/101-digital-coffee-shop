package com.interview.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.project.dto.CustomerLoginResponse;
import com.interview.project.services.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


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

    @Test
    void login() throws Exception {
        var loginResponse = CustomerLoginResponse.builder().build();
        when(customerService.login(any())).thenReturn(loginResponse);

        MvcResult mvcResult = mockMvc.perform(post("/customer/login")
                                            .contentType(MediaType.APPLICATION_JSON).content(""))
                .andExpect(status().isOk()).andReturn();

        CustomerLoginResponse response = mapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerLoginResponse.class);
        assertEquals(loginResponse.getIsSuccess(), response.getIsSuccess());
    }
}
