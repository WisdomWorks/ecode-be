package com.example.codeE.security;

import java.util.Random;

import com.example.codeE.constant.Constant;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPassword {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public static String generateRandomPassword() {
        StringBuilder password = new StringBuilder();
        Random r = new Random();
        password.append(Constant.LOWER_CHAR.charAt(r.nextInt(Constant.LOWER_CHAR.length())));
        password.append(Constant.NUMBER_CHAR.charAt(r.nextInt(Constant.NUMBER_CHAR.length())));
        int remainingLength = 8 - password.length(); // Ensure at least 6 characters
        String combination = Constant.UPPER_CHAR + Constant.LOWER_CHAR + Constant.NUMBER_CHAR + Constant.SPECIAL_CHAR;
        for (int i = 0; i < remainingLength; i++) {
            password.append(combination.charAt(r.nextInt(combination.length())));
        }

        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < password.length(); i++) {
            int randomIndex = r.nextInt(password.length());
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }

        return new String(passwordArray);
    }
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
    public static String passwordEncoder(String password){
        return passwordEncoder.encode(password);
    }

    public static boolean checkPasswordBcrypt(String plainTextPassword, String hashedPassword) {
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
    }

}
