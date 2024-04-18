package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizAnswersDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link QuizAnswers#QuizAnswers(String, List)}
     *   <li>{@link QuizAnswers#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("QuizAnswers{quizAnswerId='null', questionId='42', answers=[]}",
                (new QuizAnswers("42", new ArrayList<>())).toString());
    }
}
