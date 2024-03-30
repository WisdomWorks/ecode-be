package com.example.codeE.mapper.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class CourseTeacherDTODiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseTeacherDTO#CourseTeacherDTO()}
     *   <li>{@link CourseTeacherDTO#setIsMain(Boolean)}
     *   <li>{@link CourseTeacherDTO#setTeacherId(String)}
     *   <li>{@link CourseTeacherDTO#getIsMain()}
     *   <li>{@link CourseTeacherDTO#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CourseTeacherDTO actualCourseTeacherDTO = new CourseTeacherDTO();
        actualCourseTeacherDTO.setIsMain(true);
        actualCourseTeacherDTO.setTeacherId("42");
        Boolean actualIsMain = actualCourseTeacherDTO.getIsMain();

        // Assert that nothing has changed
        assertEquals("42", actualCourseTeacherDTO.getTeacherId());
        assertTrue(actualIsMain);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseTeacherDTO#CourseTeacherDTO(String, Boolean)}
     *   <li>{@link CourseTeacherDTO#setIsMain(Boolean)}
     *   <li>{@link CourseTeacherDTO#setTeacherId(String)}
     *   <li>{@link CourseTeacherDTO#getIsMain()}
     *   <li>{@link CourseTeacherDTO#getTeacherId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CourseTeacherDTO actualCourseTeacherDTO = new CourseTeacherDTO("42", true);
        actualCourseTeacherDTO.setIsMain(true);
        actualCourseTeacherDTO.setTeacherId("42");
        Boolean actualIsMain = actualCourseTeacherDTO.getIsMain();

        // Assert that nothing has changed
        assertEquals("42", actualCourseTeacherDTO.getTeacherId());
        assertTrue(actualIsMain);
    }
}
