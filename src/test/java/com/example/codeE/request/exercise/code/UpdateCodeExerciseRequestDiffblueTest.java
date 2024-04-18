package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.codeE.model.exercise.common.problem.TestCase;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class UpdateCodeExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateCodeExerciseRequest#UpdateCodeExerciseRequest()}
     *   <li>{@link UpdateCodeExerciseRequest#setAllowedLanguageIds(List)}
     *   <li>{@link UpdateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setKey(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setMemoryLimit(Integer)}
     *   <li>{@link UpdateCodeExerciseRequest#setPoints(Double)}
     *   <li>{@link UpdateCodeExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setTestCases(List)}
     *   <li>{@link UpdateCodeExerciseRequest#setTimeLimit(Double)}
     *   <li>{@link UpdateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setType(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setUsingAiGrading(boolean)}
     *   <li>{@link UpdateCodeExerciseRequest#getAllowedLanguageIds()}
     *   <li>{@link UpdateCodeExerciseRequest#getDescription()}
     *   <li>{@link UpdateCodeExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getEndTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateCodeExerciseRequest#getKey()}
     *   <li>{@link UpdateCodeExerciseRequest#getMemoryLimit()}
     *   <li>{@link UpdateCodeExerciseRequest#getPoints()}
     *   <li>{@link UpdateCodeExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateCodeExerciseRequest#getStartTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getTemplate()}
     *   <li>{@link UpdateCodeExerciseRequest#getTestCases()}
     *   <li>{@link UpdateCodeExerciseRequest#getTimeLimit()}
     *   <li>{@link UpdateCodeExerciseRequest#getTopicId()}
     *   <li>{@link UpdateCodeExerciseRequest#getType()}
     *   <li>{@link UpdateCodeExerciseRequest#isUsingAiGrading()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateCodeExerciseRequest actualUpdateCodeExerciseRequest = new UpdateCodeExerciseRequest();
        ArrayList<String> allowedLanguageIds = new ArrayList<>();
        actualUpdateCodeExerciseRequest.setAllowedLanguageIds(allowedLanguageIds);
        actualUpdateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        actualUpdateCodeExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateCodeExerciseRequest.setEndTime(endTime);
        actualUpdateCodeExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateCodeExerciseRequest.setExerciseId("42");
        actualUpdateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateCodeExerciseRequest.setKey("Key");
        actualUpdateCodeExerciseRequest.setMemoryLimit(1);
        actualUpdateCodeExerciseRequest.setPoints(10.0d);
        actualUpdateCodeExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateCodeExerciseRequest.setStartTime(startTime);
        actualUpdateCodeExerciseRequest.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualUpdateCodeExerciseRequest.setTestCases(testCases);
        actualUpdateCodeExerciseRequest.setTimeLimit(10.0d);
        actualUpdateCodeExerciseRequest.setTopicId("42");
        actualUpdateCodeExerciseRequest.setType("Type");
        actualUpdateCodeExerciseRequest.setUsingAiGrading(true);
        List<String> actualAllowedLanguageIds = actualUpdateCodeExerciseRequest.getAllowedLanguageIds();
        String actualDescription = actualUpdateCodeExerciseRequest.getDescription();
        int actualDurationTime = actualUpdateCodeExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateCodeExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateCodeExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateCodeExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateCodeExerciseRequest.getExerciseName();
        String actualKey = actualUpdateCodeExerciseRequest.getKey();
        Integer actualMemoryLimit = actualUpdateCodeExerciseRequest.getMemoryLimit();
        Double actualPoints = actualUpdateCodeExerciseRequest.getPoints();
        int actualReAttempt = actualUpdateCodeExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualUpdateCodeExerciseRequest.getTemplate();
        List<TestCase> actualTestCases = actualUpdateCodeExerciseRequest.getTestCases();
        Double actualTimeLimit = actualUpdateCodeExerciseRequest.getTimeLimit();
        String actualTopicId = actualUpdateCodeExerciseRequest.getTopicId();
        String actualType = actualUpdateCodeExerciseRequest.getType();
        boolean actualIsUsingAiGradingResult = actualUpdateCodeExerciseRequest.isUsingAiGrading();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Type", actualType);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
        assertTrue(actualIsUsingAiGradingResult);
        assertSame(allowedLanguageIds, actualAllowedLanguageIds);
        assertSame(testCases, actualTestCases);
        assertSame(endTime, actualEndTime);
        assertSame(startTime, actualStartTime);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateCodeExerciseRequest#UpdateCodeExerciseRequest(String, String, String, String, Date, Date, int, int, String, String, String, Double, Integer, List, Double, String, List, boolean)}
     *   <li>{@link UpdateCodeExerciseRequest#setAllowedLanguageIds(List)}
     *   <li>{@link UpdateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setKey(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setMemoryLimit(Integer)}
     *   <li>{@link UpdateCodeExerciseRequest#setPoints(Double)}
     *   <li>{@link UpdateCodeExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setTestCases(List)}
     *   <li>{@link UpdateCodeExerciseRequest#setTimeLimit(Double)}
     *   <li>{@link UpdateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setType(String)}
     *   <li>{@link UpdateCodeExerciseRequest#setUsingAiGrading(boolean)}
     *   <li>{@link UpdateCodeExerciseRequest#getAllowedLanguageIds()}
     *   <li>{@link UpdateCodeExerciseRequest#getDescription()}
     *   <li>{@link UpdateCodeExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getEndTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateCodeExerciseRequest#getKey()}
     *   <li>{@link UpdateCodeExerciseRequest#getMemoryLimit()}
     *   <li>{@link UpdateCodeExerciseRequest#getPoints()}
     *   <li>{@link UpdateCodeExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateCodeExerciseRequest#getStartTime()}
     *   <li>{@link UpdateCodeExerciseRequest#getTemplate()}
     *   <li>{@link UpdateCodeExerciseRequest#getTestCases()}
     *   <li>{@link UpdateCodeExerciseRequest#getTimeLimit()}
     *   <li>{@link UpdateCodeExerciseRequest#getTopicId()}
     *   <li>{@link UpdateCodeExerciseRequest#getType()}
     *   <li>{@link UpdateCodeExerciseRequest#isUsingAiGrading()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> allowedLanguageIds = new ArrayList<>();

        // Act
        UpdateCodeExerciseRequest actualUpdateCodeExerciseRequest = new UpdateCodeExerciseRequest("42", "42",
                "Exercise Name", "Key", startTime, endTime, 1, 1, "Exercise Description", "Type",
                "The characteristics of someone or something", 10.0d, 1, allowedLanguageIds, 10.0d, "Template",
                new ArrayList<>(), true);
        ArrayList<String> allowedLanguageIds2 = new ArrayList<>();
        actualUpdateCodeExerciseRequest.setAllowedLanguageIds(allowedLanguageIds2);
        actualUpdateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        actualUpdateCodeExerciseRequest.setDurationTime(1);
        Date endTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateCodeExerciseRequest.setEndTime(endTime2);
        actualUpdateCodeExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateCodeExerciseRequest.setExerciseId("42");
        actualUpdateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateCodeExerciseRequest.setKey("Key");
        actualUpdateCodeExerciseRequest.setMemoryLimit(1);
        actualUpdateCodeExerciseRequest.setPoints(10.0d);
        actualUpdateCodeExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateCodeExerciseRequest.setStartTime(startTime2);
        actualUpdateCodeExerciseRequest.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualUpdateCodeExerciseRequest.setTestCases(testCases);
        actualUpdateCodeExerciseRequest.setTimeLimit(10.0d);
        actualUpdateCodeExerciseRequest.setTopicId("42");
        actualUpdateCodeExerciseRequest.setType("Type");
        actualUpdateCodeExerciseRequest.setUsingAiGrading(true);
        List<String> actualAllowedLanguageIds = actualUpdateCodeExerciseRequest.getAllowedLanguageIds();
        String actualDescription = actualUpdateCodeExerciseRequest.getDescription();
        int actualDurationTime = actualUpdateCodeExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateCodeExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateCodeExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateCodeExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateCodeExerciseRequest.getExerciseName();
        String actualKey = actualUpdateCodeExerciseRequest.getKey();
        Integer actualMemoryLimit = actualUpdateCodeExerciseRequest.getMemoryLimit();
        Double actualPoints = actualUpdateCodeExerciseRequest.getPoints();
        int actualReAttempt = actualUpdateCodeExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualUpdateCodeExerciseRequest.getTemplate();
        List<TestCase> actualTestCases = actualUpdateCodeExerciseRequest.getTestCases();
        Double actualTimeLimit = actualUpdateCodeExerciseRequest.getTimeLimit();
        String actualTopicId = actualUpdateCodeExerciseRequest.getTopicId();
        String actualType = actualUpdateCodeExerciseRequest.getType();
        boolean actualIsUsingAiGradingResult = actualUpdateCodeExerciseRequest.isUsingAiGrading();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Type", actualType);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
        assertTrue(actualIsUsingAiGradingResult);
        assertEquals(allowedLanguageIds, actualAllowedLanguageIds);
        assertEquals(allowedLanguageIds, actualTestCases);
        assertSame(allowedLanguageIds2, actualAllowedLanguageIds);
        assertSame(testCases, actualTestCases);
        assertSame(endTime2, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
