package com.example.codeE.mapper.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserFromExcelDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UserFromExcel#UserFromExcel()}
     *   <li>{@link UserFromExcel#setEmail(String)}
     *   <li>{@link UserFromExcel#setName(String)}
     *   <li>{@link UserFromExcel#setRole(String)}
     *   <li>{@link UserFromExcel#setUserId(String)}
     *   <li>{@link UserFromExcel#setUsername(String)}
     *   <li>{@link UserFromExcel#getEmail()}
     *   <li>{@link UserFromExcel#getName()}
     *   <li>{@link UserFromExcel#getRole()}
     *   <li>{@link UserFromExcel#getUserId()}
     *   <li>{@link UserFromExcel#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UserFromExcel actualUserFromExcel = new UserFromExcel();
        actualUserFromExcel.setEmail("jane.doe@example.org");
        actualUserFromExcel.setName("Name");
        actualUserFromExcel.setRole("Role");
        actualUserFromExcel.setUserId("42");
        actualUserFromExcel.setUsername("janedoe");
        String actualEmail = actualUserFromExcel.getEmail();
        String actualName = actualUserFromExcel.getName();
        String actualRole = actualUserFromExcel.getRole();
        String actualUserId = actualUserFromExcel.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("Role", actualRole);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualUserFromExcel.getUsername());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UserFromExcel#UserFromExcel(String, String, String, String, String)}
     *   <li>{@link UserFromExcel#setEmail(String)}
     *   <li>{@link UserFromExcel#setName(String)}
     *   <li>{@link UserFromExcel#setRole(String)}
     *   <li>{@link UserFromExcel#setUserId(String)}
     *   <li>{@link UserFromExcel#setUsername(String)}
     *   <li>{@link UserFromExcel#getEmail()}
     *   <li>{@link UserFromExcel#getName()}
     *   <li>{@link UserFromExcel#getRole()}
     *   <li>{@link UserFromExcel#getUserId()}
     *   <li>{@link UserFromExcel#getUsername()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UserFromExcel actualUserFromExcel = new UserFromExcel("42", "Name", "jane.doe@example.org", "janedoe", "Role");
        actualUserFromExcel.setEmail("jane.doe@example.org");
        actualUserFromExcel.setName("Name");
        actualUserFromExcel.setRole("Role");
        actualUserFromExcel.setUserId("42");
        actualUserFromExcel.setUsername("janedoe");
        String actualEmail = actualUserFromExcel.getEmail();
        String actualName = actualUserFromExcel.getName();
        String actualRole = actualUserFromExcel.getRole();
        String actualUserId = actualUserFromExcel.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("Role", actualRole);
        assertEquals("jane.doe@example.org", actualEmail);
        assertEquals("janedoe", actualUserFromExcel.getUsername());
    }
}
