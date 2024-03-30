package com.example.codeE.request.exercise.essay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateEssaySubmissionRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateEssaySubmissionRequest#CreateEssaySubmissionRequest()}
     *   <li>{@link CreateEssaySubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateEssaySubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateEssaySubmissionRequest#setSubmission(String)}
     *   <li>{@link CreateEssaySubmissionRequest#getExerciseId()}
     *   <li>{@link CreateEssaySubmissionRequest#getStudentId()}
     *   <li>{@link CreateEssaySubmissionRequest#getSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateEssaySubmissionRequest actualCreateEssaySubmissionRequest = new CreateEssaySubmissionRequest();
        actualCreateEssaySubmissionRequest.setExerciseId("42");
        actualCreateEssaySubmissionRequest.setStudentId("42");
        actualCreateEssaySubmissionRequest.setSubmission("Submission");
        String actualExerciseId = actualCreateEssaySubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateEssaySubmissionRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Submission", actualCreateEssaySubmissionRequest.getSubmission());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateEssaySubmissionRequest#CreateEssaySubmissionRequest(String, String, String)}
     *   <li>{@link CreateEssaySubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateEssaySubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateEssaySubmissionRequest#setSubmission(String)}
     *   <li>{@link CreateEssaySubmissionRequest#getExerciseId()}
     *   <li>{@link CreateEssaySubmissionRequest#getStudentId()}
     *   <li>{@link CreateEssaySubmissionRequest#getSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateEssaySubmissionRequest actualCreateEssaySubmissionRequest = new CreateEssaySubmissionRequest("42", "42",
                "Submission");
        actualCreateEssaySubmissionRequest.setExerciseId("42");
        actualCreateEssaySubmissionRequest.setStudentId("42");
        actualCreateEssaySubmissionRequest.setSubmission("Submission");
        String actualExerciseId = actualCreateEssaySubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateEssaySubmissionRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Submission", actualCreateEssaySubmissionRequest.getSubmission());
    }
}
