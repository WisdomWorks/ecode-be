package com.example.codeE.service.exercise.problem;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.repository.CodeExerciseTestcaseRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CodeExerciseTestcaseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CodeExerciseTestcaseImplDiffblueTest {
    @Autowired
    private CodeExerciseTestcaseImpl codeExerciseTestcaseImpl;

    @MockBean
    private CodeExerciseTestcaseRepository codeExerciseTestcaseRepository;

    /**
     * Method under test: {@link CodeExerciseTestcaseImpl#saveTestCase(TestCase)}
     */
    @Test
    void testSaveTestCase() {
        // Arrange
        TestCase testCase = new TestCase();
        testCase.setExerciseId("42");
        testCase.setInput("Input");
        testCase.setOutput("Output");
        testCase.setPoints(1);
        testCase.setTestcaseId("42");
        when(codeExerciseTestcaseRepository.save(Mockito.<TestCase>any())).thenReturn(testCase);

        TestCase testCase2 = new TestCase();
        testCase2.setExerciseId("42");
        testCase2.setInput("Input");
        testCase2.setOutput("Output");
        testCase2.setPoints(1);
        testCase2.setTestcaseId("42");

        // Act
        TestCase actualSaveTestCaseResult = codeExerciseTestcaseImpl.saveTestCase(testCase2);

        // Assert
        verify(codeExerciseTestcaseRepository).save(isA(TestCase.class));
        assertSame(testCase, actualSaveTestCaseResult);
    }

    /**
     * Method under test: {@link CodeExerciseTestcaseImpl#deleteTestCase(String)}
     */
    @Test
    void testDeleteTestCase() {
        // Arrange
        doNothing().when(codeExerciseTestcaseRepository).deleteById(Mockito.<String>any());

        // Act
        codeExerciseTestcaseImpl.deleteTestCase("42");

        // Assert that nothing has changed
        verify(codeExerciseTestcaseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeExerciseTestcaseImpl#getAllZeroPointTestCases(String)}
     */
    @Test
    void testGetAllZeroPointTestCases() {
        // Arrange
        ArrayList<TestCase> testCaseList = new ArrayList<>();
        when(codeExerciseTestcaseRepository.findByExerciseIdAndPoints(Mockito.<String>any(), anyInt()))
                .thenReturn(testCaseList);

        // Act
        List<TestCase> actualAllZeroPointTestCases = codeExerciseTestcaseImpl.getAllZeroPointTestCases("42");

        // Assert
        verify(codeExerciseTestcaseRepository).findByExerciseIdAndPoints(eq("42"), eq(0));
        assertTrue(actualAllZeroPointTestCases.isEmpty());
        assertSame(testCaseList, actualAllZeroPointTestCases);
    }
}
