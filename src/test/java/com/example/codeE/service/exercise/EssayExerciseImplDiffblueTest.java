package com.example.codeE.service.exercise;

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

import com.example.codeE.model.course.Course;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.EssayExerciseRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.SessionExerciseRepository;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
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

@ContextConfiguration(classes = {EssayExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EssayExerciseImplDiffblueTest {
    @Autowired
    private EssayExerciseImpl essayExerciseImpl;

    @MockBean
    private EssayExerciseRepository essayExerciseRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private SessionExerciseRepository sessionExerciseRepository;

    /**
     * Method under test:
     * {@link EssayExerciseImpl#createEssayExercise(EssayExercise)}
     */
    @Test
    void testCreateEssayExercise() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        when(essayExerciseRepository.save(Mockito.<EssayExercise>any())).thenReturn(essayExercise);

        EssayExercise essayExercise2 = new EssayExercise();
        essayExercise2.setCreatedDate("2020-03-01");
        essayExercise2.setDurationTime(1);
        essayExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setExerciseDescription("Exercise Description");
        essayExercise2.setExerciseId("42");
        essayExercise2.setExerciseName("Exercise Name");
        essayExercise2.setKey("Key");
        essayExercise2.setPublicGroupIds(new ArrayList<>());
        essayExercise2.setQuestion("Question");
        essayExercise2.setReAttempt(1);
        essayExercise2.setShowAll(true);
        essayExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setTopicId("42");
        essayExercise2.setType("Type");
        essayExercise2.setUpdatedDate("2020-03-01");
        essayExercise2.setUsingAiGrading(true);

        // Act
        EssayExercise actualCreateEssayExerciseResult = essayExerciseImpl.createEssayExercise(essayExercise2);

        // Assert
        verify(essayExerciseRepository).save(isA(EssayExercise.class));
        assertSame(essayExercise, actualCreateEssayExerciseResult);
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#createEssayExercise(EssayExercise)}
     */
    @Test
    void testCreateEssayExercise2() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        when(essayExerciseRepository.save(Mockito.<EssayExercise>any())).thenReturn(essayExercise);
        EssayExercise essayExercise2 = mock(EssayExercise.class);
        when(essayExercise2.getReAttempt()).thenReturn(-1);
        when(essayExercise2.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(essayExercise2).setQuestion(Mockito.<String>any());
        doNothing().when(essayExercise2).setUsingAiGrading(anyBoolean());
        doNothing().when(essayExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(essayExercise2).setDurationTime(anyInt());
        doNothing().when(essayExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(essayExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(essayExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(essayExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(essayExercise2).setKey(Mockito.<String>any());
        doNothing().when(essayExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(essayExercise2).setReAttempt(anyInt());
        doNothing().when(essayExercise2).setShowAll(anyBoolean());
        doNothing().when(essayExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(essayExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(essayExercise2).setType(Mockito.<String>any());
        doNothing().when(essayExercise2).setUpdatedDate(Mockito.<String>any());
        essayExercise2.setCreatedDate("2020-03-01");
        essayExercise2.setDurationTime(1);
        essayExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setExerciseDescription("Exercise Description");
        essayExercise2.setExerciseId("42");
        essayExercise2.setExerciseName("Exercise Name");
        essayExercise2.setKey("Key");
        essayExercise2.setPublicGroupIds(new ArrayList<>());
        essayExercise2.setQuestion("Question");
        essayExercise2.setReAttempt(1);
        essayExercise2.setShowAll(true);
        essayExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setTopicId("42");
        essayExercise2.setType("Type");
        essayExercise2.setUpdatedDate("2020-03-01");
        essayExercise2.setUsingAiGrading(true);

        // Act
        EssayExercise actualCreateEssayExerciseResult = essayExerciseImpl.createEssayExercise(essayExercise2);

        // Assert
        verify(essayExercise2).setQuestion(eq("Question"));
        verify(essayExercise2).setUsingAiGrading(eq(true));
        verify(essayExercise2).getPublicGroupIds();
        verify(essayExercise2).getReAttempt();
        verify(essayExercise2).setCreatedDate(eq("2020-03-01"));
        verify(essayExercise2).setDurationTime(eq(1));
        verify(essayExercise2).setEndTime(isA(Date.class));
        verify(essayExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(essayExercise2).setExerciseId(eq("42"));
        verify(essayExercise2).setExerciseName(eq("Exercise Name"));
        verify(essayExercise2).setKey(eq("Key"));
        verify(essayExercise2).setPublicGroupIds(isA(List.class));
        verify(essayExercise2, atLeast(1)).setReAttempt(eq(1));
        verify(essayExercise2).setShowAll(eq(true));
        verify(essayExercise2).setStartTime(isA(Date.class));
        verify(essayExercise2).setTopicId(eq("42"));
        verify(essayExercise2).setType(eq("Type"));
        verify(essayExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(essayExerciseRepository).save(isA(EssayExercise.class));
        assertSame(essayExercise, actualCreateEssayExerciseResult);
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#createEssayExercise(EssayExercise)}
     */
    @Test
    void testCreateEssayExercise3() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        when(essayExerciseRepository.save(Mockito.<EssayExercise>any())).thenReturn(essayExercise);

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

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("foo");
        EssayExercise essayExercise2 = mock(EssayExercise.class);
        when(essayExercise2.getReAttempt()).thenReturn(1);
        when(essayExercise2.getPublicGroupIds()).thenReturn(stringList);
        doNothing().when(essayExercise2).setQuestion(Mockito.<String>any());
        doNothing().when(essayExercise2).setUsingAiGrading(anyBoolean());
        doNothing().when(essayExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(essayExercise2).setDurationTime(anyInt());
        doNothing().when(essayExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(essayExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(essayExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(essayExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(essayExercise2).setKey(Mockito.<String>any());
        doNothing().when(essayExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(essayExercise2).setReAttempt(anyInt());
        doNothing().when(essayExercise2).setShowAll(anyBoolean());
        doNothing().when(essayExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(essayExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(essayExercise2).setType(Mockito.<String>any());
        doNothing().when(essayExercise2).setUpdatedDate(Mockito.<String>any());
        essayExercise2.setCreatedDate("2020-03-01");
        essayExercise2.setDurationTime(1);
        essayExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setExerciseDescription("Exercise Description");
        essayExercise2.setExerciseId("42");
        essayExercise2.setExerciseName("Exercise Name");
        essayExercise2.setKey("Key");
        essayExercise2.setPublicGroupIds(new ArrayList<>());
        essayExercise2.setQuestion("Question");
        essayExercise2.setReAttempt(1);
        essayExercise2.setShowAll(true);
        essayExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setTopicId("42");
        essayExercise2.setType("Type");
        essayExercise2.setUpdatedDate("2020-03-01");
        essayExercise2.setUsingAiGrading(true);

        // Act
        EssayExercise actualCreateEssayExerciseResult = essayExerciseImpl.createEssayExercise(essayExercise2);

        // Assert
        verify(essayExercise2).setQuestion(eq("Question"));
        verify(essayExercise2).setUsingAiGrading(eq(true));
        verify(essayExercise2).getPublicGroupIds();
        verify(essayExercise2).getReAttempt();
        verify(essayExercise2).setCreatedDate(eq("2020-03-01"));
        verify(essayExercise2).setDurationTime(eq(1));
        verify(essayExercise2).setEndTime(isA(Date.class));
        verify(essayExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(essayExercise2).setExerciseId(eq("42"));
        verify(essayExercise2).setExerciseName(eq("Exercise Name"));
        verify(essayExercise2).setKey(eq("Key"));
        verify(essayExercise2).setPublicGroupIds(isA(List.class));
        verify(essayExercise2).setReAttempt(eq(1));
        verify(essayExercise2).setShowAll(eq(true));
        verify(essayExercise2).setStartTime(isA(Date.class));
        verify(essayExercise2).setTopicId(eq("42"));
        verify(essayExercise2).setType(eq("Type"));
        verify(essayExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(groupRepository).findById(eq("foo"));
        verify(essayExerciseRepository).save(isA(EssayExercise.class));
        assertSame(essayExercise, actualCreateEssayExerciseResult);
    }

    /**
     * Method under test: {@link EssayExerciseImpl#getEssayExerciseById(String)}
     */
    @Test
    void testGetEssayExerciseById() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        Optional<EssayExercise> ofResult = Optional.of(essayExercise);
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        EssayExercise actualEssayExerciseById = essayExerciseImpl.getEssayExerciseById("42");

        // Assert
        verify(essayExerciseRepository).findById(eq("42"));
        assertSame(essayExercise, actualEssayExerciseById);
    }

    /**
     * Method under test: {@link EssayExerciseImpl#getEssayExerciseById(String)}
     */
    @Test
    void testGetEssayExerciseById2() {
        // Arrange
        Optional<EssayExercise> emptyResult = Optional.empty();
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essayExerciseImpl.getEssayExerciseById("42"));
        verify(essayExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssayExerciseImpl#getEssayExerciseById(String)}
     */
    @Test
    void testGetEssayExerciseById3() {
        // Arrange
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essayExerciseImpl.getEssayExerciseById("42"));
        verify(essayExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#getEssayExerciseDetail(String, HttpServletRequest)}
     */
    @Test
    void testGetEssayExerciseDetail() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        Optional<EssayExercise> ofResult = Optional.of(essayExercise);
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(sessionExerciseRepository.findAll()).thenThrow(new RuntimeException("yyyy-MM-dd'T'HH:mm:ss'Z'"));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> essayExerciseImpl.getEssayExerciseDetail("42", new MockHttpServletRequest()));
        verify(essayExerciseRepository).findById(eq("42"));
        verify(sessionExerciseRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#getEssayExerciseDetail(String, HttpServletRequest)}
     */
    @Test
    void testGetEssayExerciseDetail2() {
        // Arrange
        Optional<EssayExercise> emptyResult = Optional.empty();
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> essayExerciseImpl.getEssayExerciseDetail("42", new MockHttpServletRequest()));
        verify(essayExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssayExerciseImpl#deleteEssayExerciseById(String)}
     */
    @Test
    void testDeleteEssayExerciseById() {
        // Arrange
        doNothing().when(essayExerciseRepository).deleteById(Mockito.<String>any());

        // Act
        essayExerciseImpl.deleteEssayExerciseById("42");

        // Assert that nothing has changed
        verify(essayExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test: {@link EssayExerciseImpl#deleteEssayExerciseById(String)}
     */
    @Test
    void testDeleteEssayExerciseById2() {
        // Arrange
        doThrow(new RuntimeException("foo")).when(essayExerciseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essayExerciseImpl.deleteEssayExerciseById("42"));
        verify(essayExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#updateEssayExercise(String, UpdateEssayExerciseRequest)}
     */
    @Test
    void testUpdateEssayExercise() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        Optional<EssayExercise> ofResult = Optional.of(essayExercise);

        EssayExercise essayExercise2 = new EssayExercise();
        essayExercise2.setCreatedDate("2020-03-01");
        essayExercise2.setDurationTime(1);
        essayExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setExerciseDescription("Exercise Description");
        essayExercise2.setExerciseId("42");
        essayExercise2.setExerciseName("Exercise Name");
        essayExercise2.setKey("Key");
        essayExercise2.setPublicGroupIds(new ArrayList<>());
        essayExercise2.setQuestion("Question");
        essayExercise2.setReAttempt(1);
        essayExercise2.setShowAll(true);
        essayExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setTopicId("42");
        essayExercise2.setType("Type");
        essayExercise2.setUpdatedDate("2020-03-01");
        essayExercise2.setUsingAiGrading(true);
        when(essayExerciseRepository.save(Mockito.<EssayExercise>any())).thenReturn(essayExercise2);
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        EssayExercise actualUpdateEssayExerciseResult = essayExerciseImpl.updateEssayExercise("42",
                new UpdateEssayExerciseRequest());

        // Assert
        verify(essayExerciseRepository).findById(eq("42"));
        verify(essayExerciseRepository).save(isA(EssayExercise.class));
        verify(exerciseRepository).save(isA(Exercise.class));
        assertSame(essayExercise2, actualUpdateEssayExerciseResult);
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#updateEssayExercise(String, UpdateEssayExerciseRequest)}
     */
    @Test
    void testUpdateEssayExercise2() {
        // Arrange
        EssayExercise essayExercise = new EssayExercise();
        essayExercise.setCreatedDate("2020-03-01");
        essayExercise.setDurationTime(1);
        essayExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setExerciseDescription("Exercise Description");
        essayExercise.setExerciseId("42");
        essayExercise.setExerciseName("Exercise Name");
        essayExercise.setKey("Key");
        essayExercise.setPublicGroupIds(new ArrayList<>());
        essayExercise.setQuestion("Question");
        essayExercise.setReAttempt(1);
        essayExercise.setShowAll(true);
        essayExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise.setTopicId("42");
        essayExercise.setType("Type");
        essayExercise.setUpdatedDate("2020-03-01");
        essayExercise.setUsingAiGrading(true);
        Optional<EssayExercise> ofResult = Optional.of(essayExercise);

        EssayExercise essayExercise2 = new EssayExercise();
        essayExercise2.setCreatedDate("2020-03-01");
        essayExercise2.setDurationTime(1);
        essayExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setExerciseDescription("Exercise Description");
        essayExercise2.setExerciseId("42");
        essayExercise2.setExerciseName("Exercise Name");
        essayExercise2.setKey("Key");
        essayExercise2.setPublicGroupIds(new ArrayList<>());
        essayExercise2.setQuestion("Question");
        essayExercise2.setReAttempt(1);
        essayExercise2.setShowAll(true);
        essayExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        essayExercise2.setTopicId("42");
        essayExercise2.setType("Type");
        essayExercise2.setUpdatedDate("2020-03-01");
        essayExercise2.setUsingAiGrading(true);
        when(essayExerciseRepository.save(Mockito.<EssayExercise>any())).thenReturn(essayExercise2);
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        when(exerciseRepository.save(Mockito.<Exercise>any())).thenThrow(new RuntimeException("essay"));

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> essayExerciseImpl.updateEssayExercise("42", new UpdateEssayExerciseRequest()));
        verify(essayExerciseRepository).findById(eq("42"));
        verify(exerciseRepository).save(isA(Exercise.class));
    }

    /**
     * Method under test:
     * {@link EssayExerciseImpl#updateEssayExercise(String, UpdateEssayExerciseRequest)}
     */
    @Test
    void testUpdateEssayExercise3() {
        // Arrange
        Optional<EssayExercise> emptyResult = Optional.empty();
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> essayExerciseImpl.updateEssayExercise("42", new UpdateEssayExerciseRequest()));
        verify(essayExerciseRepository).findById(eq("42"));
    }
}
