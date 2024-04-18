package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class AddStudentToCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AddStudentToCourseRequest#AddStudentToCourseRequest()}
     *   <li>{@link AddStudentToCourseRequest#setCourseId(String)}
     *   <li>{@link AddStudentToCourseRequest#setStudentIds(List)}
     *   <li>{@link AddStudentToCourseRequest#getCourseId()}
     *   <li>{@link AddStudentToCourseRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        AddStudentToCourseRequest actualAddStudentToCourseRequest = new AddStudentToCourseRequest();
        actualAddStudentToCourseRequest.setCourseId("42");
        ArrayList<String> studentIds = new ArrayList<>();
        actualAddStudentToCourseRequest.setStudentIds(studentIds);
        String actualCourseId = actualAddStudentToCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertSame(studentIds, actualAddStudentToCourseRequest.getStudentIds());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link AddStudentToCourseRequest#AddStudentToCourseRequest(List, String)}
     *   <li>{@link AddStudentToCourseRequest#setCourseId(String)}
     *   <li>{@link AddStudentToCourseRequest#setStudentIds(List)}
     *   <li>{@link AddStudentToCourseRequest#getCourseId()}
     *   <li>{@link AddStudentToCourseRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> studentIds = new ArrayList<>();

        // Act
        AddStudentToCourseRequest actualAddStudentToCourseRequest = new AddStudentToCourseRequest(studentIds, "42");
        actualAddStudentToCourseRequest.setCourseId("42");
        ArrayList<String> studentIds2 = new ArrayList<>();
        actualAddStudentToCourseRequest.setStudentIds(studentIds2);
        String actualCourseId = actualAddStudentToCourseRequest.getCourseId();
        List<String> actualStudentIds = actualAddStudentToCourseRequest.getStudentIds();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals(studentIds, actualStudentIds);
        assertSame(studentIds2, actualStudentIds);
    }
}
