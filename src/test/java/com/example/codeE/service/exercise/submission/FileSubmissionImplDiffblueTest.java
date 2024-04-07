package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.FileSubmissionRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.response.FileSubmissionsResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {FileSubmissionImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class FileSubmissionImplDiffblueTest {
    @MockBean
    private CloudStorageHelper cloudStorageHelper;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @Autowired
    private FileSubmissionImpl fileSubmissionImpl;

    @MockBean
    private FileSubmissionRepository fileSubmissionRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link FileSubmissionImpl#createSubmission(CreateFileSubmissionRequest, MultipartFile)}
     */
    @Test
    void testCreateSubmission() throws Exception {
        // Arrange
        when(cloudStorageHelper.uploadFile(Mockito.<MultipartFile>any(), anyBoolean(), Mockito.<String>any()))
                .thenReturn("Upload File");

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");
        when(fileSubmissionRepository.save(Mockito.<FileSubmission>any())).thenReturn(fileSubmission);

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

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42",
                "https://example.org/example");

        // Act
        FileSubmission actualCreateSubmissionResult = fileSubmissionImpl.createSubmission(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("file-submissions/42/42/"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).save(isA(FileSubmission.class));
        assertEquals("Upload File", createRequest.getUrl());
        assertSame(fileSubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#createSubmission(CreateFileSubmissionRequest, MultipartFile)}
     */
    @Test
    void testCreateSubmission2() throws Exception {
        // Arrange
        when(cloudStorageHelper.uploadFile(Mockito.<MultipartFile>any(), anyBoolean(), Mockito.<String>any()))
                .thenReturn("Upload File");

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(fileSubmissionRepository.save(Mockito.<FileSubmission>any())).thenThrow(new IllegalArgumentException("foo"));

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

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42",
                "https://example.org/example");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.createSubmission(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("file-submissions/42/42/"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).save(isA(FileSubmission.class));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#createSubmission(CreateFileSubmissionRequest, MultipartFile)}
     */
    @Test
    void testCreateSubmission3() throws IOException {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42",
                "https://example.org/example");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.createSubmission(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#createSubmission(CreateFileSubmissionRequest, MultipartFile)}
     */
    @Test
    void testCreateSubmission4() throws IOException {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42",
                "https://example.org/example");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.createSubmission(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")))));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<FileSubmissionsResponse> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertTrue(actualFileSubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId2() {
        // Arrange
        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

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
        List<FileSubmissionsResponse> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionsByExerciseId.size());
        User student = actualFileSubmissionsByExerciseId.get(0).getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId3() {
        // Arrange
        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");

        FileSubmission fileSubmission2 = new FileSubmission();
        fileSubmission2.setDateGrade("2020/03/01");
        fileSubmission2.setDateSubmit("2020/03/01");
        fileSubmission2.setExerciseId("Exercise Id");
        fileSubmission2.setFileUrl("42");
        fileSubmission2.setReviewable(false);
        fileSubmission2.setScore(0.5f);
        fileSubmission2.setStudentId("Student Id");
        fileSubmission2.setSubmissionId("Submission Id");
        fileSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission2);
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

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
        List<FileSubmissionsResponse> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionsByExerciseId.size());
        User student = actualFileSubmissionsByExerciseId.get(0).getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link FileSubmissionImpl#getFileSubmissionById(String)}
     */
    @Test
    void testGetFileSubmissionById() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");
        Optional<FileSubmission> ofResult2 = Optional.of(fileSubmission);
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);

        // Act
        FileSubmissionsResponse actualFileSubmissionById = fileSubmissionImpl.getFileSubmissionById("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        User student = actualFileSubmissionById.getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link FileSubmissionImpl#getFileSubmissionById(String)}
     */
    @Test
    void testGetFileSubmissionById2() {
        // Arrange
        when(exerciseRepository.findById(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");
        Optional<FileSubmission> ofResult = Optional.of(fileSubmission);
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getFileSubmissionById("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link FileSubmissionImpl#getFileSubmissionById(String)}
     */
    @Test
    void testGetFileSubmissionById3() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");
        Optional<FileSubmission> ofResult = Optional.of(fileSubmission);
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.getFileSubmissionById("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link FileSubmissionImpl#getFileSubmissionById(String)}
     */
    @Test
    void testGetFileSubmissionById4() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Optional<FileSubmission> emptyResult = Optional.empty();
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.getFileSubmissionById("42"));
        verify(fileSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link FileSubmissionImpl#getFileSubmissionById(String)}
     */
    @Test
    void testGetFileSubmissionById5() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");
        Optional<FileSubmission> ofResult2 = Optional.of(fileSubmission);
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.getFileSubmissionById("42"));
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetFileSubmissionByUserId() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<FileSubmission> actualFileSubmissionByUserId = fileSubmissionImpl.getFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertTrue(actualFileSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetFileSubmissionByUserId2() {
        // Arrange
        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

        // Act
        List<FileSubmission> actualFileSubmissionByUserId = fileSubmissionImpl.getFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetFileSubmissionByUserId3() {
        // Arrange
        FileSubmission fileSubmission = new FileSubmission();
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");

        FileSubmission fileSubmission2 = new FileSubmission();
        fileSubmission2.setDateGrade("2020/03/01");
        fileSubmission2.setDateSubmit("2020/03/01");
        fileSubmission2.setExerciseId("Exercise Id");
        fileSubmission2.setFileUrl("42");
        fileSubmission2.setReviewable(false);
        fileSubmission2.setScore(0.5f);
        fileSubmission2.setStudentId("Student Id");
        fileSubmission2.setSubmissionId("Submission Id");
        fileSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission2);
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

        // Act
        List<FileSubmission> actualFileSubmissionByUserId = fileSubmissionImpl.getFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetFileSubmissionByUserId4() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getFileSubmissionByUserId("42", "42"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetFileSubmissionByUserId5() {
        // Arrange
        FileSubmission fileSubmission = mock(FileSubmission.class);
        when(fileSubmission.getStudentId()).thenReturn("foo");
        when(fileSubmission.getExerciseId()).thenReturn("42");
        doNothing().when(fileSubmission).setFileUrl(Mockito.<String>any());
        doNothing().when(fileSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(fileSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(fileSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(fileSubmission).setReviewable(anyBoolean());
        doNothing().when(fileSubmission).setScore(Mockito.<Float>any());
        doNothing().when(fileSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(fileSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(fileSubmission).setTeacherComment(Mockito.<String>any());
        fileSubmission.setDateGrade("2020-03-01");
        fileSubmission.setDateSubmit("2020-03-01");
        fileSubmission.setExerciseId("42");
        fileSubmission.setFileUrl("https://example.org/example");
        fileSubmission.setReviewable(true);
        fileSubmission.setScore(10.0f);
        fileSubmission.setStudentId("42");
        fileSubmission.setSubmissionId("42");
        fileSubmission.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

        // Act
        List<FileSubmission> actualFileSubmissionByUserId = fileSubmissionImpl.getFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmission).setFileUrl(eq("https://example.org/example"));
        verify(fileSubmission).getExerciseId();
        verify(fileSubmission).getStudentId();
        verify(fileSubmission).setDateGrade(eq("2020-03-01"));
        verify(fileSubmission).setDateSubmit(eq("2020-03-01"));
        verify(fileSubmission).setExerciseId(eq("42"));
        verify(fileSubmission).setReviewable(eq(true));
        verify(fileSubmission).setScore(isA(Float.class));
        verify(fileSubmission).setStudentId(eq("42"));
        verify(fileSubmission).setSubmissionId(eq("42"));
        verify(fileSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(fileSubmissionRepository).findAll();
        assertTrue(actualFileSubmissionByUserId.isEmpty());
    }
}
