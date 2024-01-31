package com.example.codeE.service.user;

import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.GetUsersRequest;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class UserImplTest {
    @InjectMocks
    private UserImpl userImplement;
    @Mock
    private UserRepository userRepository;

    private List<User> mockDataUser;

    @BeforeEach
    void setUp() {
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
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(mockDataUser);
        assertEquals(mockDataUser, userImplement.getAllUsers());
    }

    @Test
    void getUsersByRoleAndSearchKeyword() {
        when(userRepository.findUsersByRoleAndSearchKeyword(Mockito.any(), Mockito.any())).thenReturn(mockDataUser);
        assertEquals(mockDataUser, userImplement
                .getUsersByRoleAndSearchKeyword(new GetUsersRequest("Teacher", "name", 1, 2)));
    }

    @Test
    void paginateUsers() {
        when(userRepository
                .findUsersByRoleAndSearchKeywordWithPagination(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(mockDataUser);
        assertEquals(mockDataUser, userImplement.paginateUsers(new GetUsersRequest("Teacher", "name", 1, 2)));
    }
}