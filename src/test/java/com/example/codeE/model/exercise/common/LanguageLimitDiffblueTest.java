package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LanguageLimitDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link LanguageLimit#LanguageLimit()}
     *   <li>{@link LanguageLimit#setLanguageId(String)}
     *   <li>{@link LanguageLimit#setLanguageLimitId(String)}
     *   <li>{@link LanguageLimit#setMemoryLimit(Integer)}
     *   <li>{@link LanguageLimit#setProblemId(String)}
     *   <li>{@link LanguageLimit#setTimeLimit(Double)}
     *   <li>{@link LanguageLimit#getLanguageId()}
     *   <li>{@link LanguageLimit#getLanguageLimitId()}
     *   <li>{@link LanguageLimit#getMemoryLimit()}
     *   <li>{@link LanguageLimit#getProblemId()}
     *   <li>{@link LanguageLimit#getTimeLimit()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        LanguageLimit actualLanguageLimit = new LanguageLimit();
        actualLanguageLimit.setLanguageId("en");
        actualLanguageLimit.setLanguageLimitId("en");
        actualLanguageLimit.setMemoryLimit(1);
        actualLanguageLimit.setProblemId("42");
        actualLanguageLimit.setTimeLimit(10.0d);
        String actualLanguageId = actualLanguageLimit.getLanguageId();
        String actualLanguageLimitId = actualLanguageLimit.getLanguageLimitId();
        Integer actualMemoryLimit = actualLanguageLimit.getMemoryLimit();
        String actualProblemId = actualLanguageLimit.getProblemId();
        Double actualTimeLimit = actualLanguageLimit.getTimeLimit();

        // Assert that nothing has changed
        assertEquals("42", actualProblemId);
        assertEquals("en", actualLanguageId);
        assertEquals("en", actualLanguageLimitId);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link LanguageLimit#LanguageLimit(String, String, String, Double, Integer)}
     *   <li>{@link LanguageLimit#setLanguageId(String)}
     *   <li>{@link LanguageLimit#setLanguageLimitId(String)}
     *   <li>{@link LanguageLimit#setMemoryLimit(Integer)}
     *   <li>{@link LanguageLimit#setProblemId(String)}
     *   <li>{@link LanguageLimit#setTimeLimit(Double)}
     *   <li>{@link LanguageLimit#getLanguageId()}
     *   <li>{@link LanguageLimit#getLanguageLimitId()}
     *   <li>{@link LanguageLimit#getMemoryLimit()}
     *   <li>{@link LanguageLimit#getProblemId()}
     *   <li>{@link LanguageLimit#getTimeLimit()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        LanguageLimit actualLanguageLimit = new LanguageLimit("en", "42", "en", 10.0d, 1);
        actualLanguageLimit.setLanguageId("en");
        actualLanguageLimit.setLanguageLimitId("en");
        actualLanguageLimit.setMemoryLimit(1);
        actualLanguageLimit.setProblemId("42");
        actualLanguageLimit.setTimeLimit(10.0d);
        String actualLanguageId = actualLanguageLimit.getLanguageId();
        String actualLanguageLimitId = actualLanguageLimit.getLanguageLimitId();
        Integer actualMemoryLimit = actualLanguageLimit.getMemoryLimit();
        String actualProblemId = actualLanguageLimit.getProblemId();
        Double actualTimeLimit = actualLanguageLimit.getTimeLimit();

        // Assert that nothing has changed
        assertEquals("42", actualProblemId);
        assertEquals("en", actualLanguageId);
        assertEquals("en", actualLanguageLimitId);
        assertEquals(1, actualMemoryLimit.intValue());
        assertEquals(10.0d, actualTimeLimit.doubleValue());
    }
}
