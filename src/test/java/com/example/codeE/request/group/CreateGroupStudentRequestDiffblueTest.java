package com.example.codeE.request.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CreateGroupStudentRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateGroupStudentRequest#setDescription(String)}
     *   <li>{@link CreateGroupStudentRequest#setGroupId(String)}
     *   <li>{@link CreateGroupStudentRequest#setStudentIds(List)}
     *   <li>{@link CreateGroupStudentRequest#getDescription()}
     *   <li>{@link CreateGroupStudentRequest#getGroupId()}
     *   <li>{@link CreateGroupStudentRequest#getStudentIds()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        CreateGroupStudentRequest createGroupStudentRequest = new CreateGroupStudentRequest();

        // Act
        createGroupStudentRequest.setDescription("The characteristics of someone or something");
        createGroupStudentRequest.setGroupId("42");
        ArrayList<String> studentIds = new ArrayList<>();
        createGroupStudentRequest.setStudentIds(studentIds);
        String actualDescription = createGroupStudentRequest.getDescription();
        String actualGroupId = createGroupStudentRequest.getGroupId();

        // Assert that nothing has changed
        assertEquals("42", actualGroupId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertSame(studentIds, createGroupStudentRequest.getStudentIds());
    }
}
