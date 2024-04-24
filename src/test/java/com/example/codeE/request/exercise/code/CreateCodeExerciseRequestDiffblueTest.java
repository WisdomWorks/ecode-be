package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.common.problem.TestCase;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreateCodeExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateCodeExerciseRequest#CreateCodeExerciseRequest()}
     *   <li>{@link CreateCodeExerciseRequest#setAllowedLanguageIds(List)}
     *   <li>{@link CreateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link CreateCodeExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setKey(String)}
     *   <li>{@link CreateCodeExerciseRequest#setPoints(Double)}
     *   <li>{@link CreateCodeExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link CreateCodeExerciseRequest#setTestCases(List)}
     *   <li>{@link CreateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateCodeExerciseRequest#getAllowedLanguageIds()}
     *   <li>{@link CreateCodeExerciseRequest#getDescription()}
     *   <li>{@link CreateCodeExerciseRequest#getDurationTime()}
     *   <li>{@link CreateCodeExerciseRequest#getEndTime()}
     *   <li>{@link CreateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link CreateCodeExerciseRequest#getKey()}
     *   <li>{@link CreateCodeExerciseRequest#getPoints()}
     *   <li>{@link CreateCodeExerciseRequest#getReAttempt()}
     *   <li>{@link CreateCodeExerciseRequest#getStartTime()}
     *   <li>{@link CreateCodeExerciseRequest#getTemplate()}
     *   <li>{@link CreateCodeExerciseRequest#getTestCases()}
     *   <li>{@link CreateCodeExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateCodeExerciseRequest actualCreateCodeExerciseRequest = new CreateCodeExerciseRequest();
        ArrayList<String> allowedLanguageIds = new ArrayList<>();
        actualCreateCodeExerciseRequest.setAllowedLanguageIds(allowedLanguageIds);
        actualCreateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        actualCreateCodeExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setEndTime(endTime);
        actualCreateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualCreateCodeExerciseRequest.setKey("Key");
        actualCreateCodeExerciseRequest.setPoints(10.0d);
        actualCreateCodeExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setStartTime(startTime);
        actualCreateCodeExerciseRequest.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualCreateCodeExerciseRequest.setTestCases(testCases);
        actualCreateCodeExerciseRequest.setTopicId("42");
        List<String> actualAllowedLanguageIds = actualCreateCodeExerciseRequest.getAllowedLanguageIds();
        String actualDescription = actualCreateCodeExerciseRequest.getDescription();
        int actualDurationTime = actualCreateCodeExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateCodeExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateCodeExerciseRequest.getExerciseName();
        String actualKey = actualCreateCodeExerciseRequest.getKey();
        Double actualPoints = actualCreateCodeExerciseRequest.getPoints();
        int actualReAttempt = actualCreateCodeExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualCreateCodeExerciseRequest.getTemplate();
        List<TestCase> actualTestCases = actualCreateCodeExerciseRequest.getTestCases();

        // Assert that nothing has changed
        assertEquals("42", actualCreateCodeExerciseRequest.getTopicId());
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertEquals(10.0d, actualPoints.doubleValue());
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
     * {@link CreateCodeExerciseRequest#CreateCodeExerciseRequest(String, String, String, Date, Date, int, int, String, List, Double, List, String)}
     *   <li>{@link CreateCodeExerciseRequest#setAllowedLanguageIds(List)}
     *   <li>{@link CreateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link CreateCodeExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setKey(String)}
     *   <li>{@link CreateCodeExerciseRequest#setPoints(Double)}
     *   <li>{@link CreateCodeExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link CreateCodeExerciseRequest#setTestCases(List)}
     *   <li>{@link CreateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateCodeExerciseRequest#getAllowedLanguageIds()}
     *   <li>{@link CreateCodeExerciseRequest#getDescription()}
     *   <li>{@link CreateCodeExerciseRequest#getDurationTime()}
     *   <li>{@link CreateCodeExerciseRequest#getEndTime()}
     *   <li>{@link CreateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link CreateCodeExerciseRequest#getKey()}
     *   <li>{@link CreateCodeExerciseRequest#getPoints()}
     *   <li>{@link CreateCodeExerciseRequest#getReAttempt()}
     *   <li>{@link CreateCodeExerciseRequest#getStartTime()}
     *   <li>{@link CreateCodeExerciseRequest#getTemplate()}
     *   <li>{@link CreateCodeExerciseRequest#getTestCases()}
     *   <li>{@link CreateCodeExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> allowedLanguageIds = new ArrayList<>();

        // Act
        CreateCodeExerciseRequest actualCreateCodeExerciseRequest = new CreateCodeExerciseRequest("42", "Exercise Name",
                "Key", startTime, endTime, 1, 1, "The characteristics of someone or something", allowedLanguageIds, 10.0d,
                new ArrayList<>(), "Template");
        ArrayList<String> allowedLanguageIds2 = new ArrayList<>();
        actualCreateCodeExerciseRequest.setAllowedLanguageIds(allowedLanguageIds2);
        actualCreateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        actualCreateCodeExerciseRequest.setDurationTime(1);
        Date endTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setEndTime(endTime2);
        actualCreateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualCreateCodeExerciseRequest.setKey("Key");
        actualCreateCodeExerciseRequest.setPoints(10.0d);
        actualCreateCodeExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setStartTime(startTime2);
        actualCreateCodeExerciseRequest.setTemplate("Template");
        ArrayList<TestCase> testCases = new ArrayList<>();
        actualCreateCodeExerciseRequest.setTestCases(testCases);
        actualCreateCodeExerciseRequest.setTopicId("42");
        List<String> actualAllowedLanguageIds = actualCreateCodeExerciseRequest.getAllowedLanguageIds();
        String actualDescription = actualCreateCodeExerciseRequest.getDescription();
        int actualDurationTime = actualCreateCodeExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateCodeExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateCodeExerciseRequest.getExerciseName();
        String actualKey = actualCreateCodeExerciseRequest.getKey();
        Double actualPoints = actualCreateCodeExerciseRequest.getPoints();
        int actualReAttempt = actualCreateCodeExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualCreateCodeExerciseRequest.getTemplate();
        List<TestCase> actualTestCases = actualCreateCodeExerciseRequest.getTestCases();

        // Assert that nothing has changed
        assertEquals("42", actualCreateCodeExerciseRequest.getTopicId());
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(allowedLanguageIds, actualAllowedLanguageIds);
        assertEquals(allowedLanguageIds, actualTestCases);
        assertSame(allowedLanguageIds2, actualAllowedLanguageIds);
        assertSame(testCases, actualTestCases);
        assertSame(endTime2, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
