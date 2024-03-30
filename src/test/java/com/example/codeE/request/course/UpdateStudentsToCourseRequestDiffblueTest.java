package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class UpdateStudentsToCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateStudentsToCourseRequest#UpdateStudentsToCourseRequest()}
     *   <li>{@link UpdateStudentsToCourseRequest#setCourseId(String)}
     *   <li>{@link UpdateStudentsToCourseRequest#setStudentIds(List)}
     *   <li>{@link UpdateStudentsToCourseRequest#getCourseId()}
     *   <li>{@link UpdateStudentsToCourseRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateStudentsToCourseRequest actualUpdateStudentsToCourseRequest = new UpdateStudentsToCourseRequest();
        actualUpdateStudentsToCourseRequest.setCourseId("42");
        ArrayList<String> studentIds = new ArrayList<>();
        actualUpdateStudentsToCourseRequest.setStudentIds(studentIds);
        String actualCourseId = actualUpdateStudentsToCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertSame(studentIds, actualUpdateStudentsToCourseRequest.getStudentIds());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateStudentsToCourseRequest#UpdateStudentsToCourseRequest(String, List)}
     *   <li>{@link UpdateStudentsToCourseRequest#setCourseId(String)}
     *   <li>{@link UpdateStudentsToCourseRequest#setStudentIds(List)}
     *   <li>{@link UpdateStudentsToCourseRequest#getCourseId()}
     *   <li>{@link UpdateStudentsToCourseRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> studentIds = new ArrayList<>();

        // Act
        UpdateStudentsToCourseRequest actualUpdateStudentsToCourseRequest = new UpdateStudentsToCourseRequest("42",
                studentIds);
        actualUpdateStudentsToCourseRequest.setCourseId("42");
        ArrayList<String> studentIds2 = new ArrayList<>();
        actualUpdateStudentsToCourseRequest.setStudentIds(studentIds2);
        String actualCourseId = actualUpdateStudentsToCourseRequest.getCourseId();
        List<String> actualStudentIds = actualUpdateStudentsToCourseRequest.getStudentIds();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals(studentIds, actualStudentIds);
        assertSame(studentIds2, actualStudentIds);
    }
}
