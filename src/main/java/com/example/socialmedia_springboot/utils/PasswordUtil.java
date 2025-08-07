package com.example.socialmedia_springboot.utils;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String HashPassword(String password) {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.verifyer().verify(plainPassword.toCharArray(), hashedPassword).verified;
    }

}