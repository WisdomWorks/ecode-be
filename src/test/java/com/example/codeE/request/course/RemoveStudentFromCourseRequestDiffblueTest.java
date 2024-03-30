package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RemoveStudentFromCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RemoveStudentFromCourseRequest#RemoveStudentFromCourseRequest()}
     *   <li>{@link RemoveStudentFromCourseRequest#setCourseId(String)}
     *   <li>{@link RemoveStudentFromCourseRequest#setStudentId(String)}
     *   <li>{@link RemoveStudentFromCourseRequest#getCourseId()}
     *   <li>{@link RemoveStudentFromCourseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        RemoveStudentFromCourseRequest actualRemoveStudentFromCourseRequest = new RemoveStudentFromCourseRequest();
        actualRemoveStudentFromCourseRequest.setCourseId("42");
        actualRemoveStudentFromCourseRequest.setStudentId("42");
        String actualCourseId = actualRemoveStudentFromCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualRemoveStudentFromCourseRequest.getStudentId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link RemoveStudentFromCourseRequest#RemoveStudentFromCourseRequest(String, String)}
     *   <li>{@link RemoveStudentFromCourseRequest#setCourseId(String)}
     *   <li>{@link RemoveStudentFromCourseRequest#setStudentId(String)}
     *   <li>{@link RemoveStudentFromCourseRequest#getCourseId()}
     *   <li>{@link RemoveStudentFromCourseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        RemoveStudentFromCourseRequest actualRemoveStudentFromCourseRequest = new RemoveStudentFromCourseRequest("42",
                "42");
        actualRemoveStudentFromCourseRequest.setCourseId("42");
        actualRemoveStudentFromCourseRequest.setStudentId("42");
        String actualCourseId = actualRemoveStudentFromCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualRemoveStudentFromCourseRequest.getStudentId());
    }
}
