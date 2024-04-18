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
     *   <li>{@link CreateQuizSubmissionRequest#setTeacherComment(String)}
     *   <li>{@link CreateQuizSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateQuizSubmissionRequest#getStudentId()}
     *   <li>{@link CreateQuizSubmissionRequest#getSubmission()}
     *   <li>{@link CreateQuizSubmissionRequest#getTeacherComment()}
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
        actualCreateQuizSubmissionRequest.setTeacherComment("Teacher Comment");
        String actualExerciseId = actualCreateQuizSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateQuizSubmissionRequest.getStudentId();
        List<QuizAnswers> actualSubmission = actualCreateQuizSubmissionRequest.getSubmission();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Teacher Comment", actualCreateQuizSubmissionRequest.getTeacherComment());
        assertSame(submission, actualSubmission);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateQuizSubmissionRequest#CreateQuizSubmissionRequest(String, String, List, String)}
     *   <li>{@link CreateQuizSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateQuizSubmissionRequest#setSubmission(List)}
     *   <li>{@link CreateQuizSubmissionRequest#setTeacherComment(String)}
     *   <li>{@link CreateQuizSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateQuizSubmissionRequest#getStudentId()}
     *   <li>{@link CreateQuizSubmissionRequest#getSubmission()}
     *   <li>{@link CreateQuizSubmissionRequest#getTeacherComment()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<QuizAnswers> submission = new ArrayList<>();

        // Act
        CreateQuizSubmissionRequest actualCreateQuizSubmissionRequest = new CreateQuizSubmissionRequest("42", "42",
                submission, "Teacher Comment");
        actualCreateQuizSubmissionRequest.setExerciseId("42");
        actualCreateQuizSubmissionRequest.setStudentId("42");
        ArrayList<QuizAnswers> submission2 = new ArrayList<>();
        actualCreateQuizSubmissionRequest.setSubmission(submission2);
        actualCreateQuizSubmissionRequest.setTeacherComment("Teacher Comment");
        String actualExerciseId = actualCreateQuizSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateQuizSubmissionRequest.getStudentId();
        List<QuizAnswers> actualSubmission = actualCreateQuizSubmissionRequest.getSubmission();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Teacher Comment", actualCreateQuizSubmissionRequest.getTeacherComment());
        assertEquals(submission, actualSubmission);
        assertSame(submission2, actualSubmission);
    }
}
