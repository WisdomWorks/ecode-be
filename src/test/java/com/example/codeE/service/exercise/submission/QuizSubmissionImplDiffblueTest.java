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

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.repository.QuizAnswersRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import com.example.codeE.repository.QuizSubmissionRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.quiz.QuizSubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {QuizSubmissionImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QuizSubmissionImplDiffblueTest {
    @MockBean
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    @MockBean
    private QuizAnswersRepository quizAnswersRepository;

    @MockBean
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizSubmissionImpl quizSubmissionImpl;

    @MockBean
    private QuizSubmissionRepository quizSubmissionRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#createSubmission(QuizSubmission)}
     */
    @Test
    void testCreateSubmission() {
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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        when(quizSubmissionRepository.save(Mockito.<QuizSubmission>any())).thenReturn(quizSubmission);

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

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020-03-01");
        quizSubmission2.setDateSubmit("2020-03-01");
        quizSubmission2.setExerciseId("42");
        quizSubmission2.setReviewable(true);
        quizSubmission2.setScore(10.0f);
        quizSubmission2.setStudentId("42");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("42");
        quizSubmission2.setTeacherComment("Teacher Comment");

        // Act
        QuizSubmission actualCreateSubmissionResult = quizSubmissionImpl.createSubmission(quizSubmission2);

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(quizSubmissionRepository).save(isA(QuizSubmission.class));
        assertSame(quizSubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#createSubmission(QuizSubmission)}
     */
    @Test
    void testCreateSubmission2() {
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
        when(quizSubmissionRepository.save(Mockito.<QuizSubmission>any())).thenThrow(new NoSuchElementException("foo"));

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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.createSubmission(quizSubmission));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(quizSubmissionRepository).save(isA(QuizSubmission.class));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#createSubmission(QuizSubmission)}
     */
    @Test
    void testCreateSubmission3() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.createSubmission(quizSubmission));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#createSubmission(QuizSubmission)}
     */
    @Test
    void testCreateSubmission4() {
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
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.createSubmission(quizSubmission));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#createSubmission(QuizSubmission)}
     */
    @Test
    void testCreateSubmission5() {
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

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");
        when(quizAnswersRepository.save(Mockito.<QuizAnswers>any())).thenReturn(quizAnswers);

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        when(quizSubmissionRepository.save(Mockito.<QuizSubmission>any())).thenReturn(quizSubmission);

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

        QuizAnswers quizAnswers2 = new QuizAnswers();
        quizAnswers2.setAnswers(new ArrayList<>());
        quizAnswers2.setQuestionId("42");
        quizAnswers2.setQuizAnswerId("42");

        ArrayList<QuizAnswers> submission = new ArrayList<>();
        submission.add(quizAnswers2);

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020-03-01");
        quizSubmission2.setDateSubmit("2020-03-01");
        quizSubmission2.setExerciseId("42");
        quizSubmission2.setReviewable(true);
        quizSubmission2.setScore(10.0f);
        quizSubmission2.setStudentId("42");
        quizSubmission2.setSubmission(submission);
        quizSubmission2.setSubmissionId("42");
        quizSubmission2.setTeacherComment("Teacher Comment");

        // Act
        QuizSubmission actualCreateSubmissionResult = quizSubmissionImpl.createSubmission(quizSubmission2);

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(quizSubmissionRepository).save(isA(QuizSubmission.class));
        verify(quizAnswersRepository).save(isA(QuizAnswers.class));
        assertEquals("42", quizSubmission2.getSubmission().get(0).getQuizAnswerId());
        assertSame(quizSubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionByExerciseId() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<QuizSubmission> actualQuizSubmissionByExerciseId = quizSubmissionImpl.getQuizSubmissionByExerciseId("42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertTrue(actualQuizSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionByExerciseId2() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        List<QuizSubmission> actualQuizSubmissionByExerciseId = quizSubmissionImpl.getQuizSubmissionByExerciseId("42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertEquals(1, actualQuizSubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionByExerciseId3() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020/03/01");
        quizSubmission2.setDateSubmit("2020/03/01");
        quizSubmission2.setExerciseId("Exercise Id");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(0.5f);
        quizSubmission2.setStudentId("Student Id");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("Submission Id");
        quizSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission2);
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        List<QuizSubmission> actualQuizSubmissionByExerciseId = quizSubmissionImpl.getQuizSubmissionByExerciseId("42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertEquals(1, actualQuizSubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionByExerciseId4() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getQuizSubmissionByExerciseId("42"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId() {
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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        AllSubmissionResponse<SubmissionDetail> actualQuizSubmissionsByExerciseId = quizSubmissionImpl
                .getQuizSubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(quizSubmissionRepository).findAll();
        assertEquals("42", actualQuizSubmissionsByExerciseId.getExerciseId());
        assertNull(actualQuizSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualQuizSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualQuizSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("foo"));

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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getQuizSubmissionsByExerciseId("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId3() {
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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        AllSubmissionResponse<SubmissionDetail> actualQuizSubmissionsByExerciseId = quizSubmissionImpl
                .getQuizSubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(quizSubmissionRepository, atLeast(1)).findAll();
        assertEquals("42", actualQuizSubmissionsByExerciseId.getExerciseId());
        assertNull(actualQuizSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualQuizSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(courseStudents, actualQuizSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId4() {
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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        AllSubmissionResponse<SubmissionDetail> actualQuizSubmissionsByExerciseId = quizSubmissionImpl
                .getQuizSubmissionsByExerciseId("42");

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
        verify(quizSubmissionRepository).findAll();
        assertEquals("42", actualQuizSubmissionsByExerciseId.getExerciseId());
        OverviewScoreReport report = actualQuizSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualQuizSubmissionsByExerciseId.getGroups());
        assertEquals(courseStudentList, actualQuizSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId5() {
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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        AllSubmissionResponse<SubmissionDetail> actualQuizSubmissionsByExerciseId = quizSubmissionImpl
                .getQuizSubmissionsByExerciseId("42");

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
        verify(quizSubmissionRepository).findAll();
        assertEquals("42", actualQuizSubmissionsByExerciseId.getExerciseId());
        OverviewScoreReport report = actualQuizSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, actualQuizSubmissionsByExerciseId.getGroups().size());
        assertEquals(expectedSubmissions, actualQuizSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId6() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        new NoSuchElementException("foo");

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
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getQuizSubmissionsByExerciseId("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetQuizSubmissionsByExerciseId7() {
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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

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
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenThrow(new NoSuchElementException("42"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getQuizSubmissionsByExerciseId("42"));
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
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission() {
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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        Optional<QuizSubmission> ofResult2 = Optional.of(quizSubmission);
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act
        QuizSubmissionsResponse actualStudentQuizSubmission = quizSubmissionImpl.getStudentQuizSubmission("42");

        // Assert
        verify(userRepository).findUserByUserId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(quizSubmissionRepository).findById(eq("42"));
        User student = actualStudentQuizSubmission.getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission2() {
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

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        Optional<QuizSubmission> ofResult2 = Optional.of(quizSubmission);
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getStudentQuizSubmission("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(quizSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission3() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        Optional<QuizSubmission> ofResult = Optional.of(quizSubmission);
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getStudentQuizSubmission("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(quizSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission4() {
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
        Optional<QuizSubmission> emptyResult = Optional.empty();
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getStudentQuizSubmission("42"));
        verify(quizSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetQuizSubmissionByUserId() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<QuizSubmission> actualQuizSubmissionByUserId = quizSubmissionImpl.getQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertTrue(actualQuizSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetQuizSubmissionByUserId2() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        List<QuizSubmission> actualQuizSubmissionByUserId = quizSubmissionImpl.getQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertEquals(1, actualQuizSubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetQuizSubmissionByUserId3() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020/03/01");
        quizSubmission2.setDateSubmit("2020/03/01");
        quizSubmission2.setExerciseId("Exercise Id");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(0.5f);
        quizSubmission2.setStudentId("Student Id");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("Submission Id");
        quizSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission2);
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        List<QuizSubmission> actualQuizSubmissionByUserId = quizSubmissionImpl.getQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertEquals(1, actualQuizSubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetQuizSubmissionByUserId4() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getQuizSubmissionByUserId("42", "42"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetQuizSubmissionByUserId5() {
        // Arrange
        QuizSubmission quizSubmission = mock(QuizSubmission.class);
        when(quizSubmission.getStudentId()).thenReturn("foo");
        when(quizSubmission.getExerciseId()).thenReturn("42");
        doNothing().when(quizSubmission).setSubmission(Mockito.<List<QuizAnswers>>any());
        doNothing().when(quizSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(quizSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(quizSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(quizSubmission).setReviewable(anyBoolean());
        doNothing().when(quizSubmission).setScore(Mockito.<Float>any());
        doNothing().when(quizSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(quizSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(quizSubmission).setTeacherComment(Mockito.<String>any());
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        List<QuizSubmission> actualQuizSubmissionByUserId = quizSubmissionImpl.getQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmission).setSubmission(isA(List.class));
        verify(quizSubmission).getExerciseId();
        verify(quizSubmission).getStudentId();
        verify(quizSubmission).setDateGrade(eq("2020-03-01"));
        verify(quizSubmission).setDateSubmit(eq("2020-03-01"));
        verify(quizSubmission).setExerciseId(eq("42"));
        verify(quizSubmission).setReviewable(eq(true));
        verify(quizSubmission).setScore(eq(10.0f));
        verify(quizSubmission).setStudentId(eq("42"));
        verify(quizSubmission).setSubmissionId(eq("42"));
        verify(quizSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(quizSubmissionRepository).findAll();
        assertTrue(actualQuizSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission() {
        // Arrange
        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();

        // Act and Assert
        assertEquals(Float.NaN, quizSubmissionImpl.gradeSubmission(quizSubmission, new ArrayList<>()));
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission2() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers);

        // Act
        float actualGradeSubmissionResult = quizSubmissionImpl.gradeSubmission(quizSubmission, new ArrayList<>());

        // Assert
        verify(quizQuestionRepository).findById(eq("42"));
        assertTrue(quizSubmission.get(0).getAnswers().isEmpty());
        assertEquals(Float.POSITIVE_INFINITY, actualGradeSubmissionResult);
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission3() {
        // Arrange
        QuizChoice quizChoice = new QuizChoice();
        quizChoice.setChoiceId("42");
        quizChoice.setContent("Not all who wander are lost");

        ArrayList<QuizChoice> answers = new ArrayList<>();
        answers.add(quizChoice);

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(answers);
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers);

        // Act
        float actualGradeSubmissionResult = quizSubmissionImpl.gradeSubmission(quizSubmission, new ArrayList<>());

        // Assert
        verify(quizQuestionRepository).findById(eq("42"));
        assertTrue(quizSubmission.get(0).getAnswers().isEmpty());
        assertEquals(Float.NaN, actualGradeSubmissionResult);
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission4() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        QuizAnswers quizAnswers2 = new QuizAnswers();
        quizAnswers2.setAnswers(new ArrayList<>());
        quizAnswers2.setQuestionId("Question Id");
        quizAnswers2.setQuizAnswerId("Quiz Answer Id");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers2);
        quizSubmission.add(quizAnswers);

        // Act
        float actualGradeSubmissionResult = quizSubmissionImpl.gradeSubmission(quizSubmission, new ArrayList<>());

        // Assert
        verify(quizQuestionRepository, atLeast(1)).findById(Mockito.<String>any());
        assertTrue(quizSubmission.get(0).getAnswers().isEmpty());
        assertTrue(quizSubmission.get(1).getAnswers().isEmpty());
        assertEquals(Float.POSITIVE_INFINITY, actualGradeSubmissionResult);
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission5() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers);

        QuizQuestion quizQuestion2 = new QuizQuestion();
        quizQuestion2.setAnswers(new ArrayList<>());
        quizQuestion2.setChoices(new ArrayList<>());
        quizQuestion2.setDescription("The characteristics of someone or something");
        quizQuestion2.setQuestionId("42");
        quizQuestion2.setTitle("Dr");

        ArrayList<QuizQuestion> quizExercise = new ArrayList<>();
        quizExercise.add(quizQuestion2);

        // Act
        float actualGradeSubmissionResult = quizSubmissionImpl.gradeSubmission(quizSubmission, quizExercise);

        // Assert
        verify(quizQuestionRepository).findById(eq("42"));
        assertEquals(10.0f, actualGradeSubmissionResult);
        assertTrue(quizSubmission.get(0).getAnswers().isEmpty());
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission6() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers);

        QuizQuestion quizQuestion2 = new QuizQuestion();
        quizQuestion2.setAnswers(new ArrayList<>());
        quizQuestion2.setChoices(new ArrayList<>());
        quizQuestion2.setDescription("The characteristics of someone or something");
        quizQuestion2.setQuestionId("42");
        quizQuestion2.setTitle("Dr");

        QuizQuestion quizQuestion3 = new QuizQuestion();
        quizQuestion3.setAnswers(new ArrayList<>());
        quizQuestion3.setChoices(new ArrayList<>());
        quizQuestion3.setDescription("Description");
        quizQuestion3.setQuestionId("Question Id");
        quizQuestion3.setTitle("Mr");

        ArrayList<QuizQuestion> quizExercise = new ArrayList<>();
        quizExercise.add(quizQuestion3);
        quizExercise.add(quizQuestion2);

        // Act
        float actualGradeSubmissionResult = quizSubmissionImpl.gradeSubmission(quizSubmission, quizExercise);

        // Assert
        verify(quizQuestionRepository).findById(eq("42"));
        assertEquals(5.0f, actualGradeSubmissionResult);
        assertTrue(quizSubmission.get(0).getAnswers().isEmpty());
    }

    /**
     * Method under test: {@link QuizSubmissionImpl#gradeSubmission(List, List)}
     */
    @Test
    void testGradeSubmission7() {
        // Arrange
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        QuizAnswers quizAnswers = new QuizAnswers();
        quizAnswers.setAnswers(new ArrayList<>());
        quizAnswers.setQuestionId("42");
        quizAnswers.setQuizAnswerId("42");

        ArrayList<QuizAnswers> quizSubmission = new ArrayList<>();
        quizSubmission.add(quizAnswers);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> quizSubmissionImpl.gradeSubmission(quizSubmission, new ArrayList<>()));
        verify(quizQuestionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        QuizSubmission actualLastQuizSubmissionByUserId = quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertNull(actualLastQuizSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId2() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        QuizSubmission actualLastQuizSubmissionByUserId = quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertSame(quizSubmission, actualLastQuizSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId3() {
        // Arrange
        QuizSubmission quizSubmission = new QuizSubmission();
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020/03/01");
        quizSubmission2.setDateSubmit("2020/03/01");
        quizSubmission2.setExerciseId("Exercise Id");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(0.5f);
        quizSubmission2.setStudentId("Student Id");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("Submission Id");
        quizSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission2);
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        QuizSubmission actualLastQuizSubmissionByUserId = quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmissionRepository).findAll();
        assertSame(quizSubmission, actualLastQuizSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId4() {
        // Arrange
        when(quizSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId5() {
        // Arrange
        QuizSubmission quizSubmission = mock(QuizSubmission.class);
        when(quizSubmission.getDateSubmit()).thenReturn("2020-03-01");
        when(quizSubmission.getStudentId()).thenReturn("42");
        when(quizSubmission.getExerciseId()).thenReturn("42");
        doNothing().when(quizSubmission).setSubmission(Mockito.<List<QuizAnswers>>any());
        doNothing().when(quizSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(quizSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(quizSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(quizSubmission).setReviewable(anyBoolean());
        doNothing().when(quizSubmission).setScore(Mockito.<Float>any());
        doNothing().when(quizSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(quizSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(quizSubmission).setTeacherComment(Mockito.<String>any());
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020-03-01");
        quizSubmission2.setDateSubmit("2020-03-01");
        quizSubmission2.setExerciseId("42");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(10.0f);
        quizSubmission2.setStudentId("42");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("42");
        quizSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission2);
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmission).setSubmission(isA(List.class));
        verify(quizSubmission).getDateSubmit();
        verify(quizSubmission).getExerciseId();
        verify(quizSubmission).getStudentId();
        verify(quizSubmission).setDateGrade(eq("2020-03-01"));
        verify(quizSubmission).setDateSubmit(eq("2020-03-01"));
        verify(quizSubmission).setExerciseId(eq("42"));
        verify(quizSubmission).setReviewable(eq(true));
        verify(quizSubmission).setScore(eq(10.0f));
        verify(quizSubmission).setStudentId(eq("42"));
        verify(quizSubmission).setSubmissionId(eq("42"));
        verify(quizSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getLastQuizSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastQuizSubmissionByUserId6() {
        // Arrange
        QuizSubmission quizSubmission = mock(QuizSubmission.class);
        when(quizSubmission.getStudentId()).thenReturn("42");
        when(quizSubmission.getExerciseId()).thenReturn("42");
        doNothing().when(quizSubmission).setSubmission(Mockito.<List<QuizAnswers>>any());
        doNothing().when(quizSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(quizSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(quizSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(quizSubmission).setReviewable(anyBoolean());
        doNothing().when(quizSubmission).setScore(Mockito.<Float>any());
        doNothing().when(quizSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(quizSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(quizSubmission).setTeacherComment(Mockito.<String>any());
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");
        quizSubmission.setTeacherComment("Teacher Comment");
        QuizSubmission quizSubmission2 = mock(QuizSubmission.class);
        when(quizSubmission2.getStudentId()).thenReturn("foo");
        when(quizSubmission2.getExerciseId()).thenReturn("42");
        doNothing().when(quizSubmission2).setSubmission(Mockito.<List<QuizAnswers>>any());
        doNothing().when(quizSubmission2).setDateGrade(Mockito.<String>any());
        doNothing().when(quizSubmission2).setDateSubmit(Mockito.<String>any());
        doNothing().when(quizSubmission2).setExerciseId(Mockito.<String>any());
        doNothing().when(quizSubmission2).setReviewable(anyBoolean());
        doNothing().when(quizSubmission2).setScore(Mockito.<Float>any());
        doNothing().when(quizSubmission2).setStudentId(Mockito.<String>any());
        doNothing().when(quizSubmission2).setSubmissionId(Mockito.<String>any());
        doNothing().when(quizSubmission2).setTeacherComment(Mockito.<String>any());
        quizSubmission2.setDateGrade("2020-03-01");
        quizSubmission2.setDateSubmit("2020-03-01");
        quizSubmission2.setExerciseId("42");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(10.0f);
        quizSubmission2.setStudentId("42");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("42");
        quizSubmission2.setTeacherComment("Teacher Comment");

        ArrayList<QuizSubmission> quizSubmissionList = new ArrayList<>();
        quizSubmissionList.add(quizSubmission2);
        quizSubmissionList.add(quizSubmission);
        when(quizSubmissionRepository.findAll()).thenReturn(quizSubmissionList);

        // Act
        quizSubmissionImpl.getLastQuizSubmissionByUserId("42", "42");

        // Assert
        verify(quizSubmission2).setSubmission(isA(List.class));
        verify(quizSubmission).setSubmission(isA(List.class));
        verify(quizSubmission2).getExerciseId();
        verify(quizSubmission).getExerciseId();
        verify(quizSubmission2).getStudentId();
        verify(quizSubmission).getStudentId();
        verify(quizSubmission2).setDateGrade(eq("2020-03-01"));
        verify(quizSubmission).setDateGrade(eq("2020-03-01"));
        verify(quizSubmission2).setDateSubmit(eq("2020-03-01"));
        verify(quizSubmission).setDateSubmit(eq("2020-03-01"));
        verify(quizSubmission2).setExerciseId(eq("42"));
        verify(quizSubmission).setExerciseId(eq("42"));
        verify(quizSubmission2).setReviewable(eq(false));
        verify(quizSubmission).setReviewable(eq(true));
        verify(quizSubmission2).setScore(eq(10.0f));
        verify(quizSubmission).setScore(eq(10.0f));
        verify(quizSubmission2).setStudentId(eq("42"));
        verify(quizSubmission).setStudentId(eq("42"));
        verify(quizSubmission2).setSubmissionId(eq("42"));
        verify(quizSubmission).setSubmissionId(eq("42"));
        verify(quizSubmission2).setTeacherComment(eq("Teacher Comment"));
        verify(quizSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(quizSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = quizSubmissionImpl
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
     * {@link QuizSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("foo"));

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
        assertThrows(NoSuchElementException.class,
                () -> quizSubmissionImpl.getOverviewScoreReportByExerciseId("42", new ArrayList<>()));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
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
        when(quizSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = quizSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(quizSubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }
}
