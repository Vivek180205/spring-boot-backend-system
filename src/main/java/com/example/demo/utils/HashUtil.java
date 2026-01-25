package com.example.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class HashUtil {

    private HashUtil() {} // prevent object creation

    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                String s = Integer.toHexString(0xff & b);
                if (s.length() == 1) hex.append('0');
                hex.append(s);
            }
            return hex.toString();
        } catch (Exception e) {
            throw new RuntimeException("Hashing error");
        }
    }
}
