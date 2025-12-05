package com.example.demo.controller;

import com.example.demo.dao.EmployeeDaoImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TestController {
    private final EmployeeDaoImpl repo;

    public TestController(EmployeeDaoImpl repo) {
        this.repo = repo;
    }

}
