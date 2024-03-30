package com.example.codeE.entity.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentNotInGroupEntityDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StudentNotInGroupEntity#StudentNotInGroupEntity()}
     *   <li>{@link StudentNotInGroupEntity#setName(String)}
     *   <li>{@link StudentNotInGroupEntity#setUserId(String)}
     *   <li>{@link StudentNotInGroupEntity#setUserName(String)}
     *   <li>{@link StudentNotInGroupEntity#getName()}
     *   <li>{@link StudentNotInGroupEntity#getUserId()}
     *   <li>{@link StudentNotInGroupEntity#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        StudentNotInGroupEntity actualStudentNotInGroupEntity = new StudentNotInGroupEntity();
        actualStudentNotInGroupEntity.setName("Name");
        actualStudentNotInGroupEntity.setUserId("42");
        actualStudentNotInGroupEntity.setUserName("janedoe");
        String actualName = actualStudentNotInGroupEntity.getName();
        String actualUserId = actualStudentNotInGroupEntity.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("janedoe", actualStudentNotInGroupEntity.getUserName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link StudentNotInGroupEntity#StudentNotInGroupEntity(String, String, String)}
     *   <li>{@link StudentNotInGroupEntity#setName(String)}
     *   <li>{@link StudentNotInGroupEntity#setUserId(String)}
     *   <li>{@link StudentNotInGroupEntity#setUserName(String)}
     *   <li>{@link StudentNotInGroupEntity#getName()}
     *   <li>{@link StudentNotInGroupEntity#getUserId()}
     *   <li>{@link StudentNotInGroupEntity#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        StudentNotInGroupEntity actualStudentNotInGroupEntity = new StudentNotInGroupEntity("42", "Name", "janedoe");
        actualStudentNotInGroupEntity.setName("Name");
        actualStudentNotInGroupEntity.setUserId("42");
        actualStudentNotInGroupEntity.setUserName("janedoe");
        String actualName = actualStudentNotInGroupEntity.getName();
        String actualUserId = actualStudentNotInGroupEntity.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("janedoe", actualStudentNotInGroupEntity.getUserName());
    }
}
