package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class UpdateUserRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateUserRequest#UpdateUserRequest()}
     *   <li>{@link UpdateUserRequest#setCreatedDate(LocalDateTime)}
     *   <li>{@link UpdateUserRequest#setUpdatedEmail(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedName(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedPassword(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedRole(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedUsername(String)}
     *   <li>{@link UpdateUserRequest#setUserId(String)}
     *   <li>{@link UpdateUserRequest#getCreatedDate()}
     *   <li>{@link UpdateUserRequest#getUpdatedEmail()}
     *   <li>{@link UpdateUserRequest#getUpdatedName()}
     *   <li>{@link UpdateUserRequest#getUpdatedPassword()}
     *   <li>{@link UpdateUserRequest#getUpdatedRole()}
     *   <li>{@link UpdateUserRequest#getUpdatedUsername()}
     *   <li>{@link UpdateUserRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateUserRequest actualUpdateUserRequest = new UpdateUserRequest();
        LocalDateTime createdDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualUpdateUserRequest.setCreatedDate(createdDate);
        actualUpdateUserRequest.setUpdatedEmail("2020-03-01");
        actualUpdateUserRequest.setUpdatedName("2020-03-01");
        actualUpdateUserRequest.setUpdatedPassword("2020-03-01");
        actualUpdateUserRequest.setUpdatedRole("2020-03-01");
        actualUpdateUserRequest.setUpdatedUsername("janedoe");
        actualUpdateUserRequest.setUserId("42");
        LocalDateTime actualCreatedDate = actualUpdateUserRequest.getCreatedDate();
        String actualUpdatedEmail = actualUpdateUserRequest.getUpdatedEmail();
        String actualUpdatedName = actualUpdateUserRequest.getUpdatedName();
        String actualUpdatedPassword = actualUpdateUserRequest.getUpdatedPassword();
        String actualUpdatedRole = actualUpdateUserRequest.getUpdatedRole();
        String actualUpdatedUsername = actualUpdateUserRequest.getUpdatedUsername();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualUpdatedEmail);
        assertEquals("2020-03-01", actualUpdatedName);
        assertEquals("2020-03-01", actualUpdatedPassword);
        assertEquals("2020-03-01", actualUpdatedRole);
        assertEquals("42", actualUpdateUserRequest.getUserId());
        assertEquals("janedoe", actualUpdatedUsername);
        assertSame(createdDate, actualCreatedDate);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateUserRequest#UpdateUserRequest(String, String, String, String, String, String, LocalDateTime)}
     *   <li>{@link UpdateUserRequest#setCreatedDate(LocalDateTime)}
     *   <li>{@link UpdateUserRequest#setUpdatedEmail(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedName(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedPassword(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedRole(String)}
     *   <li>{@link UpdateUserRequest#setUpdatedUsername(String)}
     *   <li>{@link UpdateUserRequest#setUserId(String)}
     *   <li>{@link UpdateUserRequest#getCreatedDate()}
     *   <li>{@link UpdateUserRequest#getUpdatedEmail()}
     *   <li>{@link UpdateUserRequest#getUpdatedName()}
     *   <li>{@link UpdateUserRequest#getUpdatedPassword()}
     *   <li>{@link UpdateUserRequest#getUpdatedRole()}
     *   <li>{@link UpdateUserRequest#getUpdatedUsername()}
     *   <li>{@link UpdateUserRequest#getUserId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateUserRequest actualUpdateUserRequest = new UpdateUserRequest("42", "2020-03-01", "2020-03-01", "janedoe",
                "2020-03-01", "2020-03-01", LocalDate.of(1970, 1, 1).atStartOfDay());
        LocalDateTime createdDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualUpdateUserRequest.setCreatedDate(createdDate);
        actualUpdateUserRequest.setUpdatedEmail("2020-03-01");
        actualUpdateUserRequest.setUpdatedName("2020-03-01");
        actualUpdateUserRequest.setUpdatedPassword("2020-03-01");
        actualUpdateUserRequest.setUpdatedRole("2020-03-01");
        actualUpdateUserRequest.setUpdatedUsername("janedoe");
        actualUpdateUserRequest.setUserId("42");
        LocalDateTime actualCreatedDate = actualUpdateUserRequest.getCreatedDate();
        String actualUpdatedEmail = actualUpdateUserRequest.getUpdatedEmail();
        String actualUpdatedName = actualUpdateUserRequest.getUpdatedName();
        String actualUpdatedPassword = actualUpdateUserRequest.getUpdatedPassword();
        String actualUpdatedRole = actualUpdateUserRequest.getUpdatedRole();
        String actualUpdatedUsername = actualUpdateUserRequest.getUpdatedUsername();

        // Assert that nothing has changed
        assertEquals("2020-03-01", actualUpdatedEmail);
        assertEquals("2020-03-01", actualUpdatedName);
        assertEquals("2020-03-01", actualUpdatedPassword);
        assertEquals("2020-03-01", actualUpdatedRole);
        assertEquals("42", actualUpdateUserRequest.getUserId());
        assertEquals("janedoe", actualUpdatedUsername);
        assertSame(createdDate, actualCreatedDate);
    }
}
