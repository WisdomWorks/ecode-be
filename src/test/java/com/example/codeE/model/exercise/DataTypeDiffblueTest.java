package com.example.codeE.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DataTypeDiffblueTest {
    /**
     * Method under test: {@link DataType#DataType(String, List)}
     */
    @Test
    void testNewDataType() {
        // Arrange
        ArrayList<String> dataTypes = new ArrayList<>();

        // Act
        DataType actualDataType = new DataType("Name", dataTypes);

        // Assert
        assertEquals("Name", actualDataType.getName());
        assertNull(actualDataType.getDatatypeId());
        List<String> dataTypes2 = actualDataType.getDataTypes();
        assertTrue(dataTypes2.isEmpty());
        assertSame(dataTypes, dataTypes2);
    }
}
