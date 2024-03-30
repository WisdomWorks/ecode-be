package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
     *   <li>{@link CreateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link CreateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setFunctionName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setKey(String)}
     *   <li>{@link CreateCodeExerciseRequest#setLanguage(String)}
     *   <li>{@link CreateCodeExerciseRequest#setPublicGroupIds(List)}
     *   <li>{@link CreateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link CreateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateCodeExerciseRequest#setType(String)}
     *   <li>{@link CreateCodeExerciseRequest#getDescription()}
     *   <li>{@link CreateCodeExerciseRequest#getEndTime()}
     *   <li>{@link CreateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link CreateCodeExerciseRequest#getFunctionName()}
     *   <li>{@link CreateCodeExerciseRequest#getKey()}
     *   <li>{@link CreateCodeExerciseRequest#getLanguage()}
     *   <li>{@link CreateCodeExerciseRequest#getPublicGroupIds()}
     *   <li>{@link CreateCodeExerciseRequest#getStartTime()}
     *   <li>{@link CreateCodeExerciseRequest#getTemplate()}
     *   <li>{@link CreateCodeExerciseRequest#getTopicId()}
     *   <li>{@link CreateCodeExerciseRequest#getType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateCodeExerciseRequest actualCreateCodeExerciseRequest = new CreateCodeExerciseRequest();
        actualCreateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setEndTime(endTime);
        actualCreateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualCreateCodeExerciseRequest.setFunctionName("Function Name");
        actualCreateCodeExerciseRequest.setKey("Key");
        actualCreateCodeExerciseRequest.setLanguage("en");
        ArrayList<String> publicGroupIds = new ArrayList<>();
        actualCreateCodeExerciseRequest.setPublicGroupIds(publicGroupIds);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setStartTime(startTime);
        actualCreateCodeExerciseRequest.setTemplate("Template");
        actualCreateCodeExerciseRequest.setTopicId("42");
        actualCreateCodeExerciseRequest.setType("Type");
        String actualDescription = actualCreateCodeExerciseRequest.getDescription();
        Date actualEndTime = actualCreateCodeExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateCodeExerciseRequest.getExerciseName();
        String actualFunctionName = actualCreateCodeExerciseRequest.getFunctionName();
        String actualKey = actualCreateCodeExerciseRequest.getKey();
        String actualLanguage = actualCreateCodeExerciseRequest.getLanguage();
        List<String> actualPublicGroupIds = actualCreateCodeExerciseRequest.getPublicGroupIds();
        Date actualStartTime = actualCreateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualCreateCodeExerciseRequest.getTemplate();
        String actualTopicId = actualCreateCodeExerciseRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Function Name", actualFunctionName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Type", actualCreateCodeExerciseRequest.getType());
        assertEquals("en", actualLanguage);
        assertSame(publicGroupIds, actualPublicGroupIds);
        assertSame(endTime, actualEndTime);
        assertSame(startTime, actualStartTime);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateCodeExerciseRequest#CreateCodeExerciseRequest(String, String, String, Date, Date, String, List, String, String, String, String)}
     *   <li>{@link CreateCodeExerciseRequest#setDescription(String)}
     *   <li>{@link CreateCodeExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setFunctionName(String)}
     *   <li>{@link CreateCodeExerciseRequest#setKey(String)}
     *   <li>{@link CreateCodeExerciseRequest#setLanguage(String)}
     *   <li>{@link CreateCodeExerciseRequest#setPublicGroupIds(List)}
     *   <li>{@link CreateCodeExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateCodeExerciseRequest#setTemplate(String)}
     *   <li>{@link CreateCodeExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateCodeExerciseRequest#setType(String)}
     *   <li>{@link CreateCodeExerciseRequest#getDescription()}
     *   <li>{@link CreateCodeExerciseRequest#getEndTime()}
     *   <li>{@link CreateCodeExerciseRequest#getExerciseName()}
     *   <li>{@link CreateCodeExerciseRequest#getFunctionName()}
     *   <li>{@link CreateCodeExerciseRequest#getKey()}
     *   <li>{@link CreateCodeExerciseRequest#getLanguage()}
     *   <li>{@link CreateCodeExerciseRequest#getPublicGroupIds()}
     *   <li>{@link CreateCodeExerciseRequest#getStartTime()}
     *   <li>{@link CreateCodeExerciseRequest#getTemplate()}
     *   <li>{@link CreateCodeExerciseRequest#getTopicId()}
     *   <li>{@link CreateCodeExerciseRequest#getType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> publicGroupIds = new ArrayList<>();

        // Act
        CreateCodeExerciseRequest actualCreateCodeExerciseRequest = new CreateCodeExerciseRequest("42", "Exercise Name",
                "Key", startTime, endTime, "Type", publicGroupIds, "en", "Function Name", "Template",
                "The characteristics of someone or something");
        actualCreateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        Date endTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setEndTime(endTime2);
        actualCreateCodeExerciseRequest.setExerciseName("Exercise Name");
        actualCreateCodeExerciseRequest.setFunctionName("Function Name");
        actualCreateCodeExerciseRequest.setKey("Key");
        actualCreateCodeExerciseRequest.setLanguage("en");
        ArrayList<String> publicGroupIds2 = new ArrayList<>();
        actualCreateCodeExerciseRequest.setPublicGroupIds(publicGroupIds2);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateCodeExerciseRequest.setStartTime(startTime2);
        actualCreateCodeExerciseRequest.setTemplate("Template");
        actualCreateCodeExerciseRequest.setTopicId("42");
        actualCreateCodeExerciseRequest.setType("Type");
        String actualDescription = actualCreateCodeExerciseRequest.getDescription();
        Date actualEndTime = actualCreateCodeExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateCodeExerciseRequest.getExerciseName();
        String actualFunctionName = actualCreateCodeExerciseRequest.getFunctionName();
        String actualKey = actualCreateCodeExerciseRequest.getKey();
        String actualLanguage = actualCreateCodeExerciseRequest.getLanguage();
        List<String> actualPublicGroupIds = actualCreateCodeExerciseRequest.getPublicGroupIds();
        Date actualStartTime = actualCreateCodeExerciseRequest.getStartTime();
        String actualTemplate = actualCreateCodeExerciseRequest.getTemplate();
        String actualTopicId = actualCreateCodeExerciseRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Function Name", actualFunctionName);
        assertEquals("Key", actualKey);
        assertEquals("Template", actualTemplate);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Type", actualCreateCodeExerciseRequest.getType());
        assertEquals("en", actualLanguage);
        assertEquals(publicGroupIds, actualPublicGroupIds);
        assertSame(publicGroupIds2, actualPublicGroupIds);
        assertSame(endTime2, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
