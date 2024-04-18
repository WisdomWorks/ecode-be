package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateUserRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateUserRequest#CreateUserRequest()}
     *   <li>{@link CreateUserRequest#setEmail(String)}
     *   <li>{@link CreateUserRequest#setName(String)}
     *   <li>{@link CreateUserRequest#setRole(String)}
     *   <li>{@link CreateUserRequest#setUsername(String)}
     *   <li>{@link CreateUserRequest#getEmail()}
     *   <li>{@link CreateUserRequest#getName()}
     *   <li>{@link CreateUserRequest#getRole()}
     *   <li>{@link CreateUserRequest#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateUserRequest actualCreateUserRequest = new CreateUserRequest();
        actualCreateUserRequest.setEmail("jane.doe@example.org");
        actualCreateUserRequest.setName("Name");
        actualCreateUserRequest.setRole("Role");
        actualCreateUserRequest.setUsername("janedoe");
        String actualEmail = actualCreateUserRequest.getEmail();
        String actualName = actualCreateUserRequest.getName();
        String actualRole = actualCreateUserRequest.getRole();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("Role", actualRole);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualCreateUserRequest.getUsername());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateUserRequest#CreateUserRequest(String, String, String, String)}
     *   <li>{@link CreateUserRequest#setEmail(String)}
     *   <li>{@link CreateUserRequest#setName(String)}
     *   <li>{@link CreateUserRequest#setRole(String)}
     *   <li>{@link CreateUserRequest#setUsername(String)}
     *   <li>{@link CreateUserRequest#getEmail()}
     *   <li>{@link CreateUserRequest#getName()}
     *   <li>{@link CreateUserRequest#getRole()}
     *   <li>{@link CreateUserRequest#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateUserRequest actualCreateUserRequest = new CreateUserRequest("Name", "jane.doe@example.org", "janedoe",
                "Role");
        actualCreateUserRequest.setEmail("jane.doe@example.org");
        actualCreateUserRequest.setName("Name");
        actualCreateUserRequest.setRole("Role");
        actualCreateUserRequest.setUsername("janedoe");
        String actualEmail = actualCreateUserRequest.getEmail();
        String actualName = actualCreateUserRequest.getName();
        String actualRole = actualCreateUserRequest.getRole();

        // Assert that nothing has changed
        assertEquals("Name", actualName);
        assertEquals("Role", actualRole);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualCreateUserRequest.getUsername());
    }
}
