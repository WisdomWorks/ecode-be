package com.example.codeE.request.exercise.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.common.SubmissionTestCase;
import com.example.codeE.model.user.User;
import com.example.codeE.service.judge.JudgeImpl;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class CodeSubmissionsResponseDiffblueTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link CodeSubmissionsResponse#CodeSubmissionsResponse()}
     *   <li>{@link CodeSubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link CodeSubmissionsResponse#setResultTestcases(List)}
     *   <li>{@link CodeSubmissionsResponse#setStudent(User)}
     *   <li>{@link CodeSubmissionsResponse#setSubmissions(CodeSubmission)}
     *   <li>{@link CodeSubmissionsResponse#getExercise()}
     *   <li>{@link CodeSubmissionsResponse#getResultTestcases()}
     *   <li>{@link CodeSubmissionsResponse#getStudent()}
     *   <li>{@link CodeSubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        CodeSubmissionsResponse actualCodeSubmissionsResponse = new CodeSubmissionsResponse();
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
        actualCodeSubmissionsResponse.setExercise(exercise);
        ArrayList<SubmissionTestCase> resultTestcases = new ArrayList<>();
        actualCodeSubmissionsResponse.setResultTestcases(resultTestcases);
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
        actualCodeSubmissionsResponse.setStudent(student);
        CodeSubmission submissions = new CodeSubmission();
        submissions.setCasePoints(10.0d);
        submissions.setCaseTotal(10.0d);
        submissions.setCurrentTestcase(1);
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setError("An error occurred");
        submissions.setExerciseId("42");
        submissions.setJudgeService(new JudgeImpl());
        submissions.setJudgedOn("Judged On");
        submissions.setLanguageId("en");
        submissions.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        submissions.setMemory(1);
        submissions.setPretested(true);
        submissions.setResult("Result");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setSource("Source");
        submissions.setStatus("Status");
        submissions.setStudentId("42");
        submissions.setSubmissionId("42");
        submissions.setTeacherComment("Teacher Comment");
        submissions.setTime(10.0d);
        actualCodeSubmissionsResponse.setSubmissions(submissions);
        Exercise actualExercise = actualCodeSubmissionsResponse.getExercise();
        List<SubmissionTestCase> actualResultTestcases = actualCodeSubmissionsResponse.getResultTestcases();
        User actualStudent = actualCodeSubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertSame(submissions, actualCodeSubmissionsResponse.getSubmissions());
        assertSame(exercise, actualExercise);
        assertSame(student, actualStudent);
        assertSame(resultTestcases, actualResultTestcases);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>
     * {@link CodeSubmissionsResponse#CodeSubmissionsResponse(CodeSubmission, User, Exercise, List)}
     *   <li>{@link CodeSubmissionsResponse#setExercise(Exercise)}
     *   <li>{@link CodeSubmissionsResponse#setResultTestcases(List)}
     *   <li>{@link CodeSubmissionsResponse#setStudent(User)}
     *   <li>{@link CodeSubmissionsResponse#setSubmissions(CodeSubmission)}
     *   <li>{@link CodeSubmissionsResponse#getExercise()}
     *   <li>{@link CodeSubmissionsResponse#getResultTestcases()}
     *   <li>{@link CodeSubmissionsResponse#getStudent()}
     *   <li>{@link CodeSubmissionsResponse#getSubmissions()}
     * </ul>
     */
    @Test
    void testGettersAndSetters2() {
        // Arrange
        CodeSubmission submissions = new CodeSubmission();
        submissions.setCasePoints(10.0d);
        submissions.setCaseTotal(10.0d);
        submissions.setCurrentTestcase(1);
        submissions.setDateGrade("2020-03-01");
        submissions.setDateSubmit("2020-03-01");
        submissions.setError("An error occurred");
        submissions.setExerciseId("42");
        submissions.setJudgeService(new JudgeImpl());
        submissions.setJudgedOn("Judged On");
        submissions.setLanguageId("en");
        submissions.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        submissions.setMemory(1);
        submissions.setPretested(true);
        submissions.setResult("Result");
        submissions.setReviewable(true);
        submissions.setScore(10.0f);
        submissions.setSource("Source");
        submissions.setStatus("Status");
        submissions.setStudentId("42");
        submissions.setSubmissionId("42");
        submissions.setTeacherComment("Teacher Comment");
        submissions.setTime(10.0d);

        User student = new User();
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        student.setCourseStudents(courseStudents);
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
        CodeSubmissionsResponse actualCodeSubmissionsResponse = new CodeSubmissionsResponse(submissions, student, exercise,
                new ArrayList<>());
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
        actualCodeSubmissionsResponse.setExercise(exercise2);
        ArrayList<SubmissionTestCase> resultTestcases = new ArrayList<>();
        actualCodeSubmissionsResponse.setResultTestcases(resultTestcases);
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
        actualCodeSubmissionsResponse.setStudent(student2);
        CodeSubmission submissions2 = new CodeSubmission();
        submissions2.setCasePoints(10.0d);
        submissions2.setCaseTotal(10.0d);
        submissions2.setCurrentTestcase(1);
        submissions2.setDateGrade("2020-03-01");
        submissions2.setDateSubmit("2020-03-01");
        submissions2.setError("An error occurred");
        submissions2.setExerciseId("42");
        submissions2.setJudgeService(new JudgeImpl());
        submissions2.setJudgedOn("Judged On");
        submissions2.setLanguageId("en");
        submissions2.setLockedAfter(LocalDate.of(1970, 1, 1).atStartOfDay());
        submissions2.setMemory(1);
        submissions2.setPretested(true);
        submissions2.setResult("Result");
        submissions2.setReviewable(true);
        submissions2.setScore(10.0f);
        submissions2.setSource("Source");
        submissions2.setStatus("Status");
        submissions2.setStudentId("42");
        submissions2.setSubmissionId("42");
        submissions2.setTeacherComment("Teacher Comment");
        submissions2.setTime(10.0d);
        actualCodeSubmissionsResponse.setSubmissions(submissions2);
        Exercise actualExercise = actualCodeSubmissionsResponse.getExercise();
        List<SubmissionTestCase> actualResultTestcases = actualCodeSubmissionsResponse.getResultTestcases();
        User actualStudent = actualCodeSubmissionsResponse.getStudent();

        // Assert that nothing has changed
        assertEquals(courseStudents, actualResultTestcases);
        assertSame(submissions2, actualCodeSubmissionsResponse.getSubmissions());
        assertSame(exercise2, actualExercise);
        assertSame(student2, actualStudent);
        assertSame(resultTestcases, actualResultTestcases);
    }
}
