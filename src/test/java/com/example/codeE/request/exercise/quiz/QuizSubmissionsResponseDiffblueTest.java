package com.example.codeE.request.exercise.quiz;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.user.User;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

class QuizSubmissionsResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link QuizSubmissionsResponse#QuizSubmissionsResponse()}
     *   <li>{@link QuizSubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link QuizSubmissionsResponse#setStudent(User)}
     *   <li>{@link QuizSubmissionsResponse#setSubmissions(QuizSubmission)}
     *   <li>{@link QuizSubmissionsResponse#getExercise()}
     *   <li>{@link QuizSubmissionsResponse#getStudent()}
     *   <li>{@link QuizSubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        QuizSubmissionsResponse actualQuizSubmissionsResponse = new QuizSubmissionsResponse();
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
        actualQuizSubmissionsResponse.setExercise(exercise);
        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");
        actualQuizSubmissionsResponse.setStudent(student);
        QuizSubmission submissions = new QuizSubmission();
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setExerciseId("42");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setStudentId("42");
        submissions.setSubmission(new ArrayList<>());
        submissions.setSubmissionId("42");
        submissions.setTeacherComment("Teacher Comment");
        actualQuizSubmissionsResponse.setSubmissions(submissions);
        Exercise actualExercise = actualQuizSubmissionsResponse.getExercise();
        User actualStudent = actualQuizSubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertSame(exercise, actualExercise);
        assertSame(submissions, actualQuizSubmissionsResponse.getSubmissions());
        assertSame(student, actualStudent);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link QuizSubmissionsResponse#QuizSubmissionsResponse(QuizSubmission, User, Exercise)}
     *   <li>{@link QuizSubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link QuizSubmissionsResponse#setStudent(User)}
     *   <li>{@link QuizSubmissionsResponse#setSubmissions(QuizSubmission)}
     *   <li>{@link QuizSubmissionsResponse#getExercise()}
     *   <li>{@link QuizSubmissionsResponse#getStudent()}
     *   <li>{@link QuizSubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        QuizSubmission submissions = new QuizSubmission();
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setExerciseId("42");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setStudentId("42");
        submissions.setSubmission(new ArrayList<>());
        submissions.setSubmissionId("42");
        submissions.setTeacherComment("Teacher Comment");

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Name");
        student.setPassword("iloveyou");
        student.setRole("Role");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

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

        // Act
        QuizSubmissionsResponse actualQuizSubmissionsResponse = new QuizSubmissionsResponse(submissions, student, exercise);
        Exercise exercise2 = new Exercise();
        exercise2.setCreatedDate("2020-03-01");
        exercise2.setDurationTime(1);
        exercise2.setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        exercise2.setExerciseDescription("Exercise Description");
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
        actualQuizSubmissionsResponse.setExercise(exercise2);
        User student2 = new User();
        student2.setCourseStudents(new ArrayList<>());
        student2.setCourseTeachers(new ArrayList<>());
        student2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student2.setEmail("jane.doe@example.org");
        student2.setGroupStudents(new ArrayList<>());
        student2.setName("Name");
        student2.setPassword("iloveyou");
        student2.setRole("Role");
        student2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student2.setUserId("42");
        student2.setUsername("janedoe");
        actualQuizSubmissionsResponse.setStudent(student2);
        QuizSubmission submissions2 = new QuizSubmission();
        submissions2.setDateGrade("2020-03-01");
        submissions2.setDateSubmit("2020-03-01");
        submissions2.setExerciseId("42");
        submissions2.setReviewable(true);
        submissions2.setScore(10.0f);
        submissions2.setStudentId("42");
        submissions2.setSubmission(new ArrayList<>());
        submissions2.setSubmissionId("42");
        submissions2.setTeacherComment("Teacher Comment");
        actualQuizSubmissionsResponse.setSubmissions(submissions2);
        Exercise actualExercise = actualQuizSubmissionsResponse.getExercise();
        User actualStudent = actualQuizSubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertSame(exercise2, actualExercise);
        assertSame(submissions2, actualQuizSubmissionsResponse.getSubmissions());
        assertSame(student2, actualStudent);
    }
}
