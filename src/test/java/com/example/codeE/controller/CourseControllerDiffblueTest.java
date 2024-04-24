package com.example.codeE.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.CourseEnrollmentRequest;
import com.example.codeE.request.course.CourseEnrollmentResponse;
import com.example.codeE.request.course.CourseResponse;
import com.example.codeE.request.course.CourseTeacherResponse;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.request.course.UpdateStudentsToCourseRequest;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.courseStudent.CourseStudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {CourseController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class CourseControllerDiffblueTest {
    @Autowired
    private CourseController courseController;

    @MockBean
    private CourseService courseService;

    @MockBean
    private CourseStudentService courseStudentService;

    /**
     * Method under test: {@link CourseController#getById(String)}
     */
    @Test
    void testGetById() throws Exception {
        // Arrange
        when(courseService.getById(Mockito.<String>any())).thenReturn(new CourseResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courses/{courseId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"courseId\":null,\"courseName\":null,\"semester\":null,\"enrollKey\":null,\"description\":null,\"createdDate\""
                                        + ":null,\"updatedDate\":null,\"students\":null,\"teacher\":null}"));
    }

    /**
     * Method under test: {@link CourseController#getById(String)}
     */
    @Test
    void testGetById2() throws Exception {
        // Arrange
        when(courseService.getAll()).thenReturn(new ArrayList<>());
        when(courseService.getById(Mockito.<String>any())).thenReturn(new CourseResponse());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courses/{courseId}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseController#getAll()}
     */
    @Test
    void testGetAll() throws Exception {
        // Arrange
        when(courseService.getAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courses");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseController#createOne(CreateCourseRequest)}
     */
    @Test
    void testCreateOne2() throws Exception {
        // Arrange
        when(courseService.createOne(Mockito.<CreateCourseRequest>any())).thenReturn(new CourseResponse());

        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Course Name");
        createCourseRequest.setDescription("The characteristics of someone or something");
        createCourseRequest.setSemester("42");
        createCourseRequest.setTeacherId("42");
        String content = (new ObjectMapper()).writeValueAsString(createCourseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"courseId\":null,\"courseName\":null,\"semester\":null,\"enrollKey\":null,\"description\":null,\"createdDate\""
                                        + ":null,\"updatedDate\":null,\"students\":null,\"teacher\":null}"));
    }

    /**
     * Method under test:
     * {@link CourseController#importCoursesByExcel(MultipartFile)}
     */
    @Test
    void testImportCoursesByExcel() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CourseController courseController = new CourseController();

        // Act
        ResponseEntity<?> actualImportCoursesByExcelResult = courseController
                .importCoursesByExcel(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));

        // Assert
        assertEquals(400, actualImportCoursesByExcelResult.getStatusCodeValue());
        assertTrue(actualImportCoursesByExcelResult.hasBody());
        assertTrue(actualImportCoursesByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseController#updateById(UpdateCourseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateById() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: jakarta.validation.ValidationException: HV000028: Unexpected exception during isValid call.
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:593)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   jakarta.validation.ValidationException: HV000028: Unexpected exception during isValid call.
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateSingleConstraint(ConstraintTree.java:186)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:66)
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
        //   java.lang.NullPointerException: Cannot invoke "com.example.codeE.repository.CourseRepository.existsById(Object)" because "this.courseRepository" is null
        //       at com.example.codeE.validator.id.ExistingIdValidator.isValid(ExistingIdValidator.java:43)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateSingleConstraint(ConstraintTree.java:180)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:66)
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
        CourseController courseController = new CourseController();

        // Act
        courseController.updateById(new UpdateCourseRequest("42", "Course Name", "Semester", "Enroll Key",
                "The characteristics of someone or something", "42"));
    }

    /**
     * Method under test:
     * {@link CourseController#updateStudentsInCourse(UpdateStudentsToCourseRequest)}
     */
    @Test
    void testUpdateStudentsInCourse() throws Exception {
        // Arrange
        when(courseService.getById(Mockito.<String>any())).thenReturn(new CourseResponse());
        when(courseStudentService.updateStudentsInCourse(Mockito.<UpdateStudentsToCourseRequest>any()))
                .thenReturn(new ArrayList<>());

        UpdateStudentsToCourseRequest updateStudentsToCourseRequest = new UpdateStudentsToCourseRequest();
        updateStudentsToCourseRequest.setCourseId("42");
        updateStudentsToCourseRequest.setStudentIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(updateStudentsToCourseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/courses/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"courseId\":null,\"courseName\":null,\"semester\":null,\"enrollKey\":null,\"description\":null,\"createdDate\""
                                        + ":null,\"updatedDate\":null,\"students\":null,\"teacher\":null}"));
    }

    /**
     * Method under test:
     * {@link CourseController#deleteById(RemoveStudentFromCourseRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteById() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   jakarta.servlet.ServletException: Request processing failed: jakarta.validation.ValidationException: HV000028: Unexpected exception during isValid call.
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   jakarta.validation.ValidationException: HV000028: Unexpected exception during isValid call.
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateSingleConstraint(ConstraintTree.java:186)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:66)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:75)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.doValidateConstraint(MetaConstraint.java:130)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.validateConstraint(MetaConstraint.java:123)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateMetaConstraint(ValidatorImpl.java:555)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForSingleDefaultGroupElement(ValidatorImpl.java:518)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForDefaultGroup(ValidatorImpl.java:488)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForCurrentGroup(ValidatorImpl.java:450)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateInContext(ValidatorImpl.java:400)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validate(ValidatorImpl.java:172)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   java.lang.NullPointerException: Cannot invoke "com.example.codeE.repository.CourseStudentRepository.existsByStudentIdAndCourseId(String, String)" because "this.courseStudentRepository" is null
        //       at com.example.codeE.validator.id.ExistingIdValidator.isValid(ExistingIdValidator.java:49)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateSingleConstraint(ConstraintTree.java:180)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.SimpleConstraintTree.validateConstraints(SimpleConstraintTree.java:66)
        //       at org.hibernate.validator.internal.engine.constraintvalidation.ConstraintTree.validateConstraints(ConstraintTree.java:75)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.doValidateConstraint(MetaConstraint.java:130)
        //       at org.hibernate.validator.internal.metadata.core.MetaConstraint.validateConstraint(MetaConstraint.java:123)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateMetaConstraint(ValidatorImpl.java:555)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForSingleDefaultGroupElement(ValidatorImpl.java:518)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForDefaultGroup(ValidatorImpl.java:488)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateConstraintsForCurrentGroup(ValidatorImpl.java:450)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validateInContext(ValidatorImpl.java:400)
        //       at org.hibernate.validator.internal.engine.ValidatorImpl.validate(ValidatorImpl.java:172)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:596)
        //       at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        CourseController courseController = new CourseController();

        // Act
        courseController.deleteById(new RemoveStudentFromCourseRequest("42", "42"));
    }

    /**
     * Method under test: {@link CourseController#deleteById(String)}
     */
    @Test
    void testDeleteById2() throws Exception {
        // Arrange
        doNothing().when(courseService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/courses/{courseId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete course successfully\"}"));
    }

    /**
     * Method under test: {@link CourseController#deleteById(String)}
     */
    @Test
    void testDeleteById3() throws Exception {
        // Arrange
        doNothing().when(courseService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/courses/{courseId}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete course successfully\"}"));
    }

    /**
     * Method under test:
     * {@link CourseController#unEnrollUserInCourse(String, String)}
     */
    @Test
    void testUnEnrollUserInCourse() throws Exception {
        // Arrange
        doNothing().when(courseService).unEnrollUserInCourse(Mockito.<String>any(), Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/courses/unEnrollment")
                .param("courseId", "foo")
                .param("userId", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"UnEnroll successful\"}"));
    }

    /**
     * Method under test: {@link CourseController#getCourseByUserId(String)}
     */
    @Test
    void testGetCourseByUserId() throws Exception {
        // Arrange
        when(courseService.getCourseByUserId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courses/user/{userId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CourseController#getCourseByUserId(String)}
     */
    @Test
    void testGetCourseByUserId2() throws Exception {
        // Arrange
        when(courseService.getById(Mockito.<String>any())).thenReturn(new CourseResponse());
        when(courseService.getCourseByUserId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/courses/user/{userId}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"courseId\":null,\"courseName\":null,\"semester\":null,\"enrollKey\":null,\"description\":null,\"createdDate\""
                                        + ":null,\"updatedDate\":null,\"students\":null,\"teacher\":null}"));
    }

    /**
     * Method under test:
     * {@link CourseController#addStudentToCourse(AddStudentToCourseRequest)}
     */
    @Test
    void testAddStudentToCourse() throws Exception {
        // Arrange
        when(courseStudentService.addStudentToCourse(Mockito.<AddStudentToCourseRequest>any()))
                .thenReturn(new ArrayList<>());

        AddStudentToCourseRequest addStudentToCourseRequest = new AddStudentToCourseRequest();
        addStudentToCourseRequest.setCourseId("42");
        addStudentToCourseRequest.setStudentIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(addStudentToCourseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/courses/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link CourseController#addStudentsToCourse(ImportStudentToCourseRequest)}
     */
    @Test
    void testAddStudentsToCourse() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        CourseController courseController = new CourseController();

        ImportStudentToCourseRequest request = new ImportStudentToCourseRequest();
        request.setFile(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));

        // Act
        ResponseEntity<?> actualAddStudentsToCourseResult = courseController.addStudentsToCourse(request);

        // Assert
        assertEquals(400, actualAddStudentsToCourseResult.getStatusCodeValue());
        assertTrue(actualAddStudentsToCourseResult.hasBody());
        assertTrue(actualAddStudentsToCourseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link CourseController#createOne(CreateCourseRequest)}
     */
    @Test
    void testCreateOne() throws Exception {
        // Arrange
        CreateCourseRequest createCourseRequest = new CreateCourseRequest();
        createCourseRequest.setCourseName("Course Name");
        createCourseRequest.setDescription("The characteristics of someone or something");
        createCourseRequest.setSemester("Semester");
        createCourseRequest.setTeacherId("42");
        String content = (new ObjectMapper()).writeValueAsString(createCourseRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/courses")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test:
     * {@link CourseController#enrollUserToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollUserToCourse() throws Exception {
        // Arrange
        when(courseService.enrollStudentToCourse(Mockito.<CourseEnrollmentRequest>any()))
                .thenReturn(new CourseEnrollmentResponse<>());

        CourseEnrollmentRequest courseEnrollmentRequest = new CourseEnrollmentRequest();
        courseEnrollmentRequest.setCourseId("42");
        courseEnrollmentRequest.setEnrollmentKey("Enrollment Key");
        courseEnrollmentRequest.setStudentId("42");
        String content = (new ObjectMapper()).writeValueAsString(courseEnrollmentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/courses/enrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"status\":0}"));
    }

    /**
     * Method under test:
     * {@link CourseController#enrollUserToCourse(CourseEnrollmentRequest)}
     */
    @Test
    void testEnrollUserToCourse2() throws Exception {
        // Arrange
        CourseTeacherResponse courseTeacherResponse = new CourseTeacherResponse();
        when(courseService.enrollStudentToCourse(Mockito.<CourseEnrollmentRequest>any()))
                .thenReturn(new CourseEnrollmentResponse<>("Not all who wander are lost", "An error occurred",
                        courseTeacherResponse, new ArrayList<>(), 201));

        CourseEnrollmentRequest courseEnrollmentRequest = new CourseEnrollmentRequest();
        courseEnrollmentRequest.setCourseId("42");
        courseEnrollmentRequest.setEnrollmentKey("Enrollment Key");
        courseEnrollmentRequest.setStudentId("42");
        String content = (new ObjectMapper()).writeValueAsString(courseEnrollmentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/courses/enrollment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(courseController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"message\":\"Not all who wander are lost\",\"error\":\"An error occurred\",\"value\":{\"courseId\":null,\"courseName"
                                        + "\":null,\"semester\":null,\"description\":null,\"createdDate\":null,\"updatedDate\":null,\"teacher\":null},\"values"
                                        + "\":[],\"status\":201}"));
    }
}
