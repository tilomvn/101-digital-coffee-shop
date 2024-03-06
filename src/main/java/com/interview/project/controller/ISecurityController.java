package com.interview.project.controller;

import com.interview.project.dto.*;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public interface ISecurityController {

    @Hidden
    @RequestMapping(path = "/unauthorized", method = { RequestMethod.GET })
    ResponseEntity<UnauthorizedResponse> unauthorized();
}
