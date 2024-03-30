package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateCourseRequest#CreateCourseRequest()}
     *   <li>{@link CreateCourseRequest#setCourseName(String)}
     *   <li>{@link CreateCourseRequest#setDescription(String)}
     *   <li>{@link CreateCourseRequest#setSemester(String)}
     *   <li>{@link CreateCourseRequest#setTeacherId(String)}
     *   <li>{@link CreateCourseRequest#getCourseName()}
     *   <li>{@link CreateCourseRequest#getDescription()}
     *   <li>{@link CreateCourseRequest#getSemester()}
     *   <li>{@link CreateCourseRequest#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateCourseRequest actualCreateCourseRequest = new CreateCourseRequest();
        actualCreateCourseRequest.setCourseName("Course Name");
        actualCreateCourseRequest.setDescription("The characteristics of someone or something");
        actualCreateCourseRequest.setSemester("Semester");
        actualCreateCourseRequest.setTeacherId("42");
        String actualCourseName = actualCreateCourseRequest.getCourseName();
        String actualDescription = actualCreateCourseRequest.getDescription();
        String actualSemester = actualCreateCourseRequest.getSemester();

        // Assert that nothing has changed
        assertEquals("42", actualCreateCourseRequest.getTeacherId());
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateCourseRequest#CreateCourseRequest(String, String, String, String)}
     *   <li>{@link CreateCourseRequest#setCourseName(String)}
     *   <li>{@link CreateCourseRequest#setDescription(String)}
     *   <li>{@link CreateCourseRequest#setSemester(String)}
     *   <li>{@link CreateCourseRequest#setTeacherId(String)}
     *   <li>{@link CreateCourseRequest#getCourseName()}
     *   <li>{@link CreateCourseRequest#getDescription()}
     *   <li>{@link CreateCourseRequest#getSemester()}
     *   <li>{@link CreateCourseRequest#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateCourseRequest actualCreateCourseRequest = new CreateCourseRequest("Course Name", "Semester",
                "The characteristics of someone or something", "42");
        actualCreateCourseRequest.setCourseName("Course Name");
        actualCreateCourseRequest.setDescription("The characteristics of someone or something");
        actualCreateCourseRequest.setSemester("Semester");
        actualCreateCourseRequest.setTeacherId("42");
        String actualCourseName = actualCreateCourseRequest.getCourseName();
        String actualDescription = actualCreateCourseRequest.getDescription();
        String actualSemester = actualCreateCourseRequest.getSemester();

        // Assert that nothing has changed
        assertEquals("42", actualCreateCourseRequest.getTeacherId());
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
    }
}
