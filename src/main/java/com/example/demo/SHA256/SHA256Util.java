package com.example.demo.SHA256;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class SHA256Util {

        @Value("${SHA.salt}")
        private  String strSalt;


        public  String getSecurePassword(String passwordToHash){

            byte salt[] = strSalt.getBytes();

            if (salt==null){
                return null;
            }

            System.out.println(salt);
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


