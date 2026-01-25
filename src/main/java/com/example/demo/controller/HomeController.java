package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

//SPRING SECURITY

    @GetMapping("/home")
    public String home() {
        return "LOGIN SUCCESS 🎉";
    }
}
