package com.example.codeE.request.exercise.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateFileSubmissionRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateFileSubmissionRequest#CreateFileSubmissionRequest()}
     *   <li>{@link CreateFileSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateFileSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateFileSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateFileSubmissionRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateFileSubmissionRequest actualCreateFileSubmissionRequest = new CreateFileSubmissionRequest();
        actualCreateFileSubmissionRequest.setExerciseId("42");
        actualCreateFileSubmissionRequest.setStudentId("42");
        String actualExerciseId = actualCreateFileSubmissionRequest.getExerciseId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualCreateFileSubmissionRequest.getStudentId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateFileSubmissionRequest#CreateFileSubmissionRequest(String, String)}
     *   <li>{@link CreateFileSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateFileSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateFileSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateFileSubmissionRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateFileSubmissionRequest actualCreateFileSubmissionRequest = new CreateFileSubmissionRequest("42", "42");
        actualCreateFileSubmissionRequest.setExerciseId("42");
        actualCreateFileSubmissionRequest.setStudentId("42");
        String actualExerciseId = actualCreateFileSubmissionRequest.getExerciseId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualCreateFileSubmissionRequest.getStudentId());
    }
}
