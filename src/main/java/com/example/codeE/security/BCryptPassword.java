package com.example.codeE.security;

import java.util.Random;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class BCryptPassword {
    public static String generateRandomPassword() {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String number = "0123456789";
        String specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?";
        StringBuilder password = new StringBuilder();
        Random r = new Random();
        password.append(lower.charAt(r.nextInt(lower.length())));
        password.append(number.charAt(r.nextInt(number.length())));
        int remainingLength = 8 - password.length(); // Ensure at least 6 characters
        String combination = upper + lower + number + specialChars;
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
}
