package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RuntimeVersionDiffblueTest {
    /**
     * Method under test:
     * {@link RuntimeVersion#RuntimeVersion(String, String, String, String, int)}
     */
    @Test
    void testNewRuntimeVersion() {
        // Arrange and Act
        RuntimeVersion actualRuntimeVersion = new RuntimeVersion("en", "42", "Name", "1.0.2", 1);

        // Assert
        assertEquals("1.0.2", actualRuntimeVersion.getVersion());
        assertEquals("42", actualRuntimeVersion.getJudgeId());
        assertEquals("Name", actualRuntimeVersion.getName());
        assertEquals("en", actualRuntimeVersion.getLanguageId());
        assertNull(actualRuntimeVersion.getRuntimeVersionId());
        assertEquals(1, actualRuntimeVersion.getPriority());
    }
}
