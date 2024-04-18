package com.example.codeE.service.authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.user.LoginRequest;
import com.example.codeE.request.user.UserAuthenRequest;
import com.example.codeE.service.course.CourseService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthenImpl.class, AuthenticationManager.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthenImplDiffblueTest {
    @Autowired
    private AuthenImpl authenImpl;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private CourseService courseService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link AuthenImpl#signIn(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignIn() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();

        LoginRequest signInRequest = new LoginRequest();
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        UserAuthenRequest actualSignInResult = authenImpl.signIn(signInRequest, new Response());

        // Assert
        assertEquals("Cannot invoke \"com.example.codeE.repository.UserRepository.findUserByUserName(String)\" because"
                + " \"this.userRepository\" is null", actualSignInResult.getError());
        assertEquals("Something wrong when login", actualSignInResult.getMessage());
        assertEquals(500, actualSignInResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#signIn(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignIn2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();
        LoginRequest signInRequest = mock(LoginRequest.class);
        when(signInRequest.getUserName()).thenReturn("janedoe");
        doNothing().when(signInRequest).setPassword(Mockito.<String>any());
        doNothing().when(signInRequest).setUserName(Mockito.<String>any());
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        UserAuthenRequest actualSignInResult = authenImpl.signIn(signInRequest, new Response());

        // Assert
        verify(signInRequest).getUserName();
        verify(signInRequest).setPassword(eq("iloveyou"));
        verify(signInRequest).setUserName(eq("janedoe"));
        assertEquals("Cannot invoke \"com.example.codeE.repository.UserRepository.findUserByUserName(String)\" because"
                + " \"this.userRepository\" is null", actualSignInResult.getError());
        assertEquals("Something wrong when login", actualSignInResult.getMessage());
        assertEquals(500, actualSignInResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#signIn(LoginRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignIn3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3e3d6c92 testClass = com.example.codeE.service.authentication.DiffblueFakeClass1162, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        LoginRequest signInRequest = new LoginRequest();
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        authenImpl.signIn(signInRequest, new Response());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionAdmin(String, HttpServletResponse)}
     */
    @Test
    void testCreateNewSessionAdmin() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();

        // Act
        UserAuthenRequest actualCreateNewSessionAdminResult = authenImpl.createNewSessionAdmin("ABC123", new Response());

        // Assert
        assertEquals("Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs"
                + " must contain exactly 4.  Found: 0", actualCreateNewSessionAdminResult.getError());
        assertEquals("Something wrong when get new session", actualCreateNewSessionAdminResult.getMessage());
        assertEquals(500, actualCreateNewSessionAdminResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionAdmin(String, HttpServletResponse)}
     */
    @Test
    void testCreateNewSessionAdmin2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        UserAuthenRequest actualCreateNewSessionAdminResult = (new AuthenImpl()).createNewSessionAdmin("ABC123",
                mock(HttpServletResponseWrapper.class));

        // Assert
        assertEquals("Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs"
                + " must contain exactly 4.  Found: 0", actualCreateNewSessionAdminResult.getError());
        assertEquals("Something wrong when get new session", actualCreateNewSessionAdminResult.getMessage());
        assertEquals(500, actualCreateNewSessionAdminResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionAdmin(String, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewSessionAdmin3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@4447919c testClass = com.example.codeE.service.authentication.DiffblueFakeClass1076, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        authenImpl.createNewSessionAdmin("ABC123", new Response());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionUser(String, HttpServletResponse)}
     */
    @Test
    void testCreateNewSessionUser() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();

        // Act
        UserAuthenRequest actualCreateNewSessionUserResult = authenImpl.createNewSessionUser("ABC123", new Response());

        // Assert
        assertEquals("Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs"
                + " must contain exactly 4.  Found: 0", actualCreateNewSessionUserResult.getError());
        assertEquals("Something wrong when get new session", actualCreateNewSessionUserResult.getMessage());
        assertEquals(500, actualCreateNewSessionUserResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionUser(String, HttpServletResponse)}
     */
    @Test
    void testCreateNewSessionUser2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange and Act
        UserAuthenRequest actualCreateNewSessionUserResult = (new AuthenImpl()).createNewSessionUser("ABC123",
                mock(HttpServletResponseWrapper.class));

        // Assert
        assertEquals("Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs"
                + " must contain exactly 4.  Found: 0", actualCreateNewSessionUserResult.getError());
        assertEquals("Something wrong when get new session", actualCreateNewSessionUserResult.getMessage());
        assertEquals(500, actualCreateNewSessionUserResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#createNewSessionUser(String, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateNewSessionUser3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@69e75381 testClass = com.example.codeE.service.authentication.DiffblueFakeClass1119, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        authenImpl.createNewSessionUser("ABC123", new Response());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#SendForgetPasswordOTP(String, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSendForgetPasswordOTP() throws NoSuchMethodException {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3826a7cc testClass = com.example.codeE.service.authentication.DiffblueFakeClass1033, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        authenImpl.SendForgetPasswordOTP("janedoe", new Response());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#CheckOTP(String, String, HttpServletRequest)}
     */
    @Test
    void testCheckOTP() {
        // Arrange, Act and Assert
        assertFalse(authenImpl.CheckOTP("OTP", "42", new MockHttpServletRequest()));
    }

    /**
     * Method under test:
     * {@link AuthenImpl#CheckOTP(String, String, HttpServletRequest)}
     */
    @Test
    void testCheckOTP2() {
        // Arrange
        HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
        when(request.getCookies()).thenReturn(new Cookie[]{new Cookie("Name", "https://example.org/example")});

        // Act
        boolean actualCheckOTPResult = authenImpl.CheckOTP("OTP", "42", request);

        // Assert
        verify(request).getCookies();
        assertFalse(actualCheckOTPResult);
    }

    /**
     * Method under test: {@link AuthenImpl#updatePassword(String, String)}
     */
    @Test
    void testUpdatePassword() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertFalse((new AuthenImpl()).updatePassword("42", "iloveyou"));
    }

    /**
     * Method under test: {@link AuthenImpl#updatePassword(String, String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdatePassword2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@3bf17f2e testClass = com.example.codeE.service.authentication.DiffblueFakeClass1396, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange and Act
        authenImpl.updatePassword("42", "iloveyou");
    }

    /**
     * Method under test:
     * {@link AuthenImpl#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();

        LoginRequest signInRequest = new LoginRequest();
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        UserAuthenRequest actualSignInAdminResult = authenImpl.signInAdmin(signInRequest, new Response());

        // Assert
        assertEquals("Cannot invoke \"com.example.codeE.repository.UserRepository.findUserByUserName(String)\" because"
                + " \"this.userRepository\" is null", actualSignInAdminResult.getError());
        assertEquals("Something wrong when login", actualSignInAdminResult.getMessage());
        assertEquals(500, actualSignInAdminResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    void testSignInAdmin2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        AuthenImpl authenImpl = new AuthenImpl();
        LoginRequest signInRequest = mock(LoginRequest.class);
        when(signInRequest.getUserName()).thenReturn("janedoe");
        doNothing().when(signInRequest).setPassword(Mockito.<String>any());
        doNothing().when(signInRequest).setUserName(Mockito.<String>any());
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        UserAuthenRequest actualSignInAdminResult = authenImpl.signInAdmin(signInRequest, new Response());

        // Assert
        verify(signInRequest).getUserName();
        verify(signInRequest).setPassword(eq("iloveyou"));
        verify(signInRequest).setUserName(eq("janedoe"));
        assertEquals("Cannot invoke \"com.example.codeE.repository.UserRepository.findUserByUserName(String)\" because"
                + " \"this.userRepository\" is null", actualSignInAdminResult.getError());
        assertEquals("Something wrong when login", actualSignInAdminResult.getMessage());
        assertEquals(500, actualSignInAdminResult.getStatusCode());
    }

    /**
     * Method under test:
     * {@link AuthenImpl#signInAdmin(LoginRequest, HttpServletResponse)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignInAdmin3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@29f46c21 testClass = com.example.codeE.service.authentication.DiffblueFakeClass1279, locations = [], classes = [com.example.codeE.service.authentication.AuthenImpl, org.springframework.security.authentication.AuthenticationManager], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@62950b58, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@3d1983cd, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@daec7222, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@4e0d119d], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //       at java.base/java.util.Optional.map(Optional.java:260)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        LoginRequest signInRequest = new LoginRequest();
        signInRequest.setPassword("iloveyou");
        signInRequest.setUserName("janedoe");

        // Act
        authenImpl.signInAdmin(signInRequest, new Response());
    }
}
