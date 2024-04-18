package com.example.codeE.model.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class ExerciseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Exercise#Exercise(String, String, String, String, Date, Date, int, int, String, String, boolean, List)}
     *   <li>{@link Exercise#onCreate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> publicGroupIds = new ArrayList<>();

        // Act
        Exercise actualExercise = new Exercise("42", "42", "Exercise Name", "Key", startTime, endTime, 1, 1, "Type",
                "Exercise Description", true, publicGroupIds);
        actualExercise.onCreate();

        // Assert
        assertEquals("42", actualExercise.getExerciseId());
        assertEquals("42", actualExercise.getTopicId());
        assertEquals("Exercise Description", actualExercise.getExerciseDescription());
        assertEquals("Exercise Name", actualExercise.getExerciseName());
        assertEquals("Key", actualExercise.getKey());
        assertEquals("Type", actualExercise.getType());
        assertNull(actualExercise.getCreatedDate());
        assertNull(actualExercise.getUpdatedDate());
        assertEquals(1, actualExercise.getDurationTime());
        assertEquals(1, actualExercise.getReAttempt());
        assertTrue(actualExercise.isShowAll());
        List<String> publicGroupIds2 = actualExercise.getPublicGroupIds();
        assertTrue(publicGroupIds2.isEmpty());
        assertSame(publicGroupIds, publicGroupIds2);
        assertSame(endTime, actualExercise.getEndTime());
        assertSame(startTime, actualExercise.getStartTime());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Exercise#Exercise(String, String, String, Date, Date, int, int, String, String, boolean, List)}
     *   <li>{@link Exercise#onCreate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> publicGroupIds = new ArrayList<>();

        // Act
        Exercise actualExercise = new Exercise("42", "Exercise Name", "Key", startTime, endTime, 1, 1, "Type",
                "Exercise Description", true, publicGroupIds);
        actualExercise.onCreate();

        // Assert
        assertEquals("42", actualExercise.getTopicId());
        assertEquals("Exercise Description", actualExercise.getExerciseDescription());
        assertEquals("Exercise Name", actualExercise.getExerciseName());
        assertEquals("Key", actualExercise.getKey());
        assertEquals("Type", actualExercise.getType());
        assertNull(actualExercise.getCreatedDate());
        assertNull(actualExercise.getExerciseId());
        assertNull(actualExercise.getUpdatedDate());
        assertEquals(1, actualExercise.getDurationTime());
        assertEquals(1, actualExercise.getReAttempt());
        assertTrue(actualExercise.isShowAll());
        List<String> publicGroupIds2 = actualExercise.getPublicGroupIds();
        assertTrue(publicGroupIds2.isEmpty());
        assertSame(publicGroupIds, publicGroupIds2);
        assertSame(endTime, actualExercise.getEndTime());
        assertSame(startTime, actualExercise.getStartTime());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link Exercise#Exercise(String, String, String, Date, Date, String, List)}
     *   <li>{@link Exercise#onCreate()}
     * </ul>
     */
    @Test
    void testGettersAndSetters3() {
        // Arrange
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        ArrayList<String> publicGroupIds = new ArrayList<>();

        // Act
        Exercise actualExercise = new Exercise("42", "Exercise Name", "Key", startTime, endTime, "Type", publicGroupIds);
        actualExercise.onCreate();

        // Assert
        assertEquals("42", actualExercise.getTopicId());
        assertEquals("Exercise Name", actualExercise.getExerciseName());
        assertEquals("Key", actualExercise.getKey());
        assertEquals("Type", actualExercise.getType());
        assertNull(actualExercise.getCreatedDate());
        assertNull(actualExercise.getExerciseDescription());
        assertNull(actualExercise.getExerciseId());
        assertNull(actualExercise.getUpdatedDate());
        assertEquals(0, actualExercise.getDurationTime());
        assertEquals(0, actualExercise.getReAttempt());
        assertTrue(actualExercise.isShowAll());
        List<String> publicGroupIds2 = actualExercise.getPublicGroupIds();
        assertTrue(publicGroupIds2.isEmpty());
        assertSame(publicGroupIds, publicGroupIds2);
        assertSame(endTime, actualExercise.getEndTime());
        assertSame(startTime, actualExercise.getStartTime());
    }
}
