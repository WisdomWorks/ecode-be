package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class SessionExerciseDiffblueTest {
    /**
     * Method under test:
     * {@link SessionExercise#SessionExercise(String, String, String, String, String)}
     */
    @Test
    void testNewSessionExercise() {
        // Arrange and Act
        SessionExercise actualSessionExercise = new SessionExercise("42", "42", "42", "Time Start", "User Urgent");

        // Assert
        assertEquals("42", actualSessionExercise.getExerciseId());
        assertEquals("42", actualSessionExercise.getLoginId());
        assertEquals("42", actualSessionExercise.getStudentId());
        assertEquals("Time Start", actualSessionExercise.getTimeStart());
        assertEquals("User Urgent", actualSessionExercise.getUserUrgent());
        assertNull(actualSessionExercise.getSessionId());
    }
}
