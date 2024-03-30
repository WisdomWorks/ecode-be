package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CourseEnrollmentRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseEnrollmentRequest#CourseEnrollmentRequest()}
     *   <li>{@link CourseEnrollmentRequest#setCourseId(String)}
     *   <li>{@link CourseEnrollmentRequest#setEnrollmentKey(String)}
     *   <li>{@link CourseEnrollmentRequest#setStudentId(String)}
     *   <li>{@link CourseEnrollmentRequest#getCourseId()}
     *   <li>{@link CourseEnrollmentRequest#getEnrollmentKey()}
     *   <li>{@link CourseEnrollmentRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CourseEnrollmentRequest actualCourseEnrollmentRequest = new CourseEnrollmentRequest();
        actualCourseEnrollmentRequest.setCourseId("42");
        actualCourseEnrollmentRequest.setEnrollmentKey("Enrollment Key");
        actualCourseEnrollmentRequest.setStudentId("42");
        String actualCourseId = actualCourseEnrollmentRequest.getCourseId();
        String actualEnrollmentKey = actualCourseEnrollmentRequest.getEnrollmentKey();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualCourseEnrollmentRequest.getStudentId());
        assertEquals("Enrollment Key", actualEnrollmentKey);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CourseEnrollmentRequest#CourseEnrollmentRequest(String, String, String)}
     *   <li>{@link CourseEnrollmentRequest#setCourseId(String)}
     *   <li>{@link CourseEnrollmentRequest#setEnrollmentKey(String)}
     *   <li>{@link CourseEnrollmentRequest#setStudentId(String)}
     *   <li>{@link CourseEnrollmentRequest#getCourseId()}
     *   <li>{@link CourseEnrollmentRequest#getEnrollmentKey()}
     *   <li>{@link CourseEnrollmentRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CourseEnrollmentRequest actualCourseEnrollmentRequest = new CourseEnrollmentRequest("42", "42", "Enrollment Key");
        actualCourseEnrollmentRequest.setCourseId("42");
        actualCourseEnrollmentRequest.setEnrollmentKey("Enrollment Key");
        actualCourseEnrollmentRequest.setStudentId("42");
        String actualCourseId = actualCourseEnrollmentRequest.getCourseId();
        String actualEnrollmentKey = actualCourseEnrollmentRequest.getEnrollmentKey();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualCourseEnrollmentRequest.getStudentId());
        assertEquals("Enrollment Key", actualEnrollmentKey);
    }
}
