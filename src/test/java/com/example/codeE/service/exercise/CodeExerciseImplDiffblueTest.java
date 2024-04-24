package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.repository.CodeExerciseRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.request.exercise.code.CodeDetailResponse;
import com.example.codeE.request.exercise.code.UpdateCodeExerciseRequest;
import com.google.api.client.testing.util.TestableByteArrayOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
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

    @MockBean
    private ExerciseRepository exerciseRepository;

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
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
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
        codeExercise2.setExerciseDescription("Exercise Description");
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
        codeExercise2.setTemplate("Template");
        codeExercise2.setTestCases(new ArrayList<>());
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
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
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
        codeExercise2.setExerciseDescription("Exercise Description");
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
        codeExercise2.setTemplate("Template");
        codeExercise2.setTestCases(new ArrayList<>());
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
        codeExercise3.setExerciseDescription("42");
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
        codeExercise3.setTemplate("42");
        codeExercise3.setTestCases(new ArrayList<>());
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
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
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
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
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
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
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

    /**
     * Method under test: {@link CodeExerciseImpl#getCodeExerciseDetail(String)}
     */
    @Test
    void testGetCodeExerciseDetail() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        ArrayList<String> allowedLanguageIds = new ArrayList<>();
        codeExercise.setAllowedLanguageIds(allowedLanguageIds);
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        codeExercise.setEndTime(endTime);
        codeExercise.setExerciseDescription("Exercise Description");
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
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        codeExercise.setStartTime(startTime);
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeDetailResponse actualCodeExerciseDetail = codeExerciseImpl.getCodeExerciseDetail("42");

        // Assert
        verify(codeExerciseRepository).findById(eq("42"));
        assertEquals("42", actualCodeExerciseDetail.getExerciseId());
        assertEquals("42", actualCodeExerciseDetail.getTopicId());
        assertEquals("Exercise Name", actualCodeExerciseDetail.getExerciseName());
        assertEquals("The characteristics of someone or something", actualCodeExerciseDetail.getDescription());
        assertEquals("Type", actualCodeExerciseDetail.getType());
        assertEquals(1, actualCodeExerciseDetail.getDurationTime());
        assertEquals(1, actualCodeExerciseDetail.getReAttempt());
        assertTrue(actualCodeExerciseDetail.getLanguageTemplate().isEmpty());
        assertEquals(allowedLanguageIds, actualCodeExerciseDetail.getTestCases());
        assertSame(endTime, actualCodeExerciseDetail.getEndTime());
        assertSame(startTime, actualCodeExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getCodeExerciseDetail(String)}
     */
    @Test
    void testGetCodeExerciseDetail2() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenReturn(1);
        when(codeExercise.getReAttempt()).thenReturn(1);
        when(codeExercise.getDescription()).thenReturn("The characteristics of someone or something");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getType()).thenReturn("Type");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(codeExercise.getEndTime()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(codeExercise.getStartTime()).thenReturn(fromResult2);
        when(codeExercise.getAllowedLanguageIds()).thenReturn(stringList);
        when(codeExercise.getTestCases()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setAllowedLanguageIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setMemoryLimit(Mockito.<Integer>any());
        doNothing().when(codeExercise).setPartial(anyBoolean());
        doNothing().when(codeExercise).setPoints(Mockito.<Double>any());
        doNothing().when(codeExercise).setShortCircuit(Mockito.<Boolean>any());
        doNothing().when(codeExercise).setTemplate(Mockito.<String>any());
        doNothing().when(codeExercise).setTestCases(Mockito.<List<TestCase>>any());
        doNothing().when(codeExercise).setTimeLimit(Mockito.<Double>any());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(codeExercise).setKey(Mockito.<String>any());
        doNothing().when(codeExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setReAttempt(anyInt());
        doNothing().when(codeExercise).setShowAll(anyBoolean());
        doNothing().when(codeExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setTopicId(Mockito.<String>any());
        doNothing().when(codeExercise).setType(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeDetailResponse actualCodeExerciseDetail = codeExerciseImpl.getCodeExerciseDetail("42");

        // Assert
        verify(codeExercise).getAllowedLanguageIds();
        verify(codeExercise).getDescription();
        verify(codeExercise, atLeast(1)).getTestCases();
        verify(codeExercise).setAllowedLanguageIds(isA(List.class));
        verify(codeExercise).setDescription(eq("The characteristics of someone or something"));
        verify(codeExercise).setMemoryLimit(isA(Integer.class));
        verify(codeExercise).setPartial(eq(true));
        verify(codeExercise).setPoints(isA(Double.class));
        verify(codeExercise).setShortCircuit(isA(Boolean.class));
        verify(codeExercise).setTemplate(eq("Template"));
        verify(codeExercise, atLeast(1)).setTestCases(isA(List.class));
        verify(codeExercise).setTimeLimit(isA(Double.class));
        verify(codeExercise, atLeast(1)).getDurationTime();
        verify(codeExercise, atLeast(1)).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getReAttempt();
        verify(codeExercise, atLeast(1)).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getType();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseDescription(eq("Exercise Description"));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise).setPublicGroupIds(isA(List.class));
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(codeExerciseRepository).findById(eq("42"));
        assertEquals("42", actualCodeExerciseDetail.getExerciseId());
        assertEquals("42", actualCodeExerciseDetail.getTopicId());
        assertEquals("Exercise Name", actualCodeExerciseDetail.getExerciseName());
        assertEquals("The characteristics of someone or something", actualCodeExerciseDetail.getDescription());
        assertEquals("Type", actualCodeExerciseDetail.getType());
        assertEquals(1, actualCodeExerciseDetail.getDurationTime());
        assertEquals(1, actualCodeExerciseDetail.getReAttempt());
        assertEquals(1, actualCodeExerciseDetail.getLanguageTemplate().size());
        assertTrue(actualCodeExerciseDetail.getTestCases().isEmpty());
        assertSame(fromResult, actualCodeExerciseDetail.getEndTime());
        assertSame(fromResult2, actualCodeExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link CodeExerciseImpl#getCodeExerciseDetail(String)}
     */
    @Test
    void testGetCodeExerciseDetail3() {
        // Arrange
        TestCase testCase = new TestCase();
        testCase.setExerciseId("42");
        testCase.setInput("Input");
        testCase.setOutput("Output");
        testCase.setPoints(1);
        testCase.setTestcaseId("42");

        ArrayList<TestCase> testCaseList = new ArrayList<>();
        testCaseList.add(testCase);
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenReturn(1);
        when(codeExercise.getReAttempt()).thenReturn(1);
        when(codeExercise.getDescription()).thenReturn("The characteristics of someone or something");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getType()).thenReturn("Type");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(codeExercise.getEndTime()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(codeExercise.getStartTime()).thenReturn(fromResult2);
        when(codeExercise.getAllowedLanguageIds()).thenReturn(new ArrayList<>());
        when(codeExercise.getTestCases()).thenReturn(testCaseList);
        doNothing().when(codeExercise).setAllowedLanguageIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setMemoryLimit(Mockito.<Integer>any());
        doNothing().when(codeExercise).setPartial(anyBoolean());
        doNothing().when(codeExercise).setPoints(Mockito.<Double>any());
        doNothing().when(codeExercise).setShortCircuit(Mockito.<Boolean>any());
        doNothing().when(codeExercise).setTemplate(Mockito.<String>any());
        doNothing().when(codeExercise).setTestCases(Mockito.<List<TestCase>>any());
        doNothing().when(codeExercise).setTimeLimit(Mockito.<Double>any());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(codeExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(codeExercise).setKey(Mockito.<String>any());
        doNothing().when(codeExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(codeExercise).setReAttempt(anyInt());
        doNothing().when(codeExercise).setShowAll(anyBoolean());
        doNothing().when(codeExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(codeExercise).setTopicId(Mockito.<String>any());
        doNothing().when(codeExercise).setType(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        CodeDetailResponse actualCodeExerciseDetail = codeExerciseImpl.getCodeExerciseDetail("42");

        // Assert
        verify(codeExercise).getAllowedLanguageIds();
        verify(codeExercise).getDescription();
        verify(codeExercise, atLeast(1)).getTestCases();
        verify(codeExercise).setAllowedLanguageIds(isA(List.class));
        verify(codeExercise).setDescription(eq("The characteristics of someone or something"));
        verify(codeExercise).setMemoryLimit(isA(Integer.class));
        verify(codeExercise).setPartial(eq(true));
        verify(codeExercise).setPoints(isA(Double.class));
        verify(codeExercise).setShortCircuit(isA(Boolean.class));
        verify(codeExercise).setTemplate(eq("Template"));
        verify(codeExercise, atLeast(1)).setTestCases(isA(List.class));
        verify(codeExercise).setTimeLimit(isA(Double.class));
        verify(codeExercise, atLeast(1)).getDurationTime();
        verify(codeExercise, atLeast(1)).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getReAttempt();
        verify(codeExercise, atLeast(1)).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getType();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseDescription(eq("Exercise Description"));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise).setPublicGroupIds(isA(List.class));
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(codeExerciseRepository).findById(eq("42"));
        assertEquals("42", actualCodeExerciseDetail.getExerciseId());
        assertEquals("42", actualCodeExerciseDetail.getTopicId());
        assertEquals("Exercise Name", actualCodeExerciseDetail.getExerciseName());
        assertEquals("The characteristics of someone or something", actualCodeExerciseDetail.getDescription());
        assertEquals("Type", actualCodeExerciseDetail.getType());
        assertEquals(1, actualCodeExerciseDetail.getDurationTime());
        assertEquals(1, actualCodeExerciseDetail.getReAttempt());
        assertEquals(1, actualCodeExerciseDetail.getTestCases().size());
        assertTrue(actualCodeExerciseDetail.getLanguageTemplate().isEmpty());
        assertSame(fromResult, actualCodeExerciseDetail.getEndTime());
        assertSame(fromResult2, actualCodeExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link CodeExerciseImpl#createCodeExercise(CodeExercise)}
     */
    @Test
    void testCreateCodeExercise() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        when(codeExerciseRepository.save(Mockito.<CodeExercise>any())).thenReturn(codeExercise);

        CodeExercise codeExercise2 = new CodeExercise();
        codeExercise2.setAllowedLanguageIds(new ArrayList<>());
        codeExercise2.setCreatedDate("2020-03-01");
        codeExercise2.setDescription("The characteristics of someone or something");
        codeExercise2.setDurationTime(1);
        codeExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setExerciseDescription("Exercise Description");
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
        codeExercise2.setTemplate("Template");
        codeExercise2.setTestCases(new ArrayList<>());
        codeExercise2.setTimeLimit(10.0d);
        codeExercise2.setTopicId("42");
        codeExercise2.setType("Type");
        codeExercise2.setUpdatedDate("2020-03-01");

        // Act
        CodeExercise actualCreateCodeExerciseResult = codeExerciseImpl.createCodeExercise(codeExercise2);

        // Assert
        verify(codeExerciseRepository).save(isA(CodeExercise.class));
        assertSame(codeExercise, actualCreateCodeExerciseResult);
    }

    /**
     * Method under test: {@link CodeExerciseImpl#createCodeExercise(CodeExercise)}
     */
    @Test
    void testCreateCodeExercise2() {
        // Arrange
        when(codeExerciseRepository.save(Mockito.<CodeExercise>any())).thenThrow(new RuntimeException("foo"));

        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.createCodeExercise(codeExercise));
        verify(codeExerciseRepository).save(isA(CodeExercise.class));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#deleteCodeExercise(String)}
     */
    @Test
    void testDeleteCodeExercise() {
        // Arrange
        doNothing().when(codeExerciseRepository).deleteById(Mockito.<String>any());

        // Act
        codeExerciseImpl.deleteCodeExercise("42");

        // Assert that nothing has changed
        verify(codeExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#deleteCodeExercise(String)}
     */
    @Test
    void testDeleteCodeExercise2() {
        // Arrange
        doThrow(new RuntimeException("foo")).when(codeExerciseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> codeExerciseImpl.deleteCodeExercise("42"));
        verify(codeExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test:
     * {@link CodeExerciseImpl#updateCodeExercise(String, UpdateCodeExerciseRequest)}
     */
    @Test
    void testUpdateCodeExercise() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);

        CodeExercise codeExercise2 = new CodeExercise();
        codeExercise2.setAllowedLanguageIds(new ArrayList<>());
        codeExercise2.setCreatedDate("2020-03-01");
        codeExercise2.setDescription("The characteristics of someone or something");
        codeExercise2.setDurationTime(1);
        codeExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setExerciseDescription("Exercise Description");
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
        codeExercise2.setTemplate("Template");
        codeExercise2.setTestCases(new ArrayList<>());
        codeExercise2.setTimeLimit(10.0d);
        codeExercise2.setTopicId("42");
        codeExercise2.setType("Type");
        codeExercise2.setUpdatedDate("2020-03-01");
        when(codeExerciseRepository.save(Mockito.<CodeExercise>any())).thenReturn(codeExercise2);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseDescription("Exercise Description");
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenReturn(exercise);

        // Act
        CodeExercise actualUpdateCodeExerciseResult = codeExerciseImpl.updateCodeExercise("42",
                new UpdateCodeExerciseRequest());

        // Assert
        verify(codeExerciseRepository).findById(eq("42"));
        verify(codeExerciseRepository).save(isA(CodeExercise.class));
        verify(exerciseRepository).save(isA(Exercise.class));
        assertSame(codeExercise2, actualUpdateCodeExerciseResult);
    }

    /**
     * Method under test:
     * {@link CodeExerciseImpl#updateCodeExercise(String, UpdateCodeExerciseRequest)}
     */
    @Test
    void testUpdateCodeExercise2() {
        // Arrange
        CodeExercise codeExercise = new CodeExercise();
        codeExercise.setAllowedLanguageIds(new ArrayList<>());
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDescription("The characteristics of someone or something");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseDescription("Exercise Description");
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
        codeExercise.setTemplate("Template");
        codeExercise.setTestCases(new ArrayList<>());
        codeExercise.setTimeLimit(10.0d);
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<CodeExercise> ofResult = Optional.of(codeExercise);

        CodeExercise codeExercise2 = new CodeExercise();
        codeExercise2.setAllowedLanguageIds(new ArrayList<>());
        codeExercise2.setCreatedDate("2020-03-01");
        codeExercise2.setDescription("The characteristics of someone or something");
        codeExercise2.setDurationTime(1);
        codeExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise2.setExerciseDescription("Exercise Description");
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
        codeExercise2.setTemplate("Template");
        codeExercise2.setTestCases(new ArrayList<>());
        codeExercise2.setTimeLimit(10.0d);
        codeExercise2.setTopicId("42");
        codeExercise2.setType("Type");
        codeExercise2.setUpdatedDate("2020-03-01");
        when(codeExerciseRepository.save(Mockito.<CodeExercise>any())).thenReturn(codeExercise2);
        when(codeExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenThrow(new RuntimeException("code"));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> codeExerciseImpl.updateCodeExercise("42", new UpdateCodeExerciseRequest()));
        verify(codeExerciseRepository).findById(eq("42"));
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test: {@link CodeExerciseImpl#createProblemFolder(List, String)}
     */
    @Test
    void testCreateProblemFolder() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            // Arrange
            mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)))
                    .thenThrow(new RuntimeException("archive: iozip.zip\ntest_cases:"));
            mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
                    .thenReturn(new ByteArrayOutputStream(1));
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

            // Act and Assert
            assertThrows(RuntimeException.class, () -> codeExerciseImpl.createProblemFolder(new ArrayList<>(), "42"));
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)));
            mockFiles.verify(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class)));
        }
    }

    /**
     * Method under test: {@link CodeExerciseImpl#createProblemFolder(List, String)}
     */
    @Test
    void testCreateProblemFolder2() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            // Arrange
            ArrayList<Path> pathList = new ArrayList<>();
            pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            Stream<Path> streamResult = pathList.stream();
            mockFiles.when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(false);
            mockFiles.when(() -> Files.copy(Mockito.<Path>any(), Mockito.<OutputStream>any())).thenReturn(1L);
            mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
            mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
                    .thenReturn(mock(TestableByteArrayOutputStream.class));
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

            TestCase testCase = new TestCase();
            testCase.setExerciseId("42");
            testCase.setInput("archive: iozip.zip\ntest_cases:");
            testCase.setOutput("archive: iozip.zip\ntest_cases:");
            testCase.setPoints(1);
            testCase.setTestcaseId("42");

            ArrayList<TestCase> testCaseList = new ArrayList<>();
            testCaseList.add(testCase);

            // Act and Assert
            assertThrows(RuntimeException.class, () -> codeExerciseImpl.createProblemFolder(testCaseList, "42"));
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)));
        }
    }

    /**
     * Method under test: {@link CodeExerciseImpl#createProblemFolder(List, String)}
     */
    @Test
    void testCreateProblemFolder3() throws IOException {
        try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
            // Arrange
            ArrayList<Path> pathList = new ArrayList<>();
            pathList.add(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            Stream<Path> streamResult = pathList.stream();
            mockFiles.when(() -> Files.isDirectory(Mockito.<Path>any(), isA(LinkOption[].class))).thenReturn(false);
            mockFiles.when(() -> Files.copy(Mockito.<Path>any(), Mockito.<OutputStream>any())).thenReturn(1L);
            mockFiles.when(() -> Files.walk(Mockito.<Path>any(), isA(FileVisitOption[].class))).thenReturn(streamResult);
            mockFiles.when(() -> Files.newOutputStream(Mockito.<Path>any(), isA(OpenOption[].class)))
                    .thenReturn(mock(TestableByteArrayOutputStream.class));
            mockFiles.when(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));
            mockFiles.when(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)))
                    .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

            TestCase testCase = new TestCase();
            testCase.setExerciseId("42");
            testCase.setInput("archive: iozip.zip\ntest_cases:");
            testCase.setOutput("archive: iozip.zip\ntest_cases:");
            testCase.setPoints(1);
            testCase.setTestcaseId("42");

            TestCase testCase2 = new TestCase();
            testCase2.setExerciseId("archive: iozip.zip\ntest_cases:");
            testCase2.setInput("iozip");
            testCase2.setOutput("iozip");
            testCase2.setPoints(0);
            testCase2.setTestcaseId("archive: iozip.zip\ntest_cases:");

            ArrayList<TestCase> testCaseList = new ArrayList<>();
            testCaseList.add(testCase2);
            testCaseList.add(testCase);

            // Act and Assert
            assertThrows(RuntimeException.class, () -> codeExerciseImpl.createProblemFolder(testCaseList, "42"));
            mockFiles.verify(() -> Files.createDirectories(Mockito.<Path>any(), isA(FileAttribute[].class)));
            mockFiles.verify(() -> Files.createTempDirectory(Mockito.<String>any(), isA(FileAttribute[].class)));
        }
    }
}