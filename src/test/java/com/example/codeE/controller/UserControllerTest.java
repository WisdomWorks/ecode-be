package com.example.codeE.controller;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.service.user.UserImpl;

import com.example.codeE.service.user.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserImpl userImplement;
    @Mock
    private UserService userService;
    private MockMvc mockMvc;

    private List<User> mockDataUser;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2024-01-28 05:10:52", formatter);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.mockDataUser = new ArrayList<>();
        this.mockDataUser.add(new User("usr1", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr2", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr3", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr4", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr5", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsersSusccessfully() {
        try {
            when(userImplement.getUsersByRoleAndSearchKeyword(Mockito.any())).thenReturn(mockDataUser);

            List<User> paginatedUserList = new ArrayList<>();
            paginatedUserList.add(new User("usr1", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));
            paginatedUserList.add(new User("usr2", "user", "user@gmail.com", "username", "123", "Teacher", dateTime, dateTime));

            when(userImplement.paginateUsers(Mockito.any())).thenReturn(paginatedUserList);

            int totalRecords = userImplement
                    .getUsersByRoleAndSearchKeyword(new GetUsersRequest("Teacher", "name", 1, 2))
                    .size();

            mockMvc.perform(get("/users")
                            .param("role", "Teacher")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "1")
                            .param("pageSize", "2"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.pagination.totalRecords").value(totalRecords))
                    .andExpect(jsonPath("$.pagination.pageSize").value(2))
                    .andExpect(jsonPath("$.pagination.pageNumber").value(1))
                    .andExpect(jsonPath("$.pagination.totalPage").value(3))
                    .andExpect(jsonPath("$.users", Matchers.hasSize(2)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsersWithInvalidRole() {
        try {
            mockMvc.perform(get("/users")
                            .param("role", "role")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "1")
                            .param("pageSize", "2"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsersWithInvalidPageNumber() {
        try {
            mockMvc.perform(get("/users")
                            .param("role", "Teacher")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "-1")
                            .param("pageSize", "2"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getById_UserFound_ReturnsUser() throws Exception {
        // Arrange
        String userId = "usr1";
        User user = mockDataUser.get(0);

        Mockito.when(userService.getById(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId").value(user.getUserId()))
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.password").doesNotExist()) // Assuming password should not be returned
                .andExpect(jsonPath("$.role").value(user.getRole()))
                .andExpect(jsonPath("$.createdDate").value(user.getCreatedDate()))
                .andExpect(jsonPath("$.updatedDate").value(user.getUpdatedDate()));

        Mockito.verify(userService, Mockito.times(1)).getById(userId);
    }

    @Test
    void getById_UserNotFound_ReturnsNotFound() throws Exception {
        // Arrange
        String userId = "nonexistentUserId";

        Mockito.when(userService.getById(userId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(get("/users/{userId}", userId))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.times(1)).getById(userId);
    }

//    @Test
//    void updateById_UserFound_ReturnsUpdatedUser() throws Exception {
//        // Mock user ID and update request
//        String userId = "usr1";
//        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
//        updateUserRequest.setUpdatedName("Updated Name");
//        updateUserRequest.setUpdatedEmail("updated.email@example.com");
//        updateUserRequest.setUpdatedUsername("updated_username");
//        updateUserRequest.setUpdatedRole("admin");
//
//        User existingUser = new User("usr1", "Updated Name", "updated.email@example.com", "updated_username", "123", "admin", "2024-01-28 05:10:52", DateTimeUtil.format(LocalDateTime.now()));
//
//        // Mock behavior for userService.updateById method
//        Mockito.when(userService.updateById(Mockito.eq(userId), Mockito.any(UpdateUserRequest.class))).thenReturn(existingUser);
//
//        // Perform PATCH request
//        mockMvc.perform(patch("/users/{userId}", userId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(updateUserRequest)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.userId").value(userId)) // Ensure the userId is returned in the response
//                .andExpect(jsonPath("$.name").value("Updated Name")) // Ensure the updated fields are returned in the response
//                .andExpect(jsonPath("$.email").value("updated.email@example.com"))
//                .andExpect(jsonPath("$.username").value("updated_username"))
//                .andExpect(jsonPath("$.role").value("admin"));
//
//        Mockito.verify(userService, Mockito.times(1)).updateById(Mockito.eq(userId), Mockito.any(UpdateUserRequest.class));
//    }

//    @Test
//    void updateById_UserNotFound_ReturnsNotFound() throws Exception {
//        // Mock userId and update request
//        String nonExistingUserId = "non_existing_user_id";
//        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
//        updateUserRequest.setUpdatedName("Updated Name");
//        updateUserRequest.setUpdatedEmail("updated.email@example.com");
//        updateUserRequest.setUpdatedUsername("updated_username");
//        updateUserRequest.setUpdatedPassword("!Sjqi12Sa");
//        updateUserRequest.setUpdatedRole("admin");
//
//        // Adjust the stubbing to match the arguments exactly
//        when(userService.updateById(Mockito.eq(nonExistingUserId), Mockito.any(UpdateUserRequest.class))).thenReturn(null);
//
//        // Perform PATCH request
//        mockMvc.perform(patch("/users/{userId}", nonExistingUserId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(updateUserRequest)))
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("User not found with ID: " + nonExistingUserId));
//    }


    @Test
    public void testImportUsersByExcel() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "test.xlsx", String.valueOf(MediaType.APPLICATION_OCTET_STREAM), "some-data".getBytes());

        mockMvc.perform(multipart("/users/import-users")
                        .file(file))
                .andExpect(status().isOk());

        verify(userService).saveUserToDatabase(file);
    }

    @Test
    void deleteById_UserFound_ReturnsOk() throws Exception {
        // Arrange
        String userId = "usr1";
        User user = mockDataUser.get(0);

        Mockito.when(userService.getById(userId)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Users is deleted successfully"));

        Mockito.verify(userService, Mockito.times(1)).deleteById(userId);
    }

    @Test
    void deleteById_UserNotFound_ReturnsNotFound() throws Exception {
        // Arrange
        String userId = "nonexistentUserId";

        Mockito.when(userService.getById(userId)).thenReturn(null);

        // Act & Assert
        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.never()).deleteById(Mockito.anyString());
    }
}