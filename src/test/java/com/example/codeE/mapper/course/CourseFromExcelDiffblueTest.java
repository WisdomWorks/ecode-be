package com.example.codeE.mapper.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CourseFromExcelDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseFromExcel#CourseFromExcel()}
     *   <li>{@link CourseFromExcel#setCourseId(String)}
     *   <li>{@link CourseFromExcel#setCourseName(String)}
     *   <li>{@link CourseFromExcel#setDescription(String)}
     *   <li>{@link CourseFromExcel#setSemester(String)}
     *   <li>{@link CourseFromExcel#getCourseId()}
     *   <li>{@link CourseFromExcel#getCourseName()}
     *   <li>{@link CourseFromExcel#getDescription()}
     *   <li>{@link CourseFromExcel#getSemester()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CourseFromExcel actualCourseFromExcel = new CourseFromExcel();
        actualCourseFromExcel.setCourseId("42");
        actualCourseFromExcel.setCourseName("Course Name");
        actualCourseFromExcel.setDescription("The characteristics of someone or something");
        actualCourseFromExcel.setSemester("Semester");
        String actualCourseId = actualCourseFromExcel.getCourseId();
        String actualCourseName = actualCourseFromExcel.getCourseName();
        String actualDescription = actualCourseFromExcel.getDescription();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualCourseFromExcel.getSemester());
        assertEquals("The characteristics of someone or something", actualDescription);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseFromExcel#CourseFromExcel(String, String, String, String)}
     *   <li>{@link CourseFromExcel#setCourseId(String)}
     *   <li>{@link CourseFromExcel#setCourseName(String)}
     *   <li>{@link CourseFromExcel#setDescription(String)}
     *   <li>{@link CourseFromExcel#setSemester(String)}
     *   <li>{@link CourseFromExcel#getCourseId()}
     *   <li>{@link CourseFromExcel#getCourseName()}
     *   <li>{@link CourseFromExcel#getDescription()}
     *   <li>{@link CourseFromExcel#getSemester()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CourseFromExcel actualCourseFromExcel = new CourseFromExcel("42", "Course Name", "Semester",
                "The characteristics of someone or something");
        actualCourseFromExcel.setCourseId("42");
        actualCourseFromExcel.setCourseName("Course Name");
        actualCourseFromExcel.setDescription("The characteristics of someone or something");
        actualCourseFromExcel.setSemester("Semester");
        String actualCourseId = actualCourseFromExcel.getCourseId();
        String actualCourseName = actualCourseFromExcel.getCourseName();
        String actualDescription = actualCourseFromExcel.getDescription();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualCourseFromExcel.getSemester());
        assertEquals("The characteristics of someone or something", actualDescription);
    }
}
