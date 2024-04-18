package com.example.codeE.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.ChangePasswordRequest;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import com.example.codeE.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
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

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserControllerDiffblueTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#getUsersByRoleOrAll(String)}
     */
    @Test
    void testGetUsersByRoleOrAll() throws Exception {
        // Arrange
        when(userService.getUsersByRoleOrAll(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link UserController#getById(String)}
     */
    @Test
    void testGetById() throws Exception {
        // Arrange
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
        when(userService.getById(Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{userId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":\"42\",\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\","
                                        + "\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\",\"enabled\":true,\"authorities\""
                                        + ":[{\"authority\":\"Role\"}],\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true"
                                        + "}"));
    }

    /**
     * Method under test: {@link UserController#importUsersByExcel(MultipartFile)}
     */
    @Test
    void testImportUsersByExcel() throws IOException {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        UserController userController = new UserController();

        // Act
        ResponseEntity<?> actualImportUsersByExcelResult = userController
                .importUsersByExcel(new MockMultipartFile("Name", new ByteArrayInputStream(new byte[]{})));

        // Assert
        assertEquals(400, actualImportUsersByExcelResult.getStatusCodeValue());
        assertTrue(actualImportUsersByExcelResult.hasBody());
        assertTrue(actualImportUsersByExcelResult.getHeaders().isEmpty());
    }

    /**
     * Method under test:
     * {@link UserController#changePassword(ChangePasswordRequest)}
     */
    @Test
    void testChangePassword() throws Exception {
        // Arrange
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
        when(userService.ChangePassword(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(user);

        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setNewPassword("iloveyou");
        changePasswordRequest.setOldPassword("iloveyou");
        changePasswordRequest.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(changePasswordRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/change-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":\"42\",\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\","
                                        + "\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\",\"enabled\":true,\"authorities\""
                                        + ":[{\"authority\":\"Role\"}],\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true"
                                        + "}"));
    }

    /**
     * Method under test: {@link UserController#deleteById(String)}
     */
    @Test
    void testDeleteById() throws Exception {
        // Arrange
        doNothing().when(userService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{userId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete user successful!\"}"));
    }

    /**
     * Method under test: {@link UserController#deleteById(String)}
     */
    @Test
    void testDeleteById2() throws Exception {
        // Arrange
        doNothing().when(userService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{userId}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete user successful!\"}"));
    }

    /**
     * Method under test: {@link UserController#getUserByUserName(String, String)}
     */
    @Test
    void testGetUserByUserName() throws Exception {
        // Arrange
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
        when(userService.getUserByUserName(Mockito.<String>any(), Mockito.<String>any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/users/get-by-user-name/{username}", "janedoe")
                .param("role", "foo");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":\"42\",\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\","
                                        + "\"createdDate\":\"1970-01-01 00:00:00\",\"updatedDate\":\"1970-01-01 00:00:00\",\"enabled\":true,\"authorities\""
                                        + ":[{\"authority\":\"Role\"}],\"accountNonExpired\":true,\"credentialsNonExpired\":true,\"accountNonLocked\":true"
                                        + "}"));
    }

    /**
     * Method under test: {@link UserController#createUser(CreateUserRequest)}
     */
    @Test
    void testCreateUser() throws Exception {
        // Arrange
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail("jane.doe@example.org");
        createUserRequest.setName("Name");
        createUserRequest.setRole("Role");
        createUserRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(createUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link UserController#updateById(UpdateUserRequest)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateById() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.LocalDateTime` not supported by default: add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling (through reference chain: com.example.codeE.request.user.UpdateUserRequest["createdDate"])
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:77)
        //       at com.fasterxml.jackson.databind.SerializerProvider.reportBadDefinition(SerializerProvider.java:1308)
        //       at com.fasterxml.jackson.databind.ser.impl.UnsupportedTypeSerializer.serialize(UnsupportedTypeSerializer.java:35)
        //       at com.fasterxml.jackson.databind.ser.BeanPropertyWriter.serializeAsField(BeanPropertyWriter.java:732)
        //       at com.fasterxml.jackson.databind.ser.std.BeanSerializerBase.serializeFields(BeanSerializerBase.java:772)
        //       at com.fasterxml.jackson.databind.ser.BeanSerializer.serialize(BeanSerializer.java:178)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider._serialize(DefaultSerializerProvider.java:479)
        //       at com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.serializeValue(DefaultSerializerProvider.java:318)
        //       at com.fasterxml.jackson.databind.ObjectMapper._writeValueAndClose(ObjectMapper.java:4719)
        //       at com.fasterxml.jackson.databind.ObjectMapper.writeValueAsString(ObjectMapper.java:3964)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        updateUserRequest.setUpdatedEmail("2020-03-01");
        updateUserRequest.setUpdatedName("2020-03-01");
        updateUserRequest.setUpdatedPassword("2020-03-01");
        updateUserRequest.setUpdatedRole("2020-03-01");
        updateUserRequest.setUpdatedUsername("janedoe");
        updateUserRequest.setUserId("42");
        String content = (new ObjectMapper()).writeValueAsString(updateUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(userController).build().perform(requestBuilder);
    }
}
