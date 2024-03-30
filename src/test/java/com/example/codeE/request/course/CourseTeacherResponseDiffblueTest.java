package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class CourseTeacherResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseTeacherResponse#CourseTeacherResponse()}
     *   <li>{@link CourseTeacherResponse#setCourseId(String)}
     *   <li>{@link CourseTeacherResponse#setCourseName(String)}
     *   <li>{@link CourseTeacherResponse#setCreatedDate(LocalDateTime)}
     *   <li>{@link CourseTeacherResponse#setDescription(String)}
     *   <li>{@link CourseTeacherResponse#setSemester(String)}
     *   <li>{@link CourseTeacherResponse#setTeacher(User)}
     *   <li>{@link CourseTeacherResponse#setUpdatedDate(LocalDateTime)}
     *   <li>{@link CourseTeacherResponse#getCourseId()}
     *   <li>{@link CourseTeacherResponse#getCourseName()}
     *   <li>{@link CourseTeacherResponse#getCreatedDate()}
     *   <li>{@link CourseTeacherResponse#getDescription()}
     *   <li>{@link CourseTeacherResponse#getSemester()}
     *   <li>{@link CourseTeacherResponse#getTeacher()}
     *   <li>{@link CourseTeacherResponse#getUpdatedDate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CourseTeacherResponse actualCourseTeacherResponse = new CourseTeacherResponse();
        actualCourseTeacherResponse.setCourseId("42");
        actualCourseTeacherResponse.setCourseName("Course Name");
        LocalDateTime createdDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCourseTeacherResponse.setCreatedDate(createdDate);
        actualCourseTeacherResponse.setDescription("The characteristics of someone or something");
        actualCourseTeacherResponse.setSemester("Semester");
        User teacher = new User();
        teacher.setCourseStudents(new ArrayList<>());
        teacher.setCourseTeachers(new ArrayList<>());
        teacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher.setEmail("jane.doe@example.org");
        teacher.setGroupStudents(new ArrayList<>());
        teacher.setName("Name");
        teacher.setPassword("iloveyou");
        teacher.setRole("Role");
        teacher.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher.setUserId("42");
        teacher.setUsername("janedoe");
        actualCourseTeacherResponse.setTeacher(teacher);
        LocalDateTime updatedDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCourseTeacherResponse.setUpdatedDate(updatedDate);
        String actualCourseId = actualCourseTeacherResponse.getCourseId();
        String actualCourseName = actualCourseTeacherResponse.getCourseName();
        LocalDateTime actualCreatedDate = actualCourseTeacherResponse.getCreatedDate();
        String actualDescription = actualCourseTeacherResponse.getDescription();
        String actualSemester = actualCourseTeacherResponse.getSemester();
        User actualTeacher = actualCourseTeacherResponse.getTeacher();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertSame(teacher, actualTeacher);
        assertSame(createdDate, actualCreatedDate);
        assertSame(updatedDate, actualCourseTeacherResponse.getUpdatedDate());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CourseTeacherResponse#CourseTeacherResponse(String, String, String, String, LocalDateTime, LocalDateTime, User)}
     *   <li>{@link CourseTeacherResponse#setCourseId(String)}
     *   <li>{@link CourseTeacherResponse#setCourseName(String)}
     *   <li>{@link CourseTeacherResponse#setCreatedDate(LocalDateTime)}
     *   <li>{@link CourseTeacherResponse#setDescription(String)}
     *   <li>{@link CourseTeacherResponse#setSemester(String)}
     *   <li>{@link CourseTeacherResponse#setTeacher(User)}
     *   <li>{@link CourseTeacherResponse#setUpdatedDate(LocalDateTime)}
     *   <li>{@link CourseTeacherResponse#getCourseId()}
     *   <li>{@link CourseTeacherResponse#getCourseName()}
     *   <li>{@link CourseTeacherResponse#getCreatedDate()}
     *   <li>{@link CourseTeacherResponse#getDescription()}
     *   <li>{@link CourseTeacherResponse#getSemester()}
     *   <li>{@link CourseTeacherResponse#getTeacher()}
     *   <li>{@link CourseTeacherResponse#getUpdatedDate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        LocalDateTime createdDate = LocalDate.of(1970, 1, 1).atStartOfDay();
        LocalDateTime updatedDate = LocalDate.of(1970, 1, 1).atStartOfDay();

        User teacher = new User();
        teacher.setCourseStudents(new ArrayList<>());
        teacher.setCourseTeachers(new ArrayList<>());
        teacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher.setEmail("jane.doe@example.org");
        teacher.setGroupStudents(new ArrayList<>());
        teacher.setName("Name");
        teacher.setPassword("iloveyou");
        teacher.setRole("Role");
        teacher.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher.setUserId("42");
        teacher.setUsername("janedoe");

        // Act
        CourseTeacherResponse actualCourseTeacherResponse = new CourseTeacherResponse("42", "Course Name", "Semester",
                "The characteristics of someone or something", createdDate, updatedDate, teacher);
        actualCourseTeacherResponse.setCourseId("42");
        actualCourseTeacherResponse.setCourseName("Course Name");
        LocalDateTime createdDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCourseTeacherResponse.setCreatedDate(createdDate2);
        actualCourseTeacherResponse.setDescription("The characteristics of someone or something");
        actualCourseTeacherResponse.setSemester("Semester");
        User teacher2 = new User();
        teacher2.setCourseStudents(new ArrayList<>());
        teacher2.setCourseTeachers(new ArrayList<>());
        teacher2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher2.setEmail("jane.doe@example.org");
        teacher2.setGroupStudents(new ArrayList<>());
        teacher2.setName("Name");
        teacher2.setPassword("iloveyou");
        teacher2.setRole("Role");
        teacher2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        teacher2.setUserId("42");
        teacher2.setUsername("janedoe");
        actualCourseTeacherResponse.setTeacher(teacher2);
        LocalDateTime updatedDate2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        actualCourseTeacherResponse.setUpdatedDate(updatedDate2);
        String actualCourseId = actualCourseTeacherResponse.getCourseId();
        String actualCourseName = actualCourseTeacherResponse.getCourseName();
        LocalDateTime actualCreatedDate = actualCourseTeacherResponse.getCreatedDate();
        String actualDescription = actualCourseTeacherResponse.getDescription();
        String actualSemester = actualCourseTeacherResponse.getSemester();
        User actualTeacher = actualCourseTeacherResponse.getTeacher();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Course Name", actualCourseName);
        assertEquals("Semester", actualSemester);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertSame(teacher2, actualTeacher);
        assertSame(createdDate2, actualCreatedDate);
        assertSame(updatedDate2, actualCourseTeacherResponse.getUpdatedDate());
    }
}
