package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.service.judge.JudgeImpl;

import java.time.LocalDate;
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
        codeSubmission2.setTime(10.0d);

        // Act
        CodeSubmission actualUpdateCodeSubmissionResult = codeSubmissionImpl.updateCodeSubmission(codeSubmission2);

        // Assert
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission, actualUpdateCodeSubmissionResult);
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
        codeSubmission2.setTime(10.0d);

        // Act
        CodeSubmission actualSaveCodeSubmissionResult = codeSubmissionImpl.saveCodeSubmission(codeSubmission2);

        // Assert
        verify(codeSubmissionRepository).save(isA(CodeSubmission.class));
        assertSame(codeSubmission, actualSaveCodeSubmissionResult);
    }
}
