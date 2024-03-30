package com.example.codeE.request.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UpdateMaterialRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateMaterialRequest#UpdateMaterialRequest()}
     *   <li>{@link UpdateMaterialRequest#setCheckAll(boolean)}
     *   <li>{@link UpdateMaterialRequest#setDescription(String)}
     *   <li>{@link UpdateMaterialRequest#setMaterialId(String)}
     *   <li>{@link UpdateMaterialRequest#getDescription()}
     *   <li>{@link UpdateMaterialRequest#getMaterialId()}
     *   <li>{@link UpdateMaterialRequest#isCheckAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateMaterialRequest actualUpdateMaterialRequest = new UpdateMaterialRequest();
        actualUpdateMaterialRequest.setCheckAll(true);
        actualUpdateMaterialRequest.setDescription("The characteristics of someone or something");
        actualUpdateMaterialRequest.setMaterialId("42");
        String actualDescription = actualUpdateMaterialRequest.getDescription();
        String actualMaterialId = actualUpdateMaterialRequest.getMaterialId();

        // Assert that nothing has changed
        assertEquals("42", actualMaterialId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertTrue(actualUpdateMaterialRequest.isCheckAll());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link UpdateMaterialRequest#UpdateMaterialRequest(String, String, boolean)}
     *   <li>{@link UpdateMaterialRequest#setCheckAll(boolean)}
     *   <li>{@link UpdateMaterialRequest#setDescription(String)}
     *   <li>{@link UpdateMaterialRequest#setMaterialId(String)}
     *   <li>{@link UpdateMaterialRequest#getDescription()}
     *   <li>{@link UpdateMaterialRequest#getMaterialId()}
     *   <li>{@link UpdateMaterialRequest#isCheckAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateMaterialRequest actualUpdateMaterialRequest = new UpdateMaterialRequest("42",
                "The characteristics of someone or something", true);
        actualUpdateMaterialRequest.setCheckAll(true);
        actualUpdateMaterialRequest.setDescription("The characteristics of someone or something");
        actualUpdateMaterialRequest.setMaterialId("42");
        String actualDescription = actualUpdateMaterialRequest.getDescription();
        String actualMaterialId = actualUpdateMaterialRequest.getMaterialId();

        // Assert that nothing has changed
        assertEquals("42", actualMaterialId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertTrue(actualUpdateMaterialRequest.isCheckAll());
    }
}
