package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.QuizChoiceRepository;
import com.example.codeE.repository.QuizExerciseRepository;
import com.example.codeE.repository.QuizQuestionRepository;
import com.example.codeE.repository.SessionExerciseRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import jakarta.servlet.http.HttpServletRequest;

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
import org.springframework.mock.web.MockHttpServletRequest;
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

    @MockBean
    private SessionExerciseRepository sessionExerciseRepository;

    @MockBean
    private TopicRepository topicRepository;

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

        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        verify(topicRepository).findById(eq("42"));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
        assertSame(quizExercise, actualCreateQuizExerciseResult);
    }

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise2() {
        // Arrange
        when(quizExerciseRepository.save(Mockito.<QuizExercise>any())).thenThrow(new RuntimeException("foo"));

        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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

        // Act and Assert
        assertThrows(RuntimeException.class, () -> quizExerciseImpl.createQuizExercise(quizExercise));
        verify(topicRepository).findById(eq("42"));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
    }

    /**
     * Method under test: {@link QuizExerciseImpl#createQuizExercise(QuizExercise)}
     */
    @Test
    void testCreateQuizExercise3() {
        // Arrange
        Optional<Topic> emptyResult = Optional.empty();
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> quizExerciseImpl.createQuizExercise(quizExercise));
        verify(topicRepository).findById(eq("42"));
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

        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        QuizExercise quizExercise2 = mock(QuizExercise.class);
        when(quizExercise2.getReAttempt()).thenReturn(1);
        when(quizExercise2.getTopicId()).thenReturn("42");
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
        verify(quizExercise2).getTopicId();
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
        verify(topicRepository).findById(eq("42"));
        verify(quizExerciseRepository).save(isA(QuizExercise.class));
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
     * Method under test:
     * {@link QuizExerciseImpl#getQuizExerciseDetail(String, HttpServletRequest)}
     */
    @Test
    void testGetQuizExerciseDetail() {
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
        when(sessionExerciseRepository.findAll()).thenThrow(new RuntimeException("yyyy-MM-dd'T'HH:mm:ss'Z'"));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> quizExerciseImpl.getQuizExerciseDetail("42", new MockHttpServletRequest()));
        verify(quizExerciseRepository).findById(eq("42"));
        verify(sessionExerciseRepository).findAll();
    }

    /**
     * Method under test:
     * {@link QuizExerciseImpl#getQuizExerciseDetail(String, HttpServletRequest)}
     */
    @Test
    void testGetQuizExerciseDetail2() {
        // Arrange
        Optional<QuizExercise> emptyResult = Optional.empty();
        when(quizExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> quizExerciseImpl.getQuizExerciseDetail("42", new MockHttpServletRequest()));
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
