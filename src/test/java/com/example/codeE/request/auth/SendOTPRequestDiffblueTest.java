package com.example.codeE.request.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SendOTPRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendOTPRequest#SendOTPRequest()}
     *   <li>{@link SendOTPRequest#setUserName(String)}
     *   <li>{@link SendOTPRequest#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        SendOTPRequest actualSendOTPRequest = new SendOTPRequest();
        actualSendOTPRequest.setUserName("janedoe");

        // Assert that nothing has changed
        assertEquals("janedoe", actualSendOTPRequest.getUserName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SendOTPRequest#SendOTPRequest(String)}
     *   <li>{@link SendOTPRequest#setUserName(String)}
     *   <li>{@link SendOTPRequest#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        SendOTPRequest actualSendOTPRequest = new SendOTPRequest("janedoe");
        actualSendOTPRequest.setUserName("janedoe");

        // Assert that nothing has changed
        assertEquals("janedoe", actualSendOTPRequest.getUserName());
    }
}
