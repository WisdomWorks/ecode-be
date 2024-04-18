package com.example.codeE.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.material.Material;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.CreatePermissionMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.material.MaterialService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {MaterialController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MaterialControllerDiffblueTest {
    @Autowired
    private MaterialController materialController;

    @MockBean
    private MaterialService materialService;

    /**
     * Method under test: {@link MaterialController#getById(String)}
     */
    @Test
    void testGetById() throws Exception {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        when(materialService.getById(Mockito.<String>any())).thenReturn(material);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/materials/{materialId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"materialId\":\"42\",\"materialName\":\"Material Name\",\"materialType\":\"Material Type\",\"topicId\":\"42\","
                                        + "\"storageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\",\"showAll\":true}"));
    }

    /**
     * Method under test:
     * {@link MaterialController#createOne(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateOne() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MaterialController materialController = new MaterialController();

        CreateMaterialRequest request = new CreateMaterialRequest();
        request.setMaterialType("file");
        request.setUrl(null);

        // Act
        ResponseEntity<?> actualCreateOneResult = materialController.createOne(request, null);

        // Assert
        assertEquals(400, actualCreateOneResult.getStatusCodeValue());
        assertTrue(actualCreateOneResult.hasBody());
        assertTrue(actualCreateOneResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link MaterialController#createOne(CreateMaterialRequest, MultipartFile)}
     */
    @Test
    void testCreateOne2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        MaterialController materialController = new MaterialController();

        CreateMaterialRequest request = new CreateMaterialRequest();
        request.setMaterialType("string");
        request.setUrl(null);

        // Act
        ResponseEntity<?> actualCreateOneResult = materialController.createOne(request, null);

        // Assert
        assertEquals(400, actualCreateOneResult.getStatusCodeValue());
        assertTrue(actualCreateOneResult.hasBody());
        assertTrue(actualCreateOneResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link MaterialController#updateByMaterialAndTopicId(UpdateMaterialRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateByMaterialAndTopicId() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'java.lang.Boolean'. Check configuration for 'isCheckAll'
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   jakarta.validation.UnexpectedTypeException: HV000030: No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'java.lang.Boolean'. Check configuration for 'isCheckAll'
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
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        MaterialController materialController = new MaterialController();

        // Act
        materialController.updateByMaterialAndTopicId(
                new UpdateMaterialRequest("42", "The characteristics of someone or something", true));
    }

    /**
     * Method under test: {@link MaterialController#deleteById(String)}
     */
    @Test
    void testDeleteById() throws Exception {
        // Arrange
        doNothing().when(materialService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/materials/{materialId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete material successfully\"}"));
    }

    /**
     * Method under test: {@link MaterialController#deleteById(String)}
     */
    @Test
    void testDeleteById2() throws Exception {
        // Arrange
        doNothing().when(materialService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/materials/{materialId}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete material successfully\"}"));
    }

    /**
     * Method under test:
     * {@link MaterialController#addViewPermission(CreatePermissionMaterialRequest)}
     */
    @Test
    void testAddViewPermission() throws Exception {
        // Arrange
        when(materialService.addViewPermission(Mockito.<String>any(), Mockito.<List<String>>any(), anyBoolean()))
                .thenReturn(true);

        CreatePermissionMaterialRequest createPermissionMaterialRequest = new CreatePermissionMaterialRequest();
        createPermissionMaterialRequest.setGroupIds(new ArrayList<>());
        createPermissionMaterialRequest.setMaterialId("42");
        createPermissionMaterialRequest.setShowAll(true);
        String content = (new ObjectMapper()).writeValueAsString(createPermissionMaterialRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/materials/view")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(
                        MockMvcResultMatchers.content().string("{\"message\":\"Grant permission for material successful\"}"));
    }

    /**
     * Method under test: {@link MaterialController#getMaterialsByTopicId(String)}
     */
    @Test
    void testGetMaterialsByTopicId() throws Exception {
        // Arrange
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/materials/topic/{topicId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link MaterialController#getMaterialsByTopicId(String)}
     */
    @Test
    void testGetMaterialsByTopicId2() throws Exception {
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

        Material material = new Material();
        material.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setDescription("The characteristics of someone or something");
        material.setMaterialId("42");
        material.setMaterialName("Material Name");
        material.setMaterialType("Material Type");
        material.setShowAll(true);
        material.setStorageUrl("https://example.org/example");
        material.setTopic(topic);
        material.setTopicId("42");
        material.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        material.setViewPermissionMaterials(new ArrayList<>());
        when(materialService.getById(Mockito.<String>any())).thenReturn(material);
        when(materialService.getAllByTopicId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/materials/topic/{topicId}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(materialController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"materialId\":\"42\",\"materialName\":\"Material Name\",\"materialType\":\"Material Type\",\"topicId\":\"42\","
                                        + "\"storageUrl\":\"https://example.org/example\",\"description\":\"The characteristics of someone or"
                                        + " something\",\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\",\"showAll\":true}"));
    }

    /**
     * Method under test:
     * {@link MaterialController#removeViewPermission(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRemoveViewPermission() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/materials/view")
                .param("materialId", "foo");
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(materialController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }
}
