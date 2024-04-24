package com.example.codeE.model.exercise.common.problem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestCaseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TestCase#TestCase()}
     *   <li>{@link TestCase#setExerciseId(String)}
     *   <li>{@link TestCase#setInput(String)}
     *   <li>{@link TestCase#setOutput(String)}
     *   <li>{@link TestCase#setPoints(int)}
     *   <li>{@link TestCase#setTestcaseId(String)}
     *   <li>{@link TestCase#getExerciseId()}
     *   <li>{@link TestCase#getInput()}
     *   <li>{@link TestCase#getOutput()}
     *   <li>{@link TestCase#getPoints()}
     *   <li>{@link TestCase#getTestcaseId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        TestCase actualTestCase = new TestCase();
        actualTestCase.setExerciseId("42");
        actualTestCase.setInput("Input");
        actualTestCase.setOutput("Output");
        actualTestCase.setPoints(1);
        actualTestCase.setTestcaseId("42");
        String actualExerciseId = actualTestCase.getExerciseId();
        String actualInput = actualTestCase.getInput();
        String actualOutput = actualTestCase.getOutput();
        int actualPoints = actualTestCase.getPoints();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualTestCase.getTestcaseId());
        assertEquals("Input", actualInput);
        assertEquals("Output", actualOutput);
        assertEquals(1, actualPoints);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link TestCase#TestCase(String, String, String, String, int)}
     *   <li>{@link TestCase#setExerciseId(String)}
     *   <li>{@link TestCase#setInput(String)}
     *   <li>{@link TestCase#setOutput(String)}
     *   <li>{@link TestCase#setPoints(int)}
     *   <li>{@link TestCase#setTestcaseId(String)}
     *   <li>{@link TestCase#getExerciseId()}
     *   <li>{@link TestCase#getInput()}
     *   <li>{@link TestCase#getOutput()}
     *   <li>{@link TestCase#getPoints()}
     *   <li>{@link TestCase#getTestcaseId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        TestCase actualTestCase = new TestCase("42", "42", "Input", "Output", 1);
        actualTestCase.setExerciseId("42");
        actualTestCase.setInput("Input");
        actualTestCase.setOutput("Output");
        actualTestCase.setPoints(1);
        actualTestCase.setTestcaseId("42");
        String actualExerciseId = actualTestCase.getExerciseId();
        String actualInput = actualTestCase.getInput();
        String actualOutput = actualTestCase.getOutput();
        int actualPoints = actualTestCase.getPoints();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualTestCase.getTestcaseId());
        assertEquals("Input", actualInput);
        assertEquals("Output", actualOutput);
        assertEquals(1, actualPoints);
    }
}
