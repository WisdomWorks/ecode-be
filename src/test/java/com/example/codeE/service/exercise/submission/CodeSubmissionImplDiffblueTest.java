package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.code.CodeSubmissionsResponse;
import com.example.codeE.service.judge.JudgeImpl;
import com.example.codeE.service.judge.JudgeService;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

@ContextConfiguration(classes = {CodeSubmissionImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CodeSubmissionImplDiffblueTest {
    @Autowired
    private CodeSubmissionImpl codeSubmissionImpl;

    @MockBean
    private CodeSubmissionRepository codeSubmissionRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#checkStatusAndUpdate(CodeSubmission)}
     */
    @Test
    void testCheckStatusAndUpdate() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020-03-01");
        codeSubmission2.setDateSubmit("2020-03-01");
        codeSubmission2.setError("An error occurred");
        codeSubmission2.setExerciseId("42");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("Judged On");
        codeSubmission2.setLanguageId("en");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(true);
        codeSubmission2.setResult("Result");
        codeSubmission2.setReviewable(true);
        codeSubmission2.setScore(10.0f);
        codeSubmission2.setSource("Source");
        codeSubmission2.setStatus("Status");
        codeSubmission2.setStudentId("42");
        codeSubmission2.setSubmissionId("42");
        codeSubmission2.setTeacherComment("Teacher Comment");
        codeSubmission2.setTime(10.0d);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenReturn(codeSubmission2);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(10.0d);
        codeSubmission3.setCaseTotal(10.0d);
        codeSubmission3.setCurrentTestcase(1);
        codeSubmission3.setDateGrade("2020-03-01");
        codeSubmission3.setDateSubmit("2020-03-01");
        codeSubmission3.setError("An error occurred");
        codeSubmission3.setExerciseId("42");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("en");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(1);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(10.0f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("42");
        codeSubmission3.setSubmissionId("42");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(10.0d);

        // Act
        CodeSubmission actualCheckStatusAndUpdateResult = codeSubmissionImpl.checkStatusAndUpdate(codeSubmission3);

        // Assert
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission2, actualCheckStatusAndUpdateResult);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#checkStatusAndUpdate(CodeSubmission)}
     */
    @Test
    void testCheckStatusAndUpdate2() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new NoSuchElementException("P"));
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020-03-01");
        codeSubmission2.setDateSubmit("2020-03-01");
        codeSubmission2.setError("An error occurred");
        codeSubmission2.setExerciseId("42");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("Judged On");
        codeSubmission2.setLanguageId("en");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(true);
        codeSubmission2.setResult("Result");
        codeSubmission2.setReviewable(true);
        codeSubmission2.setScore(10.0f);
        codeSubmission2.setSource("Source");
        codeSubmission2.setStatus("Status");
        codeSubmission2.setStudentId("42");
        codeSubmission2.setSubmissionId("42");
        codeSubmission2.setTeacherComment("Teacher Comment");
        codeSubmission2.setTime(10.0d);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.checkStatusAndUpdate(codeSubmission2));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#updateStatusAndResult(String, String, String)}
     */
    @Test
    void testUpdateStatusAndResult() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020-03-01");
        codeSubmission2.setDateSubmit("2020-03-01");
        codeSubmission2.setError("An error occurred");
        codeSubmission2.setExerciseId("42");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("Judged On");
        codeSubmission2.setLanguageId("en");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(true);
        codeSubmission2.setResult("Result");
        codeSubmission2.setReviewable(true);
        codeSubmission2.setScore(10.0f);
        codeSubmission2.setSource("Source");
        codeSubmission2.setStatus("Status");
        codeSubmission2.setStudentId("42");
        codeSubmission2.setSubmissionId("42");
        codeSubmission2.setTeacherComment("Teacher Comment");
        codeSubmission2.setTime(10.0d);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenReturn(codeSubmission2);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        codeSubmissionImpl.updateStatusAndResult("42", "Status", "Result");

        // Assert
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#updateStatusAndResult(String, String, String)}
     */
    @Test
    void testUpdateStatusAndResult2() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new NoSuchElementException("AB"));
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> codeSubmissionImpl.updateStatusAndResult("42", "Status", "Result"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#updateStatusAndResultBySubmissionIdAndStatus(String, String, String, String)}
     */
    @Test
    void testUpdateStatusAndResultBySubmissionIdAndStatus() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        codeSubmissionImpl.updateStatusAndResultBySubmissionIdAndStatus("42", "Searched Status", "Status", "Result");

        // Assert that nothing has changed
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CodeSubmissionImpl#getCodeSubmissionById(String)}
     */
    @Test
    void testGetCodeSubmissionById() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeSubmission actualCodeSubmissionById = codeSubmissionImpl.getCodeSubmissionById("42");

        // Assert
        verify(codeSubmissionRepository).findById(eq("42"));
        assertSame(codeSubmission, actualCodeSubmissionById);
    }

    /**
     * Method under test: {@link CodeSubmissionImpl#getCodeSubmissionById(String)}
     */
    @Test
    void testGetCodeSubmissionById2() {
        // Arrange
        Optional<CodeSubmission> emptyResult = Optional.empty();
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionById("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CodeSubmissionImpl#getCodeSubmissionById(String)}
     */
    @Test
    void testGetCodeSubmissionById3() {
        // Arrange
        when(codeSubmissionRepository.findById(Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("No Submission found"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionById("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionResponseById(String)}
     */
    @Test
    void testGetCodeSubmissionResponseById() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Exercise> ofResult2 = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        CodeSubmissionsResponse actualCodeSubmissionResponseById = codeSubmissionImpl.getCodeSubmissionResponseById("42");

        // Assert
        verify(userRepository).findUserByUserId(eq("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        User student = actualCodeSubmissionResponseById.getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionResponseById(String)}
     */
    @Test
    void testGetCodeSubmissionResponseById2() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Exercise> ofResult2 = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionResponseById("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionResponseById(String)}
     */
    @Test
    void testGetCodeSubmissionResponseById3() {
        // Arrange
        Optional<CodeSubmission> emptyResult = Optional.empty();
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionResponseById("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionResponseById(String)}
     */
    @Test
    void testGetCodeSubmissionResponseById4() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        Optional<CodeSubmission> ofResult = Optional.of(codeSubmission);
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionResponseById("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#updateCodeSubmission(CodeSubmission)}
     */
    @Test
    void testUpdateCodeSubmission() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenReturn(codeSubmission);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020-03-01");
        codeSubmission2.setDateSubmit("2020-03-01");
        codeSubmission2.setError("An error occurred");
        codeSubmission2.setExerciseId("42");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("Judged On");
        codeSubmission2.setLanguageId("en");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(true);
        codeSubmission2.setResult("Result");
        codeSubmission2.setReviewable(true);
        codeSubmission2.setScore(10.0f);
        codeSubmission2.setSource("Source");
        codeSubmission2.setStatus("Status");
        codeSubmission2.setStudentId("42");
        codeSubmission2.setSubmissionId("42");
        codeSubmission2.setTeacherComment("Teacher Comment");
        codeSubmission2.setTime(10.0d);

        // Act
        CodeSubmission actualUpdateCodeSubmissionResult = codeSubmissionImpl.updateCodeSubmission(codeSubmission2);

        // Assert
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission, actualUpdateCodeSubmissionResult);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#updateCodeSubmission(CodeSubmission)}
     */
    @Test
    void testUpdateCodeSubmission2() {
        // Arrange
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new NoSuchElementException("foo"));

        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.updateCodeSubmission(codeSubmission));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#saveCodeSubmission(CodeSubmission)}
     */
    @Test
    void testSaveCodeSubmission() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenReturn(codeSubmission);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020-03-01");
        codeSubmission2.setDateSubmit("2020-03-01");
        codeSubmission2.setError("An error occurred");
        codeSubmission2.setExerciseId("42");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("Judged On");
        codeSubmission2.setLanguageId("en");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(true);
        codeSubmission2.setResult("Result");
        codeSubmission2.setReviewable(true);
        codeSubmission2.setScore(10.0f);
        codeSubmission2.setSource("Source");
        codeSubmission2.setStatus("Status");
        codeSubmission2.setStudentId("42");
        codeSubmission2.setSubmissionId("42");
        codeSubmission2.setTeacherComment("Teacher Comment");
        codeSubmission2.setTime(10.0d);

        // Act
        CodeSubmission actualSaveCodeSubmissionResult = codeSubmissionImpl.saveCodeSubmission(codeSubmission2);

        // Assert
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission, actualSaveCodeSubmissionResult);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#saveCodeSubmission(CodeSubmission)}
     */
    @Test
    void testSaveCodeSubmission2() {
        // Arrange
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new NoSuchElementException("foo"));

        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.saveCodeSubmission(codeSubmission));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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

        // Act
        List<CodeSubmissionsResponse> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId2() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

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

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionsByExerciseId("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId3() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

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

        // Act
        List<CodeSubmissionsResponse> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId4() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

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

        // Act
        List<CodeSubmissionsResponse> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId5() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionsByExerciseId("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId6() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(-0.5d);
        codeSubmission3.setCaseTotal(-0.5d);
        codeSubmission3.setCurrentTestcase(0);
        codeSubmission3.setDateGrade("code_submission");
        codeSubmission3.setDateSubmit("code_submission");
        codeSubmission3.setError("42");
        codeSubmission3.setExerciseId("Exercise Id");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("42");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(0);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(-0.5f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("Student Id");
        codeSubmission3.setSubmissionId("Submission Id");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(-0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission3);
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

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

        // Act
        List<CodeSubmissionsResponse> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId2() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId3() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId4() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionByUserId("42", "42"));
        verify(codeSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId5() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(-0.5d);
        codeSubmission3.setCaseTotal(-0.5d);
        codeSubmission3.setCurrentTestcase(0);
        codeSubmission3.setDateGrade("code_submission");
        codeSubmission3.setDateSubmit("code_submission");
        codeSubmission3.setError("42");
        codeSubmission3.setExerciseId("Exercise Id");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("42");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(0);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(-0.5f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("Student Id");
        codeSubmission3.setSubmissionId("Submission Id");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(-0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission3);
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId6() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(-0.5d);
        codeSubmission3.setCaseTotal(-0.5d);
        codeSubmission3.setCurrentTestcase(0);
        codeSubmission3.setDateGrade("code_submission");
        codeSubmission3.setDateSubmit("code_submission");
        codeSubmission3.setError("42");
        codeSubmission3.setExerciseId("Exercise Id");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("42");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(0);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(-0.5f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("Student Id");
        codeSubmission3.setSubmissionId("Submission Id");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(-0.5d);

        CodeSubmission codeSubmission4 = new CodeSubmission();
        codeSubmission4.setCasePoints(10.0d);
        codeSubmission4.setCaseTotal(10.0d);
        codeSubmission4.setCurrentTestcase(1);
        codeSubmission4.setDateGrade("2020-03-01");
        codeSubmission4.setDateSubmit("2020-03-01");
        codeSubmission4.setError("An error occurred");
        codeSubmission4.setExerciseId("42");
        codeSubmission4.setJudgeService(new JudgeImpl());
        codeSubmission4.setJudgedOn("code_submission");
        codeSubmission4.setLanguageId("en");
        codeSubmission4.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission4.setMemory(1);
        codeSubmission4.setPretested(false);
        codeSubmission4.setResult("code_submission");
        codeSubmission4.setReviewable(false);
        codeSubmission4.setScore(10.0f);
        codeSubmission4.setSource("code_submission");
        codeSubmission4.setStatus("code_submission");
        codeSubmission4.setStudentId("42");
        codeSubmission4.setSubmissionId("42");
        codeSubmission4.setTeacherComment("code_submission");
        codeSubmission4.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission4);
        codeSubmissionList.add(codeSubmission3);
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertEquals(1, actualCodeSubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId7() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.isPretested()).thenThrow(new NoSuchElementException("foo"));
        when(codeSubmission.getStudentId()).thenReturn("42");
        when(codeSubmission.getExerciseId()).thenReturn("42");
        when(codeSubmission.getSubmissionId()).thenReturn("42");
        doNothing().when(codeSubmission).setCasePoints(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCaseTotal(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCurrentTestcase(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setError(Mockito.<String>any());
        doNothing().when(codeSubmission).setJudgeService(Mockito.<JudgeService>any());
        doNothing().when(codeSubmission).setJudgedOn(Mockito.<String>any());
        doNothing().when(codeSubmission).setLanguageId(Mockito.<String>any());
        doNothing().when(codeSubmission).setLockedAfter(Mockito.<LocalDateTime>any());
        doNothing().when(codeSubmission).setMemory(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setPretested(anyBoolean());
        doNothing().when(codeSubmission).setResult(Mockito.<String>any());
        doNothing().when(codeSubmission).setSource(Mockito.<String>any());
        doNothing().when(codeSubmission).setStatus(Mockito.<String>any());
        doNothing().when(codeSubmission).setTime(Mockito.<Double>any());
        doNothing().when(codeSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(codeSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(codeSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(codeSubmission).setReviewable(anyBoolean());
        doNothing().when(codeSubmission).setScore(Mockito.<Float>any());
        doNothing().when(codeSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(codeSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(codeSubmission).setTeacherComment(Mockito.<String>any());
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionByUserId("42", "42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(isA(Double.class));
        verify(codeSubmission).setCaseTotal(isA(Double.class));
        verify(codeSubmission).setCurrentTestcase(isA(Integer.class));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(isA(Integer.class));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(isA(Double.class));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(isA(Float.class));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetCodeSubmissionByUserId8() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.getStudentId()).thenReturn("code_submission");
        when(codeSubmission.getExerciseId()).thenReturn("42");
        when(codeSubmission.getSubmissionId()).thenReturn("42");
        doNothing().when(codeSubmission).setCasePoints(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCaseTotal(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCurrentTestcase(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setError(Mockito.<String>any());
        doNothing().when(codeSubmission).setJudgeService(Mockito.<JudgeService>any());
        doNothing().when(codeSubmission).setJudgedOn(Mockito.<String>any());
        doNothing().when(codeSubmission).setLanguageId(Mockito.<String>any());
        doNothing().when(codeSubmission).setLockedAfter(Mockito.<LocalDateTime>any());
        doNothing().when(codeSubmission).setMemory(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setPretested(anyBoolean());
        doNothing().when(codeSubmission).setResult(Mockito.<String>any());
        doNothing().when(codeSubmission).setSource(Mockito.<String>any());
        doNothing().when(codeSubmission).setStatus(Mockito.<String>any());
        doNothing().when(codeSubmission).setTime(Mockito.<Double>any());
        doNothing().when(codeSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(codeSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(codeSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(codeSubmission).setReviewable(anyBoolean());
        doNothing().when(codeSubmission).setScore(Mockito.<Float>any());
        doNothing().when(codeSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(codeSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(codeSubmission).setTeacherComment(Mockito.<String>any());
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualCodeSubmissionByUserId = codeSubmissionImpl.getCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmission).setCasePoints(isA(Double.class));
        verify(codeSubmission).setCaseTotal(isA(Double.class));
        verify(codeSubmission).setCurrentTestcase(isA(Integer.class));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(isA(Integer.class));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(isA(Double.class));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(isA(Float.class));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).findAll();
        assertTrue(actualCodeSubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId2() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId3() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId4() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42"));
        verify(codeSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId5() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(-0.5d);
        codeSubmission3.setCaseTotal(-0.5d);
        codeSubmission3.setCurrentTestcase(0);
        codeSubmission3.setDateGrade("code_submission");
        codeSubmission3.setDateSubmit("code_submission");
        codeSubmission3.setError("42");
        codeSubmission3.setExerciseId("Exercise Id");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("42");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(0);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(-0.5f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("Student Id");
        codeSubmission3.setSubmissionId("Submission Id");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(-0.5d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission3);
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId6() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(0.5d);
        codeSubmission2.setCaseTotal(0.5d);
        codeSubmission2.setCurrentTestcase(3);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(3);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(0.5d);

        CodeSubmission codeSubmission3 = new CodeSubmission();
        codeSubmission3.setCasePoints(-0.5d);
        codeSubmission3.setCaseTotal(-0.5d);
        codeSubmission3.setCurrentTestcase(0);
        codeSubmission3.setDateGrade("code_submission");
        codeSubmission3.setDateSubmit("code_submission");
        codeSubmission3.setError("42");
        codeSubmission3.setExerciseId("Exercise Id");
        codeSubmission3.setJudgeService(new JudgeImpl());
        codeSubmission3.setJudgedOn("Judged On");
        codeSubmission3.setLanguageId("42");
        codeSubmission3.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission3.setMemory(0);
        codeSubmission3.setPretested(true);
        codeSubmission3.setResult("Result");
        codeSubmission3.setReviewable(true);
        codeSubmission3.setScore(-0.5f);
        codeSubmission3.setSource("Source");
        codeSubmission3.setStatus("Status");
        codeSubmission3.setStudentId("Student Id");
        codeSubmission3.setSubmissionId("Submission Id");
        codeSubmission3.setTeacherComment("Teacher Comment");
        codeSubmission3.setTime(-0.5d);

        CodeSubmission codeSubmission4 = new CodeSubmission();
        codeSubmission4.setCasePoints(10.0d);
        codeSubmission4.setCaseTotal(10.0d);
        codeSubmission4.setCurrentTestcase(1);
        codeSubmission4.setDateGrade("2020-03-01");
        codeSubmission4.setDateSubmit("2020-03-01");
        codeSubmission4.setError("An error occurred");
        codeSubmission4.setExerciseId("42");
        codeSubmission4.setJudgeService(new JudgeImpl());
        codeSubmission4.setJudgedOn("code_submission");
        codeSubmission4.setLanguageId("en");
        codeSubmission4.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission4.setMemory(1);
        codeSubmission4.setPretested(false);
        codeSubmission4.setResult("code_submission");
        codeSubmission4.setReviewable(false);
        codeSubmission4.setScore(10.0f);
        codeSubmission4.setSource("code_submission");
        codeSubmission4.setStatus("code_submission");
        codeSubmission4.setStudentId("42");
        codeSubmission4.setSubmissionId("42");
        codeSubmission4.setTeacherComment("code_submission");
        codeSubmission4.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission4);
        codeSubmissionList.add(codeSubmission3);
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmissionRepository).findAll();
        assertSame(codeSubmission4, actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId7() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.isPretested()).thenThrow(new NoSuchElementException("foo"));
        when(codeSubmission.getStudentId()).thenReturn("42");
        when(codeSubmission.getExerciseId()).thenReturn("42");
        when(codeSubmission.getSubmissionId()).thenReturn("42");
        doNothing().when(codeSubmission).setCasePoints(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCaseTotal(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCurrentTestcase(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setError(Mockito.<String>any());
        doNothing().when(codeSubmission).setJudgeService(Mockito.<JudgeService>any());
        doNothing().when(codeSubmission).setJudgedOn(Mockito.<String>any());
        doNothing().when(codeSubmission).setLanguageId(Mockito.<String>any());
        doNothing().when(codeSubmission).setLockedAfter(Mockito.<LocalDateTime>any());
        doNothing().when(codeSubmission).setMemory(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setPretested(anyBoolean());
        doNothing().when(codeSubmission).setResult(Mockito.<String>any());
        doNothing().when(codeSubmission).setSource(Mockito.<String>any());
        doNothing().when(codeSubmission).setStatus(Mockito.<String>any());
        doNothing().when(codeSubmission).setTime(Mockito.<Double>any());
        doNothing().when(codeSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(codeSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(codeSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(codeSubmission).setReviewable(anyBoolean());
        doNothing().when(codeSubmission).setScore(Mockito.<Float>any());
        doNothing().when(codeSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(codeSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(codeSubmission).setTeacherComment(Mockito.<String>any());
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(isA(Double.class));
        verify(codeSubmission).setCaseTotal(isA(Double.class));
        verify(codeSubmission).setCurrentTestcase(isA(Integer.class));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(isA(Integer.class));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(isA(Double.class));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(isA(Float.class));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getLastCodeSubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastCodeSubmissionByUserId8() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.getStudentId()).thenReturn("code_submission");
        when(codeSubmission.getExerciseId()).thenReturn("42");
        when(codeSubmission.getSubmissionId()).thenReturn("42");
        doNothing().when(codeSubmission).setCasePoints(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCaseTotal(Mockito.<Double>any());
        doNothing().when(codeSubmission).setCurrentTestcase(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setError(Mockito.<String>any());
        doNothing().when(codeSubmission).setJudgeService(Mockito.<JudgeService>any());
        doNothing().when(codeSubmission).setJudgedOn(Mockito.<String>any());
        doNothing().when(codeSubmission).setLanguageId(Mockito.<String>any());
        doNothing().when(codeSubmission).setLockedAfter(Mockito.<LocalDateTime>any());
        doNothing().when(codeSubmission).setMemory(Mockito.<Integer>any());
        doNothing().when(codeSubmission).setPretested(anyBoolean());
        doNothing().when(codeSubmission).setResult(Mockito.<String>any());
        doNothing().when(codeSubmission).setSource(Mockito.<String>any());
        doNothing().when(codeSubmission).setStatus(Mockito.<String>any());
        doNothing().when(codeSubmission).setTime(Mockito.<Double>any());
        doNothing().when(codeSubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(codeSubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(codeSubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(codeSubmission).setReviewable(anyBoolean());
        doNothing().when(codeSubmission).setScore(Mockito.<Float>any());
        doNothing().when(codeSubmission).setStudentId(Mockito.<String>any());
        doNothing().when(codeSubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(codeSubmission).setTeacherComment(Mockito.<String>any());
        codeSubmission.setCasePoints(10.0d);
        codeSubmission.setCaseTotal(10.0d);
        codeSubmission.setCurrentTestcase(1);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(1);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

        // Act
        CodeSubmission actualLastCodeSubmissionByUserId = codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42");

        // Assert
        verify(codeSubmission).setCasePoints(isA(Double.class));
        verify(codeSubmission).setCaseTotal(isA(Double.class));
        verify(codeSubmission).setCurrentTestcase(isA(Integer.class));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(isA(Integer.class));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(isA(Double.class));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(isA(Float.class));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }
}
