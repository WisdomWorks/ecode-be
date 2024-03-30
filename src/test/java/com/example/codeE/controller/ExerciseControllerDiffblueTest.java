package com.example.codeE.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.code.CreateCodeExerciseRequest;
import com.example.codeE.request.exercise.code.SubmitCodeExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizExerciseRequest;
import com.example.codeE.request.exercise.quiz.CreateQuizSubmissionRequest;
import com.example.codeE.request.exercise.quiz.UpdateQuizExerciseRequest;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.EssayExerciseService;
import com.example.codeE.service.exercise.ExerciseService;
import com.example.codeE.service.exercise.QuizExerciseService;
import com.example.codeE.service.exercise.QuizSubmissionService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
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

@ContextConfiguration(classes = {ExerciseController.class, MongoTemplate.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ExerciseControllerDiffblueTest {
    @MockBean
    private CodeExerciseService codeExerciseService;

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
    private JudgeService judgeService;

    @MockBean
    private MongoTemplate mongoTemplate;

    @MockBean
    private QuizExerciseService quizExerciseService;

    @MockBean
    private QuizSubmissionService quizSubmissionService;

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
     * {@link ExerciseController#createCodeExercise(CreateCodeExerciseRequest)}
     */
    @Test
    void testCreateCodeExercise() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ExerciseController exerciseController = new ExerciseController();

        // Act
        ResponseEntity<?> actualCreateCodeExerciseResult = exerciseController
                .createCodeExercise(new CreateCodeExerciseRequest());

        // Assert
        assertEquals("Api is building", actualCreateCodeExerciseResult.getBody());
        assertEquals(418, actualCreateCodeExerciseResult.getStatusCodeValue());
        assertTrue(actualCreateCodeExerciseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#createCodeExercise(CreateCodeExerciseRequest)}
     */
    @Test
    void testCreateCodeExercise2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualCreateCodeExerciseResult = (new ExerciseController())
                .createCodeExercise(mock(CreateCodeExerciseRequest.class));

        // Assert
        assertEquals("Api is building", actualCreateCodeExerciseResult.getBody());
        assertEquals(418, actualCreateCodeExerciseResult.getStatusCodeValue());
        assertTrue(actualCreateCodeExerciseResult.getHeaders().isEmpty());
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
                "code");

        // Assert
        assertEquals("API not provide", actualAllSubmissionByExerciseId.getBody());
        assertEquals(418, actualAllSubmissionByExerciseId.getStatusCodeValue());
        assertTrue(actualAllSubmissionByExerciseId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentEssaySubmission(String, String)}
     */
    @Test
    void testGetStudentEssaySubmission() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualStudentEssaySubmission = (new ExerciseController()).getStudentEssaySubmission("42", "Type");

        // Assert
        assertEquals(400, actualStudentEssaySubmission.getStatusCodeValue());
        assertTrue(actualStudentEssaySubmission.hasBody());
        assertTrue(actualStudentEssaySubmission.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentEssaySubmission(String, String)}
     */
    @Test
    void testGetStudentEssaySubmission2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualStudentEssaySubmission = (new ExerciseController()).getStudentEssaySubmission("foo",
                "code");

        // Assert
        assertEquals("API not provide", actualStudentEssaySubmission.getBody());
        assertEquals(418, actualStudentEssaySubmission.getStatusCodeValue());
        assertTrue(actualStudentEssaySubmission.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentEssayUserId(String, String, String)}
     */
    @Test
    void testGetStudentEssayUserId() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualStudentEssayUserId = (new ExerciseController()).getStudentEssayUserId("42", "42", "Type");

        // Assert
        assertEquals(400, actualStudentEssayUserId.getStatusCodeValue());
        assertTrue(actualStudentEssayUserId.hasBody());
        assertTrue(actualStudentEssayUserId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentEssayUserId(String, String, String)}
     */
    @Test
    void testGetStudentEssayUserId2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        ResponseEntity<?> actualStudentEssayUserId = (new ExerciseController()).getStudentEssayUserId("foo", "foo", "code");

        // Assert
        assertEquals("API not provide", actualStudentEssayUserId.getBody());
        assertEquals(418, actualStudentEssayUserId.getStatusCodeValue());
        assertTrue(actualStudentEssayUserId.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link ExerciseController#createCodeExercise(CreateCodeExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateCodeExercise3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@61913fca testClass = com.example.codeE.controller.DiffblueFakeClass3438, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateCodeExerciseRequest createCodeExerciseRequest = new CreateCodeExerciseRequest();
        createCodeExerciseRequest.setDescription("The characteristics of someone or something");
        createCodeExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createCodeExerciseRequest.setExerciseName("Exercise Name");
        createCodeExerciseRequest.setFunctionName("Function Name");
        createCodeExerciseRequest.setKey("Key");
        createCodeExerciseRequest.setLanguage("en");
        createCodeExerciseRequest.setPublicGroupIds(new ArrayList<>());
        createCodeExerciseRequest
                .setStartTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        createCodeExerciseRequest.setTemplate("Template");
        createCodeExerciseRequest.setTopicId("42");
        createCodeExerciseRequest.setType("Type");
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@45688d0f testClass = com.example.codeE.controller.DiffblueFakeClass3689, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateEssayExerciseRequest createEssayExerciseRequest = new CreateEssayExerciseRequest();
        createEssayExerciseRequest.setDurationTime(1);
        createEssayExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
     * {@link ExerciseController#createQuizExercise(CreateQuizExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateQuizExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@37039637 testClass = com.example.codeE.controller.DiffblueFakeClass3791, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateQuizExerciseRequest createQuizExerciseRequest = new CreateQuizExerciseRequest();
        createQuizExerciseRequest.setDurationTime(1);
        createQuizExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@41470057 testClass = com.example.codeE.controller.DiffblueFakeClass3883, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3826c9ff testClass = com.example.codeE.controller.DiffblueFakeClass3912, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
     * {@link ExerciseController#getAllSubmissionByExerciseId(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetAllSubmissionByExerciseId3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@652b6a64 testClass = com.example.codeE.controller.DiffblueFakeClass3941, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@72defc34 testClass = com.example.codeE.controller.DiffblueFakeClass3970, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
     * {@link ExerciseController#getExerciseDetail(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExerciseDetail() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3a8b281f testClass = com.example.codeE.controller.DiffblueFakeClass3993, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/detail/{exerciseId}", "42")
                .param("key", "foo")
                .param("studentId", "foo");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ExerciseController#getPreviewExercise(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPreviewExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4205745 testClass = com.example.codeE.controller.DiffblueFakeClass4029, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/exercises/{exerciseId}/preview", "42");

        // Act
        MockMvcBuilders.standaloneSetup(exerciseController).build().perform(requestBuilder);
    }

    /**
     * Method under test:
     * {@link ExerciseController#getStudentEssaySubmission(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetStudentEssaySubmission3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7556486f testClass = com.example.codeE.controller.DiffblueFakeClass4052, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
     * {@link ExerciseController#getStudentEssayUserId(String, String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetStudentEssayUserId3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5937526e testClass = com.example.codeE.controller.DiffblueFakeClass4081, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/exercises/essay/submit/user/{userId}", "42")
                .param("exerciseId", "foo")
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3369e47d testClass = com.example.codeE.controller.DiffblueFakeClass4117, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
     * Method under test:
     * {@link ExerciseController#submitCodeExercise(SubmitCodeExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSubmitCodeExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7a1c909e testClass = com.example.codeE.controller.DiffblueFakeClass4156, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3d3d25e4 testClass = com.example.codeE.controller.DiffblueFakeClass4302, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@74925669 testClass = com.example.codeE.controller.DiffblueFakeClass4364, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        CreateQuizSubmissionRequest createQuizSubmissionRequest = new CreateQuizSubmissionRequest();
        createQuizSubmissionRequest.setExerciseId("42");
        createQuizSubmissionRequest.setStudentId("42");
        createQuizSubmissionRequest.setSubmission(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(createQuizSubmissionRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/exercises/quiz/submit")
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
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@1dfbf7b testClass = com.example.codeE.controller.DiffblueFakeClass4416, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateEssayExerciseRequest updateEssayExerciseRequest = new UpdateEssayExerciseRequest();
        updateEssayExerciseRequest.setDurationTime(1);
        updateEssayExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
     * {@link ExerciseController#updateQuizExercise(UpdateQuizExerciseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateQuizExercise() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@5b5f9ca4 testClass = com.example.codeE.controller.DiffblueFakeClass4532, locations = [], classes = [com.example.codeE.controller.ExerciseController, org.springframework.data.mongodb.core.MongoTemplate], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@50dbc09d, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7ceeec2c, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@28c939d0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@1df840fd], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        UpdateQuizExerciseRequest updateQuizExerciseRequest = new UpdateQuizExerciseRequest();
        updateQuizExerciseRequest.setDurationTime(1);
        updateQuizExerciseRequest
                .setEndTime(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
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
