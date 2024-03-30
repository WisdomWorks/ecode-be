package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LoginRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LoginRequest#setPassword(String)}
     *   <li>{@link LoginRequest#setUserName(String)}
     *   <li>{@link LoginRequest#getPassword()}
     *   <li>{@link LoginRequest#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        LoginRequest loginRequest = new LoginRequest();

        // Act
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String actualPassword = loginRequest.getPassword();

        // Assert that nothing has changed
        assertEquals("iloveyou", actualPassword);
        assertEquals("janedoe", loginRequest.getUserName());
    }
}
