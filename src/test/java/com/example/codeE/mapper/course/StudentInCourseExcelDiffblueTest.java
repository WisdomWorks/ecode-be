package com.example.codeE.mapper.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StudentInCourseExcelDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StudentInCourseExcel#StudentInCourseExcel()}
     *   <li>{@link StudentInCourseExcel#setStudentName(String)}
     *   <li>{@link StudentInCourseExcel#getStudentName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        StudentInCourseExcel actualStudentInCourseExcel = new StudentInCourseExcel();
        actualStudentInCourseExcel.setStudentName("Student Name");

        // Assert that nothing has changed
        assertEquals("Student Name", actualStudentInCourseExcel.getStudentName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link StudentInCourseExcel#StudentInCourseExcel(String)}
     *   <li>{@link StudentInCourseExcel#setStudentName(String)}
     *   <li>{@link StudentInCourseExcel#getStudentName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        StudentInCourseExcel actualStudentInCourseExcel = new StudentInCourseExcel("Student Name");
        actualStudentInCourseExcel.setStudentName("Student Name");

        // Assert that nothing has changed
        assertEquals("Student Name", actualStudentInCourseExcel.getStudentName());
    }
}
