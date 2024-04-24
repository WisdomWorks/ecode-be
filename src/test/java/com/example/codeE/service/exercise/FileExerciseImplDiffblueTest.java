package com.example.codeE.service.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.example.codeE.model.exercise.FileExercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.FileExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.request.exercise.file.UpdateFileExerciseRequest;
import com.example.codeE.request.exercise.file.response.FileDetailResponse;

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

@ContextConfiguration(classes = {FileExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class FileExerciseImplDiffblueTest {
    @Autowired
    private FileExerciseImpl fileExerciseImpl;

    @MockBean
    private FileExerciseRepository fileExerciseRepository;

    @MockBean
    private GroupRepository groupRepository;

    /**
     * Method under test: {@link FileExerciseImpl#createFileExercise(FileExercise)}
     */
    @Test
    void testCreateFileExercise() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        when(fileExerciseRepository.save(Mockito.<FileExercise>any())).thenReturn(fileExercise);

        FileExercise fileExercise2 = new FileExercise();
        fileExercise2.setCreatedDate("2020-03-01");
        fileExercise2.setDurationTime(1);
        fileExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setExerciseDescription("Exercise Description");
        fileExercise2.setExerciseId("42");
        fileExercise2.setExerciseName("Exercise Name");
        fileExercise2.setKey("Key");
        fileExercise2.setPublicGroupIds(new ArrayList<>());
        fileExercise2.setQuestion("Question");
        fileExercise2.setReAttempt(1);
        fileExercise2.setShowAll(true);
        fileExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setTopicId("42");
        fileExercise2.setType("Type");
        fileExercise2.setUpdatedDate("2020-03-01");

        // Act
        FileExercise actualCreateFileExerciseResult = fileExerciseImpl.createFileExercise(fileExercise2);

        // Assert
        verify(fileExerciseRepository).save(isA(FileExercise.class));
        assertSame(fileExercise, actualCreateFileExerciseResult);
    }

    /**
     * Method under test: {@link FileExerciseImpl#createFileExercise(FileExercise)}
     */
    @Test
    void testCreateFileExercise2() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        when(fileExerciseRepository.save(Mockito.<FileExercise>any())).thenReturn(fileExercise);
        FileExercise fileExercise2 = mock(FileExercise.class);
        when(fileExercise2.getReAttempt()).thenReturn(-1);
        when(fileExercise2.getPublicGroupIds()).thenReturn(new ArrayList<>());
        doNothing().when(fileExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(fileExercise2).setDurationTime(anyInt());
        doNothing().when(fileExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(fileExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(fileExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(fileExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(fileExercise2).setKey(Mockito.<String>any());
        doNothing().when(fileExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(fileExercise2).setReAttempt(anyInt());
        doNothing().when(fileExercise2).setShowAll(anyBoolean());
        doNothing().when(fileExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(fileExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(fileExercise2).setType(Mockito.<String>any());
        doNothing().when(fileExercise2).setUpdatedDate(Mockito.<String>any());
        doNothing().when(fileExercise2).setQuestion(Mockito.<String>any());
        fileExercise2.setCreatedDate("2020-03-01");
        fileExercise2.setDurationTime(1);
        fileExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setExerciseDescription("Exercise Description");
        fileExercise2.setExerciseId("42");
        fileExercise2.setExerciseName("Exercise Name");
        fileExercise2.setKey("Key");
        fileExercise2.setPublicGroupIds(new ArrayList<>());
        fileExercise2.setQuestion("Question");
        fileExercise2.setReAttempt(1);
        fileExercise2.setShowAll(true);
        fileExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setTopicId("42");
        fileExercise2.setType("Type");
        fileExercise2.setUpdatedDate("2020-03-01");

        // Act
        FileExercise actualCreateFileExerciseResult = fileExerciseImpl.createFileExercise(fileExercise2);

        // Assert
        verify(fileExercise2).getPublicGroupIds();
        verify(fileExercise2).getReAttempt();
        verify(fileExercise2).setCreatedDate(eq("2020-03-01"));
        verify(fileExercise2).setDurationTime(eq(1));
        verify(fileExercise2).setEndTime(isA(Date.class));
        verify(fileExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(fileExercise2).setExerciseId(eq("42"));
        verify(fileExercise2).setExerciseName(eq("Exercise Name"));
        verify(fileExercise2).setKey(eq("Key"));
        verify(fileExercise2).setPublicGroupIds(isA(List.class));
        verify(fileExercise2, atLeast(1)).setReAttempt(eq(1));
        verify(fileExercise2).setShowAll(eq(true));
        verify(fileExercise2).setStartTime(isA(Date.class));
        verify(fileExercise2).setTopicId(eq("42"));
        verify(fileExercise2).setType(eq("Type"));
        verify(fileExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(fileExercise2).setQuestion(eq("Question"));
        verify(fileExerciseRepository).save(isA(FileExercise.class));
        assertSame(fileExercise, actualCreateFileExerciseResult);
    }

    /**
     * Method under test: {@link FileExerciseImpl#createFileExercise(FileExercise)}
     */
    @Test
    void testCreateFileExercise3() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        when(fileExerciseRepository.save(Mockito.<FileExercise>any())).thenReturn(fileExercise);

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
        FileExercise fileExercise2 = mock(FileExercise.class);
        when(fileExercise2.getReAttempt()).thenReturn(1);
        when(fileExercise2.getPublicGroupIds()).thenReturn(stringList);
        doNothing().when(fileExercise2).setCreatedDate(Mockito.<String>any());
        doNothing().when(fileExercise2).setDurationTime(anyInt());
        doNothing().when(fileExercise2).setEndTime(Mockito.<Date>any());
        doNothing().when(fileExercise2).setExerciseDescription(Mockito.<String>any());
        doNothing().when(fileExercise2).setExerciseId(Mockito.<String>any());
        doNothing().when(fileExercise2).setExerciseName(Mockito.<String>any());
        doNothing().when(fileExercise2).setKey(Mockito.<String>any());
        doNothing().when(fileExercise2).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(fileExercise2).setReAttempt(anyInt());
        doNothing().when(fileExercise2).setShowAll(anyBoolean());
        doNothing().when(fileExercise2).setStartTime(Mockito.<Date>any());
        doNothing().when(fileExercise2).setTopicId(Mockito.<String>any());
        doNothing().when(fileExercise2).setType(Mockito.<String>any());
        doNothing().when(fileExercise2).setUpdatedDate(Mockito.<String>any());
        doNothing().when(fileExercise2).setQuestion(Mockito.<String>any());
        fileExercise2.setCreatedDate("2020-03-01");
        fileExercise2.setDurationTime(1);
        fileExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setExerciseDescription("Exercise Description");
        fileExercise2.setExerciseId("42");
        fileExercise2.setExerciseName("Exercise Name");
        fileExercise2.setKey("Key");
        fileExercise2.setPublicGroupIds(new ArrayList<>());
        fileExercise2.setQuestion("Question");
        fileExercise2.setReAttempt(1);
        fileExercise2.setShowAll(true);
        fileExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setTopicId("42");
        fileExercise2.setType("Type");
        fileExercise2.setUpdatedDate("2020-03-01");

        // Act
        FileExercise actualCreateFileExerciseResult = fileExerciseImpl.createFileExercise(fileExercise2);

        // Assert
        verify(fileExercise2).getPublicGroupIds();
        verify(fileExercise2).getReAttempt();
        verify(fileExercise2).setCreatedDate(eq("2020-03-01"));
        verify(fileExercise2).setDurationTime(eq(1));
        verify(fileExercise2).setEndTime(isA(Date.class));
        verify(fileExercise2).setExerciseDescription(eq("Exercise Description"));
        verify(fileExercise2).setExerciseId(eq("42"));
        verify(fileExercise2).setExerciseName(eq("Exercise Name"));
        verify(fileExercise2).setKey(eq("Key"));
        verify(fileExercise2).setPublicGroupIds(isA(List.class));
        verify(fileExercise2).setReAttempt(eq(1));
        verify(fileExercise2).setShowAll(eq(true));
        verify(fileExercise2).setStartTime(isA(Date.class));
        verify(fileExercise2).setTopicId(eq("42"));
        verify(fileExercise2).setType(eq("Type"));
        verify(fileExercise2).setUpdatedDate(eq("2020-03-01"));
        verify(fileExercise2).setQuestion(eq("Question"));
        verify(groupRepository).findById(eq("foo"));
        verify(fileExerciseRepository).save(isA(FileExercise.class));
        assertSame(fileExercise, actualCreateFileExerciseResult);
    }

    /**
     * Method under test: {@link FileExerciseImpl#getFileExerciseDetail(String)}
     */
    @Test
    void testGetFileExerciseDetail() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        Date endTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        fileExercise.setEndTime(endTime);
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        Date startTime = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        fileExercise.setStartTime(startTime);
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        Optional<FileExercise> ofResult = Optional.of(fileExercise);
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        FileDetailResponse actualFileExerciseDetail = fileExerciseImpl.getFileExerciseDetail("42");

        // Assert
        verify(fileExerciseRepository).findById(eq("42"));
        assertEquals("42", actualFileExerciseDetail.getExerciseId());
        assertEquals("42", actualFileExerciseDetail.getTopicId());
        assertEquals("Exercise Description", actualFileExerciseDetail.getExerciseDescription());
        assertEquals("Exercise Name", actualFileExerciseDetail.getExerciseName());
        assertEquals("Question", actualFileExerciseDetail.getQuestion());
        assertEquals("Type", actualFileExerciseDetail.getType());
        assertEquals(1, actualFileExerciseDetail.getDurationTime());
        assertEquals(1, actualFileExerciseDetail.getReAttempt());
        assertSame(endTime, actualFileExerciseDetail.getEndTime());
        assertSame(startTime, actualFileExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link FileExerciseImpl#getFileExerciseDetail(String)}
     */
    @Test
    void testGetFileExerciseDetail2() {
        // Arrange
        FileExercise fileExercise = mock(FileExercise.class);
        when(fileExercise.getDurationTime()).thenReturn(1);
        when(fileExercise.getReAttempt()).thenReturn(1);
        when(fileExercise.getExerciseDescription()).thenReturn("Exercise Description");
        when(fileExercise.getExerciseId()).thenReturn("42");
        when(fileExercise.getExerciseName()).thenReturn("Exercise Name");
        when(fileExercise.getTopicId()).thenReturn("42");
        when(fileExercise.getType()).thenReturn("Type");
        when(fileExercise.getQuestion()).thenReturn("Question");
        Date fromResult = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(fileExercise.getEndTime()).thenReturn(fromResult);
        Date fromResult2 = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        when(fileExercise.getStartTime()).thenReturn(fromResult2);
        doNothing().when(fileExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(fileExercise).setDurationTime(anyInt());
        doNothing().when(fileExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(fileExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(fileExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(fileExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(fileExercise).setKey(Mockito.<String>any());
        doNothing().when(fileExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(fileExercise).setReAttempt(anyInt());
        doNothing().when(fileExercise).setShowAll(anyBoolean());
        doNothing().when(fileExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(fileExercise).setTopicId(Mockito.<String>any());
        doNothing().when(fileExercise).setType(Mockito.<String>any());
        doNothing().when(fileExercise).setUpdatedDate(Mockito.<String>any());
        doNothing().when(fileExercise).setQuestion(Mockito.<String>any());
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        Optional<FileExercise> ofResult = Optional.of(fileExercise);
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        FileDetailResponse actualFileExerciseDetail = fileExerciseImpl.getFileExerciseDetail("42");

        // Assert
        verify(fileExercise, atLeast(1)).getDurationTime();
        verify(fileExercise, atLeast(1)).getEndTime();
        verify(fileExercise).getExerciseDescription();
        verify(fileExercise).getExerciseId();
        verify(fileExercise).getExerciseName();
        verify(fileExercise).getReAttempt();
        verify(fileExercise, atLeast(1)).getStartTime();
        verify(fileExercise).getTopicId();
        verify(fileExercise).getType();
        verify(fileExercise).setCreatedDate(eq("2020-03-01"));
        verify(fileExercise).setDurationTime(eq(1));
        verify(fileExercise).setEndTime(isA(Date.class));
        verify(fileExercise).setExerciseDescription(eq("Exercise Description"));
        verify(fileExercise).setExerciseId(eq("42"));
        verify(fileExercise).setExerciseName(eq("Exercise Name"));
        verify(fileExercise).setKey(eq("Key"));
        verify(fileExercise).setPublicGroupIds(isA(List.class));
        verify(fileExercise).setReAttempt(eq(1));
        verify(fileExercise).setShowAll(eq(true));
        verify(fileExercise).setStartTime(isA(Date.class));
        verify(fileExercise).setTopicId(eq("42"));
        verify(fileExercise).setType(eq("Type"));
        verify(fileExercise).setUpdatedDate(eq("2020-03-01"));
        verify(fileExercise).getQuestion();
        verify(fileExercise).setQuestion(eq("Question"));
        verify(fileExerciseRepository).findById(eq("42"));
        assertEquals("42", actualFileExerciseDetail.getExerciseId());
        assertEquals("42", actualFileExerciseDetail.getTopicId());
        assertEquals("Exercise Description", actualFileExerciseDetail.getExerciseDescription());
        assertEquals("Exercise Name", actualFileExerciseDetail.getExerciseName());
        assertEquals("Question", actualFileExerciseDetail.getQuestion());
        assertEquals("Type", actualFileExerciseDetail.getType());
        assertEquals(1, actualFileExerciseDetail.getDurationTime());
        assertEquals(1, actualFileExerciseDetail.getReAttempt());
        assertSame(fromResult, actualFileExerciseDetail.getEndTime());
        assertSame(fromResult2, actualFileExerciseDetail.getStartTime());
    }

    /**
     * Method under test: {@link FileExerciseImpl#getFileExerciseDetail(String)}
     */
    @Test
    void testGetFileExerciseDetail3() {
        // Arrange
        Optional<FileExercise> emptyResult = Optional.empty();
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> fileExerciseImpl.getFileExerciseDetail("42"));
        verify(fileExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileExerciseImpl#updateFileExercise(String, UpdateFileExerciseRequest)}
     */
    @Test
    void testUpdateFileExercise() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        Optional<FileExercise> ofResult = Optional.of(fileExercise);

        FileExercise fileExercise2 = new FileExercise();
        fileExercise2.setCreatedDate("2020-03-01");
        fileExercise2.setDurationTime(1);
        fileExercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setExerciseDescription("Exercise Description");
        fileExercise2.setExerciseId("42");
        fileExercise2.setExerciseName("Exercise Name");
        fileExercise2.setKey("Key");
        fileExercise2.setPublicGroupIds(new ArrayList<>());
        fileExercise2.setQuestion("Question");
        fileExercise2.setReAttempt(1);
        fileExercise2.setShowAll(true);
        fileExercise2.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise2.setTopicId("42");
        fileExercise2.setType("Type");
        fileExercise2.setUpdatedDate("2020-03-01");
        when(fileExerciseRepository.save(Mockito.<FileExercise>any())).thenReturn(fileExercise2);
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        FileExercise actualUpdateFileExerciseResult = fileExerciseImpl.updateFileExercise("42",
                new UpdateFileExerciseRequest());

        // Assert
        verify(fileExerciseRepository).findById(eq("42"));
        verify(fileExerciseRepository).save(isA(FileExercise.class));
        assertSame(fileExercise2, actualUpdateFileExerciseResult);
    }

    /**
     * Method under test:
     * {@link FileExerciseImpl#updateFileExercise(String, UpdateFileExerciseRequest)}
     */
    @Test
    void testUpdateFileExercise2() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        Optional<FileExercise> ofResult = Optional.of(fileExercise);
        when(fileExerciseRepository.save(Mockito.<FileExercise>any())).thenThrow(new RuntimeException("file"));
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> fileExerciseImpl.updateFileExercise("42", new UpdateFileExerciseRequest()));
        verify(fileExerciseRepository).findById(eq("42"));
        verify(fileExerciseRepository).save(isA(FileExercise.class));
    }

    /**
     * Method under test:
     * {@link FileExerciseImpl#updateFileExercise(String, UpdateFileExerciseRequest)}
     */
    @Test
    void testUpdateFileExercise3() {
        // Arrange
        Optional<FileExercise> emptyResult = Optional.empty();
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> fileExerciseImpl.updateFileExercise("42", new UpdateFileExerciseRequest()));
        verify(fileExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link FileExerciseImpl#updateFileExercise(String, UpdateFileExerciseRequest)}
     */
    @Test
    void testUpdateFileExercise4() {
        // Arrange
        FileExercise fileExercise = new FileExercise();
        fileExercise.setCreatedDate("2020-03-01");
        fileExercise.setDurationTime(1);
        fileExercise.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setExerciseDescription("Exercise Description");
        fileExercise.setExerciseId("42");
        fileExercise.setExerciseName("Exercise Name");
        fileExercise.setKey("Key");
        fileExercise.setPublicGroupIds(new ArrayList<>());
        fileExercise.setQuestion("Question");
        fileExercise.setReAttempt(1);
        fileExercise.setShowAll(true);
        fileExercise.setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        fileExercise.setTopicId("42");
        fileExercise.setType("Type");
        fileExercise.setUpdatedDate("2020-03-01");
        Optional<FileExercise> ofResult = Optional.of(fileExercise);
        when(fileExerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> fileExerciseImpl.updateFileExercise("42", null));
        verify(fileExerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link FileExerciseImpl#deleteFileExerciseById(String)}
     */
    @Test
    void testDeleteFileExerciseById() {
        // Arrange
        doNothing().when(fileExerciseRepository).deleteById(Mockito.<String>any());

        // Act
        fileExerciseImpl.deleteFileExerciseById("42");

        // Assert that nothing has changed
        verify(fileExerciseRepository).deleteById(eq("42"));
    }

    /**
     * Method under test: {@link FileExerciseImpl#deleteFileExerciseById(String)}
     */
    @Test
    void testDeleteFileExerciseById2() {
        // Arrange
        doThrow(new RuntimeException("foo")).when(fileExerciseRepository).deleteById(Mockito.<String>any());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> fileExerciseImpl.deleteFileExerciseById("42"));
        verify(fileExerciseRepository).deleteById(eq("42"));
    }
}
