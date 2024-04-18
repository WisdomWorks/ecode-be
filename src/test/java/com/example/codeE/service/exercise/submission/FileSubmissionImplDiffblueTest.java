package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.FileSubmissionRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.response.FileSubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;

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
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @Autowired
    private FileSubmissionImpl fileSubmissionImpl;

    @MockBean
    private FileSubmissionRepository fileSubmissionRepository;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42");

        // Act
        FileSubmission actualCreateSubmissionResult = fileSubmissionImpl.createSubmission(createRequest,
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        verify(cloudStorageHelper).uploadFile(isA(MultipartFile.class), eq(true), eq("file-submissions/42/42/"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).save(isA(FileSubmission.class));
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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42");

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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42");

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
        CreateFileSubmissionRequest createRequest = new CreateFileSubmissionRequest("42", "42");

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
        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        // Act
        AllSubmissionResponse<SubmissionDetail> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals("42", actualFileSubmissionsByExerciseId.getExerciseId());
        assertNull(actualFileSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualFileSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualFileSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));

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
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getFileSubmissionsByExerciseId("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId3() {
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

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        Topic topic = new Topic();
        topic.setCourse(course2);
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

        // Act
        AllSubmissionResponse<SubmissionDetail> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(fileSubmissionRepository, atLeast(1)).findAll();
        assertEquals("42", actualFileSubmissionsByExerciseId.getExerciseId());
        assertNull(actualFileSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualFileSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(courseStudents, actualFileSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId4() {
        // Arrange
        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.isShowAll()).thenReturn(false);
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(codeExercise).setKey(Mockito.<String>any());
        doNothing().when(codeExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setReAttempt(anyInt());
        doNothing().when(codeExercise).setShowAll(anyBoolean());
        doNothing().when(codeExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setTopicId(Mockito.<String>any());
        doNothing().when(codeExercise).setType(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        // Act
        AllSubmissionResponse<SubmissionDetail> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(codeExercise).getExerciseId();
        verify(codeExercise, atLeast(1)).getPublicGroupIds();
        verify(codeExercise, atLeast(1)).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseDescription(eq("Exercise Description"));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise).setPublicGroupIds(isA(List.class));
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals("42", actualFileSubmissionsByExerciseId.getExerciseId());
        OverviewScoreReport report = actualFileSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualFileSubmissionsByExerciseId.getGroups());
        assertEquals(courseStudentList, actualFileSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId5() {
        // Arrange
        ArrayList<CourseStudent> expectedSubmissions = new ArrayList<>();

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.isShowAll()).thenReturn(false);
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getPublicGroupIds()).thenReturn(stringList);
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(codeExercise).setKey(Mockito.<String>any());
        doNothing().when(codeExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setReAttempt(anyInt());
        doNothing().when(codeExercise).setShowAll(anyBoolean());
        doNothing().when(codeExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setTopicId(Mockito.<String>any());
        doNothing().when(codeExercise).setType(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        when(groupService.getById(Mockito.<String>any())).thenReturn(group);

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

        Topic topic = new Topic();
        topic.setCourse(course2);
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

        // Act
        AllSubmissionResponse<SubmissionDetail> actualFileSubmissionsByExerciseId = fileSubmissionImpl
                .getFileSubmissionsByExerciseId("42");

        // Assert
        verify(codeExercise).getExerciseId();
        verify(codeExercise, atLeast(1)).getPublicGroupIds();
        verify(codeExercise, atLeast(1)).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseDescription(eq("Exercise Description"));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise).setPublicGroupIds(isA(List.class));
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(groupService).getById(eq("foo"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals("42", actualFileSubmissionsByExerciseId.getExerciseId());
        OverviewScoreReport report = actualFileSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, actualFileSubmissionsByExerciseId.getGroups().size());
        assertEquals(expectedSubmissions, actualFileSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId6() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        new IllegalArgumentException("foo");

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

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.getFileSubmissionsByExerciseId("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionsByExerciseId7() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(codeExercise).setKey(Mockito.<String>any());
        doNothing().when(codeExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setReAttempt(anyInt());
        doNothing().when(codeExercise).setShowAll(anyBoolean());
        doNothing().when(codeExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setTopicId(Mockito.<String>any());
        doNothing().when(codeExercise).setType(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);
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

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

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
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenThrow(new IllegalArgumentException("42"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getFileSubmissionsByExerciseId("42"));
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseDescription(eq("Exercise Description"));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise).setPublicGroupIds(isA(List.class));
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
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
        verify(fileSubmission).setScore(eq(10.0f));
        verify(fileSubmission).setStudentId(eq("42"));
        verify(fileSubmission).setSubmissionId(eq("42"));
        verify(fileSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(fileSubmissionRepository).findAll();
        assertTrue(actualFileSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        FileSubmission actualLastFileSubmissionByUserId = fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertNull(actualLastFileSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId2() {
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
        FileSubmission actualLastFileSubmissionByUserId = fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertSame(fileSubmission, actualLastFileSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId3() {
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
        FileSubmission actualLastFileSubmissionByUserId = fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertSame(fileSubmission, actualLastFileSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId4() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId5() {
        // Arrange
        FileSubmission fileSubmission = mock(FileSubmission.class);
        when(fileSubmission.getDateSubmit()).thenReturn("2020-03-01");
        when(fileSubmission.getStudentId()).thenReturn("42");
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

        FileSubmission fileSubmission2 = new FileSubmission();
        fileSubmission2.setDateGrade("2020-03-01");
        fileSubmission2.setDateSubmit("2020-03-01");
        fileSubmission2.setExerciseId("42");
        fileSubmission2.setFileUrl("https://example.org/example");
        fileSubmission2.setReviewable(false);
        fileSubmission2.setScore(10.0f);
        fileSubmission2.setStudentId("42");
        fileSubmission2.setSubmissionId("42");
        fileSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission2);
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

        // Act
        fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmission).setFileUrl(eq("https://example.org/example"));
        verify(fileSubmission).getDateSubmit();
        verify(fileSubmission).getExerciseId();
        verify(fileSubmission).getStudentId();
        verify(fileSubmission).setDateGrade(eq("2020-03-01"));
        verify(fileSubmission).setDateSubmit(eq("2020-03-01"));
        verify(fileSubmission).setExerciseId(eq("42"));
        verify(fileSubmission).setReviewable(eq(true));
        verify(fileSubmission).setScore(eq(10.0f));
        verify(fileSubmission).setStudentId(eq("42"));
        verify(fileSubmission).setSubmissionId(eq("42"));
        verify(fileSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getLastFileSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastFileSubmissionByUserId6() {
        // Arrange
        FileSubmission fileSubmission = mock(FileSubmission.class);
        when(fileSubmission.getStudentId()).thenReturn("42");
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
        FileSubmission fileSubmission2 = mock(FileSubmission.class);
        when(fileSubmission2.getStudentId()).thenReturn("foo");
        when(fileSubmission2.getExerciseId()).thenReturn("42");
        doNothing().when(fileSubmission2).setFileUrl(Mockito.<String>any());
        doNothing().when(fileSubmission2).setDateGrade(Mockito.<String>any());
        doNothing().when(fileSubmission2).setDateSubmit(Mockito.<String>any());
        doNothing().when(fileSubmission2).setExerciseId(Mockito.<String>any());
        doNothing().when(fileSubmission2).setReviewable(anyBoolean());
        doNothing().when(fileSubmission2).setScore(Mockito.<Float>any());
        doNothing().when(fileSubmission2).setStudentId(Mockito.<String>any());
        doNothing().when(fileSubmission2).setSubmissionId(Mockito.<String>any());
        doNothing().when(fileSubmission2).setTeacherComment(Mockito.<String>any());
        fileSubmission2.setDateGrade("2020-03-01");
        fileSubmission2.setDateSubmit("2020-03-01");
        fileSubmission2.setExerciseId("42");
        fileSubmission2.setFileUrl("https://example.org/example");
        fileSubmission2.setReviewable(false);
        fileSubmission2.setScore(10.0f);
        fileSubmission2.setStudentId("42");
        fileSubmission2.setSubmissionId("42");
        fileSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<FileSubmission> fileSubmissionList = new ArrayList<>();
        fileSubmissionList.add(fileSubmission2);
        fileSubmissionList.add(fileSubmission);
        when(fileSubmissionRepository.findAll()).thenReturn(fileSubmissionList);

        // Act
        fileSubmissionImpl.getLastFileSubmissionByUserId("42", "42");

        // Assert
        verify(fileSubmission2).setFileUrl(eq("https://example.org/example"));
        verify(fileSubmission).setFileUrl(eq("https://example.org/example"));
        verify(fileSubmission2).getExerciseId();
        verify(fileSubmission).getExerciseId();
        verify(fileSubmission2).getStudentId();
        verify(fileSubmission).getStudentId();
        verify(fileSubmission2).setDateGrade(eq("2020-03-01"));
        verify(fileSubmission).setDateGrade(eq("2020-03-01"));
        verify(fileSubmission2).setDateSubmit(eq("2020-03-01"));
        verify(fileSubmission).setDateSubmit(eq("2020-03-01"));
        verify(fileSubmission2).setExerciseId(eq("42"));
        verify(fileSubmission).setExerciseId(eq("42"));
        verify(fileSubmission2).setReviewable(eq(false));
        verify(fileSubmission).setReviewable(eq(true));
        verify(fileSubmission2).setScore(eq(10.0f));
        verify(fileSubmission).setScore(eq(10.0f));
        verify(fileSubmission2).setStudentId(eq("42"));
        verify(fileSubmission).setStudentId(eq("42"));
        verify(fileSubmission2).setSubmissionId(eq("42"));
        verify(fileSubmission).setSubmissionId(eq("42"));
        verify(fileSubmission2).setTeacherComment(eq("Teacher Comment"));
        verify(fileSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission() {
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
        Optional<FileSubmission> ofResult = Optional.of(fileSubmission);

        FileSubmission fileSubmission2 = new FileSubmission();
        fileSubmission2.setDateGrade("2020-03-01");
        fileSubmission2.setDateSubmit("2020-03-01");
        fileSubmission2.setExerciseId("42");
        fileSubmission2.setFileUrl("https://example.org/example");
        fileSubmission2.setReviewable(true);
        fileSubmission2.setScore(10.0f);
        fileSubmission2.setStudentId("42");
        fileSubmission2.setSubmissionId("42");
        fileSubmission2.setTeacherComment("Teacher Comment");
        when(fileSubmissionRepository.save(Mockito.<FileSubmission>any())).thenReturn(fileSubmission2);
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        FileSubmission actualGradeSubmissionResult = fileSubmissionImpl.gradeSubmission("42", 10.0f, "Comment");

        // Assert
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(fileSubmissionRepository).save(isA(FileSubmission.class));
        assertEquals("Comment", actualGradeSubmissionResult.getTeacherComment());
        assertEquals(10.0f, actualGradeSubmissionResult.getScore().floatValue());
        assertSame(fileSubmission, actualGradeSubmissionResult);
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission2() {
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
        Optional<FileSubmission> ofResult = Optional.of(fileSubmission);
        when(fileSubmissionRepository.save(Mockito.<FileSubmission>any())).thenThrow(new IllegalArgumentException("foo"));
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.gradeSubmission("42", 10.0f, "Comment"));
        verify(fileSubmissionRepository).findById(eq("42"));
        verify(fileSubmissionRepository).save(isA(FileSubmission.class));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission3() {
        // Arrange
        Optional<FileSubmission> emptyResult = Optional.empty();
        when(fileSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> fileSubmissionImpl.gradeSubmission("42", 10.0f, "Comment"));
        verify(fileSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission4() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.gradeSubmission("42", -1.0f, "Comment"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionByExerciseId() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<FileSubmission> actualFileSubmissionByExerciseId = fileSubmissionImpl.getFileSubmissionByExerciseId("42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertTrue(actualFileSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionByExerciseId2() {
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
        List<FileSubmission> actualFileSubmissionByExerciseId = fileSubmissionImpl.getFileSubmissionByExerciseId("42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionByExerciseId3() {
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
        List<FileSubmission> actualFileSubmissionByExerciseId = fileSubmissionImpl.getFileSubmissionByExerciseId("42");

        // Assert
        verify(fileSubmissionRepository).findAll();
        assertEquals(1, actualFileSubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getFileSubmissionByExerciseId(String)}
     */
    @Test
    void testGetFileSubmissionByExerciseId4() {
        // Arrange
        when(fileSubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> fileSubmissionImpl.getFileSubmissionByExerciseId("42"));
        verify(fileSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

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

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = fileSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberStudent());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));

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

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> fileSubmissionImpl.getOverviewScoreReportByExerciseId("42", new ArrayList<>()));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId3() {
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

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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
        when(fileSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        Topic topic = new Topic();
        topic.setCourse(course2);
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

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = fileSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(fileSubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }
}
