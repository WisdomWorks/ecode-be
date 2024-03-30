package com.example.codeE.request.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DeleteStudentInGroupRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link DeleteStudentInGroupRequest#DeleteStudentInGroupRequest()}
     *   <li>{@link DeleteStudentInGroupRequest#setGroupId(String)}
     *   <li>{@link DeleteStudentInGroupRequest#setStudentIds(List)}
     *   <li>{@link DeleteStudentInGroupRequest#getGroupId()}
     *   <li>{@link DeleteStudentInGroupRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        DeleteStudentInGroupRequest actualDeleteStudentInGroupRequest = new DeleteStudentInGroupRequest();
        actualDeleteStudentInGroupRequest.setGroupId("42");
        ArrayList<String> studentIds = new ArrayList<>();
        actualDeleteStudentInGroupRequest.setStudentIds(studentIds);
        String actualGroupId = actualDeleteStudentInGroupRequest.getGroupId();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertSame(studentIds, actualDeleteStudentInGroupRequest.getStudentIds());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link DeleteStudentInGroupRequest#DeleteStudentInGroupRequest(List, String)}
     *   <li>{@link DeleteStudentInGroupRequest#setGroupId(String)}
     *   <li>{@link DeleteStudentInGroupRequest#setStudentIds(List)}
     *   <li>{@link DeleteStudentInGroupRequest#getGroupId()}
     *   <li>{@link DeleteStudentInGroupRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> studentIds = new ArrayList<>();

        // Act
        DeleteStudentInGroupRequest actualDeleteStudentInGroupRequest = new DeleteStudentInGroupRequest(studentIds, "42");
        actualDeleteStudentInGroupRequest.setGroupId("42");
        ArrayList<String> studentIds2 = new ArrayList<>();
        actualDeleteStudentInGroupRequest.setStudentIds(studentIds2);
        String actualGroupId = actualDeleteStudentInGroupRequest.getGroupId();
        List<String> actualStudentIds = actualDeleteStudentInGroupRequest.getStudentIds();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals(studentIds, actualStudentIds);
        assertSame(studentIds2, actualStudentIds);
    }
}
