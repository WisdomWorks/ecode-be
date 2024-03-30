package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.ExerciseStudentResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;

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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ExerciseImplDiffblueTest {
    @MockBean
    private EssaySubmissionService essaySubmissionService;

    @Autowired
    private ExerciseImpl exerciseImpl;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    @MockBean
    private QuizSubmissionService quizSubmissionService;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link ExerciseImpl#saveQuizExercise(QuizExercise)}
     */
    @Test
    void testSaveQuizExercise() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        QuizExercise exercise2 = new QuizExercise();
        exercise2.setCreatedDate("2020-03-01");
        exercise2.setDurationTime(1);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("42");
        exercise2.setExerciseName("Exercise Name");
        exercise2.setKey("Key");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setQuestions(new ArrayList<>());
        exercise2.setReAttempt(1);
        exercise2.setShowAll(true);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("42");
        exercise2.setType("Type");
        exercise2.setUpdatedDate("2020-03-01");

        // Act
        Exercise actualSaveQuizExerciseResult = exerciseImpl.saveQuizExercise(exercise2);

        // Assert
        verify(exerciseRepository).save(isA(Exercise.class));
        assertSame(exercise, actualSaveQuizExerciseResult);
    }

    /**
     * Method under test: {@link ExerciseImpl#saveQuizExercise(QuizExercise)}
     */
    @Test
    void testSaveQuizExercise2() {
        // Arrange
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenThrow(new IllegalArgumentException("foo"));

        QuizExercise exercise = new QuizExercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setQuestions(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> exerciseImpl.saveQuizExercise(exercise));
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test: {@link ExerciseImpl#saveEsayExercise(EssayExercise)}
     */
    @Test
    void testSaveEsayExercise() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        EssayExercise exercise2 = new EssayExercise();
        exercise2.setCreatedDate("2020-03-01");
        exercise2.setDurationTime(1);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("42");
        exercise2.setExerciseName("Exercise Name");
        exercise2.setKey("Key");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setQuestion("Question");
        exercise2.setReAttempt(1);
        exercise2.setShowAll(true);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("42");
        exercise2.setType("Type");
        exercise2.setUpdatedDate("2020-03-01");

        // Act
        Exercise actualSaveEsayExerciseResult = exerciseImpl.saveEsayExercise(exercise2);

        // Assert
        verify(exerciseRepository).save(isA(Exercise.class));
        assertSame(exercise, actualSaveEsayExerciseResult);
    }

    /**
     * Method under test: {@link ExerciseImpl#saveEsayExercise(EssayExercise)}
     */
    @Test
    void testSaveEsayExercise2() {
        // Arrange
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenThrow(new IllegalArgumentException("foo"));

        EssayExercise exercise = new EssayExercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setQuestion("Question");
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> exerciseImpl.saveEsayExercise(exercise));
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test: {@link ExerciseImpl#getPreviewExercise(String)}
     */
    @Test
    void testGetPreviewExercise() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        ExerciseStudentResponse actualPreviewExercise = exerciseImpl.getPreviewExercise("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        assertEquals("2020-03-01", actualPreviewExercise.getCreatedDate());
        assertEquals("2020-03-01", actualPreviewExercise.getUpdatedDate());
        assertEquals("42", actualPreviewExercise.getExerciseId());
        assertEquals("42", actualPreviewExercise.getTopicId());
        assertEquals("Exercise Name", actualPreviewExercise.getExerciseName());
        assertEquals("Type", actualPreviewExercise.getType());
        assertEquals(1, actualPreviewExercise.getDurationTime());
    }

    /**
     * Method under test: {@link ExerciseImpl#getPreviewExercise(String)}
     */
    @Test
    void testGetPreviewExercise2() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        new NoSuchElementException("foo");
        new NoSuchElementException("foo");

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> exerciseImpl.getPreviewExercise("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByCourseId(String)}
     */
    @Test
    void testGetExercisesByCourseId() {
        // Arrange
        when(topicRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ExerciseResponse> actualExercisesByCourseId = exerciseImpl.getExercisesByCourseId("42");

        // Assert
        verify(topicRepository).findAll();
        assertTrue(actualExercisesByCourseId.isEmpty());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByCourseId(String)}
     */
    @Test
    void testGetExercisesByCourseId2() {
        // Arrange
        when(exerciseRepository.findAll()).thenReturn(new ArrayList<>());

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.findAll()).thenReturn(topicList);

        // Act
        List<ExerciseResponse> actualExercisesByCourseId = exerciseImpl.getExercisesByCourseId("42");

        // Assert
        verify(exerciseRepository).findAll();
        verify(topicRepository).findAll();
        assertTrue(actualExercisesByCourseId.isEmpty());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByCourseId(String)}
     */
    @Test
    void testGetExercisesByCourseId3() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("42");
        exercise.setKey("42");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("42");
        exercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.findAll()).thenReturn(topicList);

        // Act
        List<ExerciseResponse> actualExercisesByCourseId = exerciseImpl.getExercisesByCourseId("42");

        // Assert
        verify(exerciseRepository).findAll();
        verify(topicRepository).findAll();
        assertEquals(1, actualExercisesByCourseId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByCourseId(String)}
     */
    @Test
    void testGetExercisesByCourseId4() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("42");
        exercise.setKey("42");
        exercise.setPublicGroupIds(new ArrayList<>());
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("42");
        exercise.setUpdatedDate("2020-03-01");

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020/03/01");
        exercise2.setDurationTime(3);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("yyyy-MM-dd HH:mm:ss");
        exercise2.setExerciseName("yyyy-MM-dd HH:mm:ss");
        exercise2.setKey("yyyy-MM-dd HH:mm:ss");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(3);
        exercise2.setShowAll(false);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("yyyy-MM-dd HH:mm:ss");
        exercise2.setType("yyyy-MM-dd HH:mm:ss");
        exercise2.setUpdatedDate("2020/03/01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise2);
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

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

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic);
        when(topicRepository.findAll()).thenReturn(topicList);

        // Act
        List<ExerciseResponse> actualExercisesByCourseId = exerciseImpl.getExercisesByCourseId("42");

        // Assert
        verify(exerciseRepository).findAll();
        verify(topicRepository).findAll();
        assertEquals(1, actualExercisesByCourseId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByCourseId(String)}
     */
    @Test
    void testGetExercisesByCourseId5() {
        // Arrange
        when(exerciseRepository.findAll()).thenReturn(new ArrayList<>());

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

        Course course2 = new Course();
        course2.setCourseId("Course Id");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("42");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic2 = new Topic();
        topic2.setCourse(course2);
        topic2.setCourseId("Course Id");
        topic2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setDescription("42");
        topic2.setMaterials(new ArrayList<>());
        topic2.setShowAll(false);
        topic2.setTopicId("Topic Id");
        topic2.setTopicName("Topic Name");
        topic2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic2.setViewPermissionTopics(new ArrayList<>());

        ArrayList<Topic> topicList = new ArrayList<>();
        topicList.add(topic2);
        topicList.add(topic);
        when(topicRepository.findAll()).thenReturn(topicList);

        // Act
        List<ExerciseResponse> actualExercisesByCourseId = exerciseImpl.getExercisesByCourseId("42");

        // Assert
        verify(exerciseRepository).findAll();
        verify(topicRepository).findAll();
        assertTrue(actualExercisesByCourseId.isEmpty());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExerciseById(String)}
     */
    @Test
    void testGetExerciseById() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        Exercise actualExerciseById = exerciseImpl.getExerciseById("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        assertSame(exercise, actualExerciseById);
    }

    /**
     * Method under test: {@link ExerciseImpl#getExerciseById(String)}
     */
    @Test
    void testGetExerciseById2() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> exerciseImpl.getExerciseById("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link ExerciseImpl#getExerciseById(String)}
     */
    @Test
    void testGetExerciseById3() {
        // Arrange
        when(exerciseRepository.findById(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> exerciseImpl.getExerciseById("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#getDetailExercise(String, String, String)}
     */
    @Test
    void testGetDetailExercise() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(DataIntegrityViolationException.class, () -> exerciseImpl.getDetailExercise("42", "Key", "42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#getDetailExercise(String, String, String)}
     */
    @Test
    void testGetDetailExercise2() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getKey()).thenReturn("foo");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> exerciseImpl.getDetailExercise("42", "Key", "42"));
        verify(codeExercise).getKey();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#getDetailExercise(String, String, String)}
     */
    @Test
    void testGetDetailExercise3() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> exerciseImpl.getDetailExercise("42", "Key", "42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link ExerciseImpl#deleteExerciseById(String)}
     */
    @Test
    void testDeleteExerciseById() {
        // Arrange
        doNothing().when(exerciseRepository).deleteById(Mockito.<String>any());

        // Act
        exerciseImpl.deleteExerciseById("42");

        // Assert that nothing has changed
        verify(exerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test: {@link ExerciseImpl#deleteExerciseById(String)}
     */
    @Test
    void testDeleteExerciseById2() {
        // Arrange
        doThrow(new IllegalArgumentException("foo")).when(exerciseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> exerciseImpl.deleteExerciseById("42"));
        verify(exerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Exercise> ofResult = Optional.of(exercise);

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020-03-01");
        exercise2.setDurationTime(1);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("42");
        exercise2.setExerciseName("Exercise Name");
        exercise2.setKey("Key");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(1);
        exercise2.setShowAll(true);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("42");
        exercise2.setType("Type");
        exercise2.setUpdatedDate("2020-03-01");
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenReturn(exercise2);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.modifiedPermission(new CreatePermissionExerciseRequest()));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission2() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.save(Mockito.<Exercise>any()))
                .thenThrow(new IllegalArgumentException("Something wrong when change permission."));
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.modifiedPermission(new CreatePermissionExerciseRequest()));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission3() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getDurationTime()).thenReturn(1);
        when(codeExercise.getReAttempt()).thenReturn(1);
        when(codeExercise.getType()).thenReturn("Type");
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        ArrayList<String> publicGroupIds = new ArrayList<>();
        exercise.setPublicGroupIds(publicGroupIds);
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenReturn(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        ExerciseResponse actualModifiedPermissionResult = exerciseImpl
                .modifiedPermission(new CreatePermissionExerciseRequest());

        // Assert
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getReAttempt();
        verify(codeExercise).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getType();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise, atLeast(1)).setPublicGroupIds(Mockito.<List<String>>any());
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise, atLeast(1)).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
        assertEquals("2020-03-01", actualModifiedPermissionResult.getCreatedDate());
        assertEquals("2020-03-01", actualModifiedPermissionResult.getUpdatedDate());
        assertEquals("42", actualModifiedPermissionResult.getExerciseId());
        assertEquals("42", actualModifiedPermissionResult.getTopicId());
        assertEquals("Exercise Name", actualModifiedPermissionResult.getExerciseName());
        assertEquals("Type", actualModifiedPermissionResult.getType());
        assertEquals(1, actualModifiedPermissionResult.getDurationTime());
        assertEquals(1, actualModifiedPermissionResult.getReAttempt());
        assertTrue(actualModifiedPermissionResult.isShowAll());
        assertEquals(publicGroupIds, actualModifiedPermissionResult.getGroups());
        assertEquals(publicGroupIds, actualModifiedPermissionResult.getStudents());
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission4() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenThrow(new NoSuchElementException("yyyy-MM-dd HH:mm:ss"));
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.modifiedPermission(new CreatePermissionExerciseRequest()));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise, atLeast(1)).setPublicGroupIds(Mockito.<List<String>>any());
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise, atLeast(1)).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission5() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getEndTime()).thenReturn(null);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.modifiedPermission(new CreatePermissionExerciseRequest()));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise, atLeast(1)).setPublicGroupIds(Mockito.<List<String>>any());
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise, atLeast(1)).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission6() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime()).thenReturn(null);
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.modifiedPermission(new CreatePermissionExerciseRequest()));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise, atLeast(1)).setPublicGroupIds(Mockito.<List<String>>any());
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise, atLeast(1)).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link ExerciseImpl#modifiedPermission(CreatePermissionExerciseRequest)}
     */
    @Test
    void testModifiedPermission7() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("yyyy-MM-dd HH:mm:ss");
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getDurationTime()).thenReturn(1);
        when(codeExercise.getReAttempt()).thenReturn(1);
        when(codeExercise.getType()).thenReturn("Type");
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getTopicId()).thenReturn("42");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(stringList);
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");
        Optional<Exercise> ofResult = Optional.of(codeExercise);

        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setExerciseId("42");
        exercise.setExerciseName("Exercise Name");
        exercise.setKey("Key");
        ArrayList<String> publicGroupIds = new ArrayList<>();
        exercise.setPublicGroupIds(publicGroupIds);
        exercise.setReAttempt(1);
        exercise.setShowAll(true);
        exercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise.setTopicId("42");
        exercise.setType("Type");
        exercise.setUpdatedDate("2020-03-01");
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenReturn(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        Optional<Group> ofResult2 = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        ExerciseResponse actualModifiedPermissionResult = exerciseImpl
                .modifiedPermission(new CreatePermissionExerciseRequest());

        // Assert
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getReAttempt();
        verify(codeExercise).getStartTime();
        verify(codeExercise).getTopicId();
        verify(codeExercise).getType();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
        verify(codeExercise).setExerciseId(eq("42"));
        verify(codeExercise).setExerciseName(eq("Exercise Name"));
        verify(codeExercise).setKey(eq("Key"));
        verify(codeExercise, atLeast(1)).setPublicGroupIds(Mockito.<List<String>>any());
        verify(codeExercise).setReAttempt(eq(1));
        verify(codeExercise, atLeast(1)).setShowAll(eq(true));
        verify(codeExercise).setStartTime(isA(Date.class));
        verify(codeExercise).setTopicId(eq("42"));
        verify(codeExercise).setType(eq("Type"));
        verify(codeExercise).setUpdatedDate(eq("2020-03-01"));
        verify(groupRepository).findById(eq("yyyy-MM-dd HH:mm:ss"));
        verify(exerciseRepository).findById(isNull());
        verify(exerciseRepository).save(isA(Exercise.class));
        assertEquals("2020-03-01", actualModifiedPermissionResult.getCreatedDate());
        assertEquals("2020-03-01", actualModifiedPermissionResult.getUpdatedDate());
        assertEquals("42", actualModifiedPermissionResult.getExerciseId());
        assertEquals("42", actualModifiedPermissionResult.getTopicId());
        List<GroupTopicResponse> groups = actualModifiedPermissionResult.getGroups();
        assertEquals(1, groups.size());
        GroupTopicResponse getResult = groups.get(0);
        assertEquals("42", getResult.getGroupId());
        assertEquals("Exercise Name", actualModifiedPermissionResult.getExerciseName());
        assertEquals("Group Name", getResult.getGroupName());
        assertEquals("Type", actualModifiedPermissionResult.getType());
        assertEquals(1, actualModifiedPermissionResult.getDurationTime());
        assertEquals(1, actualModifiedPermissionResult.getReAttempt());
        assertTrue(actualModifiedPermissionResult.isShowAll());
        assertEquals(publicGroupIds, actualModifiedPermissionResult.getStudents());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId() {
        // Arrange
        when(exerciseRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ExerciseResponse> actualExercisesByTopicId = exerciseImpl.getExercisesByTopicId("42");

        // Assert
        verify(exerciseRepository).findAll();
        assertTrue(actualExercisesByTopicId.isEmpty());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId2() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByTopicId = exerciseImpl.getExercisesByTopicId("42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByTopicId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId3() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020/03/01");
        exercise2.setDurationTime(3);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("yyyy-MM-dd HH:mm:ss");
        exercise2.setExerciseName("yyyy-MM-dd HH:mm:ss");
        exercise2.setKey("yyyy-MM-dd HH:mm:ss");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(3);
        exercise2.setShowAll(false);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("yyyy-MM-dd HH:mm:ss");
        exercise2.setType("yyyy-MM-dd HH:mm:ss");
        exercise2.setUpdatedDate("2020/03/01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise2);
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByTopicId = exerciseImpl.getExercisesByTopicId("42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByTopicId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId4() {
        // Arrange
        when(exerciseRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByTopicId("42"));
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId5() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenThrow(new NoSuchElementException("foo"));
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByTopicId("42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId6() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getEndTime()).thenReturn(null);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByTopicId("42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId7() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime()).thenReturn(null);
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByTopicId("42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByTopicId(String)}
     */
    @Test
    void testGetExercisesByTopicId8() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("42");
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenThrow(new NoSuchElementException("foo"));
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(stringList);
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByTopicId("42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(groupRepository).findById(eq("42"));
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId() {
        // Arrange
        when(exerciseRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<ExerciseResponse> actualExercisesByUserId = exerciseImpl.getExercisesByUserId("42", "42");

        // Assert
        verify(exerciseRepository).findAll();
        assertTrue(actualExercisesByUserId.isEmpty());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId2() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByUserId = exerciseImpl.getExercisesByUserId("42", "42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByUserId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId3() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020/03/01");
        exercise2.setDurationTime(3);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("yyyy-MM-dd HH:mm:ss");
        exercise2.setExerciseName("yyyy-MM-dd HH:mm:ss");
        exercise2.setKey("yyyy-MM-dd HH:mm:ss");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(3);
        exercise2.setShowAll(false);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("yyyy-MM-dd HH:mm:ss");
        exercise2.setType("yyyy-MM-dd HH:mm:ss");
        exercise2.setUpdatedDate("2020/03/01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise2);
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByUserId = exerciseImpl.getExercisesByUserId("42", "42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByUserId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId4() {
        // Arrange
        when(exerciseRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByUserId("42", "42"));
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId5() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020/03/01");
        exercise2.setDurationTime(3);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("yyyy-MM-dd HH:mm:ss");
        exercise2.setExerciseName("yyyy-MM-dd HH:mm:ss");
        exercise2.setKey("yyyy-MM-dd HH:mm:ss");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(3);
        exercise2.setShowAll(false);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("yyyy-MM-dd HH:mm:ss");
        exercise2.setType("yyyy-MM-dd HH:mm:ss");
        exercise2.setUpdatedDate("2020/03/01");

        Exercise exercise3 = new Exercise();
        exercise3.setCreatedDate("Jan 1, 2020 8:00am GMT+0100");
        exercise3.setDurationTime(0);
        exercise3.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise3.setExerciseId("Exercise Id");
        exercise3.setExerciseName("Exercise Name");
        exercise3.setKey("Key");
        exercise3.setPublicGroupIds(new ArrayList<>());
        exercise3.setReAttempt(0);
        exercise3.setShowAll(true);
        exercise3.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise3.setTopicId("Topic Id");
        exercise3.setType("Type");
        exercise3.setUpdatedDate("yyyy-MM-dd HH:mm:ss");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise3);
        exerciseList.add(exercise2);
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByUserId = exerciseImpl.getExercisesByUserId("42", "42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByUserId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId6() {
        // Arrange
        Exercise exercise = new Exercise();
        exercise.setCreatedDate("2020-03-01");
        exercise.setDurationTime(1);
        exercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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

        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020/03/01");
        exercise2.setDurationTime(3);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseId("yyyy-MM-dd HH:mm:ss");
        exercise2.setExerciseName("yyyy-MM-dd HH:mm:ss");
        exercise2.setKey("yyyy-MM-dd HH:mm:ss");
        exercise2.setPublicGroupIds(new ArrayList<>());
        exercise2.setReAttempt(3);
        exercise2.setShowAll(false);
        exercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setTopicId("yyyy-MM-dd HH:mm:ss");
        exercise2.setType("yyyy-MM-dd HH:mm:ss");
        exercise2.setUpdatedDate("2020/03/01");

        Exercise exercise3 = new Exercise();
        exercise3.setCreatedDate("Jan 1, 2020 8:00am GMT+0100");
        exercise3.setDurationTime(0);
        exercise3.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise3.setExerciseId("Exercise Id");
        exercise3.setExerciseName("Exercise Name");
        exercise3.setKey("Key");
        exercise3.setPublicGroupIds(new ArrayList<>());
        exercise3.setReAttempt(0);
        exercise3.setShowAll(true);
        exercise3.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise3.setTopicId("Topic Id");
        exercise3.setType("Type");
        exercise3.setUpdatedDate("yyyy-MM-dd HH:mm:ss");

        Exercise exercise4 = new Exercise();
        exercise4.setCreatedDate("2020-03-01");
        exercise4.setDurationTime(1);
        exercise4.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise4.setExerciseId("42");
        exercise4.setExerciseName("Topic Id");
        exercise4.setKey("Topic Id");
        exercise4.setPublicGroupIds(new ArrayList<>());
        exercise4.setReAttempt(1);
        exercise4.setShowAll(false);
        exercise4.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise4.setTopicId("42");
        exercise4.setType("Topic Id");
        exercise4.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(exercise4);
        exerciseList.add(exercise3);
        exerciseList.add(exercise2);
        exerciseList.add(exercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act
        List<ExerciseResponse> actualExercisesByUserId = exerciseImpl.getExercisesByUserId("42", "42");

        // Assert
        verify(exerciseRepository).findAll();
        assertEquals(1, actualExercisesByUserId.size());
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId7() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenThrow(new NoSuchElementException("foo"));
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByUserId("42", "42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId8() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getEndTime()).thenReturn(null);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByUserId("42", "42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId9() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime()).thenReturn(null);
        when(codeExercise.getPublicGroupIds()).thenReturn(new ArrayList<>());
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByUserId("42", "42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(exerciseRepository).findAll();
    }

    /**
     * Method under test: {@link ExerciseImpl#getExercisesByUserId(String, String)}
     */
    @Test
    void testGetExercisesByUserId10() {
        // Arrange
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("42");
        CodeExercise codeExercise = mock(CodeExercise.class);
        when(codeExercise.getDurationTime()).thenThrow(new NoSuchElementException("foo"));
        when(codeExercise.getEndTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getCreatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getExerciseId()).thenReturn("42");
        when(codeExercise.getExerciseName()).thenReturn("Exercise Name");
        when(codeExercise.getUpdatedDate()).thenReturn("2020-03-01");
        when(codeExercise.getStartTime())
                .thenReturn(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        when(codeExercise.getPublicGroupIds()).thenReturn(stringList);
        when(codeExercise.isShowAll()).thenReturn(true);
        when(codeExercise.getTopicId()).thenReturn("42");
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setDurationTime(anyInt());
        doNothing().when(codeExercise).setEndTime(Mockito.<Date>any());
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
        codeExercise.setCreatedDate("2020-03-01");
        codeExercise.setDurationTime(1);
        codeExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setExerciseId("42");
        codeExercise.setExerciseName("Exercise Name");
        codeExercise.setKey("Key");
        codeExercise.setPublicGroupIds(new ArrayList<>());
        codeExercise.setReAttempt(1);
        codeExercise.setShowAll(true);
        codeExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        codeExercise.setTopicId("42");
        codeExercise.setType("Type");
        codeExercise.setUpdatedDate("2020-03-01");

        ArrayList<Exercise> exerciseList = new ArrayList<>();
        exerciseList.add(codeExercise);
        when(exerciseRepository.findAll()).thenReturn(exerciseList);

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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        Optional<Group> ofResult = Optional.of(group);
        when(groupRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> exerciseImpl.getExercisesByUserId("42", "42"));
        verify(codeExercise).getCreatedDate();
        verify(codeExercise).getDurationTime();
        verify(codeExercise).getEndTime();
        verify(codeExercise).getExerciseId();
        verify(codeExercise).getExerciseName();
        verify(codeExercise).getPublicGroupIds();
        verify(codeExercise).getStartTime();
        verify(codeExercise, atLeast(1)).getTopicId();
        verify(codeExercise).getUpdatedDate();
        verify(codeExercise).isShowAll();
        verify(codeExercise).setCreatedDate(eq("2020-03-01"));
        verify(codeExercise).setDurationTime(eq(1));
        verify(codeExercise).setEndTime(isA(Date.class));
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
        verify(groupRepository).findById(eq("42"));
        verify(exerciseRepository).findAll();
    }
}
