package com.example.codeE.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.mapper.user.UserFromExcel;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.model.course.CourseTeacher;
import com.example.codeE.model.group.GroupStudent;
import com.example.codeE.model.user.User;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.CreateUserRequest;
import com.example.codeE.request.user.GetUsersRequest;
import com.example.codeE.request.user.UpdateUserRequest;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {UserImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserImplDiffblueTest {
    @Autowired
    private UserImpl userImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserImpl#getById(String)}
     */
    @Test
    void testGetById() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        User actualById = userImpl.getById("42");

        // Assert
        verify(userRepository).findById(eq("42"));
        LocalTime expectedToLocalTimeResult = actualById.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualById.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link UserImpl#getById(String)}
     */
    @Test
    void testGetById2() {
        // Arrange
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findById(Mockito.<String>any())).thenReturn(emptyResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getById("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#getById(String)}
     */
    @Test
    void testGetById3() {
        // Arrange
        when(userRepository.findById(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getById("42"));
        verify(userRepository).findById(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> actualAll = userImpl.getAll();

        // Assert
        verify(userRepository).findAll();
        assertTrue(actualAll.isEmpty());
        assertSame(userList, actualAll);
    }

    /**
     * Method under test: {@link UserImpl#getAll()}
     */
    @Test
    void testGetAll2() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getAll());
        verify(userRepository).findAll();
    }

    /**
     * Method under test: {@link UserImpl#getUsersByRoleOrAll(String)}
     */
    @Test
    void testGetUsersByRoleOrAll() {
        // Arrange, Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getUsersByRoleOrAll("Role"));
    }

    /**
     * Method under test: {@link UserImpl#getUsersByRoleOrAll(String)}
     */
    @Test
    void testGetUsersByRoleOrAll2() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> actualUsersByRoleOrAll = userImpl.getUsersByRoleOrAll("all");

        // Assert
        verify(userRepository).findAll();
        assertTrue(actualUsersByRoleOrAll.isEmpty());
        assertSame(userList, actualUsersByRoleOrAll);
    }

    /**
     * Method under test: {@link UserImpl#getUsersByRoleOrAll(String)}
     */
    @Test
    void testGetUsersByRoleOrAll3() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> actualUsersByRoleOrAll = userImpl.getUsersByRoleOrAll(null);

        // Assert
        verify(userRepository).findAll();
        assertTrue(actualUsersByRoleOrAll.isEmpty());
        assertSame(userList, actualUsersByRoleOrAll);
    }

    /**
     * Method under test: {@link UserImpl#getUsersByRoleOrAll(String)}
     */
    @Test
    void testGetUsersByRoleOrAll4() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new NoSuchElementException("all"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getUsersByRoleOrAll("all"));
        verify(userRepository).findAll();
    }

    /**
     * Method under test:
     * {@link UserImpl#getUsersByRoleAndSearchKeyword(GetUsersRequest)}
     */
    @Test
    void testGetUsersByRoleAndSearchKeyword() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findUsersByRoleAndSearchKeyword(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(userList);

        // Act
        List<User> actualUsersByRoleAndSearchKeyword = userImpl.getUsersByRoleAndSearchKeyword(new GetUsersRequest());

        // Assert
        verify(userRepository).findUsersByRoleAndSearchKeyword(isNull(), isNull());
        assertTrue(actualUsersByRoleAndSearchKeyword.isEmpty());
        assertSame(userList, actualUsersByRoleAndSearchKeyword);
    }

    /**
     * Method under test:
     * {@link UserImpl#getUsersByRoleAndSearchKeyword(GetUsersRequest)}
     */
    @Test
    void testGetUsersByRoleAndSearchKeyword2() {
        // Arrange
        when(userRepository.findUsersByRoleAndSearchKeyword(Mockito.<String>any(), Mockito.<String>any()))
                .thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getUsersByRoleAndSearchKeyword(new GetUsersRequest()));
        verify(userRepository).findUsersByRoleAndSearchKeyword(isNull(), isNull());
    }

    /**
     * Method under test: {@link UserImpl#createOne(CreateUserRequest)}
     */
    @Test
    void testCreateOne() {
        // Arrange, Act and Assert
        assertThrows(RuntimeException.class,
                () -> userImpl.createOne(new CreateUserRequest("Name", "jane.doe@example.org", "janedoe", "Role")));
        assertThrows(RuntimeException.class, () -> userImpl
                .createOne(new CreateUserRequest("abcdefghijklmnopqrstuvwxyz", "jane.doe@example.org", "janedoe", "Role")));
    }

    /**
     * Method under test: {@link UserImpl#updateById(String, UpdateUserRequest)}
     */
    @Test
    void testUpdateById() {
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
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setCourseStudents(new ArrayList<>());
        user2.setCourseTeachers(new ArrayList<>());
        user2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setEmail("jane.doe@example.org");
        user2.setGroupStudents(new ArrayList<>());
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUserId("42");
        user2.setUsername("janedoe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        User actualUpdateByIdResult = userImpl.updateById("42", new UpdateUserRequest());

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(userRepository).save(isA(User.class));
        LocalTime expectedToLocalTimeResult = actualUpdateByIdResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateByIdResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link UserImpl#updateById(String, UpdateUserRequest)}
     */
    @Test
    void testUpdateById2() {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.save(Mockito.<User>any())).thenThrow(new NoSuchElementException("foo"));
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.updateById("42", new UpdateUserRequest()));
        verify(userRepository).findById(eq("42"));
        verify(userRepository).save(isA(User.class));
    }

    /**
     * Method under test: {@link UserImpl#updateById(String, UpdateUserRequest)}
     */
    @Test
    void testUpdateById3() {
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
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setCourseStudents(new ArrayList<>());
        user2.setCourseTeachers(new ArrayList<>());
        user2.setCreatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setEmail("jane.doe@example.org");
        user2.setGroupStudents(new ArrayList<>());
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setRole("Role");
        user2.setUpdatedDate(LocalDate.of(1970, 1, 1).atStartOfDay());
        user2.setUserId("42");
        user2.setUsername("janedoe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findById(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        User actualUpdateByIdResult = userImpl.updateById("42", new UpdateUserRequest("42", "2020-03-01", "2020-03-01",
                "janedoe", "2020-03-01", "2020-03-01", LocalDate.of(1970, 1, 1).atStartOfDay()));

        // Assert
        verify(userRepository).findById(eq("42"));
        verify(userRepository).save(isA(User.class));
        LocalTime expectedToLocalTimeResult = actualUpdateByIdResult.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUpdateByIdResult.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link UserImpl#getUserByUserName(String, String)}
     */
    @Test
    void testGetUserByUserName() {
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
        when(userRepository.findUserByUserName(Mockito.<String>any())).thenReturn(user);

        // Act
        User actualUserByUserName = userImpl.getUserByUserName("Role", "janedoe");

        // Assert
        verify(userRepository).findUserByUserName(eq("janedoe"));
        LocalTime expectedToLocalTimeResult = actualUserByUserName.getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, actualUserByUserName.getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link UserImpl#getUserByUserName(String, String)}
     */
    @Test
    void testGetUserByUserName2() {
        // Arrange
        when(userRepository.findUserByUserName(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.getUserByUserName("Role", "janedoe"));
        verify(userRepository).findUserByUserName(eq("janedoe"));
    }

    /**
     * Method under test: {@link UserImpl#ChangePassword(String, String, String)}
     */
    @Test
    void testChangePassword() {
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(DataIntegrityViolationException.class, () -> userImpl.ChangePassword("42", "iloveyou", "iloveyou"));
        verify(userRepository).findUserByUserId(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#ChangePassword(String, String, String)}
     */
    @Test
    void testChangePassword2() {
        // Arrange
        User user = new User(new UserFromExcel("42", "Old password is incorrect", "jane.doe@example.org", "janedoe",
                "Old password is incorrect"), "iloveyou");
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(DataIntegrityViolationException.class, () -> userImpl.ChangePassword("42", "iloveyou", "iloveyou"));
        verify(userRepository).findUserByUserId(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#ChangePassword(String, String, String)}
     */
    @Test
    void testChangePassword3() {
        // Arrange
        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        doNothing().when(user).setCourseStudents(Mockito.<List<CourseStudent>>any());
        doNothing().when(user).setCourseTeachers(Mockito.<List<CourseTeacher>>any());
        doNothing().when(user).setCreatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(user).setEmail(Mockito.<String>any());
        doNothing().when(user).setGroupStudents(Mockito.<List<GroupStudent>>any());
        doNothing().when(user).setName(Mockito.<String>any());
        doNothing().when(user).setPassword(Mockito.<String>any());
        doNothing().when(user).setRole(Mockito.<String>any());
        doNothing().when(user).setUpdatedDate(Mockito.<LocalDateTime>any());
        doNothing().when(user).setUserId(Mockito.<String>any());
        doNothing().when(user).setUsername(Mockito.<String>any());
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
        when(userRepository.findUserByUserId(Mockito.<String>any())).thenReturn(user);

        // Act and Assert
        assertThrows(DataIntegrityViolationException.class, () -> userImpl.ChangePassword("42", "iloveyou", "iloveyou"));
        verify(user).getPassword();
        verify(user).setCourseStudents(isA(List.class));
        verify(user).setCourseTeachers(isA(List.class));
        verify(user).setCreatedDate(isA(LocalDateTime.class));
        verify(user).setEmail(eq("jane.doe@example.org"));
        verify(user).setGroupStudents(isA(List.class));
        verify(user).setName(eq("Name"));
        verify(user).setPassword(eq("iloveyou"));
        verify(user).setRole(eq("Role"));
        verify(user).setUpdatedDate(isA(LocalDateTime.class));
        verify(user).setUserId(eq("42"));
        verify(user).setUsername(eq("janedoe"));
        verify(userRepository).findUserByUserId(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#deleteById(String)}
     */
    @Test
    void testDeleteById() {
        // Arrange
        doNothing().when(userRepository).deleteById(Mockito.<String>any());
        when(userRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act
        userImpl.deleteById("42");

        // Assert that nothing has changed
        verify(userRepository).deleteById(eq("42"));
        verify(userRepository).existsById(eq("42"));
        assertTrue(userImpl.getAll().isEmpty());
    }

    /**
     * Method under test: {@link UserImpl#deleteById(String)}
     */
    @Test
    void testDeleteById2() {
        // Arrange
        doThrow(new NoSuchElementException("foo")).when(userRepository).deleteById(Mockito.<String>any());
        when(userRepository.existsById(Mockito.<String>any())).thenReturn(true);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.deleteById("42"));
        verify(userRepository).deleteById(eq("42"));
        verify(userRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#deleteById(String)}
     */
    @Test
    void testDeleteById3() {
        // Arrange
        when(userRepository.existsById(Mockito.<String>any())).thenReturn(false);

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.deleteById("42"));
        verify(userRepository).existsById(eq("42"));
    }

    /**
     * Method under test: {@link UserImpl#paginateUsers(GetUsersRequest)}
     */
    @Test
    void testPaginateUsers() {
        // Arrange
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findUsersByRoleAndSearchKeywordWithPagination(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenReturn(userList);

        // Act
        List<User> actualPaginateUsersResult = userImpl.paginateUsers(new GetUsersRequest());

        // Assert
        verify(userRepository).findUsersByRoleAndSearchKeywordWithPagination(isNull(), isNull(), isNull());
        assertTrue(actualPaginateUsersResult.isEmpty());
        assertSame(userList, actualPaginateUsersResult);
    }

    /**
     * Method under test: {@link UserImpl#paginateUsers(GetUsersRequest)}
     */
    @Test
    void testPaginateUsers2() {
        // Arrange
        when(userRepository.findUsersByRoleAndSearchKeywordWithPagination(Mockito.<String>any(), Mockito.<String>any(),
                Mockito.<Pageable>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.paginateUsers(new GetUsersRequest()));
        verify(userRepository).findUsersByRoleAndSearchKeywordWithPagination(isNull(), isNull(), isNull());
    }

    /**
     * Method under test: {@link UserImpl#saveUserToDatabase(MultipartFile)}
     */
    @Test
    void testSaveUserToDatabase() throws IOException {
        // Arrange and Act
        ResponseEntity<Map<String, String>> actualSaveUserToDatabaseResult = userImpl
                .saveUserToDatabase(new MockMultipartFile("Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));

        // Assert
        assertEquals(1, actualSaveUserToDatabaseResult.getBody().size());
        assertEquals(400, actualSaveUserToDatabaseResult.getStatusCodeValue());
        assertTrue(actualSaveUserToDatabaseResult.hasBody());
        assertTrue(actualSaveUserToDatabaseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserImpl#saveUserToDatabase(MultipartFile)}
     */
    @Test
    void testSaveUserToDatabase2() throws IOException {
        // Arrange
        DataInputStream contentStream = mock(DataInputStream.class);
        when(contentStream.readAllBytes()).thenReturn("AXAXAXAX".getBytes("UTF-8"));
        doNothing().when(contentStream).close();

        // Act
        ResponseEntity<Map<String, String>> actualSaveUserToDatabaseResult = userImpl
                .saveUserToDatabase(new MockMultipartFile("Name", contentStream));

        // Assert
        verify(contentStream).close();
        verify(contentStream).readAllBytes();
        assertEquals(1, actualSaveUserToDatabaseResult.getBody().size());
        assertEquals(400, actualSaveUserToDatabaseResult.getStatusCodeValue());
        assertTrue(actualSaveUserToDatabaseResult.hasBody());
        assertTrue(actualSaveUserToDatabaseResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link UserImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        when(userRepository.findUserByUserName(Mockito.<String>any())).thenReturn(user);

        // Act
        UserDetails actualLoadUserByUsernameResult = userImpl.loadUserByUsername("janedoe");

        // Assert
        verify(userRepository).findUserByUserName(eq("janedoe"));
        LocalTime expectedToLocalTimeResult = ((User) actualLoadUserByUsernameResult).getUpdatedDate().toLocalTime();
        assertSame(expectedToLocalTimeResult, ((User) actualLoadUserByUsernameResult).getCreatedDate().toLocalTime());
    }

    /**
     * Method under test: {@link UserImpl#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        when(userRepository.findUserByUserName(Mockito.<String>any())).thenThrow(new NoSuchElementException("foo"));

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> userImpl.loadUserByUsername("janedoe"));
        verify(userRepository).findUserByUserName(eq("janedoe"));
    }
}
