package com.example.codeE.request.exercise.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.common.QuizQuestion;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class UpdateQuizExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateQuizExerciseRequest#UpdateQuizExerciseRequest()}
     *   <li>{@link UpdateQuizExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateQuizExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setKey(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setQuestions(List)}
     *   <li>{@link UpdateQuizExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateQuizExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateQuizExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateQuizExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getEndTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateQuizExerciseRequest#getKey()}
     *   <li>{@link UpdateQuizExerciseRequest#getQuestions()}
     *   <li>{@link UpdateQuizExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateQuizExerciseRequest#getStartTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateQuizExerciseRequest actualUpdateQuizExerciseRequest = new UpdateQuizExerciseRequest();
        actualUpdateQuizExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateQuizExerciseRequest.setEndTime(endTime);
        actualUpdateQuizExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateQuizExerciseRequest.setExerciseId("42");
        actualUpdateQuizExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateQuizExerciseRequest.setKey("Key");
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        actualUpdateQuizExerciseRequest.setQuestions(questions);
        actualUpdateQuizExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateQuizExerciseRequest.setStartTime(startTime);
        actualUpdateQuizExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateQuizExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateQuizExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateQuizExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateQuizExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateQuizExerciseRequest.getExerciseName();
        String actualKey = actualUpdateQuizExerciseRequest.getKey();
        List<QuizQuestion> actualQuestions = actualUpdateQuizExerciseRequest.getQuestions();
        int actualReAttempt = actualUpdateQuizExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateQuizExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateQuizExerciseRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertSame(questions, actualQuestions);
        assertSame(endTime, actualEndTime);
        assertSame(startTime, actualStartTime);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateQuizExerciseRequest#UpdateQuizExerciseRequest(String, String, String, String, Date, Date, int, int, String, List)}
     *   <li>{@link UpdateQuizExerciseRequest#setDurationTime(int)}
     *   <li>{@link UpdateQuizExerciseRequest#setEndTime(Date)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseDescription(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseId(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setExerciseName(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setKey(String)}
     *   <li>{@link UpdateQuizExerciseRequest#setQuestions(List)}
     *   <li>{@link UpdateQuizExerciseRequest#setReAttempt(int)}
     *   <li>{@link UpdateQuizExerciseRequest#setStartTime(Date)}
     *   <li>{@link UpdateQuizExerciseRequest#setTopicId(String)}
     *   <li>{@link UpdateQuizExerciseRequest#getDurationTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getEndTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseDescription()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseId()}
     *   <li>{@link UpdateQuizExerciseRequest#getExerciseName()}
     *   <li>{@link UpdateQuizExerciseRequest#getKey()}
     *   <li>{@link UpdateQuizExerciseRequest#getQuestions()}
     *   <li>{@link UpdateQuizExerciseRequest#getReAttempt()}
     *   <li>{@link UpdateQuizExerciseRequest#getStartTime()}
     *   <li>{@link UpdateQuizExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<QuizQuestion> questions = new ArrayList<>();

        // Act
        UpdateQuizExerciseRequest actualUpdateQuizExerciseRequest = new UpdateQuizExerciseRequest("42", "42",
                "Exercise Name", "Key", startTime, endTime, 1, 1, "Exercise Description", questions);
        actualUpdateQuizExerciseRequest.setDurationTime(1);
        Date endTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateQuizExerciseRequest.setEndTime(endTime2);
        actualUpdateQuizExerciseRequest.setExerciseDescription("Exercise Description");
        actualUpdateQuizExerciseRequest.setExerciseId("42");
        actualUpdateQuizExerciseRequest.setExerciseName("Exercise Name");
        actualUpdateQuizExerciseRequest.setKey("Key");
        ArrayList<QuizQuestion> questions2 = new ArrayList<>();
        actualUpdateQuizExerciseRequest.setQuestions(questions2);
        actualUpdateQuizExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualUpdateQuizExerciseRequest.setStartTime(startTime2);
        actualUpdateQuizExerciseRequest.setTopicId("42");
        int actualDurationTime = actualUpdateQuizExerciseRequest.getDurationTime();
        Date actualEndTime = actualUpdateQuizExerciseRequest.getEndTime();
        String actualExerciseDescription = actualUpdateQuizExerciseRequest.getExerciseDescription();
        String actualExerciseId = actualUpdateQuizExerciseRequest.getExerciseId();
        String actualExerciseName = actualUpdateQuizExerciseRequest.getExerciseName();
        String actualKey = actualUpdateQuizExerciseRequest.getKey();
        List<QuizQuestion> actualQuestions = actualUpdateQuizExerciseRequest.getQuestions();
        int actualReAttempt = actualUpdateQuizExerciseRequest.getReAttempt();
        Date actualStartTime = actualUpdateQuizExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualUpdateQuizExerciseRequest.getTopicId());
        assertEquals("Exercise Description", actualExerciseDescription);
        assertEquals("Exercise Name", actualExerciseName);
        assertEquals("Key", actualKey);
        assertEquals(1, actualDurationTime);
        assertEquals(1, actualReAttempt);
        assertEquals(questions, actualQuestions);
        assertSame(questions2, actualQuestions);
        assertSame(endTime2, actualEndTime);
        assertSame(startTime2, actualStartTime);
    }
}
