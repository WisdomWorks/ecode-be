package com.example.codeE.request.course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class ImportStudentToCourseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ImportStudentToCourseRequest#ImportStudentToCourseRequest()}
     *   <li>{@link ImportStudentToCourseRequest#setCourseId(String)}
     *   <li>{@link ImportStudentToCourseRequest#setFile(MultipartFile)}
     *   <li>{@link ImportStudentToCourseRequest#getCourseId()}
     *   <li>{@link ImportStudentToCourseRequest#getFile()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws IOException {
        // Arrange and Act
        ImportStudentToCourseRequest actualImportStudentToCourseRequest = new ImportStudentToCourseRequest();
        actualImportStudentToCourseRequest.setCourseId("42");
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        actualImportStudentToCourseRequest.setFile(file);
        String actualCourseId = actualImportStudentToCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertSame(file, actualImportStudentToCourseRequest.getFile());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ImportStudentToCourseRequest#ImportStudentToCourseRequest(String, MultipartFile)}
     *   <li>{@link ImportStudentToCourseRequest#setCourseId(String)}
     *   <li>{@link ImportStudentToCourseRequest#setFile(MultipartFile)}
     *   <li>{@link ImportStudentToCourseRequest#getCourseId()}
     *   <li>{@link ImportStudentToCourseRequest#getFile()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() throws IOException {
        // Arrange and Act
        ImportStudentToCourseRequest actualImportStudentToCourseRequest = new ImportStudentToCourseRequest("42",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        actualImportStudentToCourseRequest.setCourseId("42");
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        actualImportStudentToCourseRequest.setFile(file);
        String actualCourseId = actualImportStudentToCourseRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertSame(file, actualImportStudentToCourseRequest.getFile());
    }
}
