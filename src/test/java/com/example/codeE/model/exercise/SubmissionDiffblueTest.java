package com.example.codeE.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubmissionDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Submission#Submission(String, String)}
     *   <li>{@link Submission#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("Submission{submissionId='42', studentId='null', exerciseId='42', score=null, dateSubmit=null,"
                + " dateGrade=null, reviewable=false}", (new Submission("42", "42")).toString());
        assertEquals("Submission{submissionId='null', studentId='42', exerciseId='42', score=10.0, dateSubmit=null,"
                + " dateGrade=null, reviewable=true}", (new Submission("42", "42", 10.0f, true,"")).toString());
        assertEquals("Submission{submissionId='null', studentId='42', exerciseId='42', score=null, dateSubmit=null,"
                + " dateGrade=null, reviewable=true}", (new Submission("42", "42", true, "")).toString());
    }
}
