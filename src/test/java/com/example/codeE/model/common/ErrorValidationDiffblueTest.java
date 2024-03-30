package com.example.codeE.model.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ErrorValidationDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorValidation#ErrorValidation()}
     *   <li>{@link ErrorValidation#setMessage(String)}
     *   <li>{@link ErrorValidation#setParam(String)}
     *   <li>{@link ErrorValidation#getMessage()}
     *   <li>{@link ErrorValidation#getParam()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ErrorValidation actualErrorValidation = new ErrorValidation();
        actualErrorValidation.setMessage("Not all who wander are lost");
        actualErrorValidation.setParam("Param");
        String actualMessage = actualErrorValidation.getMessage();

        // Assert that nothing has changed
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals("Param", actualErrorValidation.getParam());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ErrorValidation#ErrorValidation(String, String)}
     *   <li>{@link ErrorValidation#setMessage(String)}
     *   <li>{@link ErrorValidation#setParam(String)}
     *   <li>{@link ErrorValidation#getMessage()}
     *   <li>{@link ErrorValidation#getParam()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ErrorValidation actualErrorValidation = new ErrorValidation("Param", "Not all who wander are lost");
        actualErrorValidation.setMessage("Not all who wander are lost");
        actualErrorValidation.setParam("Param");
        String actualMessage = actualErrorValidation.getMessage();

        // Assert that nothing has changed
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals("Param", actualErrorValidation.getParam());
    }
}
