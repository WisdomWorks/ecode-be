package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CourseEnrollmentResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CourseEnrollmentResponse#CourseEnrollmentResponse()}
     *   <li>{@link CourseEnrollmentResponse#setError(String)}
     *   <li>{@link CourseEnrollmentResponse#setMessage(String)}
     *   <li>{@link CourseEnrollmentResponse#setStatus(int)}
     *   <li>{@link CourseEnrollmentResponse#setValue(Object)}
     *   <li>{@link CourseEnrollmentResponse#setValues(List)}
     *   <li>{@link CourseEnrollmentResponse#getError()}
     *   <li>{@link CourseEnrollmentResponse#getMessage()}
     *   <li>{@link CourseEnrollmentResponse#getStatus()}
     *   <li>{@link CourseEnrollmentResponse#getValue()}
     *   <li>{@link CourseEnrollmentResponse#getValues()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CourseEnrollmentResponse<Object> actualCourseEnrollmentResponse = new CourseEnrollmentResponse<>();
        actualCourseEnrollmentResponse.setError("An error occurred");
        actualCourseEnrollmentResponse.setMessage("Not all who wander are lost");
        actualCourseEnrollmentResponse.setStatus(1);
        actualCourseEnrollmentResponse.setValue("Value");
        ArrayList<Object> values = new ArrayList<>();
        actualCourseEnrollmentResponse.setValues(values);
        String actualError = actualCourseEnrollmentResponse.getError();
        String actualMessage = actualCourseEnrollmentResponse.getMessage();
        int actualStatus = actualCourseEnrollmentResponse.getStatus();
        actualCourseEnrollmentResponse.getValue();

        // Assert that nothing has changed
        assertEquals("An error occurred", actualError);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(1, actualStatus);
        assertSame(values, actualCourseEnrollmentResponse.getValues());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CourseEnrollmentResponse#CourseEnrollmentResponse(String, String, Object, List, int)}
     *   <li>{@link CourseEnrollmentResponse#setError(String)}
     *   <li>{@link CourseEnrollmentResponse#setMessage(String)}
     *   <li>{@link CourseEnrollmentResponse#setStatus(int)}
     *   <li>{@link CourseEnrollmentResponse#setValue(Object)}
     *   <li>{@link CourseEnrollmentResponse#setValues(List)}
     *   <li>{@link CourseEnrollmentResponse#getError()}
     *   <li>{@link CourseEnrollmentResponse#getMessage()}
     *   <li>{@link CourseEnrollmentResponse#getStatus()}
     *   <li>{@link CourseEnrollmentResponse#getValue()}
     *   <li>{@link CourseEnrollmentResponse#getValues()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<Object> values = new ArrayList<>();

        // Act
        CourseEnrollmentResponse<Object> actualCourseEnrollmentResponse = new CourseEnrollmentResponse<>(
                "Not all who wander are lost", "An error occurred", "Value", values, 1);
        actualCourseEnrollmentResponse.setError("An error occurred");
        actualCourseEnrollmentResponse.setMessage("Not all who wander are lost");
        actualCourseEnrollmentResponse.setStatus(1);
        actualCourseEnrollmentResponse.setValue("Value");
        ArrayList<Object> values2 = new ArrayList<>();
        actualCourseEnrollmentResponse.setValues(values2);
        String actualError = actualCourseEnrollmentResponse.getError();
        String actualMessage = actualCourseEnrollmentResponse.getMessage();
        int actualStatus = actualCourseEnrollmentResponse.getStatus();
        actualCourseEnrollmentResponse.getValue();
        List<Object> actualValues = actualCourseEnrollmentResponse.getValues();

        // Assert that nothing has changed
        assertEquals("An error occurred", actualError);
        assertEquals("Not all who wander are lost", actualMessage);
        assertEquals(1, actualStatus);
        assertEquals(values, actualValues);
        assertSame(values2, actualValues);
    }
}
