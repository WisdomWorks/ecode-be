package com.example.codeE.model.exercise.common.problem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExerciseDataDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExerciseData#ExerciseData()}
     *   <li>{@link ExerciseData#setChecker(String)}
     *   <li>{@link ExerciseData#setCheckerArgs(String)}
     *   <li>{@link ExerciseData#setFeedback(String)}
     *   <li>{@link ExerciseData#setGenerator(String)}
     *   <li>{@link ExerciseData#setId(String)}
     *   <li>{@link ExerciseData#setNobigmath(boolean)}
     *   <li>{@link ExerciseData#setOutputLimit(int)}
     *   <li>{@link ExerciseData#setOutputPrefix(int)}
     *   <li>{@link ExerciseData#setProblemId(String)}
     *   <li>{@link ExerciseData#setUnicode(boolean)}
     *   <li>{@link ExerciseData#setZipfile(String)}
     *   <li>{@link ExerciseData#getChecker()}
     *   <li>{@link ExerciseData#getCheckerArgs()}
     *   <li>{@link ExerciseData#getFeedback()}
     *   <li>{@link ExerciseData#getGenerator()}
     *   <li>{@link ExerciseData#getId()}
     *   <li>{@link ExerciseData#getOutputLimit()}
     *   <li>{@link ExerciseData#getOutputPrefix()}
     *   <li>{@link ExerciseData#getProblemId()}
     *   <li>{@link ExerciseData#getZipfile()}
     *   <li>{@link ExerciseData#isNobigmath()}
     *   <li>{@link ExerciseData#isUnicode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExerciseData actualExerciseData = new ExerciseData();
        actualExerciseData.setChecker("Checker");
        actualExerciseData.setCheckerArgs("Checker Args");
        actualExerciseData.setFeedback("Feedback");
        actualExerciseData.setGenerator("Generator");
        actualExerciseData.setId("42");
        actualExerciseData.setNobigmath(true);
        actualExerciseData.setOutputLimit(1);
        actualExerciseData.setOutputPrefix(1);
        actualExerciseData.setProblemId("42");
        actualExerciseData.setUnicode(true);
        actualExerciseData.setZipfile("21654");
        String actualChecker = actualExerciseData.getChecker();
        String actualCheckerArgs = actualExerciseData.getCheckerArgs();
        String actualFeedback = actualExerciseData.getFeedback();
        String actualGenerator = actualExerciseData.getGenerator();
        String actualId = actualExerciseData.getId();
        int actualOutputLimit = actualExerciseData.getOutputLimit();
        int actualOutputPrefix = actualExerciseData.getOutputPrefix();
        String actualProblemId = actualExerciseData.getProblemId();
        String actualZipfile = actualExerciseData.getZipfile();
        boolean actualIsNobigmathResult = actualExerciseData.isNobigmath();

        // Assert that nothing has changed
        assertEquals("21654", actualZipfile);
        assertEquals("42", actualId);
        assertEquals("42", actualProblemId);
        assertEquals("Checker Args", actualCheckerArgs);
        assertEquals("Checker", actualChecker);
        assertEquals("Feedback", actualFeedback);
        assertEquals("Generator", actualGenerator);
        assertEquals(1, actualOutputLimit);
        assertEquals(1, actualOutputPrefix);
        assertTrue(actualIsNobigmathResult);
        assertTrue(actualExerciseData.isUnicode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ExerciseData#ExerciseData(String, String, String, String, int, int, String, String, boolean, boolean, String)}
     *   <li>{@link ExerciseData#setChecker(String)}
     *   <li>{@link ExerciseData#setCheckerArgs(String)}
     *   <li>{@link ExerciseData#setFeedback(String)}
     *   <li>{@link ExerciseData#setGenerator(String)}
     *   <li>{@link ExerciseData#setId(String)}
     *   <li>{@link ExerciseData#setNobigmath(boolean)}
     *   <li>{@link ExerciseData#setOutputLimit(int)}
     *   <li>{@link ExerciseData#setOutputPrefix(int)}
     *   <li>{@link ExerciseData#setProblemId(String)}
     *   <li>{@link ExerciseData#setUnicode(boolean)}
     *   <li>{@link ExerciseData#setZipfile(String)}
     *   <li>{@link ExerciseData#getChecker()}
     *   <li>{@link ExerciseData#getCheckerArgs()}
     *   <li>{@link ExerciseData#getFeedback()}
     *   <li>{@link ExerciseData#getGenerator()}
     *   <li>{@link ExerciseData#getId()}
     *   <li>{@link ExerciseData#getOutputLimit()}
     *   <li>{@link ExerciseData#getOutputPrefix()}
     *   <li>{@link ExerciseData#getProblemId()}
     *   <li>{@link ExerciseData#getZipfile()}
     *   <li>{@link ExerciseData#isNobigmath()}
     *   <li>{@link ExerciseData#isUnicode()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ExerciseData actualExerciseData = new ExerciseData("42", "42", "21654", "Generator", 1, 1, "Feedback", "Checker",
                true, true, "Checker Args");
        actualExerciseData.setChecker("Checker");
        actualExerciseData.setCheckerArgs("Checker Args");
        actualExerciseData.setFeedback("Feedback");
        actualExerciseData.setGenerator("Generator");
        actualExerciseData.setId("42");
        actualExerciseData.setNobigmath(true);
        actualExerciseData.setOutputLimit(1);
        actualExerciseData.setOutputPrefix(1);
        actualExerciseData.setProblemId("42");
        actualExerciseData.setUnicode(true);
        actualExerciseData.setZipfile("21654");
        String actualChecker = actualExerciseData.getChecker();
        String actualCheckerArgs = actualExerciseData.getCheckerArgs();
        String actualFeedback = actualExerciseData.getFeedback();
        String actualGenerator = actualExerciseData.getGenerator();
        String actualId = actualExerciseData.getId();
        int actualOutputLimit = actualExerciseData.getOutputLimit();
        int actualOutputPrefix = actualExerciseData.getOutputPrefix();
        String actualProblemId = actualExerciseData.getProblemId();
        String actualZipfile = actualExerciseData.getZipfile();
        boolean actualIsNobigmathResult = actualExerciseData.isNobigmath();

        // Assert that nothing has changed
        assertEquals("21654", actualZipfile);
        assertEquals("42", actualId);
        assertEquals("42", actualProblemId);
        assertEquals("Checker Args", actualCheckerArgs);
        assertEquals("Checker", actualChecker);
        assertEquals("Feedback", actualFeedback);
        assertEquals("Generator", actualGenerator);
        assertEquals(1, actualOutputLimit);
        assertEquals(1, actualOutputPrefix);
        assertTrue(actualIsNobigmathResult);
        assertTrue(actualExerciseData.isUnicode());
    }
}
