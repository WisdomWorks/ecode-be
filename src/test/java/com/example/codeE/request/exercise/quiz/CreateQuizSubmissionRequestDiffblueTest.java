package com.example.codeE.request.exercise.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.common.QuizAnswers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreateQuizSubmissionRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateQuizSubmissionRequest#CreateQuizSubmissionRequest()}
     *   <li>{@link CreateQuizSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setSubmission(List)}
     *   <li>{@link CreateQuizSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateQuizSubmissionRequest#getStudentId()}
     *   <li>{@link CreateQuizSubmissionRequest#getSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateQuizSubmissionRequest actualCreateQuizSubmissionRequest = new CreateQuizSubmissionRequest();
        actualCreateQuizSubmissionRequest.setExerciseId("42");
        actualCreateQuizSubmissionRequest.setStudentId("42");
        ArrayList<QuizAnswers> submission = new ArrayList<>();
        actualCreateQuizSubmissionRequest.setSubmission(submission);
        String actualExerciseId = actualCreateQuizSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateQuizSubmissionRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertSame(submission, actualCreateQuizSubmissionRequest.getSubmission());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateQuizSubmissionRequest#CreateQuizSubmissionRequest(String, String, List)}
     *   <li>{@link CreateQuizSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setSubmission(List)}
     *   <li>{@link CreateQuizSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateQuizSubmissionRequest#getStudentId()}
     *   <li>{@link CreateQuizSubmissionRequest#getSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<QuizAnswers> submission = new ArrayList<>();

        // Act
        CreateQuizSubmissionRequest actualCreateQuizSubmissionRequest = new CreateQuizSubmissionRequest("42", "42",
                submission);
        actualCreateQuizSubmissionRequest.setExerciseId("42");
        actualCreateQuizSubmissionRequest.setStudentId("42");
        ArrayList<QuizAnswers> submission2 = new ArrayList<>();
        actualCreateQuizSubmissionRequest.setSubmission(submission2);
        String actualExerciseId = actualCreateQuizSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateQuizSubmissionRequest.getStudentId();
        List<QuizAnswers> actualSubmission = actualCreateQuizSubmissionRequest.getSubmission();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals(submission, actualSubmission);
        assertSame(submission2, actualSubmission);
    }
}
