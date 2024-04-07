package com.example.codeE.request.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CheckOTPRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CheckOTPRequest#CheckOTPRequest()}
     *   <li>{@link CheckOTPRequest#setOtp(String)}
     *   <li>{@link CheckOTPRequest#getOtp()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CheckOTPRequest actualCheckOTPRequest = new CheckOTPRequest();
        actualCheckOTPRequest.setOtp("Otp");

        // Assert that nothing has changed
        assertEquals("Otp", actualCheckOTPRequest.getOtp());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CheckOTPRequest#CheckOTPRequest(String)}
     *   <li>{@link CheckOTPRequest#setOtp(String)}
     *   <li>{@link CheckOTPRequest#getOtp()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CheckOTPRequest actualCheckOTPRequest = new CheckOTPRequest("Otp");
        actualCheckOTPRequest.setOtp("Otp");

        // Assert that nothing has changed
        assertEquals("Otp", actualCheckOTPRequest.getOtp());
    }
}
