package com.example.codeE.service.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.repository.SubmissionTestCaseRepository;

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

@ContextConfiguration(classes = {SubmissionTestCaseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SubmissionTestCaseImplDiffblueTest {
    @Autowired
    private SubmissionTestCaseImpl submissionTestCaseImpl;

    @MockBean
    private SubmissionTestCaseRepository submissionTestCaseRepository;

    /**
     * Method under test:
     * {@link SubmissionTestCaseImpl#deleteAllTcBySubmissionId(String)}
     */
    @Test
    void testDeleteAllTcBySubmissionId() {
        // Arrange
        when(submissionTestCaseRepository.findBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        doNothing().when(submissionTestCaseRepository).deleteAll(Mockito.<Iterable<SubmissionTestCase>>any());

        // Act
        submissionTestCaseImpl.deleteAllTcBySubmissionId("42");

        // Assert that nothing has changed
        verify(submissionTestCaseRepository).findBySubmissionId(eq("42"));
        verify(submissionTestCaseRepository).deleteAll(isA(Iterable.class));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#getMaxPosition(List)}
     */
    @Test
    void testGetMaxPosition() {
        // Arrange
        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        testCases.add(submissionTestCase);

        // Act and Assert
        assertEquals(1, submissionTestCaseImpl.getMaxPosition(testCases));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#getMaxPosition(List)}
     */
    @Test
    void testGetMaxPosition2() {
        // Arrange
        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        SubmissionTestCase submissionTestCase2 = new SubmissionTestCase();
        submissionTestCase2.setExtendedFeedback("42");
        submissionTestCase2.setFeedback("42");
        submissionTestCase2.setId("Id");
        submissionTestCase2.setMemory(0.5d);
        submissionTestCase2.setOutput("42");
        submissionTestCase2.setPoints(0.5d);
        submissionTestCase2.setStatus("42");
        submissionTestCase2.setSubmissionId("Submission Id");
        submissionTestCase2.setTestCaseId(2);
        submissionTestCase2.setTime(0.5d);
        submissionTestCase2.setTotal(0.5d);

        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        testCases.add(submissionTestCase2);
        testCases.add(submissionTestCase);

        // Act and Assert
        assertEquals(2, submissionTestCaseImpl.getMaxPosition(testCases));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#getMaxPosition(List)}
     */
    @Test
    void testGetMaxPosition3() {
        // Arrange
        SubmissionTestCase submissionTestCase = mock(SubmissionTestCase.class);
        when(submissionTestCase.getTestCaseId()).thenReturn(1);
        doNothing().when(submissionTestCase).setExtendedFeedback(Mockito.<String>any());
        doNothing().when(submissionTestCase).setFeedback(Mockito.<String>any());
        doNothing().when(submissionTestCase).setId(Mockito.<String>any());
        doNothing().when(submissionTestCase).setMemory(Mockito.<Double>any());
        doNothing().when(submissionTestCase).setOutput(Mockito.<String>any());
        doNothing().when(submissionTestCase).setPoints(Mockito.<Double>any());
        doNothing().when(submissionTestCase).setStatus(Mockito.<String>any());
        doNothing().when(submissionTestCase).setSubmissionId(Mockito.<String>any());
        doNothing().when(submissionTestCase).setTestCaseId(Mockito.<Integer>any());
        doNothing().when(submissionTestCase).setTime(Mockito.<Double>any());
        doNothing().when(submissionTestCase).setTotal(Mockito.<Double>any());
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        testCases.add(submissionTestCase);

        // Act
        int actualMaxPosition = submissionTestCaseImpl.getMaxPosition(testCases);

        // Assert
        verify(submissionTestCase).getTestCaseId();
        verify(submissionTestCase).setExtendedFeedback(eq("Extended Feedback"));
        verify(submissionTestCase).setFeedback(eq("Feedback"));
        verify(submissionTestCase).setId(eq("42"));
        verify(submissionTestCase).setMemory(isA(Double.class));
        verify(submissionTestCase).setOutput(eq("Output"));
        verify(submissionTestCase).setPoints(isA(Double.class));
        verify(submissionTestCase).setStatus(eq("Status"));
        verify(submissionTestCase).setSubmissionId(eq("42"));
        verify(submissionTestCase).setTestCaseId(isA(Integer.class));
        verify(submissionTestCase).setTime(isA(Double.class));
        verify(submissionTestCase).setTotal(isA(Double.class));
        assertEquals(1, actualMaxPosition);
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#saveAll(List)}
     */
    @Test
    void testSaveAll() {
        // Arrange
        when(submissionTestCaseRepository.saveAll(Mockito.<Iterable<SubmissionTestCase>>any()))
                .thenReturn(new ArrayList<>());

        // Act
        submissionTestCaseImpl.saveAll(new ArrayList<>());

        // Assert that nothing has changed
        verify(submissionTestCaseRepository).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#saveAll(List)}
     */
    @Test
    void testSaveAll2() {
        // Arrange
        when(submissionTestCaseRepository.saveAll(Mockito.<Iterable<SubmissionTestCase>>any()))
                .thenReturn(new ArrayList<>());

        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        testCases.add(submissionTestCase);

        // Act
        submissionTestCaseImpl.saveAll(testCases);

        // Assert that nothing has changed
        verify(submissionTestCaseRepository).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#saveAll(List)}
     */
    @Test
    void testSaveAll3() {
        // Arrange
        when(submissionTestCaseRepository.saveAll(Mockito.<Iterable<SubmissionTestCase>>any()))
                .thenReturn(new ArrayList<>());

        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        SubmissionTestCase submissionTestCase2 = new SubmissionTestCase();
        submissionTestCase2.setExtendedFeedback("42");
        submissionTestCase2.setFeedback("42");
        submissionTestCase2.setId("Id");
        submissionTestCase2.setMemory(0.5d);
        submissionTestCase2.setOutput("42");
        submissionTestCase2.setPoints(0.5d);
        submissionTestCase2.setStatus("42");
        submissionTestCase2.setSubmissionId("Submission Id");
        submissionTestCase2.setTestCaseId(2);
        submissionTestCase2.setTime(0.5d);
        submissionTestCase2.setTotal(0.5d);

        ArrayList<SubmissionTestCase> testCases = new ArrayList<>();
        testCases.add(submissionTestCase2);
        testCases.add(submissionTestCase);

        // Act
        submissionTestCaseImpl.saveAll(testCases);

        // Assert that nothing has changed
        verify(submissionTestCaseRepository).saveAll(isA(Iterable.class));
    }

    /**
     * Method under test: {@link SubmissionTestCaseImpl#findBySubmissionId(String)}
     */
    @Test
    void testFindBySubmissionId() {
        // Arrange
        ArrayList<SubmissionTestCase> submissionTestCaseList = new ArrayList<>();
        when(submissionTestCaseRepository.findBySubmissionId(Mockito.<String>any())).thenReturn(submissionTestCaseList);

        // Act
        List<SubmissionTestCase> actualFindBySubmissionIdResult = submissionTestCaseImpl.findBySubmissionId("42");

        // Assert
        verify(submissionTestCaseRepository).findBySubmissionId(eq("42"));
        assertTrue(actualFindBySubmissionIdResult.isEmpty());
        assertSame(submissionTestCaseList, actualFindBySubmissionIdResult);
    }

    /**
     * Method under test:
     * {@link SubmissionTestCaseImpl#getAllTcBySubmissionId(String)}
     */
    @Test
    void testGetAllTcBySubmissionId() {
        // Arrange
        when(submissionTestCaseRepository.findBySubmissionId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        List<SubmissionTestCase> actualAllTcBySubmissionId = submissionTestCaseImpl.getAllTcBySubmissionId("42");

        // Assert
        verify(submissionTestCaseRepository).findBySubmissionId(eq("42"));
        assertTrue(actualAllTcBySubmissionId.isEmpty());
    }

    /**
     * Method under test:
     * {@link SubmissionTestCaseImpl#getAllTcBySubmissionId(String)}
     */
    @Test
    void testGetAllTcBySubmissionId2() {
        // Arrange
        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        ArrayList<SubmissionTestCase> submissionTestCaseList = new ArrayList<>();
        submissionTestCaseList.add(submissionTestCase);
        when(submissionTestCaseRepository.findBySubmissionId(Mockito.<String>any())).thenReturn(submissionTestCaseList);

        // Act
        List<SubmissionTestCase> actualAllTcBySubmissionId = submissionTestCaseImpl.getAllTcBySubmissionId("42");

        // Assert
        verify(submissionTestCaseRepository).findBySubmissionId(eq("42"));
        assertTrue(actualAllTcBySubmissionId.isEmpty());
    }

    /**
     * Method under test:
     * {@link SubmissionTestCaseImpl#getAllTcBySubmissionId(String)}
     */
    @Test
    void testGetAllTcBySubmissionId3() {
        // Arrange
        SubmissionTestCase submissionTestCase = new SubmissionTestCase();
        submissionTestCase.setExtendedFeedback("Extended Feedback");
        submissionTestCase.setFeedback("Feedback");
        submissionTestCase.setId("42");
        submissionTestCase.setMemory(10.0d);
        submissionTestCase.setOutput("Output");
        submissionTestCase.setPoints(10.0d);
        submissionTestCase.setStatus("Status");
        submissionTestCase.setSubmissionId("42");
        submissionTestCase.setTestCaseId(1);
        submissionTestCase.setTime(10.0d);
        submissionTestCase.setTotal(10.0d);

        SubmissionTestCase submissionTestCase2 = new SubmissionTestCase();
        submissionTestCase2.setExtendedFeedback("42");
        submissionTestCase2.setFeedback("42");
        submissionTestCase2.setId("Id");
        submissionTestCase2.setMemory(0.0d);
        submissionTestCase2.setOutput("42");
        submissionTestCase2.setPoints(0.0d);
        submissionTestCase2.setStatus("42");
        submissionTestCase2.setSubmissionId("Submission Id");
        submissionTestCase2.setTestCaseId(2);
        submissionTestCase2.setTime(0.0d);
        submissionTestCase2.setTotal(0.0d);

        ArrayList<SubmissionTestCase> submissionTestCaseList = new ArrayList<>();
        submissionTestCaseList.add(submissionTestCase2);
        submissionTestCaseList.add(submissionTestCase);
        when(submissionTestCaseRepository.findBySubmissionId(Mockito.<String>any())).thenReturn(submissionTestCaseList);

        // Act
        List<SubmissionTestCase> actualAllTcBySubmissionId = submissionTestCaseImpl.getAllTcBySubmissionId("42");

        // Assert
        verify(submissionTestCaseRepository).findBySubmissionId(eq("42"));
        assertEquals(1, actualAllTcBySubmissionId.size());
    }
}
