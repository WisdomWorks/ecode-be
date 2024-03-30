package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.repository.CodeExerciseRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CodeExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CodeExerciseImplDiffblueTest {
    @Autowired
    private CodeExerciseImpl codeExerciseImpl;

    @MockBean
    private CodeExerciseRepository codeExerciseRepository;

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemIds(List)}
     */
    @Test
    void testGetProblemIds() {
        // Arrange, Act and Assert
        assertTrue(codeExerciseImpl.getProblemIds(new ArrayList<>()).isEmpty());
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemIds(List)}
     */
    @Test
    void testGetProblemIds2() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        CodeExercise codeExercise2 = new CodeExercise();
        codeExercise2.setAllowedLanguageIds(new ArrayList<>());
        codeExercise2.setCreatedDate("2020-03-01");
        codeExercise2.setDescription("The characteristics of someone or something");
        codeExercise2.setDurationTime(1);
        codeExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setExerciseId("42");
        codeExercise2.setExerciseName("Exercise Name");
        codeExercise2.setKey("Key");
        codeExercise2.setMemoryLimit(1);
        codeExercise2.setPartial(true);
        codeExercise2.setPoints(10.0d);
        codeExercise2.setPublicGroupIds(new ArrayList<>());
        codeExercise2.setReAttempt(1);
        codeExercise2.setShortCircuit(true);
        codeExercise2.setShowAll(true);
        codeExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setTimeLimit(10.0d);
        codeExercise2.setTopicId("42");
        codeExercise2.setType("Type");
        codeExercise2.setUpdatedDate("2020-03-01");

        ArrayList<CodeExercise> problems = new ArrayList<>();
        problems.add(codeExercise2);

        // Act
        List<String> actualProblemIds = codeExerciseImpl.getProblemIds(problems);

        // Assert
        verify(codeExerciseRepository).findById(eq("42"));
        assertEquals(1, actualProblemIds.size());
        assertEquals("42", actualProblemIds.get(0));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemIds(List)}
     */
    @Test
    void testGetProblemIds3() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        CodeExercise codeExercise2 = new CodeExercise();
        codeExercise2.setAllowedLanguageIds(new ArrayList<>());
        codeExercise2.setCreatedDate("2020-03-01");
        codeExercise2.setDescription("The characteristics of someone or something");
        codeExercise2.setDurationTime(1);
        codeExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setExerciseId("42");
        codeExercise2.setExerciseName("Exercise Name");
        codeExercise2.setKey("Key");
        codeExercise2.setMemoryLimit(1);
        codeExercise2.setPartial(true);
        codeExercise2.setPoints(10.0d);
        codeExercise2.setPublicGroupIds(new ArrayList<>());
        codeExercise2.setReAttempt(1);
        codeExercise2.setShortCircuit(true);
        codeExercise2.setShowAll(true);
        codeExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setTimeLimit(10.0d);
        codeExercise2.setTopicId("42");
        codeExercise2.setType("Type");
        codeExercise2.setUpdatedDate("2020-03-01");

        CodeExercise codeExercise3 = new CodeExercise();
        codeExercise3.setAllowedLanguageIds(new ArrayList<>());
        codeExercise3.setCreatedDate("2020/03/01");
        codeExercise3.setDescription("Description");
        codeExercise3.setDurationTime(0);
        codeExercise3.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise3.setExerciseId("Exercise Id");
        codeExercise3.setExerciseName("42");
        codeExercise3.setKey("42");
        codeExercise3.setMemoryLimit(0);
        codeExercise3.setPartial(false);
        codeExercise3.setPoints(0.5d);
        codeExercise3.setPublicGroupIds(new ArrayList<>());
        codeExercise3.setReAttempt(0);
        codeExercise3.setShortCircuit(false);
        codeExercise3.setShowAll(false);
        codeExercise3.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise3.setTimeLimit(0.5d);
        codeExercise3.setTopicId("Topic Id");
        codeExercise3.setType("42");
        codeExercise3.setUpdatedDate("2020/03/01");

        ArrayList<CodeExercise> problems = new ArrayList<>();
        problems.add(codeExercise3);
        problems.add(codeExercise2);

        // Act
        List<String> actualProblemIds = codeExerciseImpl.getProblemIds(problems);

        // Assert
        verify(codeExerciseRepository, atLeast(1)).findById(Mockito.<String>any());
        assertEquals(2, actualProblemIds.size());
        assertEquals("42", actualProblemIds.get(0));
        assertEquals("42", actualProblemIds.get(1));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemIds(List)}
     */
    @Test
    void testGetProblemIds4() {
        // Arrange
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<CodeExercise> problems = new ArrayList<>();
        problems.add(codeExercise);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.getProblemIds(problems));
        verify(codeExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemById(String)}
     */
    @Test
    void testGetProblemById() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeExercise actualProblemById = codeExerciseImpl.getProblemById("42");

        // Assert
        verify(codeExerciseRepository).findById(eq("42"));
        assertSame(codeExercise, actualProblemById);
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemById(String)}
     */
    @Test
    void testGetProblemById2() {
        // Arrange
        Optional<CodeExercise> emptyResult = Optional.empty();
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.getProblemById("42"));
        verify(codeExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getProblemById(String)}
     */
    @Test
    void testGetProblemById3() {
        // Arrange
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("Problem not found"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.getProblemById("42"));
        verify(codeExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getCodeExerciseById(String)}
     */
    @Test
    void testGetCodeExerciseById() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setMemoryLimit(1);
        codeExercise.setPartial(true);
        codeExercise.setPoints(10.0d);
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShortCircuit(true);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeExercise actualCodeExerciseById = codeExerciseImpl.getCodeExerciseById("42");

        // Assert
        verify(codeExerciseRepository).findById(eq("42"));
        assertSame(codeExercise, actualCodeExerciseById);
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getCodeExerciseById(String)}
     */
    @Test
    void testGetCodeExerciseById2() {
        // Arrange
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.getCodeExerciseById("42"));
        verify(codeExerciseRepository).findById(eq("42"));
    }
}
