package com.example.codeE.model.exercise.common.problem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ExerciseTestCaseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ExerciseTestCase#ExerciseTestCase()}
     *   <li>{@link ExerciseTestCase#setChecker(String)}
     *   <li>{@link ExerciseTestCase#setCheckerArgs(String)}
     *   <li>{@link ExerciseTestCase#setDatasetId(String)}
     *   <li>{@link ExerciseTestCase#setGeneratorArgs(String)}
     *   <li>{@link ExerciseTestCase#setId(String)}
     *   <li>{@link ExerciseTestCase#setInputFile(String)}
     *   <li>{@link ExerciseTestCase#setIsPretest(Boolean)}
     *   <li>{@link ExerciseTestCase#setOrder(Integer)}
     *   <li>{@link ExerciseTestCase#setOutputFile(String)}
     *   <li>{@link ExerciseTestCase#setOutputLimit(Integer)}
     *   <li>{@link ExerciseTestCase#setOutputPrefix(Integer)}
     *   <li>{@link ExerciseTestCase#setPoints(Integer)}
     *   <li>{@link ExerciseTestCase#setType(char)}
     *   <li>{@link ExerciseTestCase#getChecker()}
     *   <li>{@link ExerciseTestCase#getCheckerArgs()}
     *   <li>{@link ExerciseTestCase#getDatasetId()}
     *   <li>{@link ExerciseTestCase#getGeneratorArgs()}
     *   <li>{@link ExerciseTestCase#getId()}
     *   <li>{@link ExerciseTestCase#getInputFile()}
     *   <li>{@link ExerciseTestCase#getIsPretest()}
     *   <li>{@link ExerciseTestCase#getOrder()}
     *   <li>{@link ExerciseTestCase#getOutputFile()}
     *   <li>{@link ExerciseTestCase#getOutputLimit()}
     *   <li>{@link ExerciseTestCase#getOutputPrefix()}
     *   <li>{@link ExerciseTestCase#getPoints()}
     *   <li>{@link ExerciseTestCase#getType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        ExerciseTestCase actualExerciseTestCase = new ExerciseTestCase();
        actualExerciseTestCase.setChecker("Checker");
        actualExerciseTestCase.setCheckerArgs("Checker Args");
        actualExerciseTestCase.setDatasetId("42");
        actualExerciseTestCase.setGeneratorArgs("Generator Args");
        actualExerciseTestCase.setId("42");
        actualExerciseTestCase.setInputFile("Input File");
        actualExerciseTestCase.setIsPretest(true);
        actualExerciseTestCase.setOrder(1);
        actualExerciseTestCase.setOutputFile("Output File");
        actualExerciseTestCase.setOutputLimit(1);
        actualExerciseTestCase.setOutputPrefix(1);
        actualExerciseTestCase.setPoints(1);
        actualExerciseTestCase.setType('A');
        String actualChecker = actualExerciseTestCase.getChecker();
        String actualCheckerArgs = actualExerciseTestCase.getCheckerArgs();
        String actualDatasetId = actualExerciseTestCase.getDatasetId();
        String actualGeneratorArgs = actualExerciseTestCase.getGeneratorArgs();
        String actualId = actualExerciseTestCase.getId();
        String actualInputFile = actualExerciseTestCase.getInputFile();
        Boolean actualIsPretest = actualExerciseTestCase.getIsPretest();
        Integer actualOrder = actualExerciseTestCase.getOrder();
        String actualOutputFile = actualExerciseTestCase.getOutputFile();
        Integer actualOutputLimit = actualExerciseTestCase.getOutputLimit();
        Integer actualOutputPrefix = actualExerciseTestCase.getOutputPrefix();
        Integer actualPoints = actualExerciseTestCase.getPoints();

        // Assert that nothing has changed
        assertEquals("42", actualDatasetId);
        assertEquals("42", actualId);
        assertEquals("Checker Args", actualCheckerArgs);
        assertEquals("Checker", actualChecker);
        assertEquals("Generator Args", actualGeneratorArgs);
        assertEquals("Input File", actualInputFile);
        assertEquals("Output File", actualOutputFile);
        assertEquals('A', actualExerciseTestCase.getType());
        assertEquals(1, actualOrder.intValue());
        assertEquals(1, actualOutputLimit.intValue());
        assertEquals(1, actualOutputPrefix.intValue());
        assertEquals(1, actualPoints.intValue());
        assertTrue(actualIsPretest);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link ExerciseTestCase#ExerciseTestCase(String, String, Integer, char, String, String, String, Integer, Boolean, Integer, Integer, String, String)}
     *   <li>{@link ExerciseTestCase#setChecker(String)}
     *   <li>{@link ExerciseTestCase#setCheckerArgs(String)}
     *   <li>{@link ExerciseTestCase#setDatasetId(String)}
     *   <li>{@link ExerciseTestCase#setGeneratorArgs(String)}
     *   <li>{@link ExerciseTestCase#setId(String)}
     *   <li>{@link ExerciseTestCase#setInputFile(String)}
     *   <li>{@link ExerciseTestCase#setIsPretest(Boolean)}
     *   <li>{@link ExerciseTestCase#setOrder(Integer)}
     *   <li>{@link ExerciseTestCase#setOutputFile(String)}
     *   <li>{@link ExerciseTestCase#setOutputLimit(Integer)}
     *   <li>{@link ExerciseTestCase#setOutputPrefix(Integer)}
     *   <li>{@link ExerciseTestCase#setPoints(Integer)}
     *   <li>{@link ExerciseTestCase#setType(char)}
     *   <li>{@link ExerciseTestCase#getChecker()}
     *   <li>{@link ExerciseTestCase#getCheckerArgs()}
     *   <li>{@link ExerciseTestCase#getDatasetId()}
     *   <li>{@link ExerciseTestCase#getGeneratorArgs()}
     *   <li>{@link ExerciseTestCase#getId()}
     *   <li>{@link ExerciseTestCase#getInputFile()}
     *   <li>{@link ExerciseTestCase#getIsPretest()}
     *   <li>{@link ExerciseTestCase#getOrder()}
     *   <li>{@link ExerciseTestCase#getOutputFile()}
     *   <li>{@link ExerciseTestCase#getOutputLimit()}
     *   <li>{@link ExerciseTestCase#getOutputPrefix()}
     *   <li>{@link ExerciseTestCase#getPoints()}
     *   <li>{@link ExerciseTestCase#getType()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange and Act
        ExerciseTestCase actualExerciseTestCase = new ExerciseTestCase("42", "42", 1, 'A', "Input File", "Output File",
                "Generator Args", 1, true, 1, 1, "Checker", "Checker Args");
        actualExerciseTestCase.setChecker("Checker");
        actualExerciseTestCase.setCheckerArgs("Checker Args");
        actualExerciseTestCase.setDatasetId("42");
        actualExerciseTestCase.setGeneratorArgs("Generator Args");
        actualExerciseTestCase.setId("42");
        actualExerciseTestCase.setInputFile("Input File");
        actualExerciseTestCase.setIsPretest(true);
        actualExerciseTestCase.setOrder(1);
        actualExerciseTestCase.setOutputFile("Output File");
        actualExerciseTestCase.setOutputLimit(1);
        actualExerciseTestCase.setOutputPrefix(1);
        actualExerciseTestCase.setPoints(1);
        actualExerciseTestCase.setType('A');
        String actualChecker = actualExerciseTestCase.getChecker();
        String actualCheckerArgs = actualExerciseTestCase.getCheckerArgs();
        String actualDatasetId = actualExerciseTestCase.getDatasetId();
        String actualGeneratorArgs = actualExerciseTestCase.getGeneratorArgs();
        String actualId = actualExerciseTestCase.getId();
        String actualInputFile = actualExerciseTestCase.getInputFile();
        Boolean actualIsPretest = actualExerciseTestCase.getIsPretest();
        Integer actualOrder = actualExerciseTestCase.getOrder();
        String actualOutputFile = actualExerciseTestCase.getOutputFile();
        Integer actualOutputLimit = actualExerciseTestCase.getOutputLimit();
        Integer actualOutputPrefix = actualExerciseTestCase.getOutputPrefix();
        Integer actualPoints = actualExerciseTestCase.getPoints();

        // Assert that nothing has changed
        assertEquals("42", actualDatasetId);
        assertEquals("42", actualId);
        assertEquals("Checker Args", actualCheckerArgs);
        assertEquals("Checker", actualChecker);
        assertEquals("Generator Args", actualGeneratorArgs);
        assertEquals("Input File", actualInputFile);
        assertEquals("Output File", actualOutputFile);
        assertEquals('A', actualExerciseTestCase.getType());
        assertEquals(1, actualOrder.intValue());
        assertEquals(1, actualOutputLimit.intValue());
        assertEquals(1, actualOutputPrefix.intValue());
        assertEquals(1, actualPoints.intValue());
        assertTrue(actualIsPretest);
    }
}
