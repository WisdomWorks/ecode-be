package com.example.codeE.service.courseStudent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateStudentsToCourseRequest;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CourseStudentImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CourseStudentImplDiffblueTest {
    @Autowired
    private CourseStudentImpl courseStudentImpl;

    @MockBean
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link CourseStudentImpl#addStudentToCourse(AddStudentToCourseRequest)}
     */
    @Test
    void testAddStudentToCourse() {
        // Arrange, Act and Assert
        assertTrue(courseStudentImpl.addStudentToCourse(new AddStudentToCourseRequest()).isEmpty());
        assertTrue(courseStudentImpl.addStudentToCourse(null).isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#addStudentToCourse(AddStudentToCourseRequest)}
     */
    @Test
    void testAddStudentToCourse2() {
        // Arrange
        AddStudentToCourseRequest request = new AddStudentToCourseRequest();
        request.setStudentIds(new ArrayList<>());

        // Act and Assert
        assertTrue(courseStudentImpl.addStudentToCourse(request).isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#addStudentToCourse(AddStudentToCourseRequest)}
     */
    @Test
    void testAddStudentToCourse3() {
        // Arrange
        AddStudentToCourseRequest request = mock(AddStudentToCourseRequest.class);
        when(request.getStudentIds()).thenReturn(new ArrayList<>());

        // Act
        ArrayList<CourseStudent> actualAddStudentToCourseResult = courseStudentImpl.addStudentToCourse(request);

        // Assert
        verify(request).getStudentIds();
        assertTrue(actualAddStudentToCourseResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#addStudentToCourse(AddStudentToCourseRequest)}
     */
    @Test
    void testAddStudentToCourse4() {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");
        when(courseStudentRepository.save(Mockito.<CourseStudent>any())).thenReturn(courseStudent);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        AddStudentToCourseRequest request = mock(AddStudentToCourseRequest.class);
        when(request.getCourseId()).thenReturn("42");
        when(request.getStudentIds()).thenReturn(stringList);

        // Act
        ArrayList<CourseStudent> actualAddStudentToCourseResult = courseStudentImpl.addStudentToCourse(request);

        // Assert
        verify(request).getCourseId();
        verify(request).getStudentIds();
        verify(courseStudentRepository).save(isA(CourseStudent.class));
        assertEquals(1, actualAddStudentToCourseResult.size());
        assertSame(courseStudent, actualAddStudentToCourseResult.get(0));
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#updateStudentsInCourse(UpdateStudentsToCourseRequest)}
     */
    @Test
    void testUpdateStudentsInCourse() {
        // Arrange
        doNothing().when(courseStudentRepository).deleteAllStudentsByCourseId(Mockito.<String>any());

        // Act
        List<CourseStudent> actualUpdateStudentsInCourseResult = courseStudentImpl
                .updateStudentsInCourse(new UpdateStudentsToCourseRequest());

        // Assert
        verify(courseStudentRepository).deleteAllStudentsByCourseId(isNull());
        assertTrue(actualUpdateStudentsInCourseResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#updateStudentsInCourse(UpdateStudentsToCourseRequest)}
     */
    @Test
    void testUpdateStudentsInCourse2() {
        // Arrange
        doNothing().when(courseStudentRepository).deleteAllStudentsByCourseId(Mockito.<String>any());

        UpdateStudentsToCourseRequest request = new UpdateStudentsToCourseRequest();
        request.setStudentIds(new ArrayList<>());

        // Act
        List<CourseStudent> actualUpdateStudentsInCourseResult = courseStudentImpl.updateStudentsInCourse(request);

        // Assert
        verify(courseStudentRepository).deleteAllStudentsByCourseId(isNull());
        assertTrue(actualUpdateStudentsInCourseResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#updateStudentsInCourse(UpdateStudentsToCourseRequest)}
     */
    @Test
    void testUpdateStudentsInCourse3() {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");
        when(courseStudentRepository.save(Mockito.<CourseStudent>any())).thenReturn(courseStudent);
        doNothing().when(courseStudentRepository).deleteAllStudentsByCourseId(Mockito.<String>any());

        ArrayList<String> studentIds = new ArrayList<>();
        studentIds.add("foo");

        UpdateStudentsToCourseRequest request = new UpdateStudentsToCourseRequest();
        request.setStudentIds(studentIds);

        // Act
        List<CourseStudent> actualUpdateStudentsInCourseResult = courseStudentImpl.updateStudentsInCourse(request);

        // Assert
        verify(courseStudentRepository).deleteAllStudentsByCourseId(isNull());
        verify(courseStudentRepository).save(isA(CourseStudent.class));
        assertEquals(1, actualUpdateStudentsInCourseResult.size());
        assertSame(courseStudent, actualUpdateStudentsInCourseResult.get(0));
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#importStudentsToCourse(ImportStudentToCourseRequest)}
     */
    @Test
    void testImportStudentsToCourse() throws IOException {
        // Arrange, Act and Assert
        assertTrue(courseStudentImpl.importStudentsToCourse(new ImportStudentToCourseRequest("42",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))))).isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#importStudentsToCourse(ImportStudentToCourseRequest)}
     */
    @Test
    void testImportStudentsToCourse2() throws IOException {
        // Arrange
        ImportStudentToCourseRequest request = mock(ImportStudentToCourseRequest.class);
        when(request.getFile())
                .thenReturn(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Act
        List<String> actualImportStudentsToCourseResult = courseStudentImpl.importStudentsToCourse(request);

        // Assert
        verify(request).getFile();
        assertTrue(actualImportStudentsToCourseResult.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#deleteStudentInCourse(RemoveStudentFromCourseRequest)}
     */
    @Test
    void testDeleteStudentInCourse() {
        // Arrange
        doNothing().when(courseStudentRepository)
                .deleteByStudentIdAndCourseId(Mockito.<String>any(), Mockito.<String>any());

        // Act
        Boolean actualDeleteStudentInCourseResult = courseStudentImpl
                .deleteStudentInCourse(new RemoveStudentFromCourseRequest("42", "42"));

        // Assert
        verify(courseStudentRepository).deleteByStudentIdAndCourseId(eq("42"), eq("42"));
        assertTrue(actualDeleteStudentInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#deleteStudentInCourse(RemoveStudentFromCourseRequest)}
     */
    @Test
    void testDeleteStudentInCourse2() {
        // Arrange, Act and Assert
        assertFalse(courseStudentImpl.deleteStudentInCourse(null));
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#checkStudentInCourse(String, String)}
     */
    @Test
    void testCheckStudentInCourse() {
        // Arrange
        when(courseStudentRepository.existsByStudentIdAndCourseId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(1L);

        // Act
        Boolean actualCheckStudentInCourseResult = courseStudentImpl.checkStudentInCourse("42", "42");

        // Assert
        verify(courseStudentRepository).existsByStudentIdAndCourseId(eq("42"), eq("42"));
        assertTrue(actualCheckStudentInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#checkStudentInCourse(String, String)}
     */
    @Test
    void testCheckStudentInCourse2() {
        // Arrange
        when(courseStudentRepository.existsByStudentIdAndCourseId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(0L);

        // Act
        Boolean actualCheckStudentInCourseResult = courseStudentImpl.checkStudentInCourse("42", "42");

        // Assert
        verify(courseStudentRepository).existsByStudentIdAndCourseId(eq("42"), eq("42"));
        assertFalse(actualCheckStudentInCourseResult);
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel() throws UnsupportedEncodingException {
        // Arrange and Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl
                .getAllNamesFromExcel(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        // Assert
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel2() {
        // Arrange and Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl
                .getAllNamesFromExcel(new ByteArrayInputStream(new byte[]{-1, 'X', 'A', 'X', 'A', 'X', 'A', 'X'}));

        // Assert
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel3() throws IOException {
        // Arrange
        DataInputStream inputStream = mock(DataInputStream.class);
        doNothing().when(inputStream).reset();
        when(inputStream.read(Mockito.<byte[]>any())).thenReturn(1);
        doNothing().when(inputStream).mark(anyInt());
        when(inputStream.markSupported()).thenReturn(true);
        when(inputStream.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenReturn(1);

        // Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl.getAllNamesFromExcel(inputStream);

        // Assert
        verify(inputStream).read(isA(byte[].class));
        verify(inputStream, atLeast(1)).read(isA(byte[].class), eq(0), anyInt());
        verify(inputStream, atLeast(1)).mark(anyInt());
        verify(inputStream, atLeast(1)).markSupported();
        verify(inputStream, atLeast(1)).reset();
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel4() throws IOException {
        // Arrange
        DataInputStream inputStream = mock(DataInputStream.class);
        doThrow(new IOException("foo")).when(inputStream).reset();
        when(inputStream.read(Mockito.<byte[]>any())).thenReturn(1);
        doNothing().when(inputStream).mark(anyInt());
        when(inputStream.markSupported()).thenReturn(true);

        // Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl.getAllNamesFromExcel(inputStream);

        // Assert
        verify(inputStream).read(isA(byte[].class));
        verify(inputStream).mark(eq(1));
        verify(inputStream).markSupported();
        verify(inputStream).reset();
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel5() throws IOException {
        // Arrange
        DataInputStream inputStream = mock(DataInputStream.class);
        when(inputStream.available()).thenReturn(1);
        when(inputStream.markSupported()).thenReturn(false);
        when(inputStream.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenReturn(1);

        // Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl.getAllNamesFromExcel(inputStream);

        // Assert
        verify(inputStream, atLeast(1)).read(isA(byte[].class), anyInt(), anyInt());
        verify(inputStream, atLeast(1)).available();
        verify(inputStream).markSupported();
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }

    /**
     * Method under test:
     * {@link CourseStudentImpl#getAllNamesFromExcel(InputStream)}
     */
    @Test
    void testGetAllNamesFromExcel6() throws IOException {
        // Arrange
        DataInputStream inputStream = mock(DataInputStream.class);
        when(inputStream.available()).thenThrow(new IOException("foo"));
        when(inputStream.markSupported()).thenReturn(false);
        when(inputStream.read(Mockito.<byte[]>any(), anyInt(), anyInt())).thenReturn(1);

        // Act
        List<String> actualAllNamesFromExcel = CourseStudentImpl.getAllNamesFromExcel(inputStream);

        // Assert
        verify(inputStream).read(isA(byte[].class), eq(0), eq(8192));
        verify(inputStream).available();
        verify(inputStream).markSupported();
        assertTrue(actualAllNamesFromExcel.isEmpty());
    }
}
