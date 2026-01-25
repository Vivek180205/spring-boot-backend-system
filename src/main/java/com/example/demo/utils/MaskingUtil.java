package com.example.demo.utils;

public class MaskingUtil {

    private MaskingUtil() {}

    public static String maskAccNo(String accNo) {
        return "XXXX-XXXX-XXXX-" + accNo.substring(accNo.length() - 4);
    }
}
