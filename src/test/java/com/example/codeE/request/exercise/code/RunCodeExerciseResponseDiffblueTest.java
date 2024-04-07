package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.common.SubmissionTestCase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RunCodeExerciseResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link RunCodeExerciseResponse#RunCodeExerciseResponse()}
     *   <li>{@link RunCodeExerciseResponse#setMessage(String)}
     *   <li>{@link RunCodeExerciseResponse#setStatus(String)}
     *   <li>{@link RunCodeExerciseResponse#setTestCases(List)}
     *   <li>{@link RunCodeExerciseResponse#getMessage()}
     *   <li>{@link RunCodeExerciseResponse#getStatus()}
     *   <li>{@link RunCodeExerciseResponse#getTestCases()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        RunCodeExerciseResponse actualRunCodeExerciseResponse = new RunCodeExerciseResponse();
        actualRunCodeExerciseResponse.setMessage("Not all who wander are lost");
        actualRunCodeExerciseResponse.setStatus("Status");
        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        actualRunCodeExerciseResponse.setTestCases(testCases);
        String actualMessage = actualRunCodeExerciseResponse.getMessage();
        String actualStatus = actualRunCodeExerciseResponse.getStatus();

        // Assert that nothing has changed
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals("Status", actualStatus);
        assertSame(testCases, actualRunCodeExerciseResponse.getTestCases());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link RunCodeExerciseResponse#RunCodeExerciseResponse(String, String, List)}
     *   <li>{@link RunCodeExerciseResponse#setMessage(String)}
     *   <li>{@link RunCodeExerciseResponse#setStatus(String)}
     *   <li>{@link RunCodeExerciseResponse#setTestCases(List)}
     *   <li>{@link RunCodeExerciseResponse#getMessage()}
     *   <li>{@link RunCodeExerciseResponse#getStatus()}
     *   <li>{@link RunCodeExerciseResponse#getTestCases()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();

        // Act
        RunCodeExerciseResponse actualRunCodeExerciseResponse = new RunCodeExerciseResponse("Status",
                "Not all who wander are lost", testCases);
        actualRunCodeExerciseResponse.setMessage("Not all who wander are lost");
        actualRunCodeExerciseResponse.setStatus("Status");
        ArrayList<SubmissionTestCase> testCases2 = new ArrayList<>();
        actualRunCodeExerciseResponse.setTestCases(testCases2);
        String actualMessage = actualRunCodeExerciseResponse.getMessage();
        String actualStatus = actualRunCodeExerciseResponse.getStatus();
        List<SubmissionTestCase> actualTestCases = actualRunCodeExerciseResponse.getTestCases();

        // Assert that nothing has changed
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals("Status", actualStatus);
        assertEquals(testCases, actualTestCases);
        assertSame(testCases2, actualTestCases);
    }
}
