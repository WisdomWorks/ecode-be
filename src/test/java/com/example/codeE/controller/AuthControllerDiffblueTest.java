package com.example.codeE.controller;

import static org.mockito.Mockito.when;

import com.example.codeE.helper.JWTUtils;
import com.example.codeE.model.user.User;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import com.example.codeE.service.authentication.AuthenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthControllerDiffblueTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenService authenService;

    @MockBean
    private JWTUtils jWTUtils;

    /**
     * Method under test:
     * {@link AuthController#checkSessionAdmin(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckSessionAdmin() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/check-session/admin");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"UNAUTHORIZED\",\"message\":\"No user has been saved in cookie, please login"
                                        + " again\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(1);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signInAdmin(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":1,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123\""
                                        + ",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin2() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(200);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signInAdmin(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":200,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin3() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(403);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signInAdmin(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":403,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin4() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(404);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signInAdmin(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":404,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin5() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(401);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signInAdmin(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#checkSessionAdmin(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckSessionAdmin2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/check-session/admin");
        requestBuilder.contentType("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"UNAUTHORIZED\",\"message\":\"No user has been saved in cookie, please login"
                                        + " again\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#checkSessionUser(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckSessionUser() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/check-session/user");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"UNAUTHORIZED\",\"message\":\"No user has been saved in cookie, please login"
                                        + " again\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#checkSessionUser(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testCheckSessionUser2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/auth/check-session/user");
        requestBuilder.contentType("https://example.org/example");

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"UNAUTHORIZED\",\"message\":\"No user has been saved in cookie, please login"
                                        + " again\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#Logout(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testLogout() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/logout");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":200,\"message\":\"Logout Successfully\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#Logout(HttpServletRequest, HttpServletResponse)}
     */
    @Test
    void testLogout2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/logout", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"statusCode\":200,\"message\":\"Logout Successfully\"}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInUser(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInUser() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(1);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signIn(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":1,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123\""
                                        + ",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInUser(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInUser2() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(200);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signIn(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":200,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInUser(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInUser3() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(403);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signIn(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":403,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInUser(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInUser4() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(404);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signIn(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":404,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }

    /**
     * Method under test:
     * {@link AuthController#signInUser(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInUser5() throws Exception {
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

        UserAuthenRequest userAuthenRequest = new UserAuthenRequest();
        userAuthenRequest.setCourses(new ArrayList<>());
        userAuthenRequest.setError("An error occurred");
        userAuthenRequest.setExpirationTime("Expiration Time");
        userAuthenRequest.setMessage("Not all who wander are lost");
        userAuthenRequest.setRefreshToken("ABC123");
        userAuthenRequest.setStatusCode(401);
        userAuthenRequest.setToken("ABC123");
        userAuthenRequest.setUser(user);
        when(authenService.signIn(Mockito.<LoginRequest>any(), Mockito.<HttpServletResponse>any()))
                .thenReturn(userAuthenRequest);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("iloveyou");
        loginRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/login/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"statusCode\":401,\"error\":\"An error occurred\",\"message\":\"Not all who wander are lost\",\"token\":\"ABC123"
                                        + "\",\"refreshToken\":\"ABC123\",\"expirationTime\":\"Expiration Time\",\"user\":{\"userId\":\"42\",\"name\":\"Name\",\"email"
                                        + "\":\"jane.doe@example.org\",\"username\":\"janedoe\",\"role\":\"Role\",\"createdDate\":\"1970-01-01 00:00:00\","
                                        + "\"updatedDate\":\"1970-01-01 00:00:00\",\"authorities\":[{\"authority\":\"Role\"}],\"enabled\":true,\"credentials"
                                        + "NonExpired\":true,\"accountNonLocked\":true,\"accountNonExpired\":true},\"courses\":[]}"));
    }
}
