package com.example.codeE.model.exercise.vertexAi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GradingResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GradingResponse#GradingResponse()}
     *   <li>{@link GradingResponse#setComment(String)}
     *   <li>{@link GradingResponse#setScore(float)}
     *   <li>{@link GradingResponse#getComment()}
     *   <li>{@link GradingResponse#getScore()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GradingResponse actualGradingResponse = new GradingResponse();
        actualGradingResponse.setComment("Comment");
        actualGradingResponse.setScore(10.0f);
        String actualComment = actualGradingResponse.getComment();

        // Assert that nothing has changed
        assertEquals("Comment", actualComment);
        assertEquals(10.0f, actualGradingResponse.getScore());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GradingResponse#GradingResponse(float, String)}
     *   <li>{@link GradingResponse#setComment(String)}
     *   <li>{@link GradingResponse#setScore(float)}
     *   <li>{@link GradingResponse#getComment()}
     *   <li>{@link GradingResponse#getScore()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        GradingResponse actualGradingResponse = new GradingResponse(10.0f, "Comment");
        actualGradingResponse.setComment("Comment");
        actualGradingResponse.setScore(10.0f);
        String actualComment = actualGradingResponse.getComment();

        // Assert that nothing has changed
        assertEquals("Comment", actualComment);
        assertEquals(10.0f, actualGradingResponse.getScore());
    }
}
