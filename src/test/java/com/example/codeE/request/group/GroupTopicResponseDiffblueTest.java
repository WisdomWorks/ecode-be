package com.example.codeE.request.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GroupTopicResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GroupTopicResponse#GroupTopicResponse()}
     *   <li>{@link GroupTopicResponse#setGroupId(String)}
     *   <li>{@link GroupTopicResponse#setGroupName(String)}
     *   <li>{@link GroupTopicResponse#getGroupId()}
     *   <li>{@link GroupTopicResponse#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        GroupTopicResponse actualGroupTopicResponse = new GroupTopicResponse();
        actualGroupTopicResponse.setGroupId("42");
        actualGroupTopicResponse.setGroupName("Group Name");
        String actualGroupId = actualGroupTopicResponse.getGroupId();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals("Group Name", actualGroupTopicResponse.getGroupName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link GroupTopicResponse#GroupTopicResponse(String, String)}
     *   <li>{@link GroupTopicResponse#setGroupId(String)}
     *   <li>{@link GroupTopicResponse#setGroupName(String)}
     *   <li>{@link GroupTopicResponse#getGroupId()}
     *   <li>{@link GroupTopicResponse#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        GroupTopicResponse actualGroupTopicResponse = new GroupTopicResponse("Group Name", "42");
        actualGroupTopicResponse.setGroupId("42");
        actualGroupTopicResponse.setGroupName("Group Name");
        String actualGroupId = actualGroupTopicResponse.getGroupId();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals("Group Name", actualGroupTopicResponse.getGroupName());
    }
}
