package com.example.codeE.request.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

class CreateMaterialRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateMaterialRequest#CreateMaterialRequest()}
     *   <li>{@link CreateMaterialRequest#setDescription(String)}
     *   <li>{@link CreateMaterialRequest#setFile(MultipartFile)}
     *   <li>{@link CreateMaterialRequest#setMaterialName(String)}
     *   <li>{@link CreateMaterialRequest#setMaterialType(String)}
     *   <li>{@link CreateMaterialRequest#setTopicId(String)}
     *   <li>{@link CreateMaterialRequest#setUrl(String)}
     *   <li>{@link CreateMaterialRequest#getDescription()}
     *   <li>{@link CreateMaterialRequest#getFile()}
     *   <li>{@link CreateMaterialRequest#getMaterialName()}
     *   <li>{@link CreateMaterialRequest#getMaterialType()}
     *   <li>{@link CreateMaterialRequest#getTopicId()}
     *   <li>{@link CreateMaterialRequest#getUrl()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() throws IOException {
        // Arrange and Act
        CreateMaterialRequest actualCreateMaterialRequest = new CreateMaterialRequest();
        actualCreateMaterialRequest.setDescription("The characteristics of someone or something");
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        actualCreateMaterialRequest.setFile(file);
        actualCreateMaterialRequest.setMaterialName("Material Name");
        actualCreateMaterialRequest.setMaterialType("Material Type");
        actualCreateMaterialRequest.setTopicId("42");
        actualCreateMaterialRequest.setUrl("https://example.org/example");
        String actualDescription = actualCreateMaterialRequest.getDescription();
        MultipartFile actualFile = actualCreateMaterialRequest.getFile();
        String actualMaterialName = actualCreateMaterialRequest.getMaterialName();
        String actualMaterialType = actualCreateMaterialRequest.getMaterialType();
        String actualTopicId = actualCreateMaterialRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Material Name", actualMaterialName);
        assertEquals("Material Type", actualMaterialType);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("https://example.org/example", actualCreateMaterialRequest.getUrl());
        assertSame(file, actualFile);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreateMaterialRequest#CreateMaterialRequest(String, String, String, String, String, MultipartFile)}
     *   <li>{@link CreateMaterialRequest#setDescription(String)}
     *   <li>{@link CreateMaterialRequest#setFile(MultipartFile)}
     *   <li>{@link CreateMaterialRequest#setMaterialName(String)}
     *   <li>{@link CreateMaterialRequest#setMaterialType(String)}
     *   <li>{@link CreateMaterialRequest#setTopicId(String)}
     *   <li>{@link CreateMaterialRequest#setUrl(String)}
     *   <li>{@link CreateMaterialRequest#getDescription()}
     *   <li>{@link CreateMaterialRequest#getFile()}
     *   <li>{@link CreateMaterialRequest#getMaterialName()}
     *   <li>{@link CreateMaterialRequest#getMaterialType()}
     *   <li>{@link CreateMaterialRequest#getTopicId()}
     *   <li>{@link CreateMaterialRequest#getUrl()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() throws IOException {
        // Arrange and Act
        CreateMaterialRequest actualCreateMaterialRequest = new CreateMaterialRequest("Material Name", "Material Type",
                "42", "https://example.org/example", "The characteristics of someone or something",
                new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
        actualCreateMaterialRequest.setDescription("The characteristics of someone or something");
        MockMultipartFile file = new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

        actualCreateMaterialRequest.setFile(file);
        actualCreateMaterialRequest.setMaterialName("Material Name");
        actualCreateMaterialRequest.setMaterialType("Material Type");
        actualCreateMaterialRequest.setTopicId("42");
        actualCreateMaterialRequest.setUrl("https://example.org/example");
        String actualDescription = actualCreateMaterialRequest.getDescription();
        MultipartFile actualFile = actualCreateMaterialRequest.getFile();
        String actualMaterialName = actualCreateMaterialRequest.getMaterialName();
        String actualMaterialType = actualCreateMaterialRequest.getMaterialType();
        String actualTopicId = actualCreateMaterialRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("Material Name", actualMaterialName);
        assertEquals("Material Type", actualMaterialType);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("https://example.org/example", actualCreateMaterialRequest.getUrl());
        assertSame(file, actualFile);
    }
}
