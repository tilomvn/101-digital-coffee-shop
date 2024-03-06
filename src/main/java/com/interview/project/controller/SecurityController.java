package com.interview.project.controller;

import com.interview.project.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/security")
public class SecurityController implements ISecurityController {

    @Override
    public ResponseEntity<UnauthorizedResponse> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(UnauthorizedResponse.builder()
                .message("Unauthorized")
                .build());
    }
}
