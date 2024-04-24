package com.example.codeE.request.group;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateGroupRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateGroupRequest#CreateGroupRequest()}
     *   <li>{@link CreateGroupRequest#setCourseId(String)}
     *   <li>{@link CreateGroupRequest#setGroupName(String)}
     *   <li>{@link CreateGroupRequest#getCourseId()}
     *   <li>{@link CreateGroupRequest#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateGroupRequest actualCreateGroupRequest = new CreateGroupRequest();
        actualCreateGroupRequest.setCourseId("42");
        actualCreateGroupRequest.setGroupName("Group Name");
        String actualCourseId = actualCreateGroupRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Group Name", actualCreateGroupRequest.getGroupName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateGroupRequest#CreateGroupRequest(String, String)}
     *   <li>{@link CreateGroupRequest#setCourseId(String)}
     *   <li>{@link CreateGroupRequest#setGroupName(String)}
     *   <li>{@link CreateGroupRequest#getCourseId()}
     *   <li>{@link CreateGroupRequest#getGroupName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateGroupRequest actualCreateGroupRequest = new CreateGroupRequest("42", "Group Name");
        actualCreateGroupRequest.setCourseId("42");
        actualCreateGroupRequest.setGroupName("Group Name");
        String actualCourseId = actualCreateGroupRequest.getCourseId();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("Group Name", actualCreateGroupRequest.getGroupName());
    }
}
