package com.example.codeE.helper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.user.User;

import java.util.HashMap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JWTUtils.class})
@ExtendWith(SpringExtension.class)
class JWTUtilsDiffblueTest {
    @Autowired
    private JWTUtils jWTUtils;

    /**
     * Method under test: {@link JWTUtils#generateToken(UserDetails)}
     */
    @Test
    void testGenerateToken() {
        // Arrange
        User userDetails = mock(User.class);
        when(userDetails.getUsername()).thenReturn("janedoe");

        // Act
        jWTUtils.generateToken(userDetails);

        // Assert
        verify(userDetails).getUsername();
    }

    /**
     * Method under test:
     * {@link JWTUtils#generateRefreshToken(HashMap, UserDetails)}
     */
    @Test
    void testGenerateRefreshToken() {
        // Arrange
        HashMap<String, Object> claims = new HashMap<>();
        User userDetails = mock(User.class);
        when(userDetails.getUsername()).thenReturn("janedoe");

        // Act
        jWTUtils.generateRefreshToken(claims, userDetails);

        // Assert
        verify(userDetails).getUsername();
    }

    /**
     * Method under test: {@link JWTUtils#extractUserName(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testExtractUserName() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: 0
        //       at io.jsonwebtoken.impl.JwtTokenizer.tokenize(JwtTokenizer.java:102)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:370)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821)
        //       at com.example.codeE.helper.JWTUtils.extractClaims(JWTUtils.java:46)
        //       at com.example.codeE.helper.JWTUtils.extractUserName(JWTUtils.java:42)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jWTUtils.extractUserName("ABC123");
    }

    /**
     * Method under test: {@link JWTUtils#isTokenValid(String, UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenValid() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: 0
        //       at io.jsonwebtoken.impl.JwtTokenizer.tokenize(JwtTokenizer.java:102)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:370)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821)
        //       at com.example.codeE.helper.JWTUtils.extractClaims(JWTUtils.java:46)
        //       at com.example.codeE.helper.JWTUtils.extractUserName(JWTUtils.java:42)
        //       at com.example.codeE.helper.JWTUtils.isTokenValid(JWTUtils.java:49)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jWTUtils.isTokenValid("ABC123", new User());
    }

    /**
     * Method under test: {@link JWTUtils#isTokenExpired(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testIsTokenExpired() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: Invalid compact JWT string: Compact JWSs must contain exactly 2 period characters, and compact JWEs must contain exactly 4.  Found: 0
        //       at io.jsonwebtoken.impl.JwtTokenizer.tokenize(JwtTokenizer.java:102)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:370)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:362)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:94)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:36)
        //       at io.jsonwebtoken.impl.io.AbstractParser.parse(AbstractParser.java:29)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseSignedClaims(DefaultJwtParser.java:821)
        //       at com.example.codeE.helper.JWTUtils.extractClaims(JWTUtils.java:46)
        //       at com.example.codeE.helper.JWTUtils.isTokenExpired(JWTUtils.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        jWTUtils.isTokenExpired("ABC123");
    }
}
