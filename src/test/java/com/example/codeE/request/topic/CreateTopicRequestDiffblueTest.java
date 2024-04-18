package com.example.codeE.request.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CreateTopicRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateTopicRequest#CreateTopicRequest()}
     *   <li>{@link CreateTopicRequest#setCourseId(String)}
     *   <li>{@link CreateTopicRequest#setDescription(String)}
     *   <li>{@link CreateTopicRequest#setTopicName(String)}
     *   <li>{@link CreateTopicRequest#getCourseId()}
     *   <li>{@link CreateTopicRequest#getDescription()}
     *   <li>{@link CreateTopicRequest#getTopicName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CreateTopicRequest actualCreateTopicRequest = new CreateTopicRequest();
        actualCreateTopicRequest.setCourseId("42");
        actualCreateTopicRequest.setDescription("The characteristics of someone or something");
        actualCreateTopicRequest.setTopicName("Topic Name");
        String actualCourseId = actualCreateTopicRequest.getCourseId();
        String actualDescription = actualCreateTopicRequest.getDescription();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Topic Name", actualCreateTopicRequest.getTopicName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CreateTopicRequest#CreateTopicRequest(String, String, String)}
     *   <li>{@link CreateTopicRequest#setCourseId(String)}
     *   <li>{@link CreateTopicRequest#setDescription(String)}
     *   <li>{@link CreateTopicRequest#setTopicName(String)}
     *   <li>{@link CreateTopicRequest#getCourseId()}
     *   <li>{@link CreateTopicRequest#getDescription()}
     *   <li>{@link CreateTopicRequest#getTopicName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CreateTopicRequest actualCreateTopicRequest = new CreateTopicRequest("42", "Topic Name",
                "The characteristics of someone or something");
        actualCreateTopicRequest.setCourseId("42");
        actualCreateTopicRequest.setDescription("The characteristics of someone or something");
        actualCreateTopicRequest.setTopicName("Topic Name");
        String actualCourseId = actualCreateTopicRequest.getCourseId();
        String actualDescription = actualCreateTopicRequest.getDescription();

        // Assert that nothing has changed
        assertEquals("42", actualCourseId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Topic Name", actualCreateTopicRequest.getTopicName());
    }
}
