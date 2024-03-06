package com.interview.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.project.dto.UnauthorizedResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void anauthorized() throws Exception {
        var mvcResult = mockMvc.perform(get("/security/unauthorized"))
                                            .andExpect(status().isUnauthorized()).andReturn();

        var response = mapper.readValue(mvcResult.getResponse().getContentAsString(), UnauthorizedResponse.class);

        assertEquals(response.getMessage(), "Unauthorized");
    }
}
