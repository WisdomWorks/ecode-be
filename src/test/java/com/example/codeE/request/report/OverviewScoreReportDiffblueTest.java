package com.example.codeE.request.report;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class OverviewScoreReportDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OverviewScoreReport#OverviewScoreReport()}
     *   <li>{@link OverviewScoreReport#setAScore(int)}
     *   <li>{@link OverviewScoreReport#setBScore(int)}
     *   <li>{@link OverviewScoreReport#setCScore(int)}
     *   <li>{@link OverviewScoreReport#setNumberStudent(int)}
     *   <li>{@link OverviewScoreReport#setNumberSubmission(int)}
     *   <li>{@link OverviewScoreReport#getAScore()}
     *   <li>{@link OverviewScoreReport#getBScore()}
     *   <li>{@link OverviewScoreReport#getCScore()}
     *   <li>{@link OverviewScoreReport#getNumberStudent()}
     *   <li>{@link OverviewScoreReport#getNumberSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        OverviewScoreReport actualOverviewScoreReport = new OverviewScoreReport();
        actualOverviewScoreReport.setAScore(3);
        actualOverviewScoreReport.setBScore(3);
        actualOverviewScoreReport.setCScore(3);
        actualOverviewScoreReport.setNumberStudent(10);
        actualOverviewScoreReport.setNumberSubmission(10);
        int actualAScore = actualOverviewScoreReport.getAScore();
        int actualBScore = actualOverviewScoreReport.getBScore();
        int actualCScore = actualOverviewScoreReport.getCScore();
        int actualNumberStudent = actualOverviewScoreReport.getNumberStudent();

        // Assert that nothing has changed
        assertEquals(10, actualNumberStudent);
        assertEquals(10, actualOverviewScoreReport.getNumberSubmission());
        assertEquals(3, actualAScore);
        assertEquals(3, actualBScore);
        assertEquals(3, actualCScore);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link OverviewScoreReport#OverviewScoreReport(int, int, int, int, int)}
     *   <li>{@link OverviewScoreReport#setAScore(int)}
     *   <li>{@link OverviewScoreReport#setBScore(int)}
     *   <li>{@link OverviewScoreReport#setCScore(int)}
     *   <li>{@link OverviewScoreReport#setNumberStudent(int)}
     *   <li>{@link OverviewScoreReport#setNumberSubmission(int)}
     *   <li>{@link OverviewScoreReport#getAScore()}
     *   <li>{@link OverviewScoreReport#getBScore()}
     *   <li>{@link OverviewScoreReport#getCScore()}
     *   <li>{@link OverviewScoreReport#getNumberStudent()}
     *   <li>{@link OverviewScoreReport#getNumberSubmission()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        OverviewScoreReport actualOverviewScoreReport = new OverviewScoreReport(3, 3, 3, 10, 10);
        actualOverviewScoreReport.setAScore(3);
        actualOverviewScoreReport.setBScore(3);
        actualOverviewScoreReport.setCScore(3);
        actualOverviewScoreReport.setNumberStudent(10);
        actualOverviewScoreReport.setNumberSubmission(10);
        int actualAScore = actualOverviewScoreReport.getAScore();
        int actualBScore = actualOverviewScoreReport.getBScore();
        int actualCScore = actualOverviewScoreReport.getCScore();
        int actualNumberStudent = actualOverviewScoreReport.getNumberStudent();

        // Assert that nothing has changed
        assertEquals(10, actualNumberStudent);
        assertEquals(10, actualOverviewScoreReport.getNumberSubmission());
        assertEquals(3, actualAScore);
        assertEquals(3, actualBScore);
        assertEquals(3, actualCScore);
    }
}
