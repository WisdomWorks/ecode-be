package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.QuizChoiceRepository;
import com.example.codeE.repository.QuizExerciseRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import com.example.codeE.request.exercise.quiz.QuizDetailResponse;
import com.example.codeE.request.exercise.quiz.QuizQuestionResponse;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {QuizExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class QuizExerciseImplDiffblueTest {
    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private QuizChoiceRepository quizChoiceRepository;

    @Autowired
    private QuizExerciseImpl quizExerciseImpl;

    @MockBean
    private QuizExerciseRepository quizExerciseRepository;

    @MockBean
    private QuizQuestionRepository quizQuestionRepository;

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenReturn(quizExercise);

        QuizExercise quizExercise2 = new QuizExercise();
        quizExercise2.setCreatedDate("2020-03-01");
        quizExercise2.setDurationTime(1);
        quizExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setExerciseDescription("Exercise Description");
        quizExercise2.setExerciseId("42");
        quizExercise2.setExerciseName("Exercise Name");
        quizExercise2.setKey("Key");
        quizExercise2.setPublicGroupIds(new ArrayList<>());
        quizExercise2.setQuestions(new ArrayList<>());
        quizExercise2.setReAttempt(1);
        quizExercise2.setShowAll(true);
        quizExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setTopicId("42");
        quizExercise2.setType("Type");
        quizExercise2.setUpdatedDate("2020-03-01");

        // Act
        QuizExercise actualCreateQuizExerciseResult = quizExerciseImpl.createQuizExercise(quizExercise2);

        // Assert
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        assertSame(quizExercise, actualCreateQuizExerciseResult);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise2() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenReturn(quizExercise);
        QuizExercise quizExercise2 = mock(QuizExercise.class);
        when(quizExercise2.getReAttempt()).thenReturn(1);
        when(quizExercise2.getQuestions()).thenReturn(new ArrayList<>());
        doNothing().when(quizExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setDurationTime(anyInt());
        doNothing().when(quizExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise2).setKey(Mockito.<String>any());
        doNothing().when(quizExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise2).setReAttempt(anyInt());
        doNothing().when(quizExercise2).setShowAll(anyBoolean());
        doNothing().when(quizExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise2).setType(Mockito.<String>any());
        doNothing().when(quizExercise2).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise2.setCreatedDate("2020-03-01");
        quizExercise2.setDurationTime(1);
        quizExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setExerciseDescription("Exercise Description");
        quizExercise2.setExerciseId("42");
        quizExercise2.setExerciseName("Exercise Name");
        quizExercise2.setKey("Key");
        quizExercise2.setPublicGroupIds(new ArrayList<>());
        quizExercise2.setQuestions(new ArrayList<>());
        quizExercise2.setReAttempt(1);
        quizExercise2.setShowAll(true);
        quizExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setTopicId("42");
        quizExercise2.setType("Type");
        quizExercise2.setUpdatedDate("2020-03-01");

        // Act
        QuizExercise actualCreateQuizExerciseResult = quizExerciseImpl.createQuizExercise(quizExercise2);

        // Assert
        verify(quizExercise2).getReAttempt();
        verify(quizExercise2).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise2).setDurationTime(eq(1));
        verify(quizExercise2).setEndTime(isA(Date.class));
        verify(quizExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise2).setExerciseId(eq("42"));
        verify(quizExercise2).setExerciseName(eq("Exercise Name"));
        verify(quizExercise2).setKey(eq("Key"));
        verify(quizExercise2).setPublicGroupIds(isA(List.class));
        verify(quizExercise2).setReAttempt(eq(1));
        verify(quizExercise2).setShowAll(eq(true));
        verify(quizExercise2).setStartTime(isA(Date.class));
        verify(quizExercise2).setTopicId(eq("42"));
        verify(quizExercise2).setType(eq("Type"));
        verify(quizExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise2).getQuestions();
        verify(quizExercise2).setQuestions(isA(List.class));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        assertSame(quizExercise, actualCreateQuizExerciseResult);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise3() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenReturn(quizExercise);
        QuizExercise quizExercise2 = mock(QuizExercise.class);
        when(quizExercise2.getReAttempt()).thenReturn(0);
        when(quizExercise2.getQuestions()).thenReturn(new ArrayList<>());
        doNothing().when(quizExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setDurationTime(anyInt());
        doNothing().when(quizExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise2).setKey(Mockito.<String>any());
        doNothing().when(quizExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise2).setReAttempt(anyInt());
        doNothing().when(quizExercise2).setShowAll(anyBoolean());
        doNothing().when(quizExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise2).setType(Mockito.<String>any());
        doNothing().when(quizExercise2).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise2.setCreatedDate("2020-03-01");
        quizExercise2.setDurationTime(1);
        quizExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setExerciseDescription("Exercise Description");
        quizExercise2.setExerciseId("42");
        quizExercise2.setExerciseName("Exercise Name");
        quizExercise2.setKey("Key");
        quizExercise2.setPublicGroupIds(new ArrayList<>());
        quizExercise2.setQuestions(new ArrayList<>());
        quizExercise2.setReAttempt(1);
        quizExercise2.setShowAll(true);
        quizExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setTopicId("42");
        quizExercise2.setType("Type");
        quizExercise2.setUpdatedDate("2020-03-01");

        // Act
        QuizExercise actualCreateQuizExerciseResult = quizExerciseImpl.createQuizExercise(quizExercise2);

        // Assert
        verify(quizExercise2).getReAttempt();
        verify(quizExercise2).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise2).setDurationTime(eq(1));
        verify(quizExercise2).setEndTime(isA(Date.class));
        verify(quizExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise2).setExerciseId(eq("42"));
        verify(quizExercise2).setExerciseName(eq("Exercise Name"));
        verify(quizExercise2).setKey(eq("Key"));
        verify(quizExercise2).setPublicGroupIds(isA(List.class));
        verify(quizExercise2, atLeast(1)).setReAttempt(eq(1));
        verify(quizExercise2).setShowAll(eq(true));
        verify(quizExercise2).setStartTime(isA(Date.class));
        verify(quizExercise2).setTopicId(eq("42"));
        verify(quizExercise2).setType(eq("Type"));
        verify(quizExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise2).getQuestions();
        verify(quizExercise2).setQuestions(isA(List.class));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        assertSame(quizExercise, actualCreateQuizExerciseResult);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise4() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenReturn(quizExercise);

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        when(quizQuestionRepository.save(Mockito.<QuizQuestion>any())).thenReturn(quizQuestion);

        QuizQuestion quizQuestion2 = new QuizQuestion();
        quizQuestion2.setAnswers(new ArrayList<>());
        quizQuestion2.setChoices(new ArrayList<>());
        quizQuestion2.setDescription("The characteristics of someone or something");
        quizQuestion2.setQuestionId("42");
        quizQuestion2.setTitle("Dr");

        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(quizQuestion2);
        QuizExercise quizExercise2 = mock(QuizExercise.class);
        when(quizExercise2.getReAttempt()).thenReturn(1);
        when(quizExercise2.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setDurationTime(anyInt());
        doNothing().when(quizExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise2).setKey(Mockito.<String>any());
        doNothing().when(quizExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise2).setReAttempt(anyInt());
        doNothing().when(quizExercise2).setShowAll(anyBoolean());
        doNothing().when(quizExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise2).setType(Mockito.<String>any());
        doNothing().when(quizExercise2).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise2).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise2.setCreatedDate("2020-03-01");
        quizExercise2.setDurationTime(1);
        quizExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setExerciseDescription("Exercise Description");
        quizExercise2.setExerciseId("42");
        quizExercise2.setExerciseName("Exercise Name");
        quizExercise2.setKey("Key");
        quizExercise2.setPublicGroupIds(new ArrayList<>());
        quizExercise2.setQuestions(new ArrayList<>());
        quizExercise2.setReAttempt(1);
        quizExercise2.setShowAll(true);
        quizExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setTopicId("42");
        quizExercise2.setType("Type");
        quizExercise2.setUpdatedDate("2020-03-01");

        // Act
        QuizExercise actualCreateQuizExerciseResult = quizExerciseImpl.createQuizExercise(quizExercise2);

        // Assert
        verify(quizExercise2).getReAttempt();
        verify(quizExercise2).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise2).setDurationTime(eq(1));
        verify(quizExercise2).setEndTime(isA(Date.class));
        verify(quizExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise2).setExerciseId(eq("42"));
        verify(quizExercise2).setExerciseName(eq("Exercise Name"));
        verify(quizExercise2).setKey(eq("Key"));
        verify(quizExercise2).setPublicGroupIds(isA(List.class));
        verify(quizExercise2).setReAttempt(eq(1));
        verify(quizExercise2).setShowAll(eq(true));
        verify(quizExercise2).setStartTime(isA(Date.class));
        verify(quizExercise2).setTopicId(eq("42"));
        verify(quizExercise2).setType(eq("Type"));
        verify(quizExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise2, atLeast(1)).getQuestions();
        verify(quizExercise2).setQuestions(isA(List.class));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        verify(quizQuestionRepository).save(isA(QuizQuestion.class));
        assertSame(quizExercise, actualCreateQuizExerciseResult);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseById(String)}
     */
    @Test
    void testGetQuizExerciseById() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizExercise actualQuizExerciseById = quizExerciseImpl.getQuizExerciseById("42");

        // Assert
        verify(quizExerciseRepository).findById(eq("42"));
        assertSame(quizExercise, actualQuizExerciseById);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseById(String)}
     */
    @Test
    void testGetQuizExerciseById2() {
        // Arrange
        Optional<QuizExercise> emptyResult = Optional.empty();
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizExerciseImpl.getQuizExerciseById("42"));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseById(String)}
     */
    @Test
    void testGetQuizExerciseById3() {
        // Arrange
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> quizExerciseImpl.getQuizExerciseById("42"));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseDetail(String)}
     */
    @Test
    void testGetQuizExerciseDetail() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        quizExercise.setEndTime(endTime);
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        ArrayList<String> publicGroupIds = new ArrayList<>();
        quizExercise.setPublicGroupIds(publicGroupIds);
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        quizExercise.setStartTime(startTime);
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizDetailResponse actualQuizExerciseDetail = quizExerciseImpl.getQuizExerciseDetail("42");

        // Assert
        verify(quizExerciseRepository).findById(eq("42"));
        assertEquals("42", actualQuizExerciseDetail.getExerciseId());
        assertEquals("42", actualQuizExerciseDetail.getTopicId());
        assertEquals("Exercise Description", actualQuizExerciseDetail.getExerciseDescription());
        assertEquals("Exercise Name", actualQuizExerciseDetail.getExerciseName());
        assertEquals("Type", actualQuizExerciseDetail.getType());
        assertEquals(1, actualQuizExerciseDetail.getDurationTime());
        assertEquals(1, actualQuizExerciseDetail.getReAttempt());
        assertEquals(publicGroupIds, actualQuizExerciseDetail.getQuestions());
        assertSame(endTime, actualQuizExerciseDetail.getEndTime());
        assertSame(startTime, actualQuizExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseDetail(String)}
     */
    @Test
    void testGetQuizExerciseDetail2() {
        // Arrange
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.getDurationTime()).thenReturn(1);
        when(quizExercise.getReAttempt()).thenReturn(1);
        when(quizExercise.getExerciseDescription()).thenReturn("Exercise Description");
        when(quizExercise.getExerciseId()).thenReturn("42");
        when(quizExercise.getExerciseName()).thenReturn("Exercise Name");
        when(quizExercise.getTopicId()).thenReturn("42");
        when(quizExercise.getType()).thenReturn("Type");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(quizExercise.getEndTime()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(quizExercise.getStartTime()).thenReturn(fromResult2);
        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        when(quizExercise.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizDetailResponse actualQuizExerciseDetail = quizExerciseImpl.getQuizExerciseDetail("42");

        // Assert
        verify(quizExercise, atLeast(1)).getDurationTime();
        verify(quizExercise, atLeast(1)).getEndTime();
        verify(quizExercise).getExerciseDescription();
        verify(quizExercise).getExerciseId();
        verify(quizExercise).getExerciseName();
        verify(quizExercise).getReAttempt();
        verify(quizExercise, atLeast(1)).getStartTime();
        verify(quizExercise).getTopicId();
        verify(quizExercise).getType();
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizExerciseRepository).findById(eq("42"));
        assertEquals("42", actualQuizExerciseDetail.getExerciseId());
        assertEquals("42", actualQuizExerciseDetail.getTopicId());
        assertEquals("Exercise Description", actualQuizExerciseDetail.getExerciseDescription());
        assertEquals("Exercise Name", actualQuizExerciseDetail.getExerciseName());
        assertEquals("Type", actualQuizExerciseDetail.getType());
        assertEquals(1, actualQuizExerciseDetail.getDurationTime());
        assertEquals(1, actualQuizExerciseDetail.getReAttempt());
        assertEquals(quizQuestionList, actualQuizExerciseDetail.getQuestions());
        assertSame(fromResult, actualQuizExerciseDetail.getEndTime());
        assertSame(fromResult2, actualQuizExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseDetail(String)}
     */
    @Test
    void testGetQuizExerciseDetail3() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        ArrayList<QuizChoice> answers = new ArrayList<>();
        quizQuestion.setAnswers(answers);
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");

        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(quizQuestion);
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.getDurationTime()).thenReturn(1);
        when(quizExercise.getReAttempt()).thenReturn(1);
        when(quizExercise.getExerciseDescription()).thenReturn("Exercise Description");
        when(quizExercise.getExerciseId()).thenReturn("42");
        when(quizExercise.getExerciseName()).thenReturn("Exercise Name");
        when(quizExercise.getTopicId()).thenReturn("42");
        when(quizExercise.getType()).thenReturn("Type");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(quizExercise.getEndTime()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(quizExercise.getStartTime()).thenReturn(fromResult2);
        when(quizExercise.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizDetailResponse actualQuizExerciseDetail = quizExerciseImpl.getQuizExerciseDetail("42");

        // Assert
        verify(quizExercise, atLeast(1)).getDurationTime();
        verify(quizExercise, atLeast(1)).getEndTime();
        verify(quizExercise).getExerciseDescription();
        verify(quizExercise).getExerciseId();
        verify(quizExercise).getExerciseName();
        verify(quizExercise).getReAttempt();
        verify(quizExercise, atLeast(1)).getStartTime();
        verify(quizExercise).getTopicId();
        verify(quizExercise).getType();
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizExerciseRepository).findById(eq("42"));
        assertEquals("42", actualQuizExerciseDetail.getExerciseId());
        assertEquals("42", actualQuizExerciseDetail.getTopicId());
        List<QuizQuestionResponse> questions = actualQuizExerciseDetail.getQuestions();
        assertEquals(1, questions.size());
        QuizQuestionResponse getResult = questions.get(0);
        assertEquals("42", getResult.getQuestionId());
        assertEquals("Dr", getResult.getTitle());
        assertEquals("Exercise Description", actualQuizExerciseDetail.getExerciseDescription());
        assertEquals("Exercise Name", actualQuizExerciseDetail.getExerciseName());
        assertEquals("The characteristics of someone or something", getResult.getDescription());
        assertEquals("Type", actualQuizExerciseDetail.getType());
        assertEquals(1, actualQuizExerciseDetail.getDurationTime());
        assertEquals(1, actualQuizExerciseDetail.getReAttempt());
        assertFalse(getResult.getIsMultipleChoice());
        assertEquals(answers, getResult.getChoices());
        assertSame(fromResult, actualQuizExerciseDetail.getEndTime());
        assertSame(fromResult2, actualQuizExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link QuizExerciseImpl#getQuizExerciseDetail(String)}
     */
    @Test
    void testGetQuizExerciseDetail4() {
        // Arrange
        Optional<QuizExercise> emptyResult = Optional.empty();
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizExerciseImpl.getQuizExerciseDetail("42"));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#getQuizQuestionByQuestionId(String)}
     */
    @Test
    void testGetQuizQuestionByQuestionId() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");
        Optional<QuizQuestion> ofResult = Optional.of(quizQuestion);
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        QuizQuestion actualQuizQuestionByQuestionId = quizExerciseImpl.getQuizQuestionByQuestionId("42");

        // Assert
        verify(quizQuestionRepository).findById(eq("42"));
        assertSame(quizQuestion, actualQuizQuestionByQuestionId);
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#getQuizQuestionByQuestionId(String)}
     */
    @Test
    void testGetQuizQuestionByQuestionId2() {
        // Arrange
        when(quizQuestionRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> quizExerciseImpl.getQuizQuestionByQuestionId("42"));
        verify(quizQuestionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise() {
        // Arrange
        QuizExercise quizExercise = new QuizExercise();
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.updateQuizExercise("42", new UpdateQuizExerciseRequest()));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise2() {
        // Arrange
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.getQuestions()).thenReturn(new ArrayList<>());
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.updateQuizExercise("42", new UpdateQuizExerciseRequest()));
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise3() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");

        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(quizQuestion);
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(quizQuestionRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.updateQuizExercise("42", new UpdateQuizExerciseRequest()));
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizQuestionRepository).deleteById(eq("42"));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise4() {
        // Arrange
        Optional<QuizExercise> emptyResult = Optional.empty();
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.updateQuizExercise("42", new UpdateQuizExerciseRequest()));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise5() {
        // Arrange
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

        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");

        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(quizQuestion);
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.isShowAll()).thenReturn(true);
        when(quizExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(quizExercise.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);

        QuizExercise quizExercise2 = new QuizExercise();
        quizExercise2.setCreatedDate("2020-03-01");
        quizExercise2.setDurationTime(1);
        quizExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setExerciseDescription("Exercise Description");
        quizExercise2.setExerciseId("42");
        quizExercise2.setExerciseName("Exercise Name");
        quizExercise2.setKey("Key");
        quizExercise2.setPublicGroupIds(new ArrayList<>());
        quizExercise2.setQuestions(new ArrayList<>());
        quizExercise2.setReAttempt(1);
        quizExercise2.setShowAll(true);
        quizExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise2.setTopicId("42");
        quizExercise2.setType("Type");
        quizExercise2.setUpdatedDate("2020-03-01");
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenReturn(quizExercise2);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(quizQuestionRepository).deleteById(Mockito.<String>any());
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act
        QuizExercise actualUpdateQuizExerciseResult = quizExerciseImpl.updateQuizExercise("42",
                new UpdateQuizExerciseRequest("42", "42", "Something wrong when update quiz exercise",
                        "Something wrong when update quiz exercise", startTime, endTime, 1, 1,
                        "Something wrong when update quiz exercise", new ArrayList<>()));

        // Assert
        verify(quizExercise).getPublicGroupIds();
        verify(quizExercise).isShowAll();
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizQuestionRepository).deleteById(eq("42"));
        verify(quizExerciseRepository).findById(eq("42"));
        verify(exerciseRepository).save(isA(Exercise.class));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        assertSame(quizExercise2, actualUpdateQuizExerciseResult);
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#updateQuizExercise(String, UpdateQuizExerciseRequest)}
     */
    @Test
    void testUpdateQuizExercise6() {
        // Arrange
        QuizQuestion quizQuestion = new QuizQuestion();
        quizQuestion.setAnswers(new ArrayList<>());
        quizQuestion.setChoices(new ArrayList<>());
        quizQuestion.setDescription("The characteristics of someone or something");
        quizQuestion.setQuestionId("42");
        quizQuestion.setTitle("Dr");

        ArrayList<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(quizQuestion);
        QuizExercise quizExercise = mock(QuizExercise.class);
        when(quizExercise.isShowAll()).thenThrow(new NoSuchElementException("quiz"));
        when(quizExercise.getQuestions()).thenReturn(quizQuestionList);
        doNothing().when(quizExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setDurationTime(anyInt());
        doNothing().when(quizExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(quizExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(quizExercise).setKey(Mockito.<String>any());
        doNothing().when(quizExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(quizExercise).setReAttempt(anyInt());
        doNothing().when(quizExercise).setShowAll(anyBoolean());
        doNothing().when(quizExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(quizExercise).setTopicId(Mockito.<String>any());
        doNothing().when(quizExercise).setType(Mockito.<String>any());
        doNothing().when(quizExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(quizExercise).setQuestions(Mockito.<List<QuizQuestion>>any());
        quizExercise.setCreatedDate("2020-03-01");
        quizExercise.setDurationTime(1);
        quizExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setExerciseDescription("Exercise Description");
        quizExercise.setExerciseId("42");
        quizExercise.setExerciseName("Exercise Name");
        quizExercise.setKey("Key");
        quizExercise.setPublicGroupIds(new ArrayList<>());
        quizExercise.setQuestions(new ArrayList<>());
        quizExercise.setReAttempt(1);
        quizExercise.setShowAll(true);
        quizExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        quizExercise.setTopicId("42");
        quizExercise.setType("Type");
        quizExercise.setUpdatedDate("2020-03-01");
        Optional<QuizExercise> ofResult = Optional.of(quizExercise);
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        doNothing().when(quizQuestionRepository).deleteById(Mockito.<String>any());
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.updateQuizExercise("42",
                        new UpdateQuizExerciseRequest("42", "42", "Something wrong when update quiz exercise",
                                "Something wrong when update quiz exercise", startTime, endTime, 1, 1,
                                "Something wrong when update quiz exercise", new ArrayList<>())));
        verify(quizExercise).isShowAll();
        verify(quizExercise).setCreatedDate(eq("2020-03-01"));
        verify(quizExercise).setDurationTime(eq(1));
        verify(quizExercise).setEndTime(isA(Date.class));
        verify(quizExercise).setExerciseDescription(eq("Exercise Description"));
        verify(quizExercise).setExerciseId(eq("42"));
        verify(quizExercise).setExerciseName(eq("Exercise Name"));
        verify(quizExercise).setKey(eq("Key"));
        verify(quizExercise).setPublicGroupIds(isA(List.class));
        verify(quizExercise).setReAttempt(eq(1));
        verify(quizExercise).setShowAll(eq(true));
        verify(quizExercise).setStartTime(isA(Date.class));
        verify(quizExercise).setTopicId(eq("42"));
        verify(quizExercise).setType(eq("Type"));
        verify(quizExercise).setUpdatedDate(eq("2020-03-01"));
        verify(quizExercise).getQuestions();
        verify(quizExercise).setQuestions(isA(List.class));
        verify(quizQuestionRepository).deleteById(eq("42"));
        verify(quizExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link QuizExerciseImpl#deleteQuizExerciseById(String)}
     */
    @Test
    void testDeleteQuizExerciseById() {
        // Arrange
        doNothing().when(quizExerciseRepository).deleteById(Mockito.<String>any());

        // Act
        quizExerciseImpl.deleteQuizExerciseById("42");

        // Assert that nothing has changed
        verify(quizExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test: {@link QuizExerciseImpl#deleteQuizExerciseById(String)}
     */
    @Test
    void testDeleteQuizExerciseById2() {
        // Arrange
        doThrow(new RuntimeException("foo")).when(quizExerciseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> quizExerciseImpl.deleteQuizExerciseById("42"));
        verify(quizExerciseRepository).deleteById(eq("42"));
    }
}
