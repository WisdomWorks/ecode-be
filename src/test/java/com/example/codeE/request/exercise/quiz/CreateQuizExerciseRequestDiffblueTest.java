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

class CreateQuizExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateQuizExerciseRequest#CreateQuizExerciseRequest()}
     *   <li>{@link CreateQuizExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateQuizExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateQuizExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateQuizExerciseRequest#setKey(String)}
     *   <li>{@link CreateQuizExerciseRequest#setQuestions(List)}
     *   <li>{@link CreateQuizExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateQuizExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateQuizExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateQuizExerciseRequest#getDurationTime()}
     *   <li>{@link CreateQuizExerciseRequest#getEndTime()}
     *   <li>{@link CreateQuizExerciseRequest#getExerciseName()}
     *   <li>{@link CreateQuizExerciseRequest#getKey()}
     *   <li>{@link CreateQuizExerciseRequest#getQuestions()}
     *   <li>{@link CreateQuizExerciseRequest#getReAttempt()}
     *   <li>{@link CreateQuizExerciseRequest#getStartTime()}
     *   <li>{@link CreateQuizExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateQuizExerciseRequest actualCreateQuizExerciseRequest = new CreateQuizExerciseRequest();
        actualCreateQuizExerciseRequest.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseRequest.setEndTime(endTime);
        actualCreateQuizExerciseRequest.setExerciseName("Exercise Name");
        actualCreateQuizExerciseRequest.setKey("Key");
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        actualCreateQuizExerciseRequest.setQuestions(questions);
        actualCreateQuizExerciseRequest.setReAttempt(1);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseRequest.setStartTime(startTime);
        actualCreateQuizExerciseRequest.setTopicId("42");
        int actualDurationTime = actualCreateQuizExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateQuizExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateQuizExerciseRequest.getExerciseName();
        String actualKey = actualCreateQuizExerciseRequest.getKey();
        List<QuizQuestion> actualQuestions = actualCreateQuizExerciseRequest.getQuestions();
        int actualReAttempt = actualCreateQuizExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateQuizExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateQuizExerciseRequest.getTopicId());
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
     * {@link CreateQuizExerciseRequest#CreateQuizExerciseRequest(String, String, String, Date, Date, int, int, List)}
     *   <li>{@link CreateQuizExerciseRequest#setDurationTime(int)}
     *   <li>{@link CreateQuizExerciseRequest#setEndTime(Date)}
     *   <li>{@link CreateQuizExerciseRequest#setExerciseName(String)}
     *   <li>{@link CreateQuizExerciseRequest#setKey(String)}
     *   <li>{@link CreateQuizExerciseRequest#setQuestions(List)}
     *   <li>{@link CreateQuizExerciseRequest#setReAttempt(int)}
     *   <li>{@link CreateQuizExerciseRequest#setStartTime(Date)}
     *   <li>{@link CreateQuizExerciseRequest#setTopicId(String)}
     *   <li>{@link CreateQuizExerciseRequest#getDurationTime()}
     *   <li>{@link CreateQuizExerciseRequest#getEndTime()}
     *   <li>{@link CreateQuizExerciseRequest#getExerciseName()}
     *   <li>{@link CreateQuizExerciseRequest#getKey()}
     *   <li>{@link CreateQuizExerciseRequest#getQuestions()}
     *   <li>{@link CreateQuizExerciseRequest#getReAttempt()}
     *   <li>{@link CreateQuizExerciseRequest#getStartTime()}
     *   <li>{@link CreateQuizExerciseRequest#getTopicId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<QuizQuestion> questions = new ArrayList<>();

        // Act
        CreateQuizExerciseRequest actualCreateQuizExerciseRequest = new CreateQuizExerciseRequest("42", "Exercise Name",
                "Key", startTime, endTime, 1, 1, questions);
        actualCreateQuizExerciseRequest.setDurationTime(1);
        Date endTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseRequest.setEndTime(endTime2);
        actualCreateQuizExerciseRequest.setExerciseName("Exercise Name");
        actualCreateQuizExerciseRequest.setKey("Key");
        ArrayList<QuizQuestion> questions2 = new ArrayList<>();
        actualCreateQuizExerciseRequest.setQuestions(questions2);
        actualCreateQuizExerciseRequest.setReAttempt(1);
        Date startTime2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        actualCreateQuizExerciseRequest.setStartTime(startTime2);
        actualCreateQuizExerciseRequest.setTopicId("42");
        int actualDurationTime = actualCreateQuizExerciseRequest.getDurationTime();
        Date actualEndTime = actualCreateQuizExerciseRequest.getEndTime();
        String actualExerciseName = actualCreateQuizExerciseRequest.getExerciseName();
        String actualKey = actualCreateQuizExerciseRequest.getKey();
        List<QuizQuestion> actualQuestions = actualCreateQuizExerciseRequest.getQuestions();
        int actualReAttempt = actualCreateQuizExerciseRequest.getReAttempt();
        Date actualStartTime = actualCreateQuizExerciseRequest.getStartTime();

        // Assert that nothing has changed
        assertEquals("42", actualCreateQuizExerciseRequest.getTopicId());
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
