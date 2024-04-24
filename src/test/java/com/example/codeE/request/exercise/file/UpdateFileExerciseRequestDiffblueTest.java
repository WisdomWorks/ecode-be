package com.example.codeE.request.exercise.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class UpdateFileExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateFileExerciseRequest#UpdateFileExerciseRequest()}
     *   <li>{@link UpdateFileExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateFileExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateFileExerciseRequest#setKey(String)}
     *   <li>{@link UpdateFileExerciseRequest#setQuestion(String)}
     *   <li>{@link UpdateFileExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateFileExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateFileExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateFileExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateFileExerciseRequest#getEndTime()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateFileExerciseRequest#getKey()}
     *   <li>{@link UpdateFileExerciseRequest#getQuestion()}
     *   <li>{@link UpdateFileExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateFileExerciseRequest#getStartTime()}
     *   <li>{@link UpdateFileExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateFileExerciseRequest actualUpdateFileExerciseRequest = new UpdateFileExerciseRequest();
        actualUpdateFileExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateFileExerciseRequest.setEndTime(endTime);
        actualUpdateFileExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateFileExerciseRequest.setExerciseId("42");
        actualUpdateFileExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateFileExerciseRequest.setKey("Key");
        actualUpdateFileExerciseRequest.setQuestion("Question");
        actualUpdateFileExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateFileExerciseRequest.setStartTime(startTime);
        actualUpdateFileExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateFileExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateFileExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateFileExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateFileExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateFileExerciseRequest.getExerciseName();
        String actualKey = actualUpdateFileExerciseRequest.getKey();
        String actualQuestion = actualUpdateFileExerciseRequest.getQuestion();
        int actualReAttempt = actualUpdateFileExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateFileExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateFileExerciseRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
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
     * {@link UpdateFileExerciseRequest#UpdateFileExerciseRequest(String, String, String, String, Date, Date, int, int, String, String)}
     *   <li>{@link UpdateFileExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateFileExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateFileExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateFileExerciseRequest#setKey(String)}
     *   <li>{@link UpdateFileExerciseRequest#setQuestion(String)}
     *   <li>{@link UpdateFileExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateFileExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateFileExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateFileExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateFileExerciseRequest#getEndTime()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateFileExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateFileExerciseRequest#getKey()}
     *   <li>{@link UpdateFileExerciseRequest#getQuestion()}
     *   <li>{@link UpdateFileExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateFileExerciseRequest#getStartTime()}
     *   <li>{@link UpdateFileExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        UpdateFileExerciseRequest actualUpdateFileExerciseRequest = new UpdateFileExerciseRequest("42", "42",
                "Exercise Name", "Key", startTime,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1,
                "Exercise Description", "Question");
        actualUpdateFileExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateFileExerciseRequest.setEndTime(endTime);
        actualUpdateFileExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateFileExerciseRequest.setExerciseId("42");
        actualUpdateFileExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateFileExerciseRequest.setKey("Key");
        actualUpdateFileExerciseRequest.setQuestion("Question");
        actualUpdateFileExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateFileExerciseRequest.setStartTime(startTime2);
        actualUpdateFileExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateFileExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateFileExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateFileExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateFileExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateFileExerciseRequest.getExerciseName();
        String actualKey = actualUpdateFileExerciseRequest.getKey();
        String actualQuestion = actualUpdateFileExerciseRequest.getQuestion();
        int actualReAttempt = actualUpdateFileExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateFileExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateFileExerciseRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals("Question", actualQuestion);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertSame(endTime, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
