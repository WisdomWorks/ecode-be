package com.example.codeE.service.exercise.submission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.helper.VertexAIHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.vertexAi.GradingResponse;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.CourseStudentRepository;
import com.example.codeE.repository.EssayExerciseRepository;
import com.example.codeE.repository.EssaySubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.repository.TopicRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

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
    @MockBean
    private CourseStudentRepository courseStudentRepository;

    @MockBean
    private EssayExerciseRepository essayExerciseRepository;

    @Autowired
    private EssaySubmissionImpl essaySubmissionImpl;

    @MockBean
    private EssaySubmissionRepository essaySubmissionRepository;

    @MockBean
    private ExerciseRepository exerciseRepository;

    @MockBean
    private GroupService groupService;

    @MockBean
    private GroupStudentRepository groupStudentRepository;

    @MockBean
    private TopicRepository topicRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private VertexAIHelper vertexAIHelper;

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission() throws JsonProcessingException {
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
        when(vertexAIHelper.parseJson(Mockito.<String>any())).thenReturn(new GradingResponse(10.0f, "Comment"));
        when(vertexAIHelper.generateContent(Mockito.<String>any())).thenReturn("Not all who wander are lost");

        // Act
        EssaySubmission actualCreateSubmissionResult = essaySubmissionImpl
                .createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission"));

        // Assert
        verify(vertexAIHelper).generateContent(eq(
                "You are roleplaying as an exceptional university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a particular assignment.\nAs a fair and unbiased grader, you will evaluate each submission based solely on the student's demonstrated factual knowledge and problem-solving skills, providing truthful explanations for your assessment. Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution.\nIn addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\nThe relevant assignment problem and the student's submitted solution will be provided in the following format:\n<PROBLEM>\n{Problem data}\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n{Student Submission}\n</STUDENT-SUBMISSION>\nAfter reviewing the problem and submission, output your evaluation using this JSON format, with no additional text besides the JSON:\n\n{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n\nHere is an example interaction using the specified format:\n<PROBLEM>\nWhat is 1 + 1?\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n</STUDENT-SUBMISSION>\noutput:\n\n{\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n\n\ninput: <PROBLEM>\nWhat is 1 + 1?\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n</STUDENT-SUBMISSION>\noutput: {\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n\ninput: <PROBLEM>\nQuestion\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\nSubmission\n</STUDENT-SUBMISSION>\noutput: {"));
        verify(vertexAIHelper).parseJson(eq("Not all who wander are lost"));
        verify(essayExerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
        assertSame(essaySubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission2() throws JsonProcessingException {
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
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenThrow(new RuntimeException(
                "You are roleplaying as an exceptional university professor teaching at FPT University, a renowned"
                        + " technology institution in Vietnam. It is currently the final examination period, and your task is to"
                        + " grade student submissions for a particular assignment.\n"
                        + "As a fair and unbiased grader, you will evaluate each submission based solely on the student's"
                        + " demonstrated factual knowledge and problem-solving skills, providing truthful explanations for your"
                        + " assessment. Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10"
                        + " representing a perfect solution.\n"
                        + "In addition to assigning a numeric score, please provide a clear, detailed comment explaining your"
                        + " reasoning behind the grade. The comment should offer a comprehensive rationale for the score,"
                        + " highlighting both strengths and weaknesses in the student's work as applicable.\n"
                        + "The relevant assignment problem and the student's submitted solution will be provided in the following"
                        + " format:\n" + "<PROBLEM>\n" + "{Problem data}\n" + "</PROBLEM>\n" + "\n" + "<STUDENT-SUBMISSION>\n"
                        + "{Student Submission}\n" + "</STUDENT-SUBMISSION>\n"
                        + "After reviewing the problem and submission, output your evaluation using this JSON format, with no"
                        + " additional text besides the JSON:\n" + "\n"
                        + "{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n" + "\n"
                        + "Here is an example interaction using the specified format:\n" + "<PROBLEM>\n" + "What is 1 + 1?\n"
                        + "</PROBLEM>\n" + "\n" + "<STUDENT-SUBMISSION>\n" + "</STUDENT-SUBMISSION>\n" + "output:\n" + "\n"
                        + "{\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a"
                        + " score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the"
                        + " simple addition problem presented.\"}\n" + "\n" + "\n" + "input: <PROBLEM>\n" + "What is 1 + 1?\n"
                        + "</PROBLEM>\n" + "\n" + "<STUDENT-SUBMISSION>\n" + "</STUDENT-SUBMISSION>\n"
                        + "output: {\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question,"
                        + " so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the"
                        + " simple addition problem presented.\"}\n" + "\n" + "input: <PROBLEM>\n" + "%s\n" + "</PROBLEM>\n" + "\n"
                        + "<STUDENT-SUBMISSION>\n" + "%s\n" + "</STUDENT-SUBMISSION>\n" + "output: {"));

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
        when(vertexAIHelper.parseJson(Mockito.<String>any())).thenReturn(new GradingResponse(10.0f, "Comment"));
        when(vertexAIHelper.generateContent(Mockito.<String>any())).thenReturn("Not all who wander are lost");

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(vertexAIHelper).generateContent(eq(
                "You are roleplaying as an exceptional university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a particular assignment.\nAs a fair and unbiased grader, you will evaluate each submission based solely on the student's demonstrated factual knowledge and problem-solving skills, providing truthful explanations for your assessment. Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution.\nIn addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\nThe relevant assignment problem and the student's submitted solution will be provided in the following format:\n<PROBLEM>\n{Problem data}\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n{Student Submission}\n</STUDENT-SUBMISSION>\nAfter reviewing the problem and submission, output your evaluation using this JSON format, with no additional text besides the JSON:\n\n{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n\nHere is an example interaction using the specified format:\n<PROBLEM>\nWhat is 1 + 1?\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n</STUDENT-SUBMISSION>\noutput:\n\n{\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n\n\ninput: <PROBLEM>\nWhat is 1 + 1?\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\n</STUDENT-SUBMISSION>\noutput: {\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n\ninput: <PROBLEM>\nQuestion\n</PROBLEM>\n\n<STUDENT-SUBMISSION>\nSubmission\n</STUDENT-SUBMISSION>\noutput: {"));
        verify(vertexAIHelper).parseJson(eq("Not all who wander are lost"));
        verify(essayExerciseRepository).findById(eq("42"));
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
        EssayExercise essayExercise = mock(EssayExercise.class);
        when(essayExercise.getQuestion()).thenThrow(new RuntimeException("foo"));
        when(essayExercise.isUsingAiGrading()).thenReturn(true);
        doNothing().when(essayExercise).setQuestion(Mockito.<String>any());
        doNothing().when(essayExercise).setUsingAiGrading(anyBoolean());
        doNothing().when(essayExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(essayExercise).setDurationTime(anyInt());
        doNothing().when(essayExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(essayExercise).setKey(Mockito.<String>any());
        doNothing().when(essayExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(essayExercise).setReAttempt(anyInt());
        doNothing().when(essayExercise).setShowAll(anyBoolean());
        doNothing().when(essayExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setTopicId(Mockito.<String>any());
        doNothing().when(essayExercise).setType(Mockito.<String>any());
        doNothing().when(essayExercise).setUpdatedDate(Mockito.<String>any());
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
        assertThrows(RuntimeException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(essayExercise).getQuestion();
        verify(essayExercise).isUsingAiGrading();
        verify(essayExercise).setQuestion(eq("Question"));
        verify(essayExercise).setUsingAiGrading(eq(true));
        verify(essayExercise).setCreatedDate(eq("2020-03-01"));
        verify(essayExercise).setDurationTime(eq(1));
        verify(essayExercise).setEndTime(isA(Date.class));
        verify(essayExercise).setExerciseDescription(eq("Exercise Description"));
        verify(essayExercise).setExerciseId(eq("42"));
        verify(essayExercise).setExerciseName(eq("Exercise Name"));
        verify(essayExercise).setKey(eq("Key"));
        verify(essayExercise).setPublicGroupIds(isA(List.class));
        verify(essayExercise).setReAttempt(eq(1));
        verify(essayExercise).setShowAll(eq(true));
        verify(essayExercise).setStartTime(isA(Date.class));
        verify(essayExercise).setTopicId(eq("42"));
        verify(essayExercise).setType(eq("Type"));
        verify(essayExercise).setUpdatedDate(eq("2020-03-01"));
        verify(essayExerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission4() {
        // Arrange
        EssayExercise essayExercise = mock(EssayExercise.class);
        when(essayExercise.isUsingAiGrading()).thenReturn(false);
        doNothing().when(essayExercise).setQuestion(Mockito.<String>any());
        doNothing().when(essayExercise).setUsingAiGrading(anyBoolean());
        doNothing().when(essayExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(essayExercise).setDurationTime(anyInt());
        doNothing().when(essayExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(essayExercise).setKey(Mockito.<String>any());
        doNothing().when(essayExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(essayExercise).setReAttempt(anyInt());
        doNothing().when(essayExercise).setShowAll(anyBoolean());
        doNothing().when(essayExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setTopicId(Mockito.<String>any());
        doNothing().when(essayExercise).setType(Mockito.<String>any());
        doNothing().when(essayExercise).setUpdatedDate(Mockito.<String>any());
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
        verify(essayExercise).isUsingAiGrading();
        verify(essayExercise).setQuestion(eq("Question"));
        verify(essayExercise).setUsingAiGrading(eq(true));
        verify(essayExercise).setCreatedDate(eq("2020-03-01"));
        verify(essayExercise).setDurationTime(eq(1));
        verify(essayExercise).setEndTime(isA(Date.class));
        verify(essayExercise).setExerciseDescription(eq("Exercise Description"));
        verify(essayExercise).setExerciseId(eq("42"));
        verify(essayExercise).setExerciseName(eq("Exercise Name"));
        verify(essayExercise).setKey(eq("Key"));
        verify(essayExercise).setPublicGroupIds(isA(List.class));
        verify(essayExercise).setReAttempt(eq(1));
        verify(essayExercise).setShowAll(eq(true));
        verify(essayExercise).setStartTime(isA(Date.class));
        verify(essayExercise).setTopicId(eq("42"));
        verify(essayExercise).setType(eq("Type"));
        verify(essayExercise).setUpdatedDate(eq("2020-03-01"));
        verify(essayExerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
        assertSame(essaySubmission, actualCreateSubmissionResult);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission5() {
        // Arrange
        Optional<EssayExercise> emptyResult = Optional.empty();
        when(essayExerciseRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);
        new RuntimeException("foo");

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
        verify(essayExerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#createSubmission(CreateEssaySubmissionRequest)}
     */
    @Test
    void testCreateSubmission6() {
        // Arrange
        EssayExercise essayExercise = mock(EssayExercise.class);
        doNothing().when(essayExercise).setQuestion(Mockito.<String>any());
        doNothing().when(essayExercise).setUsingAiGrading(anyBoolean());
        doNothing().when(essayExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(essayExercise).setDurationTime(anyInt());
        doNothing().when(essayExercise).setEndTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setExerciseDescription(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseId(Mockito.<String>any());
        doNothing().when(essayExercise).setExerciseName(Mockito.<String>any());
        doNothing().when(essayExercise).setKey(Mockito.<String>any());
        doNothing().when(essayExercise).setPublicGroupIds(Mockito.<List<String>>any());
        doNothing().when(essayExercise).setReAttempt(anyInt());
        doNothing().when(essayExercise).setShowAll(anyBoolean());
        doNothing().when(essayExercise).setStartTime(Mockito.<Date>any());
        doNothing().when(essayExercise).setTopicId(Mockito.<String>any());
        doNothing().when(essayExercise).setType(Mockito.<String>any());
        doNothing().when(essayExercise).setUpdatedDate(Mockito.<String>any());
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
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class,
                () -> essaySubmissionImpl.createSubmission(new CreateEssaySubmissionRequest("42", "42", "Submission")));
        verify(essayExercise).setQuestion(eq("Question"));
        verify(essayExercise).setUsingAiGrading(eq(true));
        verify(essayExercise).setCreatedDate(eq("2020-03-01"));
        verify(essayExercise).setDurationTime(eq(1));
        verify(essayExercise).setEndTime(isA(Date.class));
        verify(essayExercise).setExerciseDescription(eq("Exercise Description"));
        verify(essayExercise).setExerciseId(eq("42"));
        verify(essayExercise).setExerciseName(eq("Exercise Name"));
        verify(essayExercise).setKey(eq("Key"));
        verify(essayExercise).setPublicGroupIds(isA(List.class));
        verify(essayExercise).setReAttempt(eq(1));
        verify(essayExercise).setShowAll(eq(true));
        verify(essayExercise).setStartTime(isA(Date.class));
        verify(essayExercise).setTopicId(eq("42"));
        verify(essayExercise).setType(eq("Type"));
        verify(essayExercise).setUpdatedDate(eq("2020-03-01"));
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
        when(essaySubmissionRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getEssaySubmissionByExerciseId("42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId() {
        // Arrange
        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);
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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        AllSubmissionResponse<SubmissionDetail> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals("42", actualEssaySubmissionsByExerciseId.getExerciseId());
        assertNull(actualEssaySubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualEssaySubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, actualEssaySubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));
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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getEssaySubmissionsByExerciseId("42"));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId3() {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Course Name");
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        course.setCourseStudents(courseStudents);
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Enroll Key");
        course.setGroups(new ArrayList<>());
        course.setSemester("Semester");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

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

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);
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

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        AllSubmissionResponse<SubmissionDetail> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository, atLeast(1)).findAll();
        assertEquals("42", actualEssaySubmissionsByExerciseId.getExerciseId());
        assertNull(actualEssaySubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualEssaySubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(courseStudents, actualEssaySubmissionsByExerciseId.getSubmissions());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId4() {
        // Arrange
        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        AllSubmissionResponse<SubmissionDetail> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals("42", actualEssaySubmissionsByExerciseId.getExerciseId());
        List<SubmissionDetail> submissions = actualEssaySubmissionsByExerciseId.getSubmissions();
        assertEquals(1, submissions.size());
        SubmissionDetail getResult = submissions.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals("Name", getResult.getUserId());
        assertEquals("janedoe", getResult.getUserName());
        assertNull(actualEssaySubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualEssaySubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, getResult.getGroups());
        assertSame(essaySubmission, getResult.getSubmission());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId5() {
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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenThrow(new RuntimeException("42"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getEssaySubmissionsByExerciseId("42"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId6() {
        // Arrange
        Course course = new Course();
        course.setCourseId("");
        course.setCourseName("");
        ArrayList<CourseStudent> courseStudents = new ArrayList<>();
        course.setCourseStudents(courseStudents);
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("Description");
        course.setEnrollKey("");
        course.setGroups(new ArrayList<>());
        course.setSemester("");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("prof.einstein@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("");
        student.setPassword("Password");
        student.setRole("");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("");
        student.setUsername("Username");

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("");

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        AllSubmissionResponse<SubmissionDetail> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository, atLeast(1)).findAll();
        assertEquals("42", actualEssaySubmissionsByExerciseId.getExerciseId());
        List<SubmissionDetail> submissions = actualEssaySubmissionsByExerciseId.getSubmissions();
        assertEquals(1, submissions.size());
        SubmissionDetail getResult = submissions.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals("Name", getResult.getUserId());
        assertEquals("janedoe", getResult.getUserName());
        assertNull(actualEssaySubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualEssaySubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(1, report.getNumberStudent());
        assertEquals(courseStudents, getResult.getGroups());
        assertSame(essaySubmission, getResult.getSubmission());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId7() {
        // Arrange
        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

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
        Optional<User> ofResult3 = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult3);
        when(userService.getAllGroupsByUserId(Mockito.<String>any())).thenReturn(new ArrayList<>());

        // Act
        AllSubmissionResponse<SubmissionDetail> actualEssaySubmissionsByExerciseId = essaySubmissionImpl
                .getEssaySubmissionsByExerciseId("42");

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(userService).getAllGroupsByUserId(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(userRepository).findById(eq("42"));
        verify(exerciseRepository, atLeast(1)).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals("42", actualEssaySubmissionsByExerciseId.getExerciseId());
        List<SubmissionDetail> submissions = actualEssaySubmissionsByExerciseId.getSubmissions();
        assertEquals(1, submissions.size());
        SubmissionDetail getResult = submissions.get(0);
        assertEquals("Name", getResult.getName());
        assertEquals("Name", getResult.getUserId());
        assertEquals("janedoe", getResult.getUserName());
        assertNull(actualEssaySubmissionsByExerciseId.getGroups());
        OverviewScoreReport report = actualEssaySubmissionsByExerciseId.getReport();
        assertEquals(0, report.getAScore());
        assertEquals(0, report.getBScore());
        assertEquals(0, report.getCScore());
        assertEquals(0, report.getNumberStudent());
        assertEquals(0, report.getNumberSubmission());
        assertEquals(courseStudentList, getResult.getGroups());
        assertSame(essaySubmission, getResult.getSubmission());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getEssaySubmissionsByExerciseId(String)}
     */
    @Test
    void testGetEssaySubmissionsByExerciseId8() {
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
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.getEssaySubmissionsByExerciseId("42"));
        verify(essaySubmission).setSubmission(eq("Submission"));
        verify(essaySubmission).setDateGrade(eq("2020-03-01"));
        verify(essaySubmission).setDateSubmit(eq("2020-03-01"));
        verify(essaySubmission).setExerciseId(eq("42"));
        verify(essaySubmission).setReviewable(eq(true));
        verify(essaySubmission).setScore(eq(10.0f));
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getEssaySubmission("42"));
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
        when(essaySubmissionRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getEssaySubmissionByUserId("42", "42"));
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
        verify(essaySubmission).setScore(eq(10.0f));
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
        when(essaySubmissionRepository.findAll()).thenThrow(new RuntimeException("foo"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.getLastEssaySubmissionByUserId("42", "42"));
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
        verify(essaySubmission).setScore(eq(10.0f));
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
        verify(essaySubmission2).setScore(eq(10.0f));
        verify(essaySubmission).setScore(eq(10.0f));
        verify(essaySubmission2).setStudentId(eq("42"));
        verify(essaySubmission).setStudentId(eq("42"));
        verify(essaySubmission2).setSubmissionId(eq("42"));
        verify(essaySubmission).setSubmissionId(eq("42"));
        verify(essaySubmission2).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmission).setTeacherComment(eq("Teacher Comment"));
        verify(essaySubmissionRepository).findAll();
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#gradeSubmission(String, float, String)}
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
        EssaySubmission actualGradeSubmissionResult = essaySubmissionImpl.gradeSubmission("42", 10.0f, "Comment");

        // Assert
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
        assertEquals("Comment", actualGradeSubmissionResult.getTeacherComment());
        assertEquals(10.0f, actualGradeSubmissionResult.getScore().floatValue());
        assertSame(essaySubmission, actualGradeSubmissionResult);
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#gradeSubmission(String, float, String)}
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
        when(essaySubmissionRepository.save(Mockito.<EssaySubmission>any())).thenThrow(new RuntimeException("foo"));
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> essaySubmissionImpl.gradeSubmission("42", 10.0f, "Comment"));
        verify(essaySubmissionRepository).findById(eq("42"));
        verify(essaySubmissionRepository).save(isA(EssaySubmission.class));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission3() {
        // Arrange
        Optional<EssaySubmission> emptyResult = Optional.empty();
        when(essaySubmissionRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> essaySubmissionImpl.gradeSubmission("42", 10.0f, "Comment"));
        verify(essaySubmissionRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#gradeSubmission(String, float, String)}
     */
    @Test
    void testGradeSubmission4() {
        // Arrange, Act and Assert
        assertThrows(IllegalArgumentException.class, () -> essaySubmissionImpl.gradeSubmission("42", -1.0f, "Comment"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(new ArrayList<>());

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = essaySubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberStudent());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId2() {
        // Arrange
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenThrow(new RuntimeException("foo"));

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
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act and Assert
        assertThrows(RuntimeException.class,
                () -> essaySubmissionImpl.getOverviewScoreReportByExerciseId("42", new ArrayList<>()));
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId3() {
        // Arrange
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

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);
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

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = essaySubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId4() {
        // Arrange
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

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(-1.0f);
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

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = essaySubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }

    /**
     * Method under test:
     * {@link EssaySubmissionImpl#getOverviewScoreReportByExerciseId(String, List)}
     */
    @Test
    void testGetOverviewScoreReportByExerciseId5() {
        // Arrange
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

        CourseStudent courseStudent = new CourseStudent();
        courseStudent.setCourse(course);
        courseStudent.setCourseId("42");
        courseStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        courseStudent.setStudent(student);
        courseStudent.setStudentId("42");

        ArrayList<CourseStudent> courseStudentList = new ArrayList<>();
        courseStudentList.add(courseStudent);
        when(courseStudentRepository.getAllStudentsInCourse(Mockito.<String>any())).thenReturn(courseStudentList);

        EssaySubmission essaySubmission = new EssaySubmission();
        essaySubmission.setDateGrade("2020-03-01");
        essaySubmission.setDateSubmit("2020-03-01");
        essaySubmission.setExerciseId("42");
        essaySubmission.setReviewable(true);
        essaySubmission.setScore(-1.0f);
        essaySubmission.setStudentId("42");
        essaySubmission.setSubmission("Submission");
        essaySubmission.setSubmissionId("42");
        essaySubmission.setTeacherComment("Teacher Comment");

        EssaySubmission essaySubmission2 = new EssaySubmission();
        essaySubmission2.setDateGrade("2020/03/01");
        essaySubmission2.setDateSubmit("2020/03/01");
        essaySubmission2.setExerciseId("Exercise Id");
        essaySubmission2.setReviewable(false);
        essaySubmission2.setScore(10.0f);
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

        Course course2 = new Course();
        course2.setCourseId("42");
        course2.setCourseName("Course Name");
        course2.setCourseStudents(new ArrayList<>());
        course2.setCourseTeachers(new ArrayList<>());
        course2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course2.setDescription("The characteristics of someone or something");
        course2.setEnrollKey("Enroll Key");
        course2.setGroups(new ArrayList<>());
        course2.setSemester("Semester");
        course2.setTopics(new ArrayList<>());
        course2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Topic topic = new Topic();
        topic.setCourse(course2);
        topic.setCourseId("42");
        topic.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setDescription("The characteristics of someone or something");
        topic.setMaterials(new ArrayList<>());
        topic.setShowAll(true);
        topic.setTopicId("42");
        topic.setTopicName("Topic Name");
        topic.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        topic.setViewPermissionTopics(new ArrayList<>());
        Optional<Topic> ofResult2 = Optional.of(topic);
        when(topicRepository.findById(Mockito.<String>any())).thenReturn(ofResult2);

        // Act
        OverviewScoreReport actualOverviewScoreReportByExerciseId = essaySubmissionImpl
                .getOverviewScoreReportByExerciseId("42", new ArrayList<>());

        // Assert
        verify(courseStudentRepository).getAllStudentsInCourse(eq("42"));
        verify(exerciseRepository).findById(eq("42"));
        verify(topicRepository).findById(eq("42"));
        verify(essaySubmissionRepository).findAll();
        assertEquals(0, actualOverviewScoreReportByExerciseId.getAScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getBScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getCScore());
        assertEquals(0, actualOverviewScoreReportByExerciseId.getNumberSubmission());
        assertEquals(1, actualOverviewScoreReportByExerciseId.getNumberStudent());
    }
}
