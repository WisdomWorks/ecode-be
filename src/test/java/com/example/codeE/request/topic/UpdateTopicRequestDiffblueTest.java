package com.example.codeE.request.topic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UpdateTopicRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateTopicRequest#UpdateTopicRequest()}
     *   <li>{@link UpdateTopicRequest#setDescription(String)}
     *   <li>{@link UpdateTopicRequest#setTopicId(String)}
     *   <li>{@link UpdateTopicRequest#setTopicName(String)}
     *   <li>{@link UpdateTopicRequest#getDescription()}
     *   <li>{@link UpdateTopicRequest#getTopicId()}
     *   <li>{@link UpdateTopicRequest#getTopicName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        UpdateTopicRequest actualUpdateTopicRequest = new UpdateTopicRequest();
        actualUpdateTopicRequest.setDescription("The characteristics of someone or something");
        actualUpdateTopicRequest.setTopicId("42");
        actualUpdateTopicRequest.setTopicName("Topic Name");
        String actualDescription = actualUpdateTopicRequest.getDescription();
        String actualTopicId = actualUpdateTopicRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Topic Name", actualUpdateTopicRequest.getTopicName());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link UpdateTopicRequest#UpdateTopicRequest(String, String, String)}
     *   <li>{@link UpdateTopicRequest#setDescription(String)}
     *   <li>{@link UpdateTopicRequest#setTopicId(String)}
     *   <li>{@link UpdateTopicRequest#setTopicName(String)}
     *   <li>{@link UpdateTopicRequest#getDescription()}
     *   <li>{@link UpdateTopicRequest#getTopicId()}
     *   <li>{@link UpdateTopicRequest#getTopicName()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        UpdateTopicRequest actualUpdateTopicRequest = new UpdateTopicRequest("42", "Topic Name",
                "The characteristics of someone or something");
        actualUpdateTopicRequest.setDescription("The characteristics of someone or something");
        actualUpdateTopicRequest.setTopicId("42");
        actualUpdateTopicRequest.setTopicName("Topic Name");
        String actualDescription = actualUpdateTopicRequest.getDescription();
        String actualTopicId = actualUpdateTopicRequest.getTopicId();

        // Assert that nothing has changed
        assertEquals("42", actualTopicId);
        assertEquals("The characteristics of someone or something", actualDescription);
        assertEquals("Topic Name", actualUpdateTopicRequest.getTopicName());
    }
}
