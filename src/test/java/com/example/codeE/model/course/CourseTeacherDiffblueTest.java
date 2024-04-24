package com.example.codeE.model.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CourseTeacherDiffblueTest {
    /**
     * Method under test:
     * {@link CourseTeacher#CourseTeacher(String, String, Boolean)}
     */
    @Test
    void testNewCourseTeacher() {
        // Arrange and Act
        CourseTeacher actualCourseTeacher = new CourseTeacher("42", "42", true);

        // Assert
        assertEquals("42", actualCourseTeacher.getCourseId());
        assertEquals("42", actualCourseTeacher.getTeacherId());
        assertNull(actualCourseTeacher.getCourse());
        assertNull(actualCourseTeacher.getTeacher());
        assertNull(actualCourseTeacher.getCreatedDate());
        assertTrue(actualCourseTeacher.getIsMain());
    }
}
