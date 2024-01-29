package com.example.codeE.controller;

import com.example.codeE.service.user.UserImpl;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserImpl userImplement;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        try {
            int totalRecords = userImplement.getAllUsers().size();
            System.out.println(totalRecords);
            mockMvc.perform(MockMvcRequestBuilders
                            .get("/users")
                            .param("role", "1")
                            .param("searchKeyword", "nyan")
                            .param("pageNumber", "0")
                            .param("pageSize", "5"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.totalRecords").value(totalRecords))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.pageSize").value(5))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.pageNumber").value(0))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.pagination.totalPage").value((int) Math.ceil((double) totalRecords / 5)))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.users", Matchers.hasSize(5)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}