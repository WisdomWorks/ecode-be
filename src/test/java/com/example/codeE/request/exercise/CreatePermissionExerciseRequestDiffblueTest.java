package com.example.codeE.request.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreatePermissionExerciseRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreatePermissionExerciseRequest#CreatePermissionExerciseRequest()}
     *   <li>{@link CreatePermissionExerciseRequest#setExerciseId(String)}
     *   <li>{@link CreatePermissionExerciseRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionExerciseRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionExerciseRequest#getExerciseId()}
     *   <li>{@link CreatePermissionExerciseRequest#getGroupIds()}
     *   <li>{@link CreatePermissionExerciseRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreatePermissionExerciseRequest actualCreatePermissionExerciseRequest = new CreatePermissionExerciseRequest();
        actualCreatePermissionExerciseRequest.setExerciseId("42");
        ArrayList<String> groupIds = new ArrayList<>();
        actualCreatePermissionExerciseRequest.setGroupIds(groupIds);
        actualCreatePermissionExerciseRequest.setShowAll(true);
        String actualExerciseId = actualCreatePermissionExerciseRequest.getExerciseId();
        List<String> actualGroupIds = actualCreatePermissionExerciseRequest.getGroupIds();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertTrue(actualCreatePermissionExerciseRequest.isShowAll());
        assertSame(groupIds, actualGroupIds);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreatePermissionExerciseRequest#CreatePermissionExerciseRequest(String, List, boolean)}
     *   <li>{@link CreatePermissionExerciseRequest#setExerciseId(String)}
     *   <li>{@link CreatePermissionExerciseRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionExerciseRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionExerciseRequest#getExerciseId()}
     *   <li>{@link CreatePermissionExerciseRequest#getGroupIds()}
     *   <li>{@link CreatePermissionExerciseRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> groupIds = new ArrayList<>();

        // Act
        CreatePermissionExerciseRequest actualCreatePermissionExerciseRequest = new CreatePermissionExerciseRequest("42",
                groupIds, true);
        actualCreatePermissionExerciseRequest.setExerciseId("42");
        ArrayList<String> groupIds2 = new ArrayList<>();
        actualCreatePermissionExerciseRequest.setGroupIds(groupIds2);
        actualCreatePermissionExerciseRequest.setShowAll(true);
        String actualExerciseId = actualCreatePermissionExerciseRequest.getExerciseId();
        List<String> actualGroupIds = actualCreatePermissionExerciseRequest.getGroupIds();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertTrue(actualCreatePermissionExerciseRequest.isShowAll());
        assertEquals(groupIds, actualGroupIds);
        assertSame(groupIds2, actualGroupIds);
    }
}
