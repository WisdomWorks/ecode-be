package com.example.codeE.request.exercise.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;

import org.junit.jupiter.api.Test;

class CreateFileExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateFileExerciseRequest#CreateFileExerciseRequest()}
     *   <li>{@link CreateFileExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateFileExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateFileExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link CreateFileExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateFileExerciseRequest#setKey(String)}
     *   <li>{@link CreateFileExerciseRequest#setQuestion(String)}
     *   <li>{@link CreateFileExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateFileExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateFileExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateFileExerciseRequest#getDurationTime()}
     *   <li>{@link CreateFileExerciseRequest#getEndTime()}
     *   <li>{@link CreateFileExerciseRequest#getExerciseDescription()}
     *   <li>{@link CreateFileExerciseRequest#getExerciseName()}
     *   <li>{@link CreateFileExerciseRequest#getKey()}
     *   <li>{@link CreateFileExerciseRequest#getQuestion()}
     *   <li>{@link CreateFileExerciseRequest#getReAttempt()}
     *   <li>{@link CreateFileExerciseRequest#getStartTime()}
     *   <li>{@link CreateFileExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateFileExerciseRequest actualCreateFileExerciseRequest = new CreateFileExerciseRequest();
        actualCreateFileExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateFileExerciseRequest.setEndTime(endTime);
        actualCreateFileExerciseRequest.setExerciseDescription("Exercise Description");
        actualCreateFileExerciseRequest.setExerciseName("Exercise Name");
        actualCreateFileExerciseRequest.setKey("Key");
        actualCreateFileExerciseRequest.setQuestion("Question");
        actualCreateFileExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateFileExerciseRequest.setStartTime(startTime);
        actualCreateFileExerciseRequest.setTopicId("42");
        int actualDurationTime = actualCreateFileExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateFileExerciseRequest.getEndTime();
        String actualExerciseDescription = actualCreateFileExerciseRequest.getExerciseDescription();
        String actualExerciseName = actualCreateFileExerciseRequest.getExerciseName();
        String actualKey = actualCreateFileExerciseRequest.getKey();
        String actualQuestion = actualCreateFileExerciseRequest.getQuestion();
        int actualReAttempt = actualCreateFileExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateFileExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateFileExerciseRequest.getTopicId());
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
     * {@link CreateFileExerciseRequest#CreateFileExerciseRequest(String, String, String, Date, Date, int, int, String, String)}
     *   <li>{@link CreateFileExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateFileExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateFileExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link CreateFileExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateFileExerciseRequest#setKey(String)}
     *   <li>{@link CreateFileExerciseRequest#setQuestion(String)}
     *   <li>{@link CreateFileExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateFileExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateFileExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateFileExerciseRequest#getDurationTime()}
     *   <li>{@link CreateFileExerciseRequest#getEndTime()}
     *   <li>{@link CreateFileExerciseRequest#getExerciseDescription()}
     *   <li>{@link CreateFileExerciseRequest#getExerciseName()}
     *   <li>{@link CreateFileExerciseRequest#getKey()}
     *   <li>{@link CreateFileExerciseRequest#getQuestion()}
     *   <li>{@link CreateFileExerciseRequest#getReAttempt()}
     *   <li>{@link CreateFileExerciseRequest#getStartTime()}
     *   <li>{@link CreateFileExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        CreateFileExerciseRequest actualCreateFileExerciseRequest = new CreateFileExerciseRequest("42", "Exercise Name",
                "Key", startTime, Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()), 1, 1,
                "Exercise Description", "Question");
        actualCreateFileExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateFileExerciseRequest.setEndTime(endTime);
        actualCreateFileExerciseRequest.setExerciseDescription("Exercise Description");
        actualCreateFileExerciseRequest.setExerciseName("Exercise Name");
        actualCreateFileExerciseRequest.setKey("Key");
        actualCreateFileExerciseRequest.setQuestion("Question");
        actualCreateFileExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateFileExerciseRequest.setStartTime(startTime2);
        actualCreateFileExerciseRequest.setTopicId("42");
        int actualDurationTime = actualCreateFileExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateFileExerciseRequest.getEndTime();
        String actualExerciseDescription = actualCreateFileExerciseRequest.getExerciseDescription();
        String actualExerciseName = actualCreateFileExerciseRequest.getExerciseName();
        String actualKey = actualCreateFileExerciseRequest.getKey();
        String actualQuestion = actualCreateFileExerciseRequest.getQuestion();
        int actualReAttempt = actualCreateFileExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateFileExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateFileExerciseRequest.getTopicId());
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
