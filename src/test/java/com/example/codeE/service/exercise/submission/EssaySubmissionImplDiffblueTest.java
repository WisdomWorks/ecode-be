package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.EssaySubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;

import java.time.LocalDate;
import java.time.LocalTime;
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

@ContextConfiguration(classes = {EssaySubmissionImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EssaySubmissionImplDiffblueTest {
    @Autowired
    private EssaySubmissionImpl essaySubmissionImpl;

    @MockBean
    private EssaySubmissionRepository essaySubmissionRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenReturn(essaySubmission);

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        EssaySubmission actualCreateSubmissionResult = essaySubmissionImpl
                .createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission"));

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
        assertSame(essaySubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission2() {
        // Arrange
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenThrow(new IllegalArgumentException("foo"));

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission3() {
        // Arrange
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission4() {
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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionByExerciseId() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<EssaySubmission> actualEssaySubmissionByExerciseId = essaySubmissionImpl.getEssaySubmissionByExerciseId("42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertTrue(actualEssaySubmissionByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionByExerciseId2() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        List<EssaySubmission> actualEssaySubmissionByExerciseId = essaySubmissionImpl.getEssaySubmissionByExerciseId("42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionByExerciseId3() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020/03/01");
        essaySubmission2.setDateSubmit("2020/03/01");
        essaySubmission2.setExerciseId("Exercise Id");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(0.5f);
        essaySubmission2.setStudentId("Student Id");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("Submission Id");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        List<EssaySubmission> actualEssaySubmissionByExerciseId = essaySubmissionImpl.getEssaySubmissionByExerciseId("42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionByExerciseId.size());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionByExerciseId4() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.getEssaySubmissionByExerciseId("42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenReturn(new ArrayList<>());

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        List<EssaySubmissionsResponse> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertTrue(actualEssaySubmissionsByExerciseId.isEmpty());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId2() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.getEssaySubmissionsByExerciseId("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId3() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        List<EssaySubmissionsResponse> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionsByExerciseId.size());
        User student = actualEssaySubmissionsByExerciseId.get(0).getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId4() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020/03/01");
        essaySubmission2.setDateSubmit("2020/03/01");
        essaySubmission2.setExerciseId("Exercise Id");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(0.5f);
        essaySubmission2.setStudentId("Student Id");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("Submission Id");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult2 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        List<EssaySubmissionsResponse> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionsByExerciseId.size());
        User student = actualEssaySubmissionsByExerciseId.get(0).getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId5() {
        // Arrange
        EssaySubmission essaySubmission = mock(EssaySubmission.class);
        doNothing().when(essaySubmission).setSubmission(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(essaySubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(essaySubmission).setReviewable(anyBoolean());
        doNothing().when(essaySubmission).setScore(Mockito.<Float>any());
        doNothing().when(essaySubmission).setStudentId(Mockito.<String>any());
        doNothing().when(essaySubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(essaySubmission).setTeacherComment(Mockito.<String>any());
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.getEssaySubmissionsByExerciseId("42"));
        verify(essaySubmission).setSubmission(eq("Submission"));
        verify(essaySubmission).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission).setExerciseId(eq("42"));
        verify(essaySubmission).setReviewable(eq(true));
        verify(essaySubmission).setScore(isA(Float.class));
        verify(essaySubmission).setStudentId(eq("42"));
        verify(essaySubmission).setSubmissionId(eq("42"));
        verify(essaySubmission).setTeacherComment(eq("Teacher Comment"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#getEssaySubmission(String)}
     */
    @Test
    void testGetEssaySubmission() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        Optional<EssaySubmission> ofResult = Optional.of(essaySubmission);
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Exercise> ofResult2 = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act
        EssaySubmissionsResponse actualEssaySubmission = essaySubmissionImpl.getEssaySubmission("42");

        // Assert
        verify(userRepository).findUserByUserId(eq("42"));
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        User student = actualEssaySubmission.getStudent();
        LocalTime expectedToLocalTimeResult = student.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, student.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#getEssaySubmission(String)}
     */
    @Test
    void testGetEssaySubmission2() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        Optional<EssaySubmission> ofResult = Optional.of(essaySubmission);
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

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
        Optional<Exercise> ofResult2 = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.getEssaySubmission("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(essaySubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#getEssaySubmission(String)}
     */
    @Test
    void testGetEssaySubmission3() {
        // Arrange
        Optional<EssaySubmission> emptyResult = Optional.empty();
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

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
        Optional<Exercise> ofResult = Optional.of(exercise);
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.getEssaySubmission("42"));
        verify(essaySubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#getEssaySubmission(String)}
     */
    @Test
    void testGetEssaySubmission4() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        Optional<EssaySubmission> ofResult = Optional.of(essaySubmission);
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);
        Optional<Exercise> emptyResult = Optional.empty();
        when(exerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        User user = new User();
        user.setCourseStudents(new ArrayList<>());
        user.setCourseTeachers(new ArrayList<>());
        user.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setEmail("jane.doe@example.org");
        user.setGroupStudents(new ArrayList<>());
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRole("Role");
        user.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user.setUserId("42");
        user.setUsername("janedoe");
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.getEssaySubmission("42"));
        verify(userRepository).findUserByUserId(eq("42"));
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetEssaySubmissionByUserId() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<EssaySubmission> actualEssaySubmissionByUserId = essaySubmissionImpl.getEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertTrue(actualEssaySubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetEssaySubmissionByUserId2() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        List<EssaySubmission> actualEssaySubmissionByUserId = essaySubmissionImpl.getEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetEssaySubmissionByUserId3() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020/03/01");
        essaySubmission2.setDateSubmit("2020/03/01");
        essaySubmission2.setExerciseId("Exercise Id");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(0.5f);
        essaySubmission2.setStudentId("Student Id");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("Submission Id");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        List<EssaySubmission> actualEssaySubmissionByUserId = essaySubmissionImpl.getEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertEquals(1, actualEssaySubmissionByUserId.size());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetEssaySubmissionByUserId4() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.getEssaySubmissionByUserId("42", "42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetEssaySubmissionByUserId5() {
        // Arrange
        EssaySubmission essaySubmission = mock(EssaySubmission.class);
        when(essaySubmission.getStudentId()).thenReturn("foo");
        when(essaySubmission.getExerciseId()).thenReturn("42");
        doNothing().when(essaySubmission).setSubmission(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(essaySubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(essaySubmission).setReviewable(anyBoolean());
        doNothing().when(essaySubmission).setScore(Mockito.<Float>any());
        doNothing().when(essaySubmission).setStudentId(Mockito.<String>any());
        doNothing().when(essaySubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(essaySubmission).setTeacherComment(Mockito.<String>any());
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        List<EssaySubmission> actualEssaySubmissionByUserId = essaySubmissionImpl.getEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmission).setSubmission(eq("Submission"));
        verify(essaySubmission).getExerciseId();
        verify(essaySubmission).getStudentId();
        verify(essaySubmission).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission).setExerciseId(eq("42"));
        verify(essaySubmission).setReviewable(eq(true));
        verify(essaySubmission).setScore(isA(Float.class));
        verify(essaySubmission).setStudentId(eq("42"));
        verify(essaySubmission).setSubmissionId(eq("42"));
        verify(essaySubmission).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmissionRepository).findAll();
        assertTrue(actualEssaySubmissionByUserId.isEmpty());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        EssaySubmission actualLastEssaySubmissionByUserId = essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertNull(actualLastEssaySubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId2() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        EssaySubmission actualLastEssaySubmissionByUserId = essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertSame(essaySubmission, actualLastEssaySubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId3() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020/03/01");
        essaySubmission2.setDateSubmit("2020/03/01");
        essaySubmission2.setExerciseId("Exercise Id");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(0.5f);
        essaySubmission2.setStudentId("Student Id");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("Submission Id");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        EssaySubmission actualLastEssaySubmissionByUserId = essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmissionRepository).findAll();
        assertSame(essaySubmission, actualLastEssaySubmissionByUserId);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId4() {
        // Arrange
        when(essaySubmissionRepository.findAll()).thenThrow(new IllegalArgumentException("foo"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId5() {
        // Arrange
        EssaySubmission essaySubmission = mock(EssaySubmission.class);
        when(essaySubmission.getDateSubmit()).thenReturn("2020-03-01");
        when(essaySubmission.getStudentId()).thenReturn("42");
        when(essaySubmission.getExerciseId()).thenReturn("42");
        doNothing().when(essaySubmission).setSubmission(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(essaySubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(essaySubmission).setReviewable(anyBoolean());
        doNothing().when(essaySubmission).setScore(Mockito.<Float>any());
        doNothing().when(essaySubmission).setStudentId(Mockito.<String>any());
        doNothing().when(essaySubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(essaySubmission).setTeacherComment(Mockito.<String>any());
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020-03-01");
        essaySubmission2.setDateSubmit("2020-03-01");
        essaySubmission2.setExerciseId("42");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(10.0f);
        essaySubmission2.setStudentId("42");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("42");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmission).setSubmission(eq("Submission"));
        verify(essaySubmission).getDateSubmit();
        verify(essaySubmission).getExerciseId();
        verify(essaySubmission).getStudentId();
        verify(essaySubmission).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission).setExerciseId(eq("42"));
        verify(essaySubmission).setReviewable(eq(true));
        verify(essaySubmission).setScore(isA(Float.class));
        verify(essaySubmission).setStudentId(eq("42"));
        verify(essaySubmission).setSubmissionId(eq("42"));
        verify(essaySubmission).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getLastEssaySubmissionByUserId(String, String)}
     */
    @Test
    void testGetLastEssaySubmissionByUserId6() {
        // Arrange
        EssaySubmission essaySubmission = mock(EssaySubmission.class);
        when(essaySubmission.getStudentId()).thenReturn("42");
        when(essaySubmission.getExerciseId()).thenReturn("42");
        doNothing().when(essaySubmission).setSubmission(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateGrade(Mockito.<String>any());
        doNothing().when(essaySubmission).setDateSubmit(Mockito.<String>any());
        doNothing().when(essaySubmission).setExerciseId(Mockito.<String>any());
        doNothing().when(essaySubmission).setReviewable(anyBoolean());
        doNothing().when(essaySubmission).setScore(Mockito.<Float>any());
        doNothing().when(essaySubmission).setStudentId(Mockito.<String>any());
        doNothing().when(essaySubmission).setSubmissionId(Mockito.<String>any());
        doNothing().when(essaySubmission).setTeacherComment(Mockito.<String>any());
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        EssaySubmission essaySubmission2 = mock(EssaySubmission.class);
        when(essaySubmission2.getStudentId()).thenReturn("foo");
        when(essaySubmission2.getExerciseId()).thenReturn("42");
        doNothing().when(essaySubmission2).setSubmission(Mockito.<String>any());
        doNothing().when(essaySubmission2).setDateGrade(Mockito.<String>any());
        doNothing().when(essaySubmission2).setDateSubmit(Mockito.<String>any());
        doNothing().when(essaySubmission2).setExerciseId(Mockito.<String>any());
        doNothing().when(essaySubmission2).setReviewable(anyBoolean());
        doNothing().when(essaySubmission2).setScore(Mockito.<Float>any());
        doNothing().when(essaySubmission2).setStudentId(Mockito.<String>any());
        doNothing().when(essaySubmission2).setSubmissionId(Mockito.<String>any());
        doNothing().when(essaySubmission2).setTeacherComment(Mockito.<String>any());
        essaySubmission2.setDateGrade("2020-03-01");
        essaySubmission2.setDateSubmit("2020-03-01");
        essaySubmission2.setExerciseId("42");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(10.0f);
        essaySubmission2.setStudentId("42");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("42");
        essaySubmission2.setTeacherComment("Teacher Comment");

        ArrayList<EssaySubmission> essaySubmissionList = new ArrayList<>();
        essaySubmissionList.add(essaySubmission2);
        essaySubmissionList.add(essaySubmission);
        when(essaySubmissionRepository.findAll()).thenReturn(essaySubmissionList);

        // Act
        essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42");

        // Assert
        verify(essaySubmission2).setSubmission(eq("Submission"));
        verify(essaySubmission).setSubmission(eq("Submission"));
        verify(essaySubmission2).getExerciseId();
        verify(essaySubmission).getExerciseId();
        verify(essaySubmission2).getStudentId();
        verify(essaySubmission).getStudentId();
        verify(essaySubmission2).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission2).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission2).setExerciseId(eq("42"));
        verify(essaySubmission).setExerciseId(eq("42"));
        verify(essaySubmission2).setReviewable(eq(false));
        verify(essaySubmission).setReviewable(eq(true));
        verify(essaySubmission2).setScore(isA(Float.class));
        verify(essaySubmission).setScore(isA(Float.class));
        verify(essaySubmission2).setStudentId(eq("42"));
        verify(essaySubmission).setStudentId(eq("42"));
        verify(essaySubmission2).setSubmissionId(eq("42"));
        verify(essaySubmission).setSubmissionId(eq("42"));
        verify(essaySubmission2).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmission).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#gradeSubmission(String, float)}
     */
    @Test
    void testGradeSubmission() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        Optional<EssaySubmission> ofResult = Optional.of(essaySubmission);

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020-03-01");
        essaySubmission2.setDateSubmit("2020-03-01");
        essaySubmission2.setExerciseId("42");
        essaySubmission2.setReviewable(true);
        essaySubmission2.setScore(10.0f);
        essaySubmission2.setStudentId("42");
        essaySubmission2.setSubmission("Submission");
        essaySubmission2.setSubmissionId("42");
        essaySubmission2.setTeacherComment("Teacher Comment");
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenReturn(essaySubmission2);
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        EssaySubmission actualGradeSubmissionResult = essaySubmissionImpl.gradeSubmission("42", 10.0f);

        // Assert
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
        assertEquals(10.0f, actualGradeSubmissionResult.getScore().floatValue());
        assertSame(essaySubmission, actualGradeSubmissionResult);
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#gradeSubmission(String, float)}
     */
    @Test
    void testGradeSubmission2() {
        // Arrange
        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(10.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");
        Optional<EssaySubmission> ofResult = Optional.of(essaySubmission);
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenThrow(new IllegalArgumentException("foo"));
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.gradeSubmission("42", 10.0f));
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#gradeSubmission(String, float)}
     */
    @Test
    void testGradeSubmission3() {
        // Arrange
        Optional<EssaySubmission> emptyResult = Optional.empty();
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.gradeSubmission("42", 10.0f));
        verify(essaySubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link EssaySubmissionImpl#gradeSubmission(String, float)}
     */
    @Test
    void testGradeSubmission4() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.gradeSubmission("42", -1.0f));
    }
}
