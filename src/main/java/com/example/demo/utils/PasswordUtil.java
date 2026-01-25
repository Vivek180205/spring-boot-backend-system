package com.example.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtil {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String hashPassword(String password) {
        return encoder.encode(password);
    }

    public boolean match(String raw, String hashed) {
        return encoder.matches(raw, hashed);
    }
}

