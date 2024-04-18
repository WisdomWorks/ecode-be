package com.example.codeE.service.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.repository.SessionExerciseRepository;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.catalina.connector.Response;
import org.apache.catalina.connector.ResponseFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.DelegatingServletInputStream;
import org.springframework.mock.web.MockHttpServletMapping;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SessionExerciseImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SessionExerciseImplDiffblueTest {
    @Autowired
    private SessionExerciseImpl sessionExerciseImpl;

    @MockBean
    private SessionExerciseRepository sessionExerciseRepository;

    /**
     * Method under test:
     * {@link SessionExerciseImpl#removeSession(HttpServletResponse, HttpServletRequest)}
     */
    @Test
    void testRemoveSession() throws IOException {
        // Arrange
        when(sessionExerciseRepository.findAll()).thenReturn(new ArrayList<>());
        Response response = new Response();
        MockHttpServletRequest request = new MockHttpServletRequest();

        // Act
        sessionExerciseImpl.removeSession(response, request);

        // Assert that nothing has changed
        verify(sessionExerciseRepository).findAll();
        HttpServletResponse response2 = response.getResponse();
        assertTrue(response2 instanceof ResponseFacade);
        assertTrue(request.getInputStream() instanceof DelegatingServletInputStream);
        assertTrue(request.getHttpServletMapping() instanceof MockHttpServletMapping);
        assertTrue(request.getSession() instanceof MockHttpSession);
        assertEquals("", request.getContextPath());
        assertEquals("", request.getMethod());
        assertEquals("", request.getRequestURI());
        assertEquals("", request.getServletPath());
        assertEquals("HTTP/1.1", request.getProtocol());
        assertEquals("http", request.getScheme());
        assertEquals("localhost", request.getLocalName());
        assertEquals("localhost", request.getRemoteHost());
        assertEquals("localhost", request.getServerName());
        assertEquals(80, request.getLocalPort());
        assertEquals(80, request.getRemotePort());
        assertEquals(80, request.getServerPort());
        assertEquals(DispatcherType.REQUEST, request.getDispatcherType());
        assertFalse(request.isAsyncStarted());
        assertFalse(request.isAsyncSupported());
        assertFalse(request.isRequestedSessionIdFromURL());
        assertTrue(request.isActive());
        assertTrue(request.isRequestedSessionIdFromCookie());
        assertTrue(request.isRequestedSessionIdValid());
        ServletOutputStream expectedOutputStream = response.getOutputStream();
        assertSame(expectedOutputStream, response2.getOutputStream());
    }
}
