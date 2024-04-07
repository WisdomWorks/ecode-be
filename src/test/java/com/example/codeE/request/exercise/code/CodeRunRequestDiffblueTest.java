package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CodeRunRequestDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CodeRunRequest#CodeRunRequest()}
     *   <li>{@link CodeRunRequest#setExerciseId(String)}
     *   <li>{@link CodeRunRequest#setLanguageId(String)}
     *   <li>{@link CodeRunRequest#setSource(String)}
     *   <li>{@link CodeRunRequest#setStudentId(String)}
     *   <li>{@link CodeRunRequest#getExerciseId()}
     *   <li>{@link CodeRunRequest#getLanguageId()}
     *   <li>{@link CodeRunRequest#getSource()}
     *   <li>{@link CodeRunRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CodeRunRequest actualCodeRunRequest = new CodeRunRequest();
        actualCodeRunRequest.setExerciseId("42");
        actualCodeRunRequest.setLanguageId("en");
        actualCodeRunRequest.setSource("Source");
        actualCodeRunRequest.setStudentId("42");
        String actualExerciseId = actualCodeRunRequest.getExerciseId();
        String actualLanguageId = actualCodeRunRequest.getLanguageId();
        String actualSource = actualCodeRunRequest.getSource();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualCodeRunRequest.getStudentId());
        assertEquals("Source", actualSource);
        assertEquals("en", actualLanguageId);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CodeRunRequest#CodeRunRequest(String, String, String, String)}
     *   <li>{@link CodeRunRequest#setExerciseId(String)}
     *   <li>{@link CodeRunRequest#setLanguageId(String)}
     *   <li>{@link CodeRunRequest#setSource(String)}
     *   <li>{@link CodeRunRequest#setStudentId(String)}
     *   <li>{@link CodeRunRequest#getExerciseId()}
     *   <li>{@link CodeRunRequest#getLanguageId()}
     *   <li>{@link CodeRunRequest#getSource()}
     *   <li>{@link CodeRunRequest#getStudentId()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        CodeRunRequest actualCodeRunRequest = new CodeRunRequest("42", "42", "en", "Source");
        actualCodeRunRequest.setExerciseId("42");
        actualCodeRunRequest.setLanguageId("en");
        actualCodeRunRequest.setSource("Source");
        actualCodeRunRequest.setStudentId("42");
        String actualExerciseId = actualCodeRunRequest.getExerciseId();
        String actualLanguageId = actualCodeRunRequest.getLanguageId();
        String actualSource = actualCodeRunRequest.getSource();

        // Assert that nothing has changed
        assertEquals("42", actualExerciseId);
        assertEquals("42", actualCodeRunRequest.getStudentId());
        assertEquals("Source", actualSource);
        assertEquals("en", actualLanguageId);
    }
}
