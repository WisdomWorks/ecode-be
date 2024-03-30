package com.example.codeE.request;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ApiResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApiResponse#ApiResponse()}
     *   <li>{@link ApiResponse#setError(String)}
     *   <li>{@link ApiResponse#setMessage(String)}
     *   <li>{@link ApiResponse#setStatus(int)}
     *   <li>{@link ApiResponse#setValue(Object)}
     *   <li>{@link ApiResponse#setValues(List)}
     *   <li>{@link ApiResponse#getError()}
     *   <li>{@link ApiResponse#getMessage()}
     *   <li>{@link ApiResponse#getStatus()}
     *   <li>{@link ApiResponse#getValue()}
     *   <li>{@link ApiResponse#getValues()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ApiResponse<Object> actualApiResponse = new ApiResponse<>();
        actualApiResponse.setError("An error occurred");
        actualApiResponse.setMessage("Not all who wander are lost");
        actualApiResponse.setStatus(1);
        actualApiResponse.setValue("Value");
        ArrayList<Object> values = new ArrayList<>();
        actualApiResponse.setValues(values);
        String actualError = actualApiResponse.getError();
        String actualMessage = actualApiResponse.getMessage();
        int actualStatus = actualApiResponse.getStatus();
        actualApiResponse.getValue();

        // Assert that nothing has changed
        assertEquals("An error occurred", actualError);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(1, actualStatus);
        assertSame(values, actualApiResponse.getValues());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApiResponse#ApiResponse(List, Object, String, int, String)}
     *   <li>{@link ApiResponse#setError(String)}
     *   <li>{@link ApiResponse#setMessage(String)}
     *   <li>{@link ApiResponse#setStatus(int)}
     *   <li>{@link ApiResponse#setValue(Object)}
     *   <li>{@link ApiResponse#setValues(List)}
     *   <li>{@link ApiResponse#getError()}
     *   <li>{@link ApiResponse#getMessage()}
     *   <li>{@link ApiResponse#getStatus()}
     *   <li>{@link ApiResponse#getValue()}
     *   <li>{@link ApiResponse#getValues()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<Object> values = new ArrayList<>();

        // Act
        ApiResponse<Object> actualApiResponse = new ApiResponse<>(values, "Value", "Not all who wander are lost", 1,
                "An error occurred");
        actualApiResponse.setError("An error occurred");
        actualApiResponse.setMessage("Not all who wander are lost");
        actualApiResponse.setStatus(1);
        actualApiResponse.setValue("Value");
        ArrayList<Object> values2 = new ArrayList<>();
        actualApiResponse.setValues(values2);
        String actualError = actualApiResponse.getError();
        String actualMessage = actualApiResponse.getMessage();
        int actualStatus = actualApiResponse.getStatus();
        actualApiResponse.getValue();
        List<Object> actualValues = actualApiResponse.getValues();

        // Assert that nothing has changed
        assertEquals("An error occurred", actualError);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(1, actualStatus);
        assertEquals(values, actualValues);
        assertSame(values2, actualValues);
    }
}
