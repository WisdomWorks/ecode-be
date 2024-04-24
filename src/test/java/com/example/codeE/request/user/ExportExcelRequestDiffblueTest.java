package com.example.codeE.request.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ExportExcelRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExportExcelRequest#ExportExcelRequest()}
     *   <li>{@link ExportExcelRequest#setCourseId(String)}
     *   <li>{@link ExportExcelRequest#setFileName(String)}
     *   <li>{@link ExportExcelRequest#getCourseId()}
     *   <li>{@link ExportExcelRequest#getFileName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExportExcelRequest actualExportExcelRequest = new ExportExcelRequest();
        actualExportExcelRequest.setCourseId("42");
        actualExportExcelRequest.setFileName("foo.txt");
        String actualCourseId = actualExportExcelRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("foo.txt", actualExportExcelRequest.getFileName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExportExcelRequest#ExportExcelRequest(String, String)}
     *   <li>{@link ExportExcelRequest#setCourseId(String)}
     *   <li>{@link ExportExcelRequest#setFileName(String)}
     *   <li>{@link ExportExcelRequest#getCourseId()}
     *   <li>{@link ExportExcelRequest#getFileName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ExportExcelRequest actualExportExcelRequest = new ExportExcelRequest("foo.txt", "42");
        actualExportExcelRequest.setCourseId("42");
        actualExportExcelRequest.setFileName("foo.txt");
        String actualCourseId = actualExportExcelRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("foo.txt", actualExportExcelRequest.getFileName());
    }
}
