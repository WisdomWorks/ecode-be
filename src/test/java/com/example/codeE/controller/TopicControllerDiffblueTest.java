package com.example.codeE.controller;

import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.topic.CreatePermissionTopicRequest;
import com.example.codeE.request.topic.CreateTopicRequest;
import com.example.codeE.request.topic.UpdateTopicRequest;
import com.example.codeE.service.topic.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {TopicController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class TopicControllerDiffblueTest {
    @Autowired
    private TopicController topicController;

    @MockBean
    private TopicService topicService;

    /**
     * Method under test: {@link TopicController#getAllTopicsByCourseId(String)}
     */
    @Test
    void testGetAllTopicsByCourseId() throws Exception {
        // Arrange
        when(topicService.getAllTopicsByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/topics").param("courseId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TopicController#updateTopic(UpdateTopicRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateTopic() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'java.lang.Boolean'. Check configuration for 'isShowAll'
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'java.lang.Boolean'. Check configuration for 'isShowAll'
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getExceptionForNullValidator(ConstraintTree.java:116)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.getInitializedConstraintValidator(ConstraintTree.java:162)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:58)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:75)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.doValidateConstraint(MetaConstraint.java:130)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.validateConstraint(MetaConstraint.java:123)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateMetaConstraint(ValidatorImpl.java:555)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForSingleDefaultGroupElement(ValidatorImpl.java:518)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForDefaultGroup(ValidatorImpl.java:488)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForCurrentGroup(ValidatorImpl.java:450)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateInContext(ValidatorImpl.java:400)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validate(ValidatorImpl.java:172)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        TopicController topicController = new TopicController();

        // Act
        topicController
                .updateTopic(new UpdateTopicRequest("42", "Topic Name", "The characteristics of someone or something", true));
    }

    /**
     * Method under test:
     * {@link TopicController#addViewPermission(CreatePermissionTopicRequest)}
     */
    @Test
    void testAddViewPermission() throws Exception {
        // Arrange
        when(topicService.addViewPermission(Mockito.<String>any(), Mockito.<List<String>>any(), anyBoolean()))
                .thenReturn(true);

        CreatePermissionTopicRequest createPermissionTopicRequest = new CreatePermissionTopicRequest();
        createPermissionTopicRequest.setGroupIds(new ArrayList<>());
        createPermissionTopicRequest.setShowAll(true);
        createPermissionTopicRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createPermissionTopicRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/topics/view")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"add permission success\"}"));
    }

    /**
     * Method under test:
     * {@link TopicController#addViewPermission(CreatePermissionTopicRequest)}
     */
    @Test
    void testAddViewPermission2() throws Exception {
        // Arrange
        when(topicService.addViewPermission(Mockito.<String>any(), Mockito.<List<String>>any(), anyBoolean()))
                .thenReturn(false);

        CreatePermissionTopicRequest createPermissionTopicRequest = new CreatePermissionTopicRequest();
        createPermissionTopicRequest.setGroupIds(new ArrayList<>());
        createPermissionTopicRequest.setShowAll(true);
        createPermissionTopicRequest.setTopicId("42");
        String content = (new ObjectMapper()).writeValueAsString(createPermissionTopicRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/topics/view")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Something wrong when add view permission\"}"));
    }

    /**
     * Method under test: {@link TopicController#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId() throws Exception {
        // Arrange
        when(topicService.getTopicByUserId(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/topics/user/{userId}", "42")
                .param("courseId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TopicController#getTopicByUserId(String, String)}
     */
    @Test
    void testGetTopicByUserId2() throws Exception {
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
        when(topicService.getById(Mockito.<String>any())).thenReturn(topic);
        when(topicService.getTopicByUserId(Mockito.<String>any(), Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/topics/user/{userId}", "", "Uri Variables")
                .param("courseId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"topicId\":\"42\",\"courseId\":\"42\",\"topicName\":\"Topic Name\",\"description\":\"The characteristics of"
                                        + " someone or something\",\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\","
                                        + "\"showAll\":true}"));
    }

    /**
     * Method under test: {@link TopicController#createTopic(CreateTopicRequest)}
     */
    @Test
    void testCreateTopic() throws Exception {
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
        when(topicService.createOne(Mockito.<CreateTopicRequest>any())).thenReturn(topic);

        CreateTopicRequest createTopicRequest = new CreateTopicRequest();
        createTopicRequest.setCourseId("42");
        createTopicRequest.setDescription("The characteristics of someone or something");
        createTopicRequest.setTopicName("Topic Name");
        String content = (new ObjectMapper()).writeValueAsString(createTopicRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"topicId\":\"42\",\"courseId\":\"42\",\"topicName\":\"Topic Name\",\"description\":\"The characteristics of"
                                        + " someone or something\",\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\","
                                        + "\"showAll\":true}"));
    }

    /**
     * Method under test: {@link TopicController#deleteTopic(String)}
     */
    @Test
    void testDeleteTopic() throws Exception {
        // Arrange
        doNothing().when(topicService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/topics/{topicId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Topic deleted successfully"));
    }

    /**
     * Method under test: {@link TopicController#deleteTopic(String)}
     */
    @Test
    void testDeleteTopic2() throws Exception {
        // Arrange
        doNothing().when(topicService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/topics/{topicId}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Topic deleted successfully"));
    }

    /**
     * Method under test: {@link TopicController#getPublicGroups(String)}
     */
    @Test
    void testGetPublicGroups() throws Exception {
        // Arrange
        when(topicService.getAllGroupsByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/topics/view").param("topicId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link TopicController#getTopic(String)}
     */
    @Test
    void testGetTopic() throws Exception {
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
        when(topicService.getById(Mockito.<String>any())).thenReturn(topic);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/topics/{topicId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(topicController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"topicId\":\"42\",\"courseId\":\"42\",\"topicName\":\"Topic Name\",\"description\":\"The characteristics of"
                                        + " someone or something\",\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\","
                                        + "\"showAll\":true}"));
    }

    /**
     * Method under test: {@link TopicController#removeViewPermission(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveViewPermission() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/topics/view")
                .param("topicId", "foo");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(topicController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
