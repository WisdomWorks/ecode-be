package com.example.codeE.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.group.Group;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.model.group.UpdateGroupRequest;
import com.example.codeE.model.user.User;
import com.example.codeE.request.group.CreateGroupRequest;
import com.example.codeE.request.group.CreateGroupStudentRequest;
import com.example.codeE.service.group.GroupService;
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

@ContextConfiguration(classes = {GroupController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class GroupControllerDiffblueTest {
    @Autowired
    private GroupController groupController;

    @MockBean
    private GroupService groupService;

    /**
     * Method under test: {@link GroupController#getGroupsByCourseId(String)}
     */
    @Test
    void testGetGroupsByCourseId() throws Exception {
        // Arrange
        when(groupService.getGroupsByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groups/course/{courseId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link GroupController#getGroupsByCourseId(String)}
     */
    @Test
    void testGetGroupsByCourseId2() throws Exception {
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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        when(groupService.getById(Mockito.<String>any())).thenReturn(group);
        when(groupService.getGroupsByCourseId(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groups/course/{courseId}", "",
                "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"groupId\":\"42\",\"courseId\":\"42\",\"groupName\":\"Group Name\",\"createDate\":\"1970-01-01 00:00:00\",\"updateDate"
                                        + "\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test:
     * {@link GroupController#addStudentsToGroup(CreateGroupStudentRequest)}
     */
    @Test
    void testAddStudentsToGroup() throws Exception {
        // Arrange
        when(groupService.addStudentsToGroup(Mockito.<List<String>>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(new ArrayList<>());

        CreateGroupStudentRequest createGroupStudentRequest = new CreateGroupStudentRequest();
        createGroupStudentRequest.setDescription("The characteristics of someone or something");
        createGroupStudentRequest.setGroupId("42");
        createGroupStudentRequest.setStudentIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(createGroupStudentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groups/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Can not add student to group! "));
    }

    /**
     * Method under test:
     * {@link GroupController#addStudentsToGroup(CreateGroupStudentRequest)}
     */
    @Test
    void testAddStudentsToGroup2() throws Exception {
        // Arrange
        Course course = new Course();
        course.setCourseId("42");
        course.setCourseName("Can not add student to group! ");
        course.setCourseStudents(new ArrayList<>());
        course.setCourseTeachers(new ArrayList<>());
        course.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        course.setDescription("The characteristics of someone or something");
        course.setEnrollKey("Can not add student to group! ");
        course.setGroups(new ArrayList<>());
        course.setSemester("Can not add student to group! ");
        course.setTopics(new ArrayList<>());
        course.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Can not add student to group! ");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());

        User student = new User();
        student.setCourseStudents(new ArrayList<>());
        student.setCourseTeachers(new ArrayList<>());
        student.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setEmail("jane.doe@example.org");
        student.setGroupStudents(new ArrayList<>());
        student.setName("Can not add student to group! ");
        student.setPassword("iloveyou");
        student.setRole("Can not add student to group! ");
        student.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        student.setUserId("42");
        student.setUsername("janedoe");

        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setDescription("The characteristics of someone or something");
        groupStudent.setGroup(group);
        groupStudent.setGroupId("42");
        groupStudent.setJoinDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        groupStudent.setStudent(student);
        groupStudent.setStudentId("42");

        ArrayList<GroupStudent> groupStudentList = new ArrayList<>();
        groupStudentList.add(groupStudent);
        when(groupService.addStudentsToGroup(Mockito.<List<String>>any(), Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(groupStudentList);

        CreateGroupStudentRequest createGroupStudentRequest = new CreateGroupStudentRequest();
        createGroupStudentRequest.setDescription("The characteristics of someone or something");
        createGroupStudentRequest.setGroupId("42");
        createGroupStudentRequest.setStudentIds(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(createGroupStudentRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groups/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"groupId\":\"42\",\"studentId\":\"42\",\"description\":\"The characteristics of someone or something\",\"joinDate"
                                        + "\":\"1970-01-01 00:00:00\"}]"));
    }

    /**
     * Method under test: {@link GroupController#getStudentNotInGroup(String)}
     */
    @Test
    void testGetStudentNotInGroup() throws Exception {
        // Arrange
        when(groupService.getStudentNotInGroup(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groups/{groupId}/student/not-in-group",
                "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link GroupController#updateGroup(String, UpdateGroupRequest)}
     */
    @Test
    void testUpdateGroup() throws Exception {
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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        when(groupService.updateGroup(Mockito.<String>any(), Mockito.<UpdateGroupRequest>any())).thenReturn(group);

        UpdateGroupRequest updateGroupRequest = new UpdateGroupRequest();
        updateGroupRequest.setGroupName("Group Name");
        String content = (new ObjectMapper()).writeValueAsString(updateGroupRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/groups/{groupId}", "42")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"groupId\":\"42\",\"courseId\":\"42\",\"groupName\":\"Group Name\",\"createDate\":\"1970-01-01 00:00:00\",\"updateDate"
                                        + "\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link GroupController#createGroup(CreateGroupRequest)}
     */
    @Test
    void testCreateGroup() throws Exception {
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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        when(groupService.createOne(Mockito.<CreateGroupRequest>any())).thenReturn(group);

        CreateGroupRequest createGroupRequest = new CreateGroupRequest();
        createGroupRequest.setCourseId("42");
        createGroupRequest.setGroupName("Group Name");
        String content = (new ObjectMapper()).writeValueAsString(createGroupRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/groups")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"groupId\":\"42\",\"courseId\":\"42\",\"groupName\":\"Group Name\",\"createDate\":\"1970-01-01 00:00:00\",\"updateDate"
                                        + "\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link GroupController#deleteGroup(String)}
     */
    @Test
    void testDeleteGroup() throws Exception {
        // Arrange
        doNothing().when(groupService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/groups/{groupId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete group successfully\"}"));
    }

    /**
     * Method under test: {@link GroupController#deleteGroup(String)}
     */
    @Test
    void testDeleteGroup2() throws Exception {
        // Arrange
        doNothing().when(groupService).deleteById(Mockito.<String>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/groups/{groupId}", "42");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Delete group successfully\"}"));
    }

    /**
     * Method under test: {@link GroupController#deleteStudentInGroup(String, List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDeleteStudentInGroup() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        // TODO: Populate arranged inputs
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.delete("/groups/{groupId}/student", "42")
                .contentType(MediaType.APPLICATION_JSON);
        ArrayList<String> stringList = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(stringList));
        MockMvc buildResult = MockMvcBuilders.standaloneSetup(groupController).build();

        // Act
        ResultActions actualPerformResult = buildResult.perform(requestBuilder);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link GroupController#getGroupById(String)}
     */
    @Test
    void testGetGroupById() throws Exception {
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

        Group group = new Group();
        group.setCourse(course);
        group.setCourseId("42");
        group.setCreateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setGroupId("42");
        group.setGroupName("Group Name");
        group.setGroupStudents(new ArrayList<>());
        group.setUpdateDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        group.setViewPermissionMaterials(new ArrayList<>());
        group.setViewPermissionTopics(new ArrayList<>());
        when(groupService.getById(Mockito.<String>any())).thenReturn(group);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groups/{groupId}", "42");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"groupId\":\"42\",\"courseId\":\"42\",\"groupName\":\"Group Name\",\"createDate\":\"1970-01-01 00:00:00\",\"updateDate"
                                        + "\":\"1970-01-01 00:00:00\"}"));
    }

    /**
     * Method under test: {@link GroupController#getStudentInGroup(String)}
     */
    @Test
    void testGetStudentInGroup() throws Exception {
        // Arrange
        when(groupService.getUsersInGroup(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/groups/{groupId}/student/in-group",
                "42");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(groupController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
