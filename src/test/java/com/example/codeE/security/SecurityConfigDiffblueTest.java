package com.example.codeE.security;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.codeE.service.user.UserImpl;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@ContextConfiguration(classes = {SecurityConfig.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SecurityConfigDiffblueTest {
    @MockBean
    private Environment environment;

    @MockBean
    private JWTAuthFilter jWTAuthFilter;

    @Autowired
    private SecurityConfig securityConfig;

    @MockBean
    private UserImpl userImpl;

    /**
     * Method under test: {@link SecurityConfig#securityFilterChain(HttpSecurity)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSecurityFilterChain() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.IllegalArgumentException: objectPostProcessor cannot be null
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        AuthenticationManagerBuilder authenticationBuilder = new AuthenticationManagerBuilder(null);

        // Act
        securityConfig.securityFilterChain(new HttpSecurity(null, authenticationBuilder, new HashMap<>()));
    }

    /**
     * Method under test: {@link SecurityConfig#corsConfigurationSource()}
     */
    @Test
    void testCorsConfigurationSource() {
        // Arrange, Act and Assert
        assertTrue(securityConfig.corsConfigurationSource() instanceof UrlBasedCorsConfigurationSource);
    }

    /**
     * Method under test: {@link SecurityConfig#authenticationProvider()}
     */
    @Test
    void testAuthenticationProvider() {
        // Arrange, Act and Assert
        assertTrue(
                ((DaoAuthenticationProvider) securityConfig.authenticationProvider()).getUserCache() instanceof NullUserCache);
    }

    /**
     * Method under test: {@link SecurityConfig#passwordEncoder()}
     */
    @Test
    void testPasswordEncoder() {
        // Arrange, Act and Assert
        assertTrue(securityConfig.passwordEncoder() instanceof BCryptPasswordEncoder);
    }

    /**
     * Method under test:
     * {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager() throws Exception {
        // Arrange, Act and Assert
        assertTrue(
                ((ProviderManager) securityConfig.authenticationManager(new AuthenticationConfiguration())).getProviders()
                        .get(0) instanceof DaoAuthenticationProvider);
    }

    /**
     * Method under test:
     * {@link SecurityConfig#authenticationManager(AuthenticationConfiguration)}
     */
    @Test
    void testAuthenticationManager2() throws Exception {
        // Arrange
        AuthenticationConfiguration authenticationConfiguration = new AuthenticationConfiguration();
        authenticationConfiguration.setApplicationContext(mock(AnnotationConfigApplicationContext.class));

        // Act and Assert
        assertTrue(((ProviderManager) securityConfig.authenticationManager(authenticationConfiguration)).getProviders()
                .get(0) instanceof DaoAuthenticationProvider);
    }
}
