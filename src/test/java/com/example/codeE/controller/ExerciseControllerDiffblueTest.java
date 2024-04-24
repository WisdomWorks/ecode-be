package com.example.codeE.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.GetDetailExerciseRequest;
import com.example.codeE.request.exercise.code.CodeRunRequest;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.request.exercise.code.SubmitCodeExerciseRequest;
import com.example.codeE.request.exercise.code.UpdateCodeExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import com.example.codeE.request.exercise.file.CreateFileExerciseRequest;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.UpdateFileExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizSubmissionRequest;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.EssayExerciseService;
import com.example.codeE.service.exercise.ExerciseService;
import com.example.codeE.service.exercise.FileExerciseService;
import com.example.codeE.service.exercise.QuizExerciseService;
import com.example.codeE.service.exercise.QuizSubmissionService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.problem.CodeExerciseTestcaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import com.example.codeE.service.exercise.submission.FileSubmissionService;
import com.example.codeE.service.judge.JudgeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {ExerciseController.class, MongoTemplate.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ExerciseControllerDiffblueTest {
    @MockBean
    private CodeExerciseService codeExerciseService;

    @MockBean
    private CodeExerciseTestcaseService codeExerciseTestcaseService;

    @MockBean
    private CodeSubmissionService codeSubmissionService;

    @MockBean
    private EssayExerciseService essayExerciseService;

    @MockBean
    private EssaySubmissionService essaySubmissionService;

    @Autowired
    private ExerciseController exerciseController;

    @MockBean
    private ExerciseService exerciseService;

    @MockBean
    private FileExerciseService fileExerciseService;

    @MockBean
    private FileSubmissionService fileSubmissionService;

    @MockBean
    private JudgeService judgeService;

    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private QuizExerciseService quizExerciseService;

    @MockBean
    private QuizSubmissionService quizSubmissionService;

    @MockBean
    private SubmissionTestCaseService submissionTestCaseService;

    /**
     * Method under test:
     * {@link ExerciseController#submitFileExercise(CreateFileSubmissionRequest, MultipartFile)}
     */
    @Test
    void testSubmitFileExercise() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ExerciseController exerciseController = new ExerciseController();

        // Act
        ResponseEntity<?> actualSubmitFileExerciseResult = exerciseController
                .submitFileExercise(new CreateFileSubmissionRequest("42", "42", "https://example.org/example"), null);

        // Assert
        assertEquals(400, actualSubmitFileExerciseResult.getStatusCodeValue());
        assertTrue(actualSubmitFileExerciseResult.hasBody());
        assertTrue(actualSubmitFileExerciseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#addPermissionExercise(CreatePermissionExerciseRequest)}
     */
    @Test
    void testAddPermissionExercise() throws Exception {
        // Arrange
        when(exerciseService.modifiedPermission(Mockito.<CreatePermissionExerciseRequest>any()))
                .thenReturn(new ExerciseResponse());

        CreatePermissionExerciseRequest createPermissionExerciseRequest = new CreatePermissionExerciseRequest();
        createPermissionExerciseRequest.setExerciseId("42");
        createPermissionExerciseRequest.setGroupIds(new ArrayList<>());
        createPermissionExerciseRequest.setShowAll(true);
        String content = (new ObjectMapper()).writeValueAsString(createPermissionExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/view")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(exerciseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"exerciseId\":null,\"topicId\":null,\"exerciseName\":null,\"createdDate\":null,\"updatedDate\":null,\"startTime"
                                        + "\":null,\"endTime\":null,\"durationTime\":0,\"type\":null,\"reAttempt\":0,\"groups\":null,\"students\":null,\"showAll"
                                        + "\":false}"));
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualAllSubmissionByExerciseId = (new ExerciseController()).getAllSubmissionByExerciseId("42",
                "Type");

        // Assert
        assertEquals(400, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.hasBody());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualAllSubmissionByExerciseId = (new ExerciseController()).getAllSubmissionByExerciseId("foo",
                "quiz");

        // Assert
        assertEquals(400, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.hasBody());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualAllSubmissionByExerciseId = (new ExerciseController()).getAllSubmissionByExerciseId("foo",
                "essay");

        // Assert
        assertEquals(400, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.hasBody());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualAllSubmissionByExerciseId = (new ExerciseController()).getAllSubmissionByExerciseId("foo",
                "code");

        // Assert
        assertEquals(400, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.hasBody());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    void testGetAllSubmissionByExerciseId5() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualAllSubmissionByExerciseId = (new ExerciseController()).getAllSubmissionByExerciseId("foo",
                "file");

        // Assert
        assertEquals(400, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.hasBody());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentSubmission(String, String)}
     */
    @Test
    void testGetStudentSubmission() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualStudentSubmission = (new ExerciseController()).getStudentSubmission("42", "Type");

        // Assert
        assertEquals(400, actualStudentSubmission.getStatusCodeValue());
        assertTrue(actualStudentSubmission.hasBody());
        assertTrue(actualStudentSubmission.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getExerciseByUserId(String, String, String)}
     */
    @Test
    void testGetExerciseByUserId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualExerciseByUserId = (new ExerciseController()).getExerciseByUserId("42", "42", "Type");

        // Assert
        assertEquals(400, actualExerciseByUserId.getStatusCodeValue());
        assertTrue(actualExerciseByUserId.hasBody());
        assertTrue(actualExerciseByUserId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#createCodeExercise(CreateCodeExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCodeExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@47c3ca16 testClass = com.example.codeE.controller.DiffblueFakeClass4198, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateCodeExerciseRequest createCodeExerciseRequest = new CreateCodeExerciseRequest();
        createCodeExerciseRequest.setAllowedLanguageIds(new ArrayList<>());
        createCodeExerciseRequest.setDescription("The characteristics of someone or something");
        createCodeExerciseRequest.setDurationTime(1);
        createCodeExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createCodeExerciseRequest.setExerciseName("Exercise Name");
        createCodeExerciseRequest.setKey("Key");
        createCodeExerciseRequest.setPoints(10.0d);
        createCodeExerciseRequest.setReAttempt(1);
        createCodeExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createCodeExerciseRequest.setTemplate("Template");
        createCodeExerciseRequest.setTestCases(new ArrayList<>());
        createCodeExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createCodeExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#createEssayExercise(CreateEssayExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateEssayExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@36669f5c testClass = com.example.codeE.controller.DiffblueFakeClass4350, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateEssayExerciseRequest createEssayExerciseRequest = new CreateEssayExerciseRequest();
        createEssayExerciseRequest.setDurationTime(1);
        createEssayExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createEssayExerciseRequest.setExerciseDescription("Exercise Description");
        createEssayExerciseRequest.setExerciseName("Exercise Name");
        createEssayExerciseRequest.setKey("Key");
        createEssayExerciseRequest.setQuestion("Question");
        createEssayExerciseRequest.setReAttempt(1);
        createEssayExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createEssayExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createEssayExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/essay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#createFileExercise(CreateFileExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateFileExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@6f5b26f8 testClass = com.example.codeE.controller.DiffblueFakeClass4466, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateFileExerciseRequest createFileExerciseRequest = new CreateFileExerciseRequest();
        createFileExerciseRequest.setDurationTime(1);
        createFileExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createFileExerciseRequest.setExerciseDescription("Exercise Description");
        createFileExerciseRequest.setExerciseName("Exercise Name");
        createFileExerciseRequest.setKey("Key");
        createFileExerciseRequest.setQuestion("Question");
        createFileExerciseRequest.setReAttempt(1);
        createFileExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createFileExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createFileExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#createQuizExercise(CreateQuizExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateQuizExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2d2dd9fe testClass = com.example.codeE.controller.DiffblueFakeClass4582, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateQuizExerciseRequest createQuizExerciseRequest = new CreateQuizExerciseRequest();
        createQuizExerciseRequest.setDurationTime(1);
        createQuizExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createQuizExerciseRequest.setExerciseDescription("Exercise Description");
        createQuizExerciseRequest.setExerciseName("Exercise Name");
        createQuizExerciseRequest.setKey("Key");
        createQuizExerciseRequest.setQuestions(new ArrayList<>());
        createQuizExerciseRequest.setReAttempt(1);
        createQuizExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createQuizExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createQuizExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#deleteExerciseById(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteExerciseById() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@77655207 testClass = com.example.codeE.controller.DiffblueFakeClass4688, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/exercises/{exerciseId}", "42")
                .param("type", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllExerciseByCourseId(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllExerciseByCourseId() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@71ff6c47 testClass = com.example.codeE.controller.DiffblueFakeClass4717, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises").param("courseId", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllStudentSubmission(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllStudentSubmission() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2d3512ad testClass = com.example.codeE.controller.DiffblueFakeClass4746, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/exercises/all-submission/user/{userId}", "42")
                .param("courseId", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllSubmissionByExerciseId6() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@54ff9c9e testClass = com.example.codeE.controller.DiffblueFakeClass4775, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/exercises/{exerciseId}/all-submission", "42")
                .param("type", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ExerciseController#getExerciseById(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExerciseById() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4e43ff86 testClass = com.example.codeE.controller.DiffblueFakeClass4804, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/{exerciseId}", "42");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getExerciseByUserId(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExerciseByUserId2() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5a78b53f testClass = com.example.codeE.controller.DiffblueFakeClass4827, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/submit/user/{userId}", "42")
                .param("exerciseId", "foo")
                .param("type", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getExerciseDetail(GetDetailExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExerciseDetail() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@119d8d42 testClass = com.example.codeE.controller.DiffblueFakeClass4863, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        GetDetailExerciseRequest getDetailExerciseRequest = new GetDetailExerciseRequest();
        getDetailExerciseRequest.setExerciseId("42");
        getDetailExerciseRequest.setKey("Key");
        getDetailExerciseRequest.setStudentId("42");
        String content = (new ObjectMapper()).writeValueAsString(getDetailExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/detail")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getPreviewExercise(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPreviewExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@52cfb715 testClass = com.example.codeE.controller.DiffblueFakeClass4925, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/preview/{exerciseId}", "42")
                .param("studentId", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentSubmission(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetStudentSubmission2() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3ac7c77c testClass = com.example.codeE.controller.DiffblueFakeClass4954, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/submit/{submissionId}", "42")
                .param("type", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#gradeEssaySubmission(String, float)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGradeEssaySubmission() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@66c0036c testClass = com.example.codeE.controller.DiffblueFakeClass4983, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder paramResult = MockMvcRequestBuilders.put("/exercises/essay/grade")
                .param("essaySubmissionId", "foo");
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("score", String.valueOf(10.0f));

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ExerciseController#runCodeExercise(CodeRunRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRunCodeExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@29fe33a1 testClass = com.example.codeE.controller.DiffblueFakeClass5022, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CodeRunRequest codeRunRequest = new CodeRunRequest();
        codeRunRequest.setExerciseId("42");
        codeRunRequest.setLanguageId("en");
        codeRunRequest.setSource("Source");
        codeRunRequest.setStudentId("42");
        String content = (new ObjectMapper()).writeValueAsString(codeRunRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/code/run")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ExerciseController#runCodeExercise(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRunCodeExercise2() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@132c5736 testClass = com.example.codeE.controller.DiffblueFakeClass5168, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/code/run/{submissionId}",
                "42");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#submitCodeExercise(SubmitCodeExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSubmitCodeExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7928761f testClass = com.example.codeE.controller.DiffblueFakeClass5191, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        SubmitCodeExerciseRequest submitCodeExerciseRequest = new SubmitCodeExerciseRequest();
        submitCodeExerciseRequest.setExerciseId("42");
        submitCodeExerciseRequest.setLanguageId("en");
        submitCodeExerciseRequest.setSource("Source");
        submitCodeExerciseRequest.setStudentId("42");
        String content = (new ObjectMapper()).writeValueAsString(submitCodeExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/code/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#submitEssayExercise(CreateEssaySubmissionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSubmitEssayExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4923e428 testClass = com.example.codeE.controller.DiffblueFakeClass5337, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateEssaySubmissionRequest createEssaySubmissionRequest = new CreateEssaySubmissionRequest();
        createEssaySubmissionRequest.setExerciseId("42");
        createEssaySubmissionRequest.setStudentId("42");
        createEssaySubmissionRequest.setSubmission("Submission");
        String content = (new ObjectMapper()).writeValueAsString(createEssaySubmissionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/essay/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#submitQuizExercise(CreateQuizSubmissionRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSubmitQuizExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3ec534e7 testClass = com.example.codeE.controller.DiffblueFakeClass5399, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateQuizSubmissionRequest createQuizSubmissionRequest = new CreateQuizSubmissionRequest();
        createQuizSubmissionRequest.setExerciseId("42");
        createQuizSubmissionRequest.setStudentId("42");
        createQuizSubmissionRequest.setSubmission(new ArrayList<>());
        createQuizSubmissionRequest.setTeacherComment("Teacher Comment");
        String content = (new ObjectMapper()).writeValueAsString(createQuizSubmissionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/quiz/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#updateCodeExercise(UpdateCodeExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateCodeExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5df4db4b testClass = com.example.codeE.controller.DiffblueFakeClass5465, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateCodeExerciseRequest updateCodeExerciseRequest = new UpdateCodeExerciseRequest();
        updateCodeExerciseRequest.setAllowedLanguageIds(new ArrayList<>());
        updateCodeExerciseRequest.setDescription("The characteristics of someone or something");
        updateCodeExerciseRequest.setDurationTime(1);
        updateCodeExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateCodeExerciseRequest.setExerciseDescription("Exercise Description");
        updateCodeExerciseRequest.setExerciseId("42");
        updateCodeExerciseRequest.setExerciseName("Exercise Name");
        updateCodeExerciseRequest.setKey("Key");
        updateCodeExerciseRequest.setMemoryLimit(1);
        updateCodeExerciseRequest.setPoints(10.0d);
        updateCodeExerciseRequest.setReAttempt(1);
        updateCodeExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateCodeExerciseRequest.setTemplate("Template");
        updateCodeExerciseRequest.setTestCases(new ArrayList<>());
        updateCodeExerciseRequest.setTimeLimit(10.0d);
        updateCodeExerciseRequest.setTopicId("42");
        updateCodeExerciseRequest.setType("Type");
        String content = (new ObjectMapper()).writeValueAsString(updateCodeExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/exercises/code")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#updateEssayExercise(UpdateEssayExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateEssayExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2172ad30 testClass = com.example.codeE.controller.DiffblueFakeClass5665, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateEssayExerciseRequest updateEssayExerciseRequest = new UpdateEssayExerciseRequest();
        updateEssayExerciseRequest.setDurationTime(1);
        updateEssayExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateEssayExerciseRequest.setExerciseDescription("Exercise Description");
        updateEssayExerciseRequest.setExerciseId("42");
        updateEssayExerciseRequest.setExerciseName("Exercise Name");
        updateEssayExerciseRequest.setKey("Key");
        updateEssayExerciseRequest.setQuestion("Question");
        updateEssayExerciseRequest.setReAttempt(1);
        updateEssayExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateEssayExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(updateEssayExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/exercises/essay")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#updateFileExercise(UpdateFileExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateFileExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4d9d651f testClass = com.example.codeE.controller.DiffblueFakeClass5795, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateFileExerciseRequest updateFileExerciseRequest = new UpdateFileExerciseRequest();
        updateFileExerciseRequest.setDurationTime(1);
        updateFileExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateFileExerciseRequest.setExerciseDescription("Exercise Description");
        updateFileExerciseRequest.setExerciseId("42");
        updateFileExerciseRequest.setExerciseName("Exercise Name");
        updateFileExerciseRequest.setKey("Key");
        updateFileExerciseRequest.setQuestion("Question");
        updateFileExerciseRequest.setReAttempt(1);
        updateFileExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateFileExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(updateFileExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/exercises/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#updateQuizExercise(UpdateQuizExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateQuizExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7cfdbe90 testClass = com.example.codeE.controller.DiffblueFakeClass5925, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@24e3a9e7, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@49a4b0b6, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@b138cd24, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@73bada12], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateQuizExerciseRequest updateQuizExerciseRequest = new UpdateQuizExerciseRequest();
        updateQuizExerciseRequest.setDurationTime(1);
        updateQuizExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateQuizExerciseRequest.setExerciseDescription("Exercise Description");
        updateQuizExerciseRequest.setExerciseId("42");
        updateQuizExerciseRequest.setExerciseName("Exercise Name");
        updateQuizExerciseRequest.setKey("Key");
        updateQuizExerciseRequest.setQuestions(new ArrayList<>());
        updateQuizExerciseRequest.setReAttempt(1);
        updateQuizExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        updateQuizExerciseRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(updateQuizExerciseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/exercises/quiz")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }
}
