package com.example.codeE.request.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GetDetailExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GetDetailExerciseRequest#GetDetailExerciseRequest()}
     *   <li>{@link GetDetailExerciseRequest#setExerciseId(String)}
     *   <li>{@link GetDetailExerciseRequest#setKey(String)}
     *   <li>{@link GetDetailExerciseRequest#setStudentId(String)}
     *   <li>{@link GetDetailExerciseRequest#getExerciseId()}
     *   <li>{@link GetDetailExerciseRequest#getKey()}
     *   <li>{@link GetDetailExerciseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GetDetailExerciseRequest actualGetDetailExerciseRequest = new GetDetailExerciseRequest();
        actualGetDetailExerciseRequest.setExerciseId("42");
        actualGetDetailExerciseRequest.setKey("Key");
        actualGetDetailExerciseRequest.setStudentId("42");
        String actualExerciseId = actualGetDetailExerciseRequest.getExerciseId();
        String actualKey = actualGetDetailExerciseRequest.getKey();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualGetDetailExerciseRequest.getStudentId());
        assertEquals("Key", actualKey);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link GetDetailExerciseRequest#GetDetailExerciseRequest(String, String, String)}
     *   <li>{@link GetDetailExerciseRequest#setExerciseId(String)}
     *   <li>{@link GetDetailExerciseRequest#setKey(String)}
     *   <li>{@link GetDetailExerciseRequest#setStudentId(String)}
     *   <li>{@link GetDetailExerciseRequest#getExerciseId()}
     *   <li>{@link GetDetailExerciseRequest#getKey()}
     *   <li>{@link GetDetailExerciseRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        GetDetailExerciseRequest actualGetDetailExerciseRequest = new GetDetailExerciseRequest("42", "42", "Key");
        actualGetDetailExerciseRequest.setExerciseId("42");
        actualGetDetailExerciseRequest.setKey("Key");
        actualGetDetailExerciseRequest.setStudentId("42");
        String actualExerciseId = actualGetDetailExerciseRequest.getExerciseId();
        String actualKey = actualGetDetailExerciseRequest.getKey();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualGetDetailExerciseRequest.getStudentId());
        assertEquals("Key", actualKey);
    }
}
