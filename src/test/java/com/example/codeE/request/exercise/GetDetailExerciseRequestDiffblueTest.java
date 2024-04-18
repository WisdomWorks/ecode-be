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
     *   <li>{@link GetDetailExerciseRequest#setUserUrgent(String)}
     *   <li>{@link GetDetailExerciseRequest#getExerciseId()}
     *   <li>{@link GetDetailExerciseRequest#getKey()}
     *   <li>{@link GetDetailExerciseRequest#getStudentId()}
     *   <li>{@link GetDetailExerciseRequest#getUserUrgent()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GetDetailExerciseRequest actualGetDetailExerciseRequest = new GetDetailExerciseRequest();
        actualGetDetailExerciseRequest.setExerciseId("42");
        actualGetDetailExerciseRequest.setKey("Key");
        actualGetDetailExerciseRequest.setStudentId("42");
        actualGetDetailExerciseRequest.setUserUrgent("User Urgent");
        String actualExerciseId = actualGetDetailExerciseRequest.getExerciseId();
        String actualKey = actualGetDetailExerciseRequest.getKey();
        String actualStudentId = actualGetDetailExerciseRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Key", actualKey);
        assertEquals("User Urgent", actualGetDetailExerciseRequest.getUserUrgent());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link GetDetailExerciseRequest#GetDetailExerciseRequest(String, String, String, String)}
     *   <li>{@link GetDetailExerciseRequest#setExerciseId(String)}
     *   <li>{@link GetDetailExerciseRequest#setKey(String)}
     *   <li>{@link GetDetailExerciseRequest#setStudentId(String)}
     *   <li>{@link GetDetailExerciseRequest#setUserUrgent(String)}
     *   <li>{@link GetDetailExerciseRequest#getExerciseId()}
     *   <li>{@link GetDetailExerciseRequest#getKey()}
     *   <li>{@link GetDetailExerciseRequest#getStudentId()}
     *   <li>{@link GetDetailExerciseRequest#getUserUrgent()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        GetDetailExerciseRequest actualGetDetailExerciseRequest = new GetDetailExerciseRequest("42", "42", "Key",
                "User Urgent");
        actualGetDetailExerciseRequest.setExerciseId("42");
        actualGetDetailExerciseRequest.setKey("Key");
        actualGetDetailExerciseRequest.setStudentId("42");
        actualGetDetailExerciseRequest.setUserUrgent("User Urgent");
        String actualExerciseId = actualGetDetailExerciseRequest.getExerciseId();
        String actualKey = actualGetDetailExerciseRequest.getKey();
        String actualStudentId = actualGetDetailExerciseRequest.getStudentId();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualStudentId);
        assertEquals("Key", actualKey);
        assertEquals("User Urgent", actualGetDetailExerciseRequest.getUserUrgent());
    }
}
