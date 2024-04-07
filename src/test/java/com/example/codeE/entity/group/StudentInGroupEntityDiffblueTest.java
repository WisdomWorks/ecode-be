package com.example.codeE.entity.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class StudentInGroupEntityDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StudentInGroupEntity#StudentInGroupEntity()}
     *   <li>{@link StudentInGroupEntity#setJoinDate(LocalDateTime)}
     *   <li>{@link StudentInGroupEntity#setName(String)}
     *   <li>{@link StudentInGroupEntity#setUserId(String)}
     *   <li>{@link StudentInGroupEntity#setUserName(String)}
     *   <li>{@link StudentInGroupEntity#getJoinDate()}
     *   <li>{@link StudentInGroupEntity#getName()}
     *   <li>{@link StudentInGroupEntity#getUserId()}
     *   <li>{@link StudentInGroupEntity#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        StudentInGroupEntity actualStudentInGroupEntity = new StudentInGroupEntity();
        LocalDateTime joinDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualStudentInGroupEntity.setJoinDate(joinDate);
        actualStudentInGroupEntity.setName("Name");
        actualStudentInGroupEntity.setUserId("42");
        actualStudentInGroupEntity.setUserName("janedoe");
        LocalDateTime actualJoinDate = actualStudentInGroupEntity.getJoinDate();
        String actualName = actualStudentInGroupEntity.getName();
        String actualUserId = actualStudentInGroupEntity.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("janedoe", actualStudentInGroupEntity.getUserName());
        assertSame(joinDate, actualJoinDate);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link StudentInGroupEntity#StudentInGroupEntity(String, String, String, LocalDateTime)}
     *   <li>{@link StudentInGroupEntity#setJoinDate(LocalDateTime)}
     *   <li>{@link StudentInGroupEntity#setName(String)}
     *   <li>{@link StudentInGroupEntity#setUserId(String)}
     *   <li>{@link StudentInGroupEntity#setUserName(String)}
     *   <li>{@link StudentInGroupEntity#getJoinDate()}
     *   <li>{@link StudentInGroupEntity#getName()}
     *   <li>{@link StudentInGroupEntity#getUserId()}
     *   <li>{@link StudentInGroupEntity#getUserName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        StudentInGroupEntity actualStudentInGroupEntity = new StudentInGroupEntity("42", "Name", "janedoe",
                LocalDate.of(1970, 1, 1).atStartOfDay());
        LocalDateTime joinDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualStudentInGroupEntity.setJoinDate(joinDate);
        actualStudentInGroupEntity.setName("Name");
        actualStudentInGroupEntity.setUserId("42");
        actualStudentInGroupEntity.setUserName("janedoe");
        LocalDateTime actualJoinDate = actualStudentInGroupEntity.getJoinDate();
        String actualName = actualStudentInGroupEntity.getName();
        String actualUserId = actualStudentInGroupEntity.getUserId();

        // Assert that nothing has changed
        assertEquals("42", actualUserId);
        assertEquals("Name", actualName);
        assertEquals("janedoe", actualStudentInGroupEntity.getUserName());
        assertSame(joinDate, actualJoinDate);
    }
}
