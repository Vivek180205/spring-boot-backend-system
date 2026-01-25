package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
@Component
public class AESUtil {

    private static final String ALGO = "AES/ECB/PKCS5Padding";

    @Value("${aes.secret.key}")
    private String secretKey;

    public String encrypt(String data) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec key =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace(); // TEMP
            throw new RuntimeException("Encryption Failed");
        }
    }

    public String decrypt(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance(ALGO);
            SecretKeySpec key =
                    new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decoded = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(decoded), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Decryption Failed");
        }
    }
}
