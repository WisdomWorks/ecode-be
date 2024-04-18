package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ChangePasswordRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ChangePasswordRequest#ChangePasswordRequest()}
     *   <li>{@link ChangePasswordRequest#setNewPassword(String)}
     *   <li>{@link ChangePasswordRequest#setOldPassword(String)}
     *   <li>{@link ChangePasswordRequest#setUserId(String)}
     *   <li>{@link ChangePasswordRequest#getNewPassword()}
     *   <li>{@link ChangePasswordRequest#getOldPassword()}
     *   <li>{@link ChangePasswordRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ChangePasswordRequest actualChangePasswordRequest = new ChangePasswordRequest();
        actualChangePasswordRequest.setNewPassword("iloveyou");
        actualChangePasswordRequest.setOldPassword("iloveyou");
        actualChangePasswordRequest.setUserId("42");
        String actualNewPassword = actualChangePasswordRequest.getNewPassword();
        String actualOldPassword = actualChangePasswordRequest.getOldPassword();

        // Assert that nothing has changed
        assertEquals("42", actualChangePasswordRequest.getUserId());
        assertEquals("iloveyou", actualNewPassword);
        assertEquals("iloveyou", actualOldPassword);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ChangePasswordRequest#ChangePasswordRequest(String, String, String)}
     *   <li>{@link ChangePasswordRequest#setNewPassword(String)}
     *   <li>{@link ChangePasswordRequest#setOldPassword(String)}
     *   <li>{@link ChangePasswordRequest#setUserId(String)}
     *   <li>{@link ChangePasswordRequest#getNewPassword()}
     *   <li>{@link ChangePasswordRequest#getOldPassword()}
     *   <li>{@link ChangePasswordRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ChangePasswordRequest actualChangePasswordRequest = new ChangePasswordRequest("42", "iloveyou", "iloveyou");
        actualChangePasswordRequest.setNewPassword("iloveyou");
        actualChangePasswordRequest.setOldPassword("iloveyou");
        actualChangePasswordRequest.setUserId("42");
        String actualNewPassword = actualChangePasswordRequest.getNewPassword();
        String actualOldPassword = actualChangePasswordRequest.getOldPassword();

        // Assert that nothing has changed
        assertEquals("42", actualChangePasswordRequest.getUserId());
        assertEquals("iloveyou", actualNewPassword);
        assertEquals("iloveyou", actualOldPassword);
    }
}
