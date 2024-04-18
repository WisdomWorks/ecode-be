package com.example.codeE.judge.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PriorityMarkerDiffblueTest {
    /**
     * Method under test: {@link PriorityMarker#PriorityMarker(int)}
     */
    @Test
    void testNewPriorityMarker() {
        // Arrange, Act and Assert
        assertEquals(1, (new PriorityMarker(1)).priority);
    }
}
