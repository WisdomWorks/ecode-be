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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime dateTime = LocalDateTime.parse("2024-01-28 05:10:52", formatter);
    @BeforeEach
    void setUp() {
        this.mockDataUser = new ArrayList<>();
        this.mockDataUser.add(new User("usr1", "user1", "user1@gmail.com", "username1", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr2", "user2", "user2@gmail.com", "username2", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr3", "user3", "user3@gmail.com", "username3", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr4", "user4", "user4@gmail.com", "username4", "123", "Teacher", dateTime, dateTime));
        this.mockDataUser.add(new User("usr5", "user5", "user5@gmail.com", "username5", "123", "Teacher", dateTime, dateTime));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllUsers() {
        when(userRepository.findAll()).thenReturn(mockDataUser);
        assertEquals(mockDataUser, userImplement.getAll());
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
    @Test
    void getUserByUserName(){
        when(userRepository.findByUserName(mockDataUser.get(1).getUsername())).thenReturn(mockDataUser.get(1));
        assertEquals(mockDataUser.get(1), userImplement.getUserByUserName("username1"));
    }
}