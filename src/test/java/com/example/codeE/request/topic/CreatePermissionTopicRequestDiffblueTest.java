package com.example.codeE.request.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreatePermissionTopicRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreatePermissionTopicRequest#CreatePermissionTopicRequest()}
     *   <li>{@link CreatePermissionTopicRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionTopicRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionTopicRequest#setTopicId(String)}
     *   <li>{@link CreatePermissionTopicRequest#getGroupIds()}
     *   <li>{@link CreatePermissionTopicRequest#getTopicId()}
     *   <li>{@link CreatePermissionTopicRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreatePermissionTopicRequest actualCreatePermissionTopicRequest = new CreatePermissionTopicRequest();
        ArrayList<String> groupIds = new ArrayList<>();
        actualCreatePermissionTopicRequest.setGroupIds(groupIds);
        actualCreatePermissionTopicRequest.setShowAll(true);
        actualCreatePermissionTopicRequest.setTopicId("42");
        List<String> actualGroupIds = actualCreatePermissionTopicRequest.getGroupIds();
        String actualTopicId = actualCreatePermissionTopicRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertTrue(actualCreatePermissionTopicRequest.isShowAll());
        assertSame(groupIds, actualGroupIds);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CreatePermissionTopicRequest#CreatePermissionTopicRequest(String, List, boolean)}
     *   <li>{@link CreatePermissionTopicRequest#setGroupIds(List)}
     *   <li>{@link CreatePermissionTopicRequest#setShowAll(boolean)}
     *   <li>{@link CreatePermissionTopicRequest#setTopicId(String)}
     *   <li>{@link CreatePermissionTopicRequest#getGroupIds()}
     *   <li>{@link CreatePermissionTopicRequest#getTopicId()}
     *   <li>{@link CreatePermissionTopicRequest#isShowAll()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<String> groupIds = new ArrayList<>();

        // Act
        CreatePermissionTopicRequest actualCreatePermissionTopicRequest = new CreatePermissionTopicRequest("42", groupIds,
                true);
        ArrayList<String> groupIds2 = new ArrayList<>();
        actualCreatePermissionTopicRequest.setGroupIds(groupIds2);
        actualCreatePermissionTopicRequest.setShowAll(true);
        actualCreatePermissionTopicRequest.setTopicId("42");
        List<String> actualGroupIds = actualCreatePermissionTopicRequest.getGroupIds();
        String actualTopicId = actualCreatePermissionTopicRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertTrue(actualCreatePermissionTopicRequest.isShowAll());
        assertEquals(groupIds, actualGroupIds);
        assertSame(groupIds2, actualGroupIds);
    }
}
