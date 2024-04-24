package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizQuestionDiffblueTest {
    /**
     * Method under test:
     * {@link QuizQuestion#QuizQuestion(String, String, List, List)}
     */
    @Test
    void testNewQuizQuestion() {
        // Arrange
        ArrayList<QuizChoice> choices = new ArrayList<>();
        ArrayList<QuizChoice> answers = new ArrayList<>();

        // Act
        QuizQuestion actualQuizQuestion = new QuizQuestion("Dr", "The characteristics of someone or something", choices,
                answers);

        // Assert
        assertEquals("Dr", actualQuizQuestion.getTitle());
        assertEquals("The characteristics of someone or something", actualQuizQuestion.getDescription());
        assertNull(actualQuizQuestion.getQuestionId());
        List<QuizChoice> answers2 = actualQuizQuestion.getAnswers();
        assertTrue(answers2.isEmpty());
        List<QuizChoice> choices2 = actualQuizQuestion.getChoices();
        assertTrue(choices2.isEmpty());
        assertEquals(choices, answers2);
        assertSame(answers, answers2);
        assertSame(choices, choices2);
    }
}
