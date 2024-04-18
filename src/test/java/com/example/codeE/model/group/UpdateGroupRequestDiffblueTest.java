package com.example.codeE.model.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UpdateGroupRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateGroupRequest#UpdateGroupRequest()}
     *   <li>{@link UpdateGroupRequest#setGroupName(String)}
     *   <li>{@link UpdateGroupRequest#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateGroupRequest actualUpdateGroupRequest = new UpdateGroupRequest();
        actualUpdateGroupRequest.setGroupName("Group Name");

        // Assert that nothing has changed
        assertEquals("Group Name", actualUpdateGroupRequest.getGroupName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateGroupRequest#UpdateGroupRequest(String)}
     *   <li>{@link UpdateGroupRequest#setGroupName(String)}
     *   <li>{@link UpdateGroupRequest#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateGroupRequest actualUpdateGroupRequest = new UpdateGroupRequest("Group Name");
        actualUpdateGroupRequest.setGroupName("Group Name");

        // Assert that nothing has changed
        assertEquals("Group Name", actualUpdateGroupRequest.getGroupName());
    }
}
