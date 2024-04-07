package com.example.codeE.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.codeE.model.exercise.common.problem.TestCase;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CodeExerciseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CodeExercise#CodeExercise()}
     *   <li>{@link CodeExercise#setAllowedLanguageIds(List)}
     *   <li>{@link CodeExercise#setDescription(String)}
     *   <li>{@link CodeExercise#setMemoryLimit(Integer)}
     *   <li>{@link CodeExercise#setPartial(boolean)}
     *   <li>{@link CodeExercise#setPoints(Double)}
     *   <li>{@link CodeExercise#setShortCircuit(Boolean)}
     *   <li>{@link CodeExercise#setTemplate(String)}
     *   <li>{@link CodeExercise#setTestCases(List)}
     *   <li>{@link CodeExercise#setTimeLimit(Double)}
     *   <li>{@link CodeExercise#getAllowedLanguageIds()}
     *   <li>{@link CodeExercise#getDescription()}
     *   <li>{@link CodeExercise#getMemoryLimit()}
     *   <li>{@link CodeExercise#getPoints()}
     *   <li>{@link CodeExercise#getShortCircuit()}
     *   <li>{@link CodeExercise#getTemplate()}
     *   <li>{@link CodeExercise#getTestCases()}
     *   <li>{@link CodeExercise#getTimeLimit()}
     *   <li>{@link CodeExercise#isPartial()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CodeExercise actualCodeExercise = new CodeExercise();
        ArrayList<String> allowedLanguageIds = new ArrayList<>();
        actualCodeExercise.setAllowedLanguageIds(allowedLanguageIds);
        actualCodeExercise.setDescription("The characteristics of someone or something");
        actualCodeExercise.setMemoryLimit(1);
        actualCodeExercise.setPartial(true);
        actualCodeExercise.setPoints(10.0d);
        actualCodeExercise.setShortCircuit(true);
        actualCodeExercise.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualCodeExercise.setTestCases(testCases);
        actualCodeExercise.setTimeLimit(10.0d);
        List<String> actualAllowedLanguageIds = actualCodeExercise.getAllowedLanguageIds();
        String actualDescription = actualCodeExercise.getDescription();
        Integer actualMemoryLimit = actualCodeExercise.getMemoryLimit();
        Double actualPoints = actualCodeExercise.getPoints();
        Boolean actualShortCircuit = actualCodeExercise.getShortCircuit();
        String actualTemplate = actualCodeExercise.getTemplate();
        List<TestCase> actualTestCases = actualCodeExercise.getTestCases();
        Double actualTimeLimit = actualCodeExercise.getTimeLimit();
        boolean actualIsPartialResult = actualCodeExercise.isPartial();

        // Assert that nothing has changed
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
        assertTrue(actualShortCircuit);
        assertTrue(actualIsPartialResult);
        assertSame(allowedLanguageIds, actualAllowedLanguageIds);
        assertSame(testCases, actualTestCases);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CodeExercise#CodeExercise(String, Double, Integer, Boolean, boolean, List, Double, String, List)}
     *   <li>{@link CodeExercise#setAllowedLanguageIds(List)}
     *   <li>{@link CodeExercise#setDescription(String)}
     *   <li>{@link CodeExercise#setMemoryLimit(Integer)}
     *   <li>{@link CodeExercise#setPartial(boolean)}
     *   <li>{@link CodeExercise#setPoints(Double)}
     *   <li>{@link CodeExercise#setShortCircuit(Boolean)}
     *   <li>{@link CodeExercise#setTemplate(String)}
     *   <li>{@link CodeExercise#setTestCases(List)}
     *   <li>{@link CodeExercise#setTimeLimit(Double)}
     *   <li>{@link CodeExercise#getAllowedLanguageIds()}
     *   <li>{@link CodeExercise#getDescription()}
     *   <li>{@link CodeExercise#getMemoryLimit()}
     *   <li>{@link CodeExercise#getPoints()}
     *   <li>{@link CodeExercise#getShortCircuit()}
     *   <li>{@link CodeExercise#getTemplate()}
     *   <li>{@link CodeExercise#getTestCases()}
     *   <li>{@link CodeExercise#getTimeLimit()}
     *   <li>{@link CodeExercise#isPartial()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> allowedLanguageIds = new ArrayList<>();

        // Act
        CodeExercise actualCodeExercise = new CodeExercise("The characteristics of someone or something", 10.0d, 1, true,
                true, allowedLanguageIds, 10.0d, "Template", new ArrayList<>());
        ArrayList<String> allowedLanguageIds2 = new ArrayList<>();
        actualCodeExercise.setAllowedLanguageIds(allowedLanguageIds2);
        actualCodeExercise.setDescription("The characteristics of someone or something");
        actualCodeExercise.setMemoryLimit(1);
        actualCodeExercise.setPartial(true);
        actualCodeExercise.setPoints(10.0d);
        actualCodeExercise.setShortCircuit(true);
        actualCodeExercise.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualCodeExercise.setTestCases(testCases);
        actualCodeExercise.setTimeLimit(10.0d);
        List<String> actualAllowedLanguageIds = actualCodeExercise.getAllowedLanguageIds();
        String actualDescription = actualCodeExercise.getDescription();
        Integer actualMemoryLimit = actualCodeExercise.getMemoryLimit();
        Double actualPoints = actualCodeExercise.getPoints();
        Boolean actualShortCircuit = actualCodeExercise.getShortCircuit();
        String actualTemplate = actualCodeExercise.getTemplate();
        List<TestCase> actualTestCases = actualCodeExercise.getTestCases();
        Double actualTimeLimit = actualCodeExercise.getTimeLimit();
        boolean actualIsPartialResult = actualCodeExercise.isPartial();

        // Assert that nothing has changed
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
        assertTrue(actualShortCircuit);
        assertTrue(actualIsPartialResult);
        assertEquals(allowedLanguageIds, actualAllowedLanguageIds);
        assertEquals(allowedLanguageIds, actualTestCases);
        assertSame(allowedLanguageIds2, actualAllowedLanguageIds);
        assertSame(testCases, actualTestCases);
    }
}
