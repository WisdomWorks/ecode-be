package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.QuizAnswersRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import com.example.codeE.repository.QuizSubmissionRepository;
import com.example.codeE.repository.UserRepository;

import java.time.LocalDate;
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
    private ExerciseRepository exerciseRepository;

    @MockBean
    private QuizAnswersRepository quizAnswersRepository;

    @MockBean
    private QuizQuestionRepository quizQuestionRepository;

    @Autowired
    private QuizSubmissionImpl quizSubmissionImpl;

    @MockBean
    private QuizSubmissionRepository quizSubmissionRepository;

    @MockBean
    private UserRepository userRepository;

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

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020/03/01");
        quizSubmission2.setDateSubmit("2020/03/01");
        quizSubmission2.setExerciseId("Exercise Id");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(0.5f);
        quizSubmission2.setStudentId("Student Id");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("Submission Id");

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
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission() {
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
        Optional<QuizSubmission> ofResult = Optional.of(quizSubmission);
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizSubmission actualStudentQuizSubmission = quizSubmissionImpl.getStudentQuizSubmission("42");

        // Assert
        verify(quizSubmissionRepository).findById(eq("42"));
        assertSame(quizSubmission, actualStudentQuizSubmission);
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission2() {
        // Arrange
        Optional<QuizSubmission> emptyResult = Optional.empty();
        when(quizSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizSubmissionImpl.getStudentQuizSubmission("42"));
        verify(quizSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizSubmissionImpl#getStudentQuizSubmission(String)}
     */
    @Test
    void testGetStudentQuizSubmission3() {
        // Arrange
        when(quizSubmissionRepository.findById(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("No Submission found"));

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

        QuizSubmission quizSubmission2 = new QuizSubmission();
        quizSubmission2.setDateGrade("2020/03/01");
        quizSubmission2.setDateSubmit("2020/03/01");
        quizSubmission2.setExerciseId("Exercise Id");
        quizSubmission2.setReviewable(false);
        quizSubmission2.setScore(0.5f);
        quizSubmission2.setStudentId("Student Id");
        quizSubmission2.setSubmission(new ArrayList<>());
        quizSubmission2.setSubmissionId("Submission Id");

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
        quizSubmission.setDateGrade("2020-03-01");
        quizSubmission.setDateSubmit("2020-03-01");
        quizSubmission.setExerciseId("42");
        quizSubmission.setReviewable(true);
        quizSubmission.setScore(10.0f);
        quizSubmission.setStudentId("42");
        quizSubmission.setSubmission(new ArrayList<>());
        quizSubmission.setSubmissionId("42");

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
        verify(quizSubmission).setScore(isA(Float.class));
        verify(quizSubmission).setStudentId(eq("42"));
        verify(quizSubmission).setSubmissionId(eq("42"));
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
}
