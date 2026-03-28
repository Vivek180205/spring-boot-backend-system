package com.example.demo.controller;

import com.example.demo.dao.EmployeeBankDetailsDao;
import com.example.demo.model.EmployeeBankDetails;
import com.example.demo.model.LoginRequest;
import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private EmployeeBankDetailsDao bankDao;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {

        // 1️⃣ Authenticate (password check)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getUsername(),
                        req.getPassword()
                )
        );

        // 2️⃣ DB se DIRECT role uthao (SOURCE OF TRUTH)
        EmployeeBankDetails user = bankDao.findByUsername(req.getUsername());

        System.out.println("ROLE FROM DB = " + user.getRole());

        // 3️⃣ JWT with role
        return jwtUtil.generateToken(
                user.getUsername(),
                user.getRole()
        );
    }
}
