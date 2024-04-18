package com.example.codeE.service.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseRepository;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.CourseTeacherRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.CourseEnrollmentRequest;
import com.example.codeE.request.course.CourseEnrollmentResponse;
import com.example.codeE.request.course.CourseResponse;
import com.example.codeE.request.course.CourseTeacherResponse;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.service.courseStudent.CourseStudentService;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {CourseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CourseImplDiffblueTest {
    @Autowired
    private CourseImpl courseImpl;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private CourseStudentService courseStudentService;

    @MockBean
    private CourseTeacherRepository courseTeacherRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link CourseImpl#createOne(CreateCourseRequest)}
     */
    @Test
    void testCreateOne() {
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
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course2);
        courseTeacher.setCourseId("42");
        courseTeacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseTeacher.setIsMain(true);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherId("42");
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenReturn(courseTeacher);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CourseResponse actualCreateOneResult = courseImpl.createOne(
                new CreateCourseRequest("Course Name", "Semester", "The characteristics of someone or something", "42"));

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
        LocalTime expectedToLocalTimeResult = actualCreateOneResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateOneResult.getTeacher().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#createOne(CreateCourseRequest)}
     */
    @Test
    void testCreateOne2() {
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
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course2);
        courseTeacher.setCourseId("42");
        courseTeacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseTeacher.setIsMain(true);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherId("42");
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenReturn(courseTeacher);
        when(userRepository.findById(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("abcdefghijklmnopqrstuvwxyz"));

        // Act
        CourseResponse actualCreateOneResult = courseImpl.createOne(
                new CreateCourseRequest("Course Name", "Semester", "The characteristics of someone or something", "42"));

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
        assertNull(actualCreateOneResult);
    }

    /**
     * Method under test: {@link CourseImpl#createOne(CreateCourseRequest)}
     */
    @Test
    void testCreateOne3() {
        // Arrange
        Course course = mock(Course.class);
        when(course.getCourseId()).thenReturn("42");
        when(course.getCourseName()).thenReturn("Course Name");
        when(course.getDescription()).thenReturn("The characteristics of someone or something");
        when(course.getEnrollKey()).thenReturn("Enroll Key");
        when(course.getSemester()).thenReturn("Semester");
        when(course.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(course.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(course).setCourseId(Mockito.<String>any());
        doNothing().when(course).setCourseName(Mockito.<String>any());
        doNothing().when(course).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(course).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(course).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(course).setDescription(Mockito.<String>any());
        doNothing().when(course).setEnrollKey(Mockito.<String>any());
        doNothing().when(course).setGroups(Mockito.<List<Group>>any());
        doNothing().when(course).setSemester(Mockito.<String>any());
        doNothing().when(course).setTopics(Mockito.<List<Topic>>any());
        doNothing().when(course).setUpdatedDate(Mockito.<LocalDateTime>any());
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
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course2);
        courseTeacher.setCourseId("42");
        courseTeacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseTeacher.setIsMain(true);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherId("42");
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenReturn(courseTeacher);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CourseResponse actualCreateOneResult = courseImpl.createOne(
                new CreateCourseRequest("Course Name", "Semester", "The characteristics of someone or something", "42"));

        // Assert
        verify(course, atLeast(1)).getCourseId();
        verify(course).getCourseName();
        verify(course).getCreatedDate();
        verify(course).getDescription();
        verify(course).getEnrollKey();
        verify(course).getSemester();
        verify(course).getUpdatedDate();
        verify(course).setCourseId(eq("42"));
        verify(course).setCourseName(eq("Course Name"));
        verify(course).setCourseStudents(isA(List.class));
        verify(course).setCourseTeachers(isA(List.class));
        verify(course).setCreatedDate(isA(LocalDateTime.class));
        verify(course).setDescription(eq("The characteristics of someone or something"));
        verify(course).setEnrollKey(eq("Enroll Key"));
        verify(course).setGroups(isA(List.class));
        verify(course).setSemester(eq("Semester"));
        verify(course).setTopics(isA(List.class));
        verify(course).setUpdatedDate(isA(LocalDateTime.class));
        verify(userRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
        LocalTime expectedToLocalTimeResult = actualCreateOneResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualCreateOneResult.getTeacher().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#getById(String)}
     */
    @Test
    void testGetById() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.getTeacherInCourse(Mockito.<String>any())).thenReturn(user);
        when(userRepository.getUserInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        CourseResponse actualById = courseImpl.getById("42");

        // Assert
        verify(userRepository).getTeacherInCourse(eq("42"));
        verify(userRepository).getUserInCourse(eq("42"));
        verify(courseRepository).findById(eq("42"));
        LocalTime expectedToLocalTimeResult = actualById.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualById.getTeacher().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#getById(String)}
     */
    @Test
    void testGetById2() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(userRepository.getUserInCourse(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> courseImpl.getById("42"));
        verify(userRepository).getUserInCourse(eq("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        // Arrange
        Course course = mock(Course.class);
        when(course.getCourseId()).thenReturn("42");
        when(course.getCourseName()).thenReturn("Course Name");
        when(course.getDescription()).thenReturn("The characteristics of someone or something");
        when(course.getEnrollKey()).thenReturn("Enroll Key");
        when(course.getSemester()).thenReturn("Semester");
        when(course.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(course.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(course).setCourseId(Mockito.<String>any());
        doNothing().when(course).setCourseName(Mockito.<String>any());
        doNothing().when(course).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(course).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(course).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(course).setDescription(Mockito.<String>any());
        doNothing().when(course).setEnrollKey(Mockito.<String>any());
        doNothing().when(course).setGroups(Mockito.<List<Group>>any());
        doNothing().when(course).setSemester(Mockito.<String>any());
        doNothing().when(course).setTopics(Mockito.<List<Topic>>any());
        doNothing().when(course).setUpdatedDate(Mockito.<LocalDateTime>any());
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.getTeacherInCourse(Mockito.<String>any())).thenReturn(user);
        when(userRepository.getUserInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        CourseResponse actualById = courseImpl.getById("42");

        // Assert
        verify(course).getCourseId();
        verify(course).getCourseName();
        verify(course).getCreatedDate();
        verify(course).getDescription();
        verify(course).getEnrollKey();
        verify(course).getSemester();
        verify(course).getUpdatedDate();
        verify(course).setCourseId(eq("42"));
        verify(course).setCourseName(eq("Course Name"));
        verify(course).setCourseStudents(isA(List.class));
        verify(course).setCourseTeachers(isA(List.class));
        verify(course).setCreatedDate(isA(LocalDateTime.class));
        verify(course).setDescription(eq("The characteristics of someone or something"));
        verify(course).setEnrollKey(eq("Enroll Key"));
        verify(course).setGroups(isA(List.class));
        verify(course).setSemester(eq("Semester"));
        verify(course).setTopics(isA(List.class));
        verify(course).setUpdatedDate(isA(LocalDateTime.class));
        verify(userRepository).getTeacherInCourse(eq("42"));
        verify(userRepository).getUserInCourse(eq("42"));
        verify(courseRepository).findById(eq("42"));
        LocalTime expectedToLocalTimeResult = actualById.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualById.getTeacher().getUpdatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#getById(String)}
     */
    @Test
    void testGetById4() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getById("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        when(courseRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CourseResponse> actualAll = courseImpl.getAll();

        // Assert
        verify(courseRepository).findAll();
        assertTrue(actualAll.isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#getAll()}
     */
    @Test
    void testGetAll2() {
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

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseRepository.findAll()).thenReturn(courseList);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.getTeacherInCourse(Mockito.<String>any())).thenReturn(user);
        when(userRepository.getUserInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<CourseResponse> actualAll = courseImpl.getAll();

        // Assert
        verify(userRepository).getTeacherInCourse(eq("42"));
        verify(userRepository).getUserInCourse(eq("42"));
        verify(courseRepository).findAll();
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link CourseImpl#getAll()}
     */
    @Test
    void testGetAll3() {
        // Arrange
        Course course = mock(Course.class);
        when(course.getCourseId()).thenReturn("42");
        when(course.getCourseName()).thenReturn("Course Name");
        when(course.getDescription()).thenReturn("The characteristics of someone or something");
        when(course.getEnrollKey()).thenReturn("Enroll Key");
        when(course.getSemester()).thenReturn("Semester");
        when(course.getCreatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(course.getUpdatedDate()).thenReturn(LocalDate.of(1970, 1, 1).atStartOfDay());
        doNothing().when(course).setCourseId(Mockito.<String>any());
        doNothing().when(course).setCourseName(Mockito.<String>any());
        doNothing().when(course).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(course).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(course).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(course).setDescription(Mockito.<String>any());
        doNothing().when(course).setEnrollKey(Mockito.<String>any());
        doNothing().when(course).setGroups(Mockito.<List<Group>>any());
        doNothing().when(course).setSemester(Mockito.<String>any());
        doNothing().when(course).setTopics(Mockito.<List<Topic>>any());
        doNothing().when(course).setUpdatedDate(Mockito.<LocalDateTime>any());
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

        ArrayList<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseRepository.findAll()).thenReturn(courseList);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.getTeacherInCourse(Mockito.<String>any())).thenReturn(user);
        when(userRepository.getUserInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<CourseResponse> actualAll = courseImpl.getAll();

        // Assert
        verify(course, atLeast(1)).getCourseId();
        verify(course).getCourseName();
        verify(course).getCreatedDate();
        verify(course).getDescription();
        verify(course).getEnrollKey();
        verify(course).getSemester();
        verify(course).getUpdatedDate();
        verify(course).setCourseId(eq("42"));
        verify(course).setCourseName(eq("Course Name"));
        verify(course).setCourseStudents(isA(List.class));
        verify(course).setCourseTeachers(isA(List.class));
        verify(course).setCreatedDate(isA(LocalDateTime.class));
        verify(course).setDescription(eq("The characteristics of someone or something"));
        verify(course).setEnrollKey(eq("Enroll Key"));
        verify(course).setGroups(isA(List.class));
        verify(course).setSemester(eq("Semester"));
        verify(course).setTopics(isA(List.class));
        verify(course).setUpdatedDate(isA(LocalDateTime.class));
        verify(userRepository).getTeacherInCourse(eq("42"));
        verify(userRepository).getUserInCourse(eq("42"));
        verify(courseRepository).findAll();
        assertEquals(1, actualAll.size());
    }

    /**
     * Method under test: {@link CourseImpl#deleteById(String)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(courseRepository).deleteById(Mockito.<String>any());
        when(courseRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act
        courseImpl.deleteById("42");

        // Assert that nothing has changed
        verify(courseRepository).deleteById(eq("42"));
        verify(courseRepository).existsById(eq("42"));
        assertTrue(courseImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#deleteById(String)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        doThrow(new NoSuchElementException("foo")).when(courseRepository).deleteById(Mockito.<String>any());
        when(courseRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.deleteById("42"));
        verify(courseRepository).deleteById(eq("42"));
        verify(courseRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#deleteById(String)}
     */
    @Test
    void testDeleteById3() {
        // Arrange
        when(courseRepository.existsById(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.deleteById("42"));
        verify(courseRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#updateById(String, UpdateCourseRequest)}
     */
    @Test
    void testUpdateById() {
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
        Optional<Course> ofResult = Optional.of(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Course course3 = new Course();
        course3.setCourseId("42");
        course3.setCourseName("Course Name");
        course3.setCourseStudents(new ArrayList<>());
        course3.setCourseTeachers(new ArrayList<>());
        course3.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course3.setDescription("The characteristics of someone or something");
        course3.setEnrollKey("Enroll Key");
        course3.setGroups(new ArrayList<>());
        course3.setSemester("Semester");
        course3.setTopics(new ArrayList<>());
        course3.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course3);
        courseTeacher.setCourseId("42");
        courseTeacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseTeacher.setIsMain(true);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherId("42");
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenReturn(courseTeacher);
        doNothing().when(courseTeacherRepository).deleteTeacherInCourseByCourseId(Mockito.<String>any());

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        Course actualUpdateByIdResult = courseImpl.updateById("42", new UpdateCourseRequest("42", "Course Name", "Semester",
                "Enroll Key", "The characteristics of someone or something", "42"));

        // Assert
        verify(courseTeacherRepository).deleteTeacherInCourseByCourseId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
        LocalTime expectedToLocalTimeResult = actualUpdateByIdResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateByIdResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#updateById(String, UpdateCourseRequest)}
     */
    @Test
    void testUpdateById2() {
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
        Optional<Course> ofResult = Optional.of(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenThrow(new NoSuchElementException("foo"));
        doNothing().when(courseTeacherRepository).deleteTeacherInCourseByCourseId(Mockito.<String>any());

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.updateById("42", new UpdateCourseRequest("42",
                "Course Name", "Semester", "Enroll Key", "The characteristics of someone or something", "42")));
        verify(courseTeacherRepository).deleteTeacherInCourseByCourseId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
    }

    /**
     * Method under test: {@link CourseImpl#updateById(String, UpdateCourseRequest)}
     */
    @Test
    void testUpdateById3() {
        // Arrange
        Course course = mock(Course.class);
        doNothing().when(course).setCourseId(Mockito.<String>any());
        doNothing().when(course).setCourseName(Mockito.<String>any());
        doNothing().when(course).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(course).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(course).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(course).setDescription(Mockito.<String>any());
        doNothing().when(course).setEnrollKey(Mockito.<String>any());
        doNothing().when(course).setGroups(Mockito.<List<Group>>any());
        doNothing().when(course).setSemester(Mockito.<String>any());
        doNothing().when(course).setTopics(Mockito.<List<Topic>>any());
        doNothing().when(course).setUpdatedDate(Mockito.<LocalDateTime>any());
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
        Optional<Course> ofResult = Optional.of(course);

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        when(courseRepository.save(Mockito.<Course>any())).thenReturn(course2);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Course course3 = new Course();
        course3.setCourseId("42");
        course3.setCourseName("Course Name");
        course3.setCourseStudents(new ArrayList<>());
        course3.setCourseTeachers(new ArrayList<>());
        course3.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course3.setDescription("The characteristics of someone or something");
        course3.setEnrollKey("Enroll Key");
        course3.setGroups(new ArrayList<>());
        course3.setSemester("Semester");
        course3.setTopics(new ArrayList<>());
        course3.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseTeacher courseTeacher = new CourseTeacher();
        courseTeacher.setCourse(course3);
        courseTeacher.setCourseId("42");
        courseTeacher.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseTeacher.setIsMain(true);
        courseTeacher.setTeacher(teacher);
        courseTeacher.setTeacherId("42");
        when(courseTeacherRepository.save(Mockito.<CourseTeacher>any())).thenReturn(courseTeacher);
        doNothing().when(courseTeacherRepository).deleteTeacherInCourseByCourseId(Mockito.<String>any());

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        Course actualUpdateByIdResult = courseImpl.updateById("42", new UpdateCourseRequest("42", "Course Name", "Semester",
                "Enroll Key", "The characteristics of someone or something", "42"));

        // Assert
        verify(course).setCourseId(eq("42"));
        verify(course, atLeast(1)).setCourseName(eq("Course Name"));
        verify(course).setCourseStudents(isA(List.class));
        verify(course).setCourseTeachers(isA(List.class));
        verify(course).setCreatedDate(isA(LocalDateTime.class));
        verify(course, atLeast(1)).setDescription(eq("The characteristics of someone or something"));
        verify(course, atLeast(1)).setEnrollKey(eq("Enroll Key"));
        verify(course).setGroups(isA(List.class));
        verify(course, atLeast(1)).setSemester(eq("Semester"));
        verify(course).setTopics(isA(List.class));
        verify(course).setUpdatedDate(isA(LocalDateTime.class));
        verify(courseTeacherRepository).deleteTeacherInCourseByCourseId(eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(courseRepository).save(isA(Course.class));
        verify(courseTeacherRepository).save(isA(CourseTeacher.class));
        LocalTime expectedToLocalTimeResult = actualUpdateByIdResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateByIdResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link CourseImpl#updateById(String, UpdateCourseRequest)}
     */
    @Test
    void testUpdateById4() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.updateById("42", new UpdateCourseRequest("42",
                "Course Name", "Semester", "Enroll Key", "The characteristics of someone or something", "42")));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#importByExcel(MultipartFile)}
     */
    @Test
    void testImportByExcel() throws IOException {
        // Arrange and Act
        ResponseEntity<Map<String, String>> actualImportByExcelResult = courseImpl
                .importByExcel(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        assertEquals(2, actualImportByExcelResult.getBody().size());
        assertEquals(200, actualImportByExcelResult.getStatusCodeValue());
        assertTrue(actualImportByExcelResult.hasBody());
        assertTrue(actualImportByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#importByExcel(MultipartFile)}
     */
    @Test
    void testImportByExcel2() throws IOException {
        // Arrange and Act
        ResponseEntity<Map<String, String>> actualImportByExcelResult = courseImpl
                .importByExcel(new MockMultipartFile("message", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        assertEquals(2, actualImportByExcelResult.getBody().size());
        assertEquals(200, actualImportByExcelResult.getStatusCodeValue());
        assertTrue(actualImportByExcelResult.hasBody());
        assertTrue(actualImportByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#importByExcel(MultipartFile)}
     */
    @Test
    void testImportByExcel3() throws IOException {
        // Arrange
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();

        // Act
        ResponseEntity<Map<String, String>> actualImportByExcelResult = courseImpl
                .importByExcel(new MockMultipartFile("Name", contentStream));

        // Assert
        verify(contentStream).close();
        verify(contentStream).readAllBytes();
        assertEquals(2, actualImportByExcelResult.getBody().size());
        assertEquals(200, actualImportByExcelResult.getStatusCodeValue());
        assertTrue(actualImportByExcelResult.hasBody());
        assertTrue(actualImportByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#importByExcel(MultipartFile)}
     */
    @Test
    void testImportByExcel4() throws IOException {
        // Arrange
        MockMultipartFile file = mock(MockMultipartFile.class);
        when(file.getInputStream()).thenReturn(new FileInputStream(new FileDescriptor()));

        // Act
        ResponseEntity<Map<String, String>> actualImportByExcelResult = courseImpl.importByExcel(file);

        // Assert
        verify(file).getInputStream();
        assertEquals(2, actualImportByExcelResult.getBody().size());
        assertEquals(200, actualImportByExcelResult.getStatusCodeValue());
        assertTrue(actualImportByExcelResult.hasBody());
        assertTrue(actualImportByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#importByExcel(MultipartFile)}
     */
    @Test
    void testImportByExcel5() throws IOException {
        // Arrange
        MockMultipartFile file = mock(MockMultipartFile.class);
        when(file.getInputStream()).thenThrow(new IOException("foo"));

        // Act
        ResponseEntity<Map<String, String>> actualImportByExcelResult = courseImpl.importByExcel(file);

        // Assert
        verify(file).getInputStream();
        assertEquals(1, actualImportByExcelResult.getBody().size());
        assertEquals(500, actualImportByExcelResult.getStatusCodeValue());
        assertTrue(actualImportByExcelResult.hasBody());
        assertTrue(actualImportByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseImpl#checkCourseExistById(String)}
     */
    @Test
    void testCheckCourseExistById() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Boolean actualCheckCourseExistByIdResult = courseImpl.checkCourseExistById("42");

        // Assert
        verify(courseRepository).findById(eq("42"));
        assertTrue(actualCheckCourseExistByIdResult);
    }

    /**
     * Method under test: {@link CourseImpl#checkCourseExistById(String)}
     */
    @Test
    void testCheckCourseExistById2() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act
        Boolean actualCheckCourseExistByIdResult = courseImpl.checkCourseExistById("42");

        // Assert
        verify(courseRepository).findById(eq("42"));
        assertFalse(actualCheckCourseExistByIdResult);
    }

    /**
     * Method under test: {@link CourseImpl#checkCourseExistById(String)}
     */
    @Test
    void testCheckCourseExistById3() {
        // Arrange
        when(courseRepository.findById(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.checkCourseExistById("42"));
        verify(courseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CourseImpl#enrollStudentToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollStudentToCourse() {
        // Arrange
        when(courseStudentService.checkStudentInCourse(Mockito.<String>any(), Mockito.<String>any())).thenReturn(true);

        // Act
        CourseEnrollmentResponse<CourseTeacherResponse> actualEnrollStudentToCourseResult = courseImpl
                .enrollStudentToCourse(new CourseEnrollmentRequest("42", "42", "Enrollment Key"));

        // Assert
        verify(courseStudentService).checkStudentInCourse(eq("42"), eq("42"));
        assertEquals("Can not enroll in course", actualEnrollStudentToCourseResult.getError());
        assertEquals("User already enrolled in course", actualEnrollStudentToCourseResult.getMessage());
        assertEquals(409, actualEnrollStudentToCourseResult.getStatus());
    }

    /**
     * Method under test:
     * {@link CourseImpl#enrollStudentToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollStudentToCourse2() {
        // Arrange
        when(courseStudentService.checkStudentInCourse(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("Enroll Key"));

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> courseImpl.enrollStudentToCourse(new CourseEnrollmentRequest("42", "42", "Enrollment Key")));
        verify(courseStudentService).checkStudentInCourse(eq("42"), eq("42"));
    }

    /**
     * Method under test:
     * {@link CourseImpl#enrollStudentToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollStudentToCourse3() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(courseStudentService.checkStudentInCourse(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);

        // Act
        CourseEnrollmentResponse<CourseTeacherResponse> actualEnrollStudentToCourseResult = courseImpl
                .enrollStudentToCourse(new CourseEnrollmentRequest("42", "42", "Enrollment Key"));

        // Assert
        verify(courseStudentService).checkStudentInCourse(eq("42"), eq("42"));
        verify(courseRepository).findById(eq("42"));
        assertEquals("Invalid enrollment key", actualEnrollStudentToCourseResult.getError());
        assertEquals("Your key not match, please try again", actualEnrollStudentToCourseResult.getMessage());
        assertEquals(400, actualEnrollStudentToCourseResult.getStatus());
    }

    /**
     * Method under test:
     * {@link CourseImpl#enrollStudentToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollStudentToCourse4() {
        // Arrange
        Course course = mock(Course.class);
        when(course.getEnrollKey()).thenReturn("Enroll Key");
        doNothing().when(course).setCourseId(Mockito.<String>any());
        doNothing().when(course).setCourseName(Mockito.<String>any());
        doNothing().when(course).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(course).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(course).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(course).setDescription(Mockito.<String>any());
        doNothing().when(course).setEnrollKey(Mockito.<String>any());
        doNothing().when(course).setGroups(Mockito.<List<Group>>any());
        doNothing().when(course).setSemester(Mockito.<String>any());
        doNothing().when(course).setTopics(Mockito.<List<Topic>>any());
        doNothing().when(course).setUpdatedDate(Mockito.<LocalDateTime>any());
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(courseStudentService.checkStudentInCourse(Mockito.<String>any(), Mockito.<String>any())).thenReturn(false);

        // Act
        CourseEnrollmentResponse<CourseTeacherResponse> actualEnrollStudentToCourseResult = courseImpl
                .enrollStudentToCourse(new CourseEnrollmentRequest("42", "42", "Enrollment Key"));

        // Assert
        verify(course).getEnrollKey();
        verify(course).setCourseId(eq("42"));
        verify(course).setCourseName(eq("Course Name"));
        verify(course).setCourseStudents(isA(List.class));
        verify(course).setCourseTeachers(isA(List.class));
        verify(course).setCreatedDate(isA(LocalDateTime.class));
        verify(course).setDescription(eq("The characteristics of someone or something"));
        verify(course).setEnrollKey(eq("Enroll Key"));
        verify(course).setGroups(isA(List.class));
        verify(course).setSemester(eq("Semester"));
        verify(course).setTopics(isA(List.class));
        verify(course).setUpdatedDate(isA(LocalDateTime.class));
        verify(courseStudentService).checkStudentInCourse(eq("42"), eq("42"));
        verify(courseRepository).findById(eq("42"));
        assertEquals("Invalid enrollment key", actualEnrollStudentToCourseResult.getError());
        assertEquals("Your key not match, please try again", actualEnrollStudentToCourseResult.getMessage());
        assertEquals(400, actualEnrollStudentToCourseResult.getStatus());
    }

    /**
     * Method under test: {@link CourseImpl#unEnrollUserInCourse(String, String)}
     */
    @Test
    void testUnEnrollUserInCourse() {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        course.setCourseStudents(courseStudents);
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(courseTeacherRepository)
                .deleteByStudentIdAndCourseId(Mockito.<String>any(), Mockito.<String>any());

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        courseImpl.unEnrollUserInCourse("42", "42");

        // Assert that nothing has changed
        verify(courseTeacherRepository).deleteByStudentIdAndCourseId(eq("42"), eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        assertEquals(courseStudents, courseImpl.getAll());
    }

    /**
     * Method under test: {@link CourseImpl#unEnrollUserInCourse(String, String)}
     */
    @Test
    void testUnEnrollUserInCourse2() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doThrow(new NoSuchElementException("student")).when(courseTeacherRepository)
                .deleteByStudentIdAndCourseId(Mockito.<String>any(), Mockito.<String>any());

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> courseImpl.unEnrollUserInCourse("42", "42"));
        verify(courseTeacherRepository).deleteByStudentIdAndCourseId(eq("42"), eq("42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#unEnrollUserInCourse(String, String)}
     */
    @Test
    void testUnEnrollUserInCourse3() {
        // Arrange
        Optional<Course> emptyResult = Optional.empty();
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.unEnrollUserInCourse("42", "42"));
        verify(courseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#unEnrollUserInCourse(String, String)}
     */
    @Test
    void testUnEnrollUserInCourse4() {
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
        Optional<Course> ofResult = Optional.of(course);
        when(courseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.unEnrollUserInCourse("42", "42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByUserId(String)}
     */
    @Test
    void testGetCourseByUserId() {
        // Arrange
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.getCourseByTeacherId(Mockito.<String>any())).thenReturn(courseList);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<Course> actualCourseByUserId = courseImpl.getCourseByUserId("42");

        // Assert
        verify(courseRepository).getCourseByTeacherId(eq("42"));
        verify(userRepository).findById(eq("42"));
        assertTrue(actualCourseByUserId.isEmpty());
        assertSame(courseList, actualCourseByUserId);
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByUserId(String)}
     */
    @Test
    void testGetCourseByUserId2() {
        // Arrange
        when(courseRepository.getCourseByTeacherId(Mockito.<String>any())).thenThrow(new NoSuchElementException("student"));

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByUserId("42"));
        verify(courseRepository).getCourseByTeacherId(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByUserId(String)}
     */
    @Test
    void testGetCourseByUserId3() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByUserId("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByStudentId(String)}
     */
    @Test
    void testGetCourseByStudentId() {
        // Arrange
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.getCourseByStudentId(Mockito.<String>any())).thenReturn(courseList);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<Course> actualCourseByStudentId = courseImpl.getCourseByStudentId("42");

        // Assert
        verify(courseRepository).getCourseByStudentId(eq("42"));
        verify(userRepository).findById(eq("42"));
        assertTrue(actualCourseByStudentId.isEmpty());
        assertSame(courseList, actualCourseByStudentId);
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByStudentId(String)}
     */
    @Test
    void testGetCourseByStudentId2() {
        // Arrange
        when(courseRepository.getCourseByStudentId(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByStudentId("42"));
        verify(courseRepository).getCourseByStudentId(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByStudentId(String)}
     */
    @Test
    void testGetCourseByStudentId3() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByStudentId("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByTeacherId(String)}
     */
    @Test
    void testGetCourseByTeacherId() {
        // Arrange
        ArrayList<Course> courseList = new ArrayList<>();
        when(courseRepository.getCourseByTeacherId(Mockito.<String>any())).thenReturn(courseList);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<Course> actualCourseByTeacherId = courseImpl.getCourseByTeacherId("42");

        // Assert
        verify(courseRepository).getCourseByTeacherId(eq("42"));
        verify(userRepository).findById(eq("42"));
        assertTrue(actualCourseByTeacherId.isEmpty());
        assertSame(courseList, actualCourseByTeacherId);
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByTeacherId(String)}
     */
    @Test
    void testGetCourseByTeacherId2() {
        // Arrange
        when(courseRepository.getCourseByTeacherId(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByTeacherId("42"));
        verify(courseRepository).getCourseByTeacherId(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getCourseByTeacherId(String)}
     */
    @Test
    void testGetCourseByTeacherId3() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getCourseByTeacherId("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CourseImpl#getStudentsByCourseId(String)}
     */
    @Test
    void testGetStudentsByCourseId() {
        // Arrange
        when(courseStudentRepository.findByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findByUserIdInAndRole(Mockito.<List<String>>any(), Mockito.<String>any())).thenReturn(userList);

        // Act
        List<User> actualStudentsByCourseId = courseImpl.getStudentsByCourseId("42");

        // Assert
        verify(courseStudentRepository).findByCourseId(eq("42"));
        verify(userRepository).findByUserIdInAndRole(isA(List.class), eq("student"));
        assertTrue(actualStudentsByCourseId.isEmpty());
        assertSame(userList, actualStudentsByCourseId);
    }

    /**
     * Method under test: {@link CourseImpl#getStudentsByCourseId(String)}
     */
    @Test
    void testGetStudentsByCourseId2() {
        // Arrange
        when(courseStudentRepository.findByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(userRepository.findByUserIdInAndRole(Mockito.<List<String>>any(), Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("student"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> courseImpl.getStudentsByCourseId("42"));
        verify(courseStudentRepository).findByCourseId(eq("42"));
        verify(userRepository).findByUserIdInAndRole(isA(List.class), eq("student"));
    }
}
