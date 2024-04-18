package com.example.codeE.request.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GradeSubmissionDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GradeSubmission#GradeSubmission()}
     *   <li>{@link GradeSubmission#setComment(String)}
     *   <li>{@link GradeSubmission#setScore(float)}
     *   <li>{@link GradeSubmission#setSubmissionId(String)}
     *   <li>{@link GradeSubmission#getComment()}
     *   <li>{@link GradeSubmission#getScore()}
     *   <li>{@link GradeSubmission#getSubmissionId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GradeSubmission actualGradeSubmission = new GradeSubmission();
        actualGradeSubmission.setComment("Comment");
        actualGradeSubmission.setScore(10.0f);
        actualGradeSubmission.setSubmissionId("42");
        String actualComment = actualGradeSubmission.getComment();
        float actualScore = actualGradeSubmission.getScore();

        // Assert that nothing has changed
        assertEquals("42", actualGradeSubmission.getSubmissionId());
        assertEquals("Comment", actualComment);
        assertEquals(10.0f, actualScore);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GradeSubmission#GradeSubmission(String, float, String)}
     *   <li>{@link GradeSubmission#setComment(String)}
     *   <li>{@link GradeSubmission#setScore(float)}
     *   <li>{@link GradeSubmission#setSubmissionId(String)}
     *   <li>{@link GradeSubmission#getComment()}
     *   <li>{@link GradeSubmission#getScore()}
     *   <li>{@link GradeSubmission#getSubmissionId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        GradeSubmission actualGradeSubmission = new GradeSubmission("42", 10.0f, "Comment");
        actualGradeSubmission.setComment("Comment");
        actualGradeSubmission.setScore(10.0f);
        actualGradeSubmission.setSubmissionId("42");
        String actualComment = actualGradeSubmission.getComment();
        float actualScore = actualGradeSubmission.getScore();

        // Assert that nothing has changed
        assertEquals("42", actualGradeSubmission.getSubmissionId());
        assertEquals("Comment", actualComment);
        assertEquals(10.0f, actualScore);
    }
}
