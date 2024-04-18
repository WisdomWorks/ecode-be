package com.example.codeE.request.exercise.essay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CreateEssayExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateEssayExerciseRequest#CreateEssayExerciseRequest()}
     *   <li>{@link CreateEssayExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateEssayExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateEssayExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link CreateEssayExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateEssayExerciseRequest#setKey(String)}
     *   <li>{@link CreateEssayExerciseRequest#setQuestion(String)}
     *   <li>{@link CreateEssayExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateEssayExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateEssayExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateEssayExerciseRequest#setUsingAiGrading(boolean)}
     *   <li>{@link CreateEssayExerciseRequest#getDurationTime()}
     *   <li>{@link CreateEssayExerciseRequest#getEndTime()}
     *   <li>{@link CreateEssayExerciseRequest#getExerciseDescription()}
     *   <li>{@link CreateEssayExerciseRequest#getExerciseName()}
     *   <li>{@link CreateEssayExerciseRequest#getKey()}
     *   <li>{@link CreateEssayExerciseRequest#getQuestion()}
     *   <li>{@link CreateEssayExerciseRequest#getReAttempt()}
     *   <li>{@link CreateEssayExerciseRequest#getStartTime()}
     *   <li>{@link CreateEssayExerciseRequest#getTopicId()}
     *   <li>{@link CreateEssayExerciseRequest#isUsingAiGrading()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateEssayExerciseRequest actualCreateEssayExerciseRequest = new CreateEssayExerciseRequest();
        actualCreateEssayExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateEssayExerciseRequest.setEndTime(endTime);
        actualCreateEssayExerciseRequest.setExerciseDescription("Exercise Description");
        actualCreateEssayExerciseRequest.setExerciseName("Exercise Name");
        actualCreateEssayExerciseRequest.setKey("Key");
        actualCreateEssayExerciseRequest.setQuestion("Question");
        actualCreateEssayExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateEssayExerciseRequest.setStartTime(startTime);
        actualCreateEssayExerciseRequest.setTopicId("42");
        actualCreateEssayExerciseRequest.setUsingAiGrading(true);
        int actualDurationTime = actualCreateEssayExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateEssayExerciseRequest.getEndTime();
        String actualExerciseDescription = actualCreateEssayExerciseRequest.getExerciseDescription();
        String actualExerciseName = actualCreateEssayExerciseRequest.getExerciseName();
        String actualKey = actualCreateEssayExerciseRequest.getKey();
        String actualQuestion = actualCreateEssayExerciseRequest.getQuestion();
        int actualReAttempt = actualCreateEssayExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateEssayExerciseRequest.getStartTime();
        String actualTopicId = actualCreateEssayExerciseRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Question", actualQuestion);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertTrue(actualCreateEssayExerciseRequest.isUsingAiGrading());
        assertSame(endTime, actualEndTime);
        assertSame(startTime, actualStartTime);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateEssayExerciseRequest#CreateEssayExerciseRequest(String, String, String, Date, Date, int, int, String, String, boolean)}
     *   <li>{@link CreateEssayExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateEssayExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateEssayExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link CreateEssayExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateEssayExerciseRequest#setKey(String)}
     *   <li>{@link CreateEssayExerciseRequest#setQuestion(String)}
     *   <li>{@link CreateEssayExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateEssayExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateEssayExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateEssayExerciseRequest#setUsingAiGrading(boolean)}
     *   <li>{@link CreateEssayExerciseRequest#getDurationTime()}
     *   <li>{@link CreateEssayExerciseRequest#getEndTime()}
     *   <li>{@link CreateEssayExerciseRequest#getExerciseDescription()}
     *   <li>{@link CreateEssayExerciseRequest#getExerciseName()}
     *   <li>{@link CreateEssayExerciseRequest#getKey()}
     *   <li>{@link CreateEssayExerciseRequest#getQuestion()}
     *   <li>{@link CreateEssayExerciseRequest#getReAttempt()}
     *   <li>{@link CreateEssayExerciseRequest#getStartTime()}
     *   <li>{@link CreateEssayExerciseRequest#getTopicId()}
     *   <li>{@link CreateEssayExerciseRequest#isUsingAiGrading()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        CreateEssayExerciseRequest actualCreateEssayExerciseRequest = new CreateEssayExerciseRequest("42", "Exercise Name",
                "Key", startTime, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1,
                "Exercise Description", "Question", true);
        actualCreateEssayExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateEssayExerciseRequest.setEndTime(endTime);
        actualCreateEssayExerciseRequest.setExerciseDescription("Exercise Description");
        actualCreateEssayExerciseRequest.setExerciseName("Exercise Name");
        actualCreateEssayExerciseRequest.setKey("Key");
        actualCreateEssayExerciseRequest.setQuestion("Question");
        actualCreateEssayExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateEssayExerciseRequest.setStartTime(startTime2);
        actualCreateEssayExerciseRequest.setTopicId("42");
        actualCreateEssayExerciseRequest.setUsingAiGrading(true);
        int actualDurationTime = actualCreateEssayExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateEssayExerciseRequest.getEndTime();
        String actualExerciseDescription = actualCreateEssayExerciseRequest.getExerciseDescription();
        String actualExerciseName = actualCreateEssayExerciseRequest.getExerciseName();
        String actualKey = actualCreateEssayExerciseRequest.getKey();
        String actualQuestion = actualCreateEssayExerciseRequest.getQuestion();
        int actualReAttempt = actualCreateEssayExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateEssayExerciseRequest.getStartTime();
        String actualTopicId = actualCreateEssayExerciseRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Question", actualQuestion);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertTrue(actualCreateEssayExerciseRequest.isUsingAiGrading());
        assertSame(endTime, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
