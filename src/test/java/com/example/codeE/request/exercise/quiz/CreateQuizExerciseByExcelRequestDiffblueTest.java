package com.example.codeE.request.exercise.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CreateQuizExerciseByExcelRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateQuizExerciseByExcelRequest#CreateQuizExerciseByExcelRequest()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setDurationTime(int)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setEndTime(Date)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setExerciseDescription(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setExerciseName(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setKey(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setReAttempt(int)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setStartTime(Date)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setTopicId(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getDurationTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getEndTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getExerciseDescription()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getExerciseName()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getKey()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getReAttempt()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getStartTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateQuizExerciseByExcelRequest actualCreateQuizExerciseByExcelRequest = new CreateQuizExerciseByExcelRequest();
        actualCreateQuizExerciseByExcelRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseByExcelRequest.setEndTime(endTime);
        actualCreateQuizExerciseByExcelRequest.setExerciseDescription("Exercise Description");
        actualCreateQuizExerciseByExcelRequest.setExerciseName("Exercise Name");
        actualCreateQuizExerciseByExcelRequest.setKey("Key");
        actualCreateQuizExerciseByExcelRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseByExcelRequest.setStartTime(startTime);
        actualCreateQuizExerciseByExcelRequest.setTopicId("42");
        int actualDurationTime = actualCreateQuizExerciseByExcelRequest.getDurationTime();
        Date actualEndTime = actualCreateQuizExerciseByExcelRequest.getEndTime();
        String actualExerciseDescription = actualCreateQuizExerciseByExcelRequest.getExerciseDescription();
        String actualExerciseName = actualCreateQuizExerciseByExcelRequest.getExerciseName();
        String actualKey = actualCreateQuizExerciseByExcelRequest.getKey();
        int actualReAttempt = actualCreateQuizExerciseByExcelRequest.getReAttempt();
        Date actualStartTime = actualCreateQuizExerciseByExcelRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateQuizExerciseByExcelRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
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
     * {@link CreateQuizExerciseByExcelRequest#CreateQuizExerciseByExcelRequest(String, String, String, Date, Date, int, int, String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setDurationTime(int)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setEndTime(Date)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setExerciseDescription(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setExerciseName(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setKey(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setReAttempt(int)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setStartTime(Date)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#setTopicId(String)}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getDurationTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getEndTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getExerciseDescription()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getExerciseName()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getKey()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getReAttempt()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getStartTime()}
     *   <li>{@link CreateQuizExerciseByExcelRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        CreateQuizExerciseByExcelRequest actualCreateQuizExerciseByExcelRequest = new CreateQuizExerciseByExcelRequest("42",
                "Exercise Name", "Key", startTime,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1,
                "Exercise Description");
        actualCreateQuizExerciseByExcelRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseByExcelRequest.setEndTime(endTime);
        actualCreateQuizExerciseByExcelRequest.setExerciseDescription("Exercise Description");
        actualCreateQuizExerciseByExcelRequest.setExerciseName("Exercise Name");
        actualCreateQuizExerciseByExcelRequest.setKey("Key");
        actualCreateQuizExerciseByExcelRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseByExcelRequest.setStartTime(startTime2);
        actualCreateQuizExerciseByExcelRequest.setTopicId("42");
        int actualDurationTime = actualCreateQuizExerciseByExcelRequest.getDurationTime();
        Date actualEndTime = actualCreateQuizExerciseByExcelRequest.getEndTime();
        String actualExerciseDescription = actualCreateQuizExerciseByExcelRequest.getExerciseDescription();
        String actualExerciseName = actualCreateQuizExerciseByExcelRequest.getExerciseName();
        String actualKey = actualCreateQuizExerciseByExcelRequest.getKey();
        int actualReAttempt = actualCreateQuizExerciseByExcelRequest.getReAttempt();
        Date actualStartTime = actualCreateQuizExerciseByExcelRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateQuizExerciseByExcelRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertSame(endTime, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
