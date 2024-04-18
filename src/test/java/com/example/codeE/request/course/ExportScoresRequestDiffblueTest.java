package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExportScoresRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExportScoresRequest#ExportScoresRequest()}
     *   <li>{@link ExportScoresRequest#setCourseId(String)}
     *   <li>{@link ExportScoresRequest#getCourseId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExportScoresRequest actualExportScoresRequest = new ExportScoresRequest();
        actualExportScoresRequest.setCourseId("42");

        // Assert that nothing has changed
        assertEquals("42", actualExportScoresRequest.getCourseId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExportScoresRequest#ExportScoresRequest(String)}
     *   <li>{@link ExportScoresRequest#setCourseId(String)}
     *   <li>{@link ExportScoresRequest#getCourseId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ExportScoresRequest actualExportScoresRequest = new ExportScoresRequest("42");
        actualExportScoresRequest.setCourseId("42");

        // Assert that nothing has changed
        assertEquals("42", actualExportScoresRequest.getCourseId());
    }
}
