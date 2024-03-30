package com.example.codeE.model.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SubmissionTestCaseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link SubmissionTestCase#SubmissionTestCase()}
     *   <li>{@link SubmissionTestCase#setExtendedFeedback(String)}
     *   <li>{@link SubmissionTestCase#setFeedback(String)}
     *   <li>{@link SubmissionTestCase#setId(String)}
     *   <li>{@link SubmissionTestCase#setMemory(Double)}
     *   <li>{@link SubmissionTestCase#setOutput(String)}
     *   <li>{@link SubmissionTestCase#setPoints(Double)}
     *   <li>{@link SubmissionTestCase#setStatus(String)}
     *   <li>{@link SubmissionTestCase#setSubmissionId(String)}
     *   <li>{@link SubmissionTestCase#setTestCaseId(Integer)}
     *   <li>{@link SubmissionTestCase#setTime(Double)}
     *   <li>{@link SubmissionTestCase#setTotal(Double)}
     *   <li>{@link SubmissionTestCase#getExtendedFeedback()}
     *   <li>{@link SubmissionTestCase#getFeedback()}
     *   <li>{@link SubmissionTestCase#getId()}
     *   <li>{@link SubmissionTestCase#getMemory()}
     *   <li>{@link SubmissionTestCase#getOutput()}
     *   <li>{@link SubmissionTestCase#getPoints()}
     *   <li>{@link SubmissionTestCase#getStatus()}
     *   <li>{@link SubmissionTestCase#getSubmissionId()}
     *   <li>{@link SubmissionTestCase#getTestCaseId()}
     *   <li>{@link SubmissionTestCase#getTime()}
     *   <li>{@link SubmissionTestCase#getTotal()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        SubmissionTestCase actualSubmissionTestCase = new SubmissionTestCase();
        actualSubmissionTestCase.setExtendedFeedback("Extended Feedback");
        actualSubmissionTestCase.setFeedback("Feedback");
        actualSubmissionTestCase.setId("42");
        actualSubmissionTestCase.setMemory(10.0d);
        actualSubmissionTestCase.setOutput("Output");
        actualSubmissionTestCase.setPoints(10.0d);
        actualSubmissionTestCase.setStatus("Status");
        actualSubmissionTestCase.setSubmissionId("42");
        actualSubmissionTestCase.setTestCaseId(1);
        actualSubmissionTestCase.setTime(10.0d);
        actualSubmissionTestCase.setTotal(10.0d);
        String actualExtendedFeedback = actualSubmissionTestCase.getExtendedFeedback();
        String actualFeedback = actualSubmissionTestCase.getFeedback();
        String actualId = actualSubmissionTestCase.getId();
        Double actualMemory = actualSubmissionTestCase.getMemory();
        String actualOutput = actualSubmissionTestCase.getOutput();
        Double actualPoints = actualSubmissionTestCase.getPoints();
        String actualStatus = actualSubmissionTestCase.getStatus();
        String actualSubmissionId = actualSubmissionTestCase.getSubmissionId();
        Integer actualTestCaseId = actualSubmissionTestCase.getTestCaseId();
        Double actualTime = actualSubmissionTestCase.getTime();
        Double actualTotal = actualSubmissionTestCase.getTotal();

        // Assert that nothing has changed
        assertEquals("42", actualId);
        assertEquals("42", actualSubmissionId);
        assertEquals("Extended Feedback", actualExtendedFeedback);
        assertEquals("Feedback", actualFeedback);
        assertEquals("Output", actualOutput);
        assertEquals("Status", actualStatus);
        assertEquals(1, actualTestCaseId.intValue());
        assertEquals(10.0d, actualMemory.doubleValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTime.doubleValue());
        assertEquals(10.0d, actualTotal.doubleValue());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link SubmissionTestCase#SubmissionTestCase(String, String, Integer, String, Double, Double, Double, Double, String, String, String)}
     *   <li>{@link SubmissionTestCase#setExtendedFeedback(String)}
     *   <li>{@link SubmissionTestCase#setFeedback(String)}
     *   <li>{@link SubmissionTestCase#setId(String)}
     *   <li>{@link SubmissionTestCase#setMemory(Double)}
     *   <li>{@link SubmissionTestCase#setOutput(String)}
     *   <li>{@link SubmissionTestCase#setPoints(Double)}
     *   <li>{@link SubmissionTestCase#setStatus(String)}
     *   <li>{@link SubmissionTestCase#setSubmissionId(String)}
     *   <li>{@link SubmissionTestCase#setTestCaseId(Integer)}
     *   <li>{@link SubmissionTestCase#setTime(Double)}
     *   <li>{@link SubmissionTestCase#setTotal(Double)}
     *   <li>{@link SubmissionTestCase#getExtendedFeedback()}
     *   <li>{@link SubmissionTestCase#getFeedback()}
     *   <li>{@link SubmissionTestCase#getId()}
     *   <li>{@link SubmissionTestCase#getMemory()}
     *   <li>{@link SubmissionTestCase#getOutput()}
     *   <li>{@link SubmissionTestCase#getPoints()}
     *   <li>{@link SubmissionTestCase#getStatus()}
     *   <li>{@link SubmissionTestCase#getSubmissionId()}
     *   <li>{@link SubmissionTestCase#getTestCaseId()}
     *   <li>{@link SubmissionTestCase#getTime()}
     *   <li>{@link SubmissionTestCase#getTotal()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        SubmissionTestCase actualSubmissionTestCase = new SubmissionTestCase("42", "42", 1, "Status", 10.0d, 10.0d, 10.0d,
                10.0d, "Feedback", "Extended Feedback", "Output");
        actualSubmissionTestCase.setExtendedFeedback("Extended Feedback");
        actualSubmissionTestCase.setFeedback("Feedback");
        actualSubmissionTestCase.setId("42");
        actualSubmissionTestCase.setMemory(10.0d);
        actualSubmissionTestCase.setOutput("Output");
        actualSubmissionTestCase.setPoints(10.0d);
        actualSubmissionTestCase.setStatus("Status");
        actualSubmissionTestCase.setSubmissionId("42");
        actualSubmissionTestCase.setTestCaseId(1);
        actualSubmissionTestCase.setTime(10.0d);
        actualSubmissionTestCase.setTotal(10.0d);
        String actualExtendedFeedback = actualSubmissionTestCase.getExtendedFeedback();
        String actualFeedback = actualSubmissionTestCase.getFeedback();
        String actualId = actualSubmissionTestCase.getId();
        Double actualMemory = actualSubmissionTestCase.getMemory();
        String actualOutput = actualSubmissionTestCase.getOutput();
        Double actualPoints = actualSubmissionTestCase.getPoints();
        String actualStatus = actualSubmissionTestCase.getStatus();
        String actualSubmissionId = actualSubmissionTestCase.getSubmissionId();
        Integer actualTestCaseId = actualSubmissionTestCase.getTestCaseId();
        Double actualTime = actualSubmissionTestCase.getTime();
        Double actualTotal = actualSubmissionTestCase.getTotal();

        // Assert that nothing has changed
        assertEquals("42", actualId);
        assertEquals("42", actualSubmissionId);
        assertEquals("Extended Feedback", actualExtendedFeedback);
        assertEquals("Feedback", actualFeedback);
        assertEquals("Output", actualOutput);
        assertEquals("Status", actualStatus);
        assertEquals(1, actualTestCaseId.intValue());
        assertEquals(10.0d, actualMemory.doubleValue());
        assertEquals(10.0d, actualPoints.doubleValue());
        assertEquals(10.0d, actualTime.doubleValue());
        assertEquals(10.0d, actualTotal.doubleValue());
    }
}
