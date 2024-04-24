package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubmitCodeExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SubmitCodeExerciseRequest#SubmitCodeExerciseRequest()}
     *   <li>{@link SubmitCodeExerciseRequest#setExerciseId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setLanguageId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setSource(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setStudentId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#getExerciseId()}
     *   <li>{@link SubmitCodeExerciseRequest#getLanguageId()}
     *   <li>{@link SubmitCodeExerciseRequest#getSource()}
     *   <li>{@link SubmitCodeExerciseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        SubmitCodeExerciseRequest actualSubmitCodeExerciseRequest = new SubmitCodeExerciseRequest();
        actualSubmitCodeExerciseRequest.setExerciseId("42");
        actualSubmitCodeExerciseRequest.setLanguageId("en");
        actualSubmitCodeExerciseRequest.setSource("Source");
        actualSubmitCodeExerciseRequest.setStudentId("42");
        String actualExerciseId = actualSubmitCodeExerciseRequest.getExerciseId();
        String actualLanguageId = actualSubmitCodeExerciseRequest.getLanguageId();
        String actualSource = actualSubmitCodeExerciseRequest.getSource();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualSubmitCodeExerciseRequest.getStudentId());
        assertEquals("Source", actualSource);
        assertEquals("en", actualLanguageId);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link SubmitCodeExerciseRequest#SubmitCodeExerciseRequest(String, String, String, String)}
     *   <li>{@link SubmitCodeExerciseRequest#setExerciseId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setLanguageId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setSource(String)}
     *   <li>{@link SubmitCodeExerciseRequest#setStudentId(String)}
     *   <li>{@link SubmitCodeExerciseRequest#getExerciseId()}
     *   <li>{@link SubmitCodeExerciseRequest#getLanguageId()}
     *   <li>{@link SubmitCodeExerciseRequest#getSource()}
     *   <li>{@link SubmitCodeExerciseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        SubmitCodeExerciseRequest actualSubmitCodeExerciseRequest = new SubmitCodeExerciseRequest("42", "42", "en",
                "Source");
        actualSubmitCodeExerciseRequest.setExerciseId("42");
        actualSubmitCodeExerciseRequest.setLanguageId("en");
        actualSubmitCodeExerciseRequest.setSource("Source");
        actualSubmitCodeExerciseRequest.setStudentId("42");
        String actualExerciseId = actualSubmitCodeExerciseRequest.getExerciseId();
        String actualLanguageId = actualSubmitCodeExerciseRequest.getLanguageId();
        String actualSource = actualSubmitCodeExerciseRequest.getSource();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualSubmitCodeExerciseRequest.getStudentId());
        assertEquals("Source", actualSource);
        assertEquals("en", actualLanguageId);
    }
}
