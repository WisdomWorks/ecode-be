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

import com.example.codeE.helper.VertexAIHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.vertexAi.GradingResponse;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CodeExerciseRepository;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.CodeSubmissionDetail;
import com.example.codeE.request.exercise.code.CodeSubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.judge.JudgeImpl;
import com.example.codeE.service.judge.JudgeService;
import com.example.codeE.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    @MockBean
    private CodeExerciseRepository codeExerciseRepository;

    @Autowired
    private CodeSubmissionImpl codeSubmissionImpl;

    @MockBean
    private CodeSubmissionRepository codeSubmissionRepository;

    @MockBean
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    @MockBean
    private SubmissionTestCaseService submissionTestCaseService;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private VertexAIHelper vertexAIHelper;

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
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException("P"));
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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.checkStatusAndUpdate(codeSubmission2));
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
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException("AB"));
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
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
                .thenThrow(new IllegalArgumentException("No Submission found"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionById("42"));
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
        when(submissionTestCaseService.findBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        verify(submissionTestCaseService).findBySubmissionId(eq("42"));
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionResponseById("42"));
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
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException("foo"));

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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.updateCodeSubmission(codeSubmission));
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
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException("foo"));

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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.saveCodeSubmission(codeSubmission));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId() {
        // Arrange
        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);
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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(codeSubmissionList, actualCodeSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId2() {
        // Arrange
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionsByExerciseId("42"));
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
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
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);
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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualCodeSubmissionsByExerciseId.getSubmissions());
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
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);
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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualCodeSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId5() {
        // Arrange
        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(codeSubmissionList, actualCodeSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId6() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(-1.0d);
        codeSubmission.setCaseTotal(-1.0d);
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(-1.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);
        ArrayList<CodeSubmission> codeSubmissionList2 = new ArrayList<>();
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList2);

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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(codeSubmissionList2, actualCodeSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId7() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(-1.0d);
        codeSubmission.setCaseTotal(-1.0d);
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(-1.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);
        ArrayList<CodeSubmission> codeSubmissionList2 = new ArrayList<>();
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList2);

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
        AllSubmissionResponse<CodeSubmissionDetail> actualCodeSubmissionsByExerciseId = codeSubmissionImpl
                .getCodeSubmissionsByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals("42", actualCodeSubmissionsByExerciseId.getExerciseId());
        assertNull(actualCodeSubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualCodeSubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(codeSubmissionList2, actualCodeSubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId8() {
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
        codeSubmission.setPretested(false);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(false);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);
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
        when(submissionTestCaseService.getAllTcBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        when(userService.getAllGroupsByUserId(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("code_submission"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionsByExerciseId("42"));
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
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(submissionTestCaseService).getAllTcBySubmissionId(eq("42"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getCodeSubmissionsByExerciseId(String)}
     */
    @Test
    void testGetCodeSubmissionsByExerciseId9() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.isPretested()).thenThrow(new NoSuchElementException("foo"));
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
        codeSubmission.setPretested(false);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(false);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);
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

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.getCodeSubmissionsByExerciseId("42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(false));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
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
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(false));
        verify(codeSubmission).setScore(eq(10.0f));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
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
        when(codeSubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionByUserId("42", "42"));
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
        when(codeSubmission.isPretested()).thenThrow(new IllegalArgumentException("foo"));
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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getCodeSubmissionByUserId("42", "42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(eq(10.0f));
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
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(eq(10.0f));
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
        when(codeSubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42"));
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
        when(codeSubmission.isPretested()).thenThrow(new IllegalArgumentException("foo"));
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
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getLastCodeSubmissionByUserId("42", "42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(eq(10.0f));
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
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getStudentId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(eq(10.0f));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).findAll();
        assertNull(actualLastCodeSubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#GradeCodeSubmission(String, float, String)}
     */
    @Test
    void testGradeCodeSubmission() {
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
        CodeSubmission actualGradeCodeSubmissionResult = codeSubmissionImpl.GradeCodeSubmission("42", 10.0f, "Comment");

        // Assert
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission2, actualGradeCodeSubmissionResult);
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#GradeCodeSubmission(String, float, String)}
     */
    @Test
    void testGradeCodeSubmission2() {
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
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException("foo"));
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.GradeCodeSubmission("42", 10.0f, "Comment"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#GradeCodeSubmission(String, float, String)}
     */
    @Test
    void testGradeCodeSubmission3() {
        // Arrange
        Optional<CodeSubmission> emptyResult = Optional.empty();
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> codeSubmissionImpl.GradeCodeSubmission("42", 10.0f, "Comment"));
        verify(codeSubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#GradeCodeSubmission(String, float, String)}
     */
    @Test
    void testGradeCodeSubmission4() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.GradeCodeSubmission("42", -0.5f, "Comment"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId() {
        // Arrange
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<CodeSubmission> actualAllSubmissionByExerciseId = codeSubmissionImpl.getAllSubmissionByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        assertTrue(actualAllSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId2() {
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualAllSubmissionByExerciseId = codeSubmissionImpl.getAllSubmissionByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        assertTrue(actualAllSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId3() {
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualAllSubmissionByExerciseId = codeSubmissionImpl.getAllSubmissionByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        assertTrue(actualAllSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId4() {
        // Arrange
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any()))
                .thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getAllSubmissionByExerciseId("42"));
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId5() {
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualAllSubmissionByExerciseId = codeSubmissionImpl.getAllSubmissionByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        assertTrue(actualAllSubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId6() {
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);

        // Act
        List<CodeSubmission> actualAllSubmissionByExerciseId = codeSubmissionImpl.getAllSubmissionByExerciseId("42");

        // Assert
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
        assertEquals(1, actualAllSubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getAllSubmissionByExerciseId(String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId7() {
        // Arrange
        CodeSubmission codeSubmission = mock(CodeSubmission.class);
        when(codeSubmission.isPretested()).thenThrow(new IllegalArgumentException("foo"));
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
        when(codeSubmissionRepository.getCodeSubmissionByExerciseId(Mockito.<String>any())).thenReturn(codeSubmissionList);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.getAllSubmissionByExerciseId("42"));
        verify(codeSubmission).isPretested();
        verify(codeSubmission).setCasePoints(eq(10.0d));
        verify(codeSubmission).setCaseTotal(eq(10.0d));
        verify(codeSubmission).setCurrentTestcase(eq(1));
        verify(codeSubmission).setError(eq("An error occurred"));
        verify(codeSubmission).setJudgeService(isA(JudgeService.class));
        verify(codeSubmission).setJudgedOn(eq("Judged On"));
        verify(codeSubmission).setLanguageId(eq("en"));
        verify(codeSubmission).setLockedAfter(isA(LocalDateTime.class));
        verify(codeSubmission).setMemory(eq(1));
        verify(codeSubmission).setPretested(eq(true));
        verify(codeSubmission).setResult(eq("Result"));
        verify(codeSubmission).setSource(eq("Source"));
        verify(codeSubmission).setStatus(eq("Status"));
        verify(codeSubmission).setTime(eq(10.0d));
        verify(codeSubmission).getExerciseId();
        verify(codeSubmission).getSubmissionId();
        verify(codeSubmission).setDateGrade(eq("2020-03-01"));
        verify(codeSubmission).setDateSubmit(eq("2020-03-01"));
        verify(codeSubmission).setExerciseId(eq("42"));
        verify(codeSubmission).setReviewable(eq(true));
        verify(codeSubmission).setScore(eq(10.0f));
        verify(codeSubmission).setStudentId(eq("42"));
        verify(codeSubmission).setSubmissionId(eq("42"));
        verify(codeSubmission).setTeacherComment(eq("Teacher Comment"));
        verify(codeSubmissionRepository).getCodeSubmissionByExerciseId(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#overriedByAiGrader(String, String)}
     */
    @Test
    void testOverriedByAiGrader() throws JsonProcessingException {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        codeExercise.setUsingAiGrading(true);
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<CodeSubmission> ofResult2 = Optional.of(codeSubmission);

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
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(submissionTestCaseService.findBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(vertexAIHelper.parseJson(Mockito.<String>any())).thenReturn(new GradingResponse(10.0f, "Comment"));
        when(vertexAIHelper.generateContent(Mockito.<String>any())).thenReturn("Not all who wander are lost");

        // Act
        codeSubmissionImpl.overriedByAiGrader("42", "42");

        // Assert
        verify(vertexAIHelper).generateContent(eq(
                "You are roleplaying as an exceptional programming university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a programming assignment.\n\nAs a fair and unbiased grader, you will evaluate each submission based solely on the following:\n\n1. The correctness of the student's solution.\n2. The correctness of the student's test cases' inputs and outputs.\n3. The student's coding style and cleanliness.\n4. The student's demonstrated factual knowledge and problem-solving skills.\n\nScores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution. In case there is an error message in the Judge message (i.e., Compiler error or Internal Error), the student score is always 0, with the comment being \"Run failed\".\n\nIn addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\n\nThe relevant assignment problem, the student's submitted solution, judge message, and test cases will be provided in the following format. After reviewing the problem, submission, judge message, and test cases, output your evaluation using this JSON format with no additional text besides the JSON:\n\n<PROBLEM>\n{Problem description}\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\n{Submission Language}\n</PROGRAMMING-LANGUAGE>\n\n<STUDENT-SUBMISSION>\n{Student's submitted code}\n</STUDENT-SUBMISSION>\n\n<TEST-CASES>\n<CASE-1>\n<INPUT>\n{Test case 1 input}\n</INPUT>\n<STUDENT-OUTPUT>\n{Test case 1 student's output}\n</STUDENT-OUTPUT>\n<CASE-POINT>{Points for test case 1}</CASE-POINT>\n</CASE-1>\n\n<CASE-2>\n<INPUT>\n{Test case 2 input}\n</INPUT>\n<STUDENT-OUTPUT>\n{Test case 2 student's output}\n</STUDENT-OUTPUT>\n<CASE-POINT>{Points for test case 2}</CASE-POINT>\n</CASE-2>\n...\n</TEST-CASES>\n\nGRADE:{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n\nPlease grade the following submission. Remember to strictly follow the format above.\n\n<PROBLEM>\nWrite a Python program that prints a random number from 0 to 10.\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\nPY3\n</PROGRAMMING-LANGUAGE>\n\n<STUDENT-SUBMISSION>\nimport random\nrandom_number = random.randint(0, 10)\nprint(random_number)\n</STUDENT-SUBMISSION>\n\n<JUDGE-MESSAGE>\n</JUDGE-MESSAGE>\n\n<TEST-CASES>\n<CASE-1>\n<INPUT>\n</INPUT>\n<STUDENT-OUTPUT>\n3\n</STUDENT-OUTPUT>\n<CASE-POINT>5</CASE-POINT>\n</CASE-1>\n\n<CASE-2>\n<INPUT>\n</INPUT>\n<STUDENT-OUTPUT>\n7\n</STUDENT-OUTPUT>\n<CASE-POINT>5</CASE-POINT>\n</CASE-2>\n</TEST-CASES>\n\nGRADE:{\"score\": 10, \"comment\": \"The student's solution correctly generates and prints a random number between 0 and 10 using the 'random' module in Python. The code is clean and concise, demonstrating a good understanding of generating random numbers within a specified range.\"}\n\nHere is the real problem. Please grade the submission unbiasedly and coherently and output it as a pure JSON string without any markdown formatting.\n\n<PROBLEM>\nThe characteristics of someone or something\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\nen\n</PROGRAMMING-LANGUAGE>\n\n <STUDENT-SUBMISSION>\nSource\n</STUDENT-SUBMISSION> \n\n<TEST-CASES>\n\n</TEST-CASES>\n\nGRADE:{"));
        verify(vertexAIHelper).parseJson(eq("Not all who wander are lost"));
        verify(submissionTestCaseService).findBySubmissionId(eq("42"));
        verify(codeExerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#overriedByAiGrader(String, String)}
     */
    @Test
    void testOverriedByAiGrader2() throws JsonProcessingException {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        codeExercise.setUsingAiGrading(true);
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<CodeSubmission> ofResult2 = Optional.of(codeSubmission);
        when(codeSubmissionRepository.save(Mockito.<CodeSubmission>any())).thenThrow(new IllegalArgumentException(
                "You are roleplaying as an exceptional programming university professor teaching at FPT University, a"
                        + " renowned technology institution in Vietnam. It is currently the final examination period, and your"
                        + " task is to grade student submissions for a programming assignment.\n" + "\n"
                        + "As a fair and unbiased grader, you will evaluate each submission based solely on the following:\n" + "\n"
                        + "1. The correctness of the student's solution.\n"
                        + "2. The correctness of the student's test cases' inputs and outputs.\n"
                        + "3. The student's coding style and cleanliness.\n"
                        + "4. The student's demonstrated factual knowledge and problem-solving skills.\n" + "\n"
                        + "Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a"
                        + " perfect solution. In case there is an error message in the Judge message (i.e., Compiler error or"
                        + " Internal Error), the student score is always 0, with the comment being \"Run failed\".\n" + "\n"
                        + "In addition to assigning a numeric score, please provide a clear, detailed comment explaining your"
                        + " reasoning behind the grade. The comment should offer a comprehensive rationale for the score,"
                        + " highlighting both strengths and weaknesses in the student's work as applicable.\n" + "\n"
                        + "The relevant assignment problem, the student's submitted solution, judge message, and test cases will"
                        + " be provided in the following format. After reviewing the problem, submission, judge message, and test"
                        + " cases, output your evaluation using this JSON format with no additional text besides the JSON:\n" + "\n"
                        + "<PROBLEM>\n" + "{Problem description}\n" + "</PROBLEM>\n" + "\n" + "<PROGRAMMING-LANGUAGE>\n"
                        + "{Submission Language}\n" + "</PROGRAMMING-LANGUAGE>\n" + "\n" + "<STUDENT-SUBMISSION>\n"
                        + "{Student's submitted code}\n" + "</STUDENT-SUBMISSION>\n" + "\n" + "<TEST-CASES>\n" + "<CASE-1>\n"
                        + "<INPUT>\n" + "{Test case 1 input}\n" + "</INPUT>\n" + "<STUDENT-OUTPUT>\n"
                        + "{Test case 1 student's output}\n" + "</STUDENT-OUTPUT>\n"
                        + "<CASE-POINT>{Points for test case 1}</CASE-POINT>\n" + "</CASE-1>\n" + "\n" + "<CASE-2>\n" + "<INPUT>\n"
                        + "{Test case 2 input}\n" + "</INPUT>\n" + "<STUDENT-OUTPUT>\n" + "{Test case 2 student's output}\n"
                        + "</STUDENT-OUTPUT>\n" + "<CASE-POINT>{Points for test case 2}</CASE-POINT>\n" + "</CASE-2>\n" + "...\n"
                        + "</TEST-CASES>\n" + "\n"
                        + "GRADE:{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n" + "\n"
                        + "Please grade the following submission. Remember to strictly follow the format above.\n" + "\n"
                        + "<PROBLEM>\n" + "Write a Python program that prints a random number from 0 to 10.\n" + "</PROBLEM>\n"
                        + "\n" + "<PROGRAMMING-LANGUAGE>\n" + "PY3\n" + "</PROGRAMMING-LANGUAGE>\n" + "\n"
                        + "<STUDENT-SUBMISSION>\n" + "import random\n" + "random_number = random.randint(0, 10)\n"
                        + "print(random_number)\n" + "</STUDENT-SUBMISSION>\n" + "\n" + "<JUDGE-MESSAGE>\n" + "</JUDGE-MESSAGE>\n"
                        + "\n" + "<TEST-CASES>\n" + "<CASE-1>\n" + "<INPUT>\n" + "</INPUT>\n" + "<STUDENT-OUTPUT>\n" + "3\n"
                        + "</STUDENT-OUTPUT>\n" + "<CASE-POINT>5</CASE-POINT>\n" + "</CASE-1>\n" + "\n" + "<CASE-2>\n" + "<INPUT>\n"
                        + "</INPUT>\n" + "<STUDENT-OUTPUT>\n" + "7\n" + "</STUDENT-OUTPUT>\n" + "<CASE-POINT>5</CASE-POINT>\n"
                        + "</CASE-2>\n" + "</TEST-CASES>\n" + "\n"
                        + "GRADE:{\"score\": 10, \"comment\": \"The student's solution correctly generates and prints a random number"
                        + " between 0 and 10 using the 'random' module in Python. The code is clean and concise, demonstrating a"
                        + " good understanding of generating random numbers within a specified range.\"}\n" + "\n"
                        + "Here is the real problem. Please grade the submission unbiasedly and coherently and output it as a"
                        + " pure JSON string without any markdown formatting.\n" + "\n" + "<PROBLEM>\n" + "%s\n" + "</PROBLEM>\n"
                        + "\n" + "<PROGRAMMING-LANGUAGE>\n" + "%s\n" + "</PROGRAMMING-LANGUAGE>\n" + "\n"
                        + " <STUDENT-SUBMISSION>\n" + "%s\n" + "</STUDENT-SUBMISSION> \n" + "\n" + "<TEST-CASES>\n" + "%s\n"
                        + "</TEST-CASES>\n" + "\n" + "GRADE:{"));
        when(codeSubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(submissionTestCaseService.findBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        when(vertexAIHelper.parseJson(Mockito.<String>any())).thenReturn(new GradingResponse(10.0f, "Comment"));
        when(vertexAIHelper.generateContent(Mockito.<String>any())).thenReturn("Not all who wander are lost");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> codeSubmissionImpl.overriedByAiGrader("42", "42"));
        verify(vertexAIHelper).generateContent(eq(
                "You are roleplaying as an exceptional programming university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a programming assignment.\n\nAs a fair and unbiased grader, you will evaluate each submission based solely on the following:\n\n1. The correctness of the student's solution.\n2. The correctness of the student's test cases' inputs and outputs.\n3. The student's coding style and cleanliness.\n4. The student's demonstrated factual knowledge and problem-solving skills.\n\nScores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution. In case there is an error message in the Judge message (i.e., Compiler error or Internal Error), the student score is always 0, with the comment being \"Run failed\".\n\nIn addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\n\nThe relevant assignment problem, the student's submitted solution, judge message, and test cases will be provided in the following format. After reviewing the problem, submission, judge message, and test cases, output your evaluation using this JSON format with no additional text besides the JSON:\n\n<PROBLEM>\n{Problem description}\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\n{Submission Language}\n</PROGRAMMING-LANGUAGE>\n\n<STUDENT-SUBMISSION>\n{Student's submitted code}\n</STUDENT-SUBMISSION>\n\n<TEST-CASES>\n<CASE-1>\n<INPUT>\n{Test case 1 input}\n</INPUT>\n<STUDENT-OUTPUT>\n{Test case 1 student's output}\n</STUDENT-OUTPUT>\n<CASE-POINT>{Points for test case 1}</CASE-POINT>\n</CASE-1>\n\n<CASE-2>\n<INPUT>\n{Test case 2 input}\n</INPUT>\n<STUDENT-OUTPUT>\n{Test case 2 student's output}\n</STUDENT-OUTPUT>\n<CASE-POINT>{Points for test case 2}</CASE-POINT>\n</CASE-2>\n...\n</TEST-CASES>\n\nGRADE:{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n\nPlease grade the following submission. Remember to strictly follow the format above.\n\n<PROBLEM>\nWrite a Python program that prints a random number from 0 to 10.\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\nPY3\n</PROGRAMMING-LANGUAGE>\n\n<STUDENT-SUBMISSION>\nimport random\nrandom_number = random.randint(0, 10)\nprint(random_number)\n</STUDENT-SUBMISSION>\n\n<JUDGE-MESSAGE>\n</JUDGE-MESSAGE>\n\n<TEST-CASES>\n<CASE-1>\n<INPUT>\n</INPUT>\n<STUDENT-OUTPUT>\n3\n</STUDENT-OUTPUT>\n<CASE-POINT>5</CASE-POINT>\n</CASE-1>\n\n<CASE-2>\n<INPUT>\n</INPUT>\n<STUDENT-OUTPUT>\n7\n</STUDENT-OUTPUT>\n<CASE-POINT>5</CASE-POINT>\n</CASE-2>\n</TEST-CASES>\n\nGRADE:{\"score\": 10, \"comment\": \"The student's solution correctly generates and prints a random number between 0 and 10 using the 'random' module in Python. The code is clean and concise, demonstrating a good understanding of generating random numbers within a specified range.\"}\n\nHere is the real problem. Please grade the submission unbiasedly and coherently and output it as a pure JSON string without any markdown formatting.\n\n<PROBLEM>\nThe characteristics of someone or something\n</PROBLEM>\n\n<PROGRAMMING-LANGUAGE>\nen\n</PROGRAMMING-LANGUAGE>\n\n <STUDENT-SUBMISSION>\nSource\n</STUDENT-SUBMISSION> \n\n<TEST-CASES>\n\n</TEST-CASES>\n\nGRADE:{"));
        verify(vertexAIHelper).parseJson(eq("Not all who wander are lost"));
        verify(submissionTestCaseService).findBySubmissionId(eq("42"));
        verify(codeExerciseRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findById(eq("42"));
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = codeSubmissionImpl
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
     * {@link CodeSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
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
                () -> codeSubmissionImpl.getOverviewScoreReportByExerciseId("42", new ArrayList<>()));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId3() {
        // Arrange
        when(codeSubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = codeSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId4() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(-1.0d);
        codeSubmission.setCaseTotal(-1.0d);
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(-1.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = codeSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }

    /**
     * Method under test:
     * {@link CodeSubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId5() {
        // Arrange
        CodeSubmission codeSubmission = new CodeSubmission();
        codeSubmission.setCasePoints(-1.0d);
        codeSubmission.setCaseTotal(-1.0d);
        codeSubmission.setCurrentTestcase(3);
        codeSubmission.setDateGrade("2020-03-01");
        codeSubmission.setDateSubmit("2020-03-01");
        codeSubmission.setError("An error occurred");
        codeSubmission.setExerciseId("42");
        codeSubmission.setJudgeService(new JudgeImpl());
        codeSubmission.setJudgedOn("Judged On");
        codeSubmission.setLanguageId("en");
        codeSubmission.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission.setMemory(3);
        codeSubmission.setPretested(true);
        codeSubmission.setResult("Result");
        codeSubmission.setReviewable(true);
        codeSubmission.setScore(10.0f);
        codeSubmission.setSource("Source");
        codeSubmission.setStatus("Status");
        codeSubmission.setStudentId("42");
        codeSubmission.setSubmissionId("42");
        codeSubmission.setTeacherComment("Teacher Comment");
        codeSubmission.setTime(-1.0d);

        CodeSubmission codeSubmission2 = new CodeSubmission();
        codeSubmission2.setCasePoints(10.0d);
        codeSubmission2.setCaseTotal(10.0d);
        codeSubmission2.setCurrentTestcase(1);
        codeSubmission2.setDateGrade("2020/03/01");
        codeSubmission2.setDateSubmit("2020/03/01");
        codeSubmission2.setError("code_submission");
        codeSubmission2.setExerciseId("code_submission");
        codeSubmission2.setJudgeService(new JudgeImpl());
        codeSubmission2.setJudgedOn("42");
        codeSubmission2.setLanguageId("eng");
        codeSubmission2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        codeSubmission2.setMemory(1);
        codeSubmission2.setPretested(false);
        codeSubmission2.setResult("42");
        codeSubmission2.setReviewable(false);
        codeSubmission2.setScore(0.5f);
        codeSubmission2.setSource("42");
        codeSubmission2.setStatus("42");
        codeSubmission2.setStudentId("code_submission");
        codeSubmission2.setSubmissionId("code_submission");
        codeSubmission2.setTeacherComment("42");
        codeSubmission2.setTime(10.0d);

        ArrayList<CodeSubmission> codeSubmissionList = new ArrayList<>();
        codeSubmissionList.add(codeSubmission2);
        codeSubmissionList.add(codeSubmission);
        when(codeSubmissionRepository.findAll()).thenReturn(codeSubmissionList);

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
        OverviewScoreReport actualOverviewScoreReportByExerciseId = codeSubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(codeSubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }
}
