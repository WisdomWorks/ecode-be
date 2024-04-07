package com.example.codeE.request.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UpdateGroupRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateGroupRequest#UpdateGroupRequest()}
     *   <li>{@link UpdateGroupRequest#setGroupId(String)}
     *   <li>{@link UpdateGroupRequest#getGroupId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateGroupRequest actualUpdateGroupRequest = new UpdateGroupRequest();
        actualUpdateGroupRequest.setGroupId("42");

        // Assert that nothing has changed
        assertEquals("42", actualUpdateGroupRequest.getGroupId());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateGroupRequest#UpdateGroupRequest(String)}
     *   <li>{@link UpdateGroupRequest#setGroupId(String)}
     *   <li>{@link UpdateGroupRequest#getGroupId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateGroupRequest actualUpdateGroupRequest = new UpdateGroupRequest("42");
        actualUpdateGroupRequest.setGroupId("42");

        // Assert that nothing has changed
        assertEquals("42", actualUpdateGroupRequest.getGroupId());
    }
}
