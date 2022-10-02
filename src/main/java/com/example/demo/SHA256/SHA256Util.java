package com.example.demo.SHA256;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class SHA256Util {

    private static String strSalt;

    private static byte [] salt;

    @Value("${SHA.salt}")
    public void setStrSalt(String strSalt) {
        SHA256Util.strSalt = strSalt;
    }


    public static String getSecurePassword(String passwordToHash){

        salt = strSalt.getBytes();

        System.out.println("strSalt="+strSalt);
        System.out.println("salt="+salt);

        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // Add password bytes to digest
            md.update(salt);
            // Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }




}


