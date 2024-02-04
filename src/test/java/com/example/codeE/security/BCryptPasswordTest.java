package com.example.codeE.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BCryptPasswordTest {

    @Test
    void testCorrectPassword() {
        String password = "password";
        String encodedPassword = BCryptPassword.hashPassword(password);
        assert (BCryptPassword.checkPassword(password, encodedPassword));
    }

    @Test
    void testIncorrectPassword() {
        String password = "password";
        String encodedPassword = BCryptPassword.hashPassword(password);
        assert (!BCryptPassword.checkPassword("wrongPassword", encodedPassword));
    }

    @Test
    void testEmptyPassword() {
        String password = "password";
        String encodedPassword = BCryptPassword.hashPassword(password);
        assert (!BCryptPassword.checkPassword("", encodedPassword));
    }

}
