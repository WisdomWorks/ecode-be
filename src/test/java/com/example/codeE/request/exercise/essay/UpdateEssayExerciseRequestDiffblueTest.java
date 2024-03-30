package com.example.codeE.request.exercise.essay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UpdateEssayExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateEssayExerciseRequest#UpdateEssayExerciseRequest()}
     *   <li>{@link UpdateEssayExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateEssayExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateEssayExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setKey(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setQuestion(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateEssayExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateEssayExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateEssayExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getEndTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateEssayExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateEssayExerciseRequest#getKey()}
     *   <li>{@link UpdateEssayExerciseRequest#getQuestion()}
     *   <li>{@link UpdateEssayExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateEssayExerciseRequest#getStartTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateEssayExerciseRequest actualUpdateEssayExerciseRequest = new UpdateEssayExerciseRequest();
        actualUpdateEssayExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateEssayExerciseRequest.setEndTime(endTime);
        actualUpdateEssayExerciseRequest.setExerciseId("42");
        actualUpdateEssayExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateEssayExerciseRequest.setKey("Key");
        actualUpdateEssayExerciseRequest.setQuestion("Question");
        actualUpdateEssayExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateEssayExerciseRequest.setStartTime(startTime);
        actualUpdateEssayExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateEssayExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateEssayExerciseRequest.getEndTime();
        String actualExerciseId = actualUpdateEssayExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateEssayExerciseRequest.getExerciseName();
        String actualKey = actualUpdateEssayExerciseRequest.getKey();
        String actualQuestion = actualUpdateEssayExerciseRequest.getQuestion();
        int actualReAttempt = actualUpdateEssayExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateEssayExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateEssayExerciseRequest.getTopicId());
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Question", actualQuestion);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertSame(endTime, actualEndTime);
        assertSame(startTime, actualStartTime);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateEssayExerciseRequest#UpdateEssayExerciseRequest(String, String, String, String, Date, Date, int, int, String)}
     *   <li>{@link UpdateEssayExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateEssayExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateEssayExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setKey(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setQuestion(String)}
     *   <li>{@link UpdateEssayExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateEssayExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateEssayExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateEssayExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getEndTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateEssayExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateEssayExerciseRequest#getKey()}
     *   <li>{@link UpdateEssayExerciseRequest#getQuestion()}
     *   <li>{@link UpdateEssayExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateEssayExerciseRequest#getStartTime()}
     *   <li>{@link UpdateEssayExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        UpdateEssayExerciseRequest actualUpdateEssayExerciseRequest = new UpdateEssayExerciseRequest("42", "42",
                "Exercise Name", "Key", startTime,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1, "Question");
        actualUpdateEssayExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateEssayExerciseRequest.setEndTime(endTime);
        actualUpdateEssayExerciseRequest.setExerciseId("42");
        actualUpdateEssayExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateEssayExerciseRequest.setKey("Key");
        actualUpdateEssayExerciseRequest.setQuestion("Question");
        actualUpdateEssayExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateEssayExerciseRequest.setStartTime(startTime2);
        actualUpdateEssayExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateEssayExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateEssayExerciseRequest.getEndTime();
        String actualExerciseId = actualUpdateEssayExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateEssayExerciseRequest.getExerciseName();
        String actualKey = actualUpdateEssayExerciseRequest.getKey();
        String actualQuestion = actualUpdateEssayExerciseRequest.getQuestion();
        int actualReAttempt = actualUpdateEssayExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateEssayExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateEssayExerciseRequest.getTopicId());
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Question", actualQuestion);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertSame(endTime, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
