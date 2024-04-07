package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UpdateCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateCourseRequest#UpdateCourseRequest()}
     *   <li>{@link UpdateCourseRequest#setCourseId(String)}
     *   <li>{@link UpdateCourseRequest#setCourseName(String)}
     *   <li>{@link UpdateCourseRequest#setDescription(String)}
     *   <li>{@link UpdateCourseRequest#setEnrollKey(String)}
     *   <li>{@link UpdateCourseRequest#setSemester(String)}
     *   <li>{@link UpdateCourseRequest#setTeacherId(String)}
     *   <li>{@link UpdateCourseRequest#getCourseId()}
     *   <li>{@link UpdateCourseRequest#getCourseName()}
     *   <li>{@link UpdateCourseRequest#getDescription()}
     *   <li>{@link UpdateCourseRequest#getEnrollKey()}
     *   <li>{@link UpdateCourseRequest#getSemester()}
     *   <li>{@link UpdateCourseRequest#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateCourseRequest actualUpdateCourseRequest = new UpdateCourseRequest();
        actualUpdateCourseRequest.setCourseId("42");
        actualUpdateCourseRequest.setCourseName("Course Name");
        actualUpdateCourseRequest.setDescription("The characteristics of someone or something");
        actualUpdateCourseRequest.setEnrollKey("Enroll Key");
        actualUpdateCourseRequest.setSemester("Semester");
        actualUpdateCourseRequest.setTeacherId("42");
        String actualCourseId = actualUpdateCourseRequest.getCourseId();
        String actualCourseName = actualUpdateCourseRequest.getCourseName();
        String actualDescription = actualUpdateCourseRequest.getDescription();
        String actualEnrollKey = actualUpdateCourseRequest.getEnrollKey();
        String actualSemester = actualUpdateCourseRequest.getSemester();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualUpdateCourseRequest.getTeacherId());
        assertEquals("Course Name", actualCourseName);
        assertEquals("Enroll Key", actualEnrollKey);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateCourseRequest#UpdateCourseRequest(String, String, String, String, String, String)}
     *   <li>{@link UpdateCourseRequest#setCourseId(String)}
     *   <li>{@link UpdateCourseRequest#setCourseName(String)}
     *   <li>{@link UpdateCourseRequest#setDescription(String)}
     *   <li>{@link UpdateCourseRequest#setEnrollKey(String)}
     *   <li>{@link UpdateCourseRequest#setSemester(String)}
     *   <li>{@link UpdateCourseRequest#setTeacherId(String)}
     *   <li>{@link UpdateCourseRequest#getCourseId()}
     *   <li>{@link UpdateCourseRequest#getCourseName()}
     *   <li>{@link UpdateCourseRequest#getDescription()}
     *   <li>{@link UpdateCourseRequest#getEnrollKey()}
     *   <li>{@link UpdateCourseRequest#getSemester()}
     *   <li>{@link UpdateCourseRequest#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateCourseRequest actualUpdateCourseRequest = new UpdateCourseRequest("42", "Course Name", "Semester",
                "Enroll Key", "The characteristics of someone or something", "42");
        actualUpdateCourseRequest.setCourseId("42");
        actualUpdateCourseRequest.setCourseName("Course Name");
        actualUpdateCourseRequest.setDescription("The characteristics of someone or something");
        actualUpdateCourseRequest.setEnrollKey("Enroll Key");
        actualUpdateCourseRequest.setSemester("Semester");
        actualUpdateCourseRequest.setTeacherId("42");
        String actualCourseId = actualUpdateCourseRequest.getCourseId();
        String actualCourseName = actualUpdateCourseRequest.getCourseName();
        String actualDescription = actualUpdateCourseRequest.getDescription();
        String actualEnrollKey = actualUpdateCourseRequest.getEnrollKey();
        String actualSemester = actualUpdateCourseRequest.getSemester();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("42", actualUpdateCourseRequest.getTeacherId());
        assertEquals("Course Name", actualCourseName);
        assertEquals("Enroll Key", actualEnrollKey);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
    }
}
