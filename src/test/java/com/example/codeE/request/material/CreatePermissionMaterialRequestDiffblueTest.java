package com.example.codeE.request.material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreatePermissionMaterialRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreatePermissionMaterialRequest#CreatePermissionMaterialRequest()}
     *   <li>{@link CreatePermissionMaterialRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionMaterialRequest#setMaterialId(String)}
     *   <li>{@link CreatePermissionMaterialRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionMaterialRequest#getGroupIds()}
     *   <li>{@link CreatePermissionMaterialRequest#getMaterialId()}
     *   <li>{@link CreatePermissionMaterialRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreatePermissionMaterialRequest actualCreatePermissionMaterialRequest = new CreatePermissionMaterialRequest();
        ArrayList<String> groupIds = new ArrayList<>();
        actualCreatePermissionMaterialRequest.setGroupIds(groupIds);
        actualCreatePermissionMaterialRequest.setMaterialId("42");
        actualCreatePermissionMaterialRequest.setShowAll(true);
        List<String> actualGroupIds = actualCreatePermissionMaterialRequest.getGroupIds();
        String actualMaterialId = actualCreatePermissionMaterialRequest.getMaterialId();

        // Assert that nothing has changed
        assertEquals("42", actualMaterialId);
        assertTrue(actualCreatePermissionMaterialRequest.isShowAll());
        assertSame(groupIds, actualGroupIds);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreatePermissionMaterialRequest#CreatePermissionMaterialRequest(String, List, boolean)}
     *   <li>{@link CreatePermissionMaterialRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionMaterialRequest#setMaterialId(String)}
     *   <li>{@link CreatePermissionMaterialRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionMaterialRequest#getGroupIds()}
     *   <li>{@link CreatePermissionMaterialRequest#getMaterialId()}
     *   <li>{@link CreatePermissionMaterialRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> groupIds = new ArrayList<>();

        // Act
        CreatePermissionMaterialRequest actualCreatePermissionMaterialRequest = new CreatePermissionMaterialRequest("42",
                groupIds, true);
        ArrayList<String> groupIds2 = new ArrayList<>();
        actualCreatePermissionMaterialRequest.setGroupIds(groupIds2);
        actualCreatePermissionMaterialRequest.setMaterialId("42");
        actualCreatePermissionMaterialRequest.setShowAll(true);
        List<String> actualGroupIds = actualCreatePermissionMaterialRequest.getGroupIds();
        String actualMaterialId = actualCreatePermissionMaterialRequest.getMaterialId();

        // Assert that nothing has changed
        assertEquals("42", actualMaterialId);
        assertTrue(actualCreatePermissionMaterialRequest.isShowAll());
        assertEquals(groupIds, actualGroupIds);
        assertSame(groupIds2, actualGroupIds);
    }
}
