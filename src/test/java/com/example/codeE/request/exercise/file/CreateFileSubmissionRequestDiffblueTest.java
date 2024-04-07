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
     *   <li>{@link CreateFileSubmissionRequest#setUrl(String)}
     *   <li>{@link CreateFileSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateFileSubmissionRequest#getStudentId()}
     *   <li>{@link CreateFileSubmissionRequest#getUrl()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateFileSubmissionRequest actualCreateFileSubmissionRequest = new CreateFileSubmissionRequest();
        actualCreateFileSubmissionRequest.setExerciseId("42");
        actualCreateFileSubmissionRequest.setStudentId("42");
        actualCreateFileSubmissionRequest.setUrl("https://example.org/example");
        String actualExerciseId = actualCreateFileSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateFileSubmissionRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("https://example.org/example", actualCreateFileSubmissionRequest.getUrl());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateFileSubmissionRequest#CreateFileSubmissionRequest(String, String, String)}
     *   <li>{@link CreateFileSubmissionRequest#setExerciseId(String)}
     *   <li>{@link CreateFileSubmissionRequest#setStudentId(String)}
     *   <li>{@link CreateFileSubmissionRequest#setUrl(String)}
     *   <li>{@link CreateFileSubmissionRequest#getExerciseId()}
     *   <li>{@link CreateFileSubmissionRequest#getStudentId()}
     *   <li>{@link CreateFileSubmissionRequest#getUrl()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateFileSubmissionRequest actualCreateFileSubmissionRequest = new CreateFileSubmissionRequest("42", "42",
                "https://example.org/example");
        actualCreateFileSubmissionRequest.setExerciseId("42");
        actualCreateFileSubmissionRequest.setStudentId("42");
        actualCreateFileSubmissionRequest.setUrl("https://example.org/example");
        String actualExerciseId = actualCreateFileSubmissionRequest.getExerciseId();
        String actualStudentId = actualCreateFileSubmissionRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("https://example.org/example", actualCreateFileSubmissionRequest.getUrl());
    }
}
