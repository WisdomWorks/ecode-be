package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.EssaySubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenReturn(essaySubmission);

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
}
