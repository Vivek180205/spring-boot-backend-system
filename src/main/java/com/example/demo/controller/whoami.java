package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class whoami {
    @GetMapping("/whoami")
    public Object whoami(Authentication auth) {
        return auth;
    }
}
