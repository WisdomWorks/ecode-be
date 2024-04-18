package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class forgetPasswordRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link forgetPasswordRequest#forgetPasswordRequest()}
     *   <li>{@link forgetPasswordRequest#setPassword(String)}
     *   <li>{@link forgetPasswordRequest#setUserId(String)}
     *   <li>{@link forgetPasswordRequest#getPassword()}
     *   <li>{@link forgetPasswordRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        forgetPasswordRequest actualForgetPasswordRequest = new forgetPasswordRequest();
        actualForgetPasswordRequest.setPassword("iloveyou");
        actualForgetPasswordRequest.setUserId("42");
        String actualPassword = actualForgetPasswordRequest.getPassword();

        // Assert that nothing has changed
        assertEquals("42", actualForgetPasswordRequest.getUserId());
        assertEquals("iloveyou", actualPassword);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link forgetPasswordRequest#forgetPasswordRequest(String, String)}
     *   <li>{@link forgetPasswordRequest#setPassword(String)}
     *   <li>{@link forgetPasswordRequest#setUserId(String)}
     *   <li>{@link forgetPasswordRequest#getPassword()}
     *   <li>{@link forgetPasswordRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        forgetPasswordRequest actualForgetPasswordRequest = new forgetPasswordRequest("42", "iloveyou");
        actualForgetPasswordRequest.setPassword("iloveyou");
        actualForgetPasswordRequest.setUserId("42");
        String actualPassword = actualForgetPasswordRequest.getPassword();

        // Assert that nothing has changed
        assertEquals("42", actualForgetPasswordRequest.getUserId());
        assertEquals("iloveyou", actualPassword);
    }
}
