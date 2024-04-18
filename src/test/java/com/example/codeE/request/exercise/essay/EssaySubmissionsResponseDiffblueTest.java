package com.example.codeE.request.exercise.essay;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

class EssaySubmissionsResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link EssaySubmissionsResponse#EssaySubmissionsResponse()}
     *   <li>{@link EssaySubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link EssaySubmissionsResponse#setStudent(User)}
     *   <li>{@link EssaySubmissionsResponse#setSubmissions(EssaySubmission)}
     *   <li>{@link EssaySubmissionsResponse#getExercise()}
     *   <li>{@link EssaySubmissionsResponse#getStudent()}
     *   <li>{@link EssaySubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        EssaySubmissionsResponse actualEssaySubmissionsResponse = new EssaySubmissionsResponse();
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
        actualEssaySubmissionsResponse.setExercise(exercise);
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
        actualEssaySubmissionsResponse.setStudent(student);
        EssaySubmission submissions = new EssaySubmission();
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setExerciseId("42");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setStudentId("42");
        submissions.setSubmission("Submission");
        submissions.setSubmissionId("42");
        submissions.setTeacherComment("Teacher Comment");
        actualEssaySubmissionsResponse.setSubmissions(submissions);
        Exercise actualExercise = actualEssaySubmissionsResponse.getExercise();
        User actualStudent = actualEssaySubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertSame(submissions, actualEssaySubmissionsResponse.getSubmissions());
        assertSame(exercise, actualExercise);
        assertSame(student, actualStudent);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link EssaySubmissionsResponse#EssaySubmissionsResponse(EssaySubmission, User, Exercise)}
     *   <li>{@link EssaySubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link EssaySubmissionsResponse#setStudent(User)}
     *   <li>{@link EssaySubmissionsResponse#setSubmissions(EssaySubmission)}
     *   <li>{@link EssaySubmissionsResponse#getExercise()}
     *   <li>{@link EssaySubmissionsResponse#getStudent()}
     *   <li>{@link EssaySubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        EssaySubmission submissions = new EssaySubmission();
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setExerciseId("42");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setStudentId("42");
        submissions.setSubmission("Submission");
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
        EssaySubmissionsResponse actualEssaySubmissionsResponse = new EssaySubmissionsResponse(submissions, student,
                exercise);
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
        actualEssaySubmissionsResponse.setExercise(exercise2);
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
        actualEssaySubmissionsResponse.setStudent(student2);
        EssaySubmission submissions2 = new EssaySubmission();
        submissions2.setDateGrade("2020-03-01");
        submissions2.setDateSubmit("2020-03-01");
        submissions2.setExerciseId("42");
        submissions2.setReviewable(true);
        submissions2.setScore(10.0f);
        submissions2.setStudentId("42");
        submissions2.setSubmission("Submission");
        submissions2.setSubmissionId("42");
        submissions2.setTeacherComment("Teacher Comment");
        actualEssaySubmissionsResponse.setSubmissions(submissions2);
        Exercise actualExercise = actualEssaySubmissionsResponse.getExercise();
        User actualStudent = actualEssaySubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertSame(submissions2, actualEssaySubmissionsResponse.getSubmissions());
        assertSame(exercise2, actualExercise);
        assertSame(student2, actualStudent);
    }
}
