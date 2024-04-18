package com.example.codeE.request.exercise.quiz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.common.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ExcelResultDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExcelResult#ExcelResult()}
     *   <li>{@link ExcelResult#setFailedRows(List)}
     *   <li>{@link ExcelResult#setQuestions(List)}
     *   <li>{@link ExcelResult#getFailedRows()}
     *   <li>{@link ExcelResult#getQuestions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExcelResult actualExcelResult = new ExcelResult();
        ArrayList<Integer> failedRows = new ArrayList<>();
        actualExcelResult.setFailedRows(failedRows);
        ArrayList<QuizQuestion> questions = new ArrayList<>();
        actualExcelResult.setQuestions(questions);
        List<Integer> actualFailedRows = actualExcelResult.getFailedRows();

        // Assert that nothing has changed
        assertSame(failedRows, actualFailedRows);
        assertSame(questions, actualExcelResult.getQuestions());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExcelResult#ExcelResult(List, List)}
     *   <li>{@link ExcelResult#setFailedRows(List)}
     *   <li>{@link ExcelResult#setQuestions(List)}
     *   <li>{@link ExcelResult#getFailedRows()}
     *   <li>{@link ExcelResult#getQuestions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        ArrayList<QuizQuestion> questions = new ArrayList<>();

        // Act
        ExcelResult actualExcelResult = new ExcelResult(questions, new ArrayList<>());
        ArrayList<Integer> failedRows = new ArrayList<>();
        actualExcelResult.setFailedRows(failedRows);
        ArrayList<QuizQuestion> questions2 = new ArrayList<>();
        actualExcelResult.setQuestions(questions2);
        List<Integer> actualFailedRows = actualExcelResult.getFailedRows();
        List<QuizQuestion> actualQuestions = actualExcelResult.getQuestions();

        // Assert that nothing has changed
        assertEquals(questions, actualFailedRows);
        assertEquals(questions, actualQuestions);
        assertSame(failedRows, actualFailedRows);
        assertSame(questions2, actualQuestions);
    }
}
