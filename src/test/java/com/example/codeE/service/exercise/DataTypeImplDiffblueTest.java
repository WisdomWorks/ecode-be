package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.DataType;
import com.example.codeE.repository.DataTypeRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DataTypeImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DataTypeImplDiffblueTest {
    @Autowired
    private DataTypeImpl dataTypeImpl;

    @MockBean
    private DataTypeRepository dataTypeRepository;

    /**
     * Method under test: {@link DataTypeImpl#getAllDataTypes()}
     */
    @Test
    void testGetAllDataTypes() {
        // Arrange
        ArrayList<DataType> dataTypeList = new ArrayList<>();
        when(dataTypeRepository.findAll()).thenReturn(dataTypeList);

        // Act
        List<DataType> actualAllDataTypes = dataTypeImpl.getAllDataTypes();

        // Assert
        verify(dataTypeRepository).findAll();
        assertTrue(actualAllDataTypes.isEmpty());
        assertSame(dataTypeList, actualAllDataTypes);
    }
}
