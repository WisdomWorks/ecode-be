package com.example.codeE.controller;

import com.example.codeE.model.user.User;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.service.user.UserImpl;
import com.example.codeE.helper.ExcelHelper;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @InjectMocks
    private UserController userController;
    @Mock
    private UserImpl userImplement;
    private MockMvc mockMvc;

    private List<User> mockDataUser;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        this.mockDataUser = new ArrayList<>();
        this.mockDataUser.add(new User("usr1", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr2", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr3", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr4", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
        this.mockDataUser.add(new User("usr5", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsersSusccessfully() {
        try {
            when(userImplement.getUsersByRoleAndSearchKeyword(Mockito.any())).thenReturn(mockDataUser);

            List<User> paginatedUserList = new ArrayList<>();
            paginatedUserList.add(new User("usr1", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));
            paginatedUserList.add(new User("usr2", "user", "user@gmail.com", "username", "123", "Teacher", "2024-01-28 05:10:52", "2024-01-28 05:10:52"));

            when(userImplement.paginateUsers(Mockito.any())).thenReturn(paginatedUserList);

            int totalRecords = userImplement
                    .getUsersByRoleAndSearchKeyword(new GetUsersRequest("Teacher", "name", 1, 2))
                    .size();

            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users")
                            .param("role", "Teacher")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "1")
                            .param("pageSize", "2"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.totalRecords").value(totalRecords))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.pageSize").value(2))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.pageNumber").value(1))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.totalPage").value(3))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.users", Matchers.hasSize(2)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsersWithInvalidRole() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users")
                            .param("role", "role")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "1")
                            .param("pageSize", "2"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getAllUsersWithInvalidPageNumber() {
        try {
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users")
                            .param("role", "Teacher")
                            .param("searchKeyword", "name")
                            .param("pageNumber", "-1")
                            .param("pageSize", "2"))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testExportExcel(){
        var wb = new XSSFWorkbook();
        ArrayList<User> list = new ArrayList<>();    
        list.add(new User(UUID.randomUUID().toString(), "hieu", "hieu123@email.com", "de123233", "Student", "createdDate", "updatedDate"));
        list.add(new User(UUID.randomUUID().toString(), "khiem", "khiem123@email.com", "de123123", "Student", "123-23-23", "2323-232-232"));
        ExcelHelper.writeExcel(wb, list, "test.xlsx", "test");
    }
    @Test 
    void testImportExcel(){
       
    }
}