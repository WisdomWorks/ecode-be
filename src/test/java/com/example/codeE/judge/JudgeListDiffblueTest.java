package com.example.codeE.judge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.judge.handlers.JudgeHandler;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JudgeList.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JudgeListDiffblueTest {
    @MockBean
    private JudgeHandler judgeHandler;

    @Autowired
    private JudgeList judgeList;

    /**
     * Method under test: {@link JudgeList#handleFreeJudge()}
     */
    @Test
    void testHandleFreeJudge() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.handleFreeJudge();

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#handleFreeJudge()}
     */
    @Test
    void testHandleFreeJudge2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(mock(JudgeHandler.class));

        // Act
        judgeList.handleFreeJudge();

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#handleFreeJudge()}
     */
    @Test
    void testHandleFreeJudge3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     JudgeList.judge
        //     JudgeList.priorities

        // Arrange and Act
        judgeList.handleFreeJudge();
    }

    /**
     * Method under test: {@link JudgeList#register()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegister() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.codeE.service.judge.JudgeService.disconnectJudge(com.example.codeE.model.exercise.common.Judge, boolean)" because "<local3>.judgeService" is null
        //       at com.example.codeE.model.exercise.common.Judge.disconnect(Judge.java:58)
        //       at com.example.codeE.judge.JudgeList.disconnect(JudgeList.java:86)
        //       at com.example.codeE.judge.JudgeList.register(JudgeList.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        judgeList.register();
    }

    /**
     * Method under test: {@link JudgeList#disconnect(boolean)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDisconnect() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.codeE.service.judge.JudgeService.disconnectJudge(com.example.codeE.model.exercise.common.Judge, boolean)" because "this.judgeService" is null
        //       at com.example.codeE.model.exercise.common.Judge.disconnect(Judge.java:58)
        //       at com.example.codeE.judge.JudgeList.disconnect(JudgeList.java:86)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange and Act
        judgeList.disconnect(true);
    }

    /**
     * Method under test: {@link JudgeList#updateProblems()}
     */
    @Test
    void testUpdateProblems() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.updateProblems();

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#updateProblems()}
     */
    @Test
    void testUpdateProblems2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(mock(JudgeHandler.class));

        // Act
        judgeList.updateProblems();

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#updateProblems()}
     */
    @Test
    void testUpdateProblems3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     JudgeList.judge
        //     JudgeList.priorities

        // Arrange and Act
        judgeList.updateProblems();
    }

    /**
     * Method under test: {@link JudgeList#updateDisableJudge(boolean)}
     */
    @Test
    void testUpdateDisableJudge() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.updateDisableJudge(true);

        // Assert
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#updateDisableJudge(boolean)}
     */
    @Test
    void testUpdateDisableJudge2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(mock(JudgeHandler.class));

        // Act
        judgeList.updateDisableJudge(true);

        // Assert
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#updateDisableJudge(boolean)}
     */
    @Test
    void testUpdateDisableJudge3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     JudgeList.judge
        //     JudgeList.priorities

        // Arrange and Act
        judgeList.updateDisableJudge(true);
    }

    /**
     * Method under test: {@link JudgeList#onJudgeFree()}
     */
    @Test
    void testOnJudgeFree() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.onJudgeFree();

        // Assert
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#onJudgeFree()}
     */
    @Test
    void testOnJudgeFree2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(mock(JudgeHandler.class));

        // Act
        judgeList.onJudgeFree();

        // Assert
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test: {@link JudgeList#onJudgeFree()}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testOnJudgeFree3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Diffblue AI was unable to find a test

        // Arrange
        doNothing().when(judgeHandler)
                .submit(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

        // Act
        judgeList.onJudgeFree();

        // Assert
        verify(judgeHandler).submit(eq("Submission Id"), eq("Problem"), eq("en"), eq("Source"));
    }

    /**
     * Method under test: {@link JudgeList#abort(String)}
     */
    @Test
    void testAbort() {
        // Arrange, Act and Assert
        assertFalse(judgeList.abort("Submission"));
    }

    /**
     * Method under test: {@link JudgeList#checkPriority(int)}
     */
    @Test
    void testCheckPriority() {
        // Arrange, Act and Assert
        assertTrue(judgeList.checkPriority(1));
        assertFalse(judgeList.checkPriority(4));
        assertFalse(judgeList.checkPriority(-1));
    }

    /**
     * Method under test:
     * {@link JudgeList#judge(String, String, String, String, String, int)}
     */
    @Test
    void testJudge() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.judge("42", "Problem", "en", "Source", "42", 1);

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test:
     * {@link JudgeList#judge(String, String, String, String, String, int)}
     */
    @Test
    void testJudge2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.judge("foo", null, null, null, "foo", 1);

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test:
     * {@link JudgeList#judge(String, String, String, String, String, int)}
     */
    @Test
    void testJudge3() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(new JudgeHandler());

        // Act
        judgeList.judge("foo", null, null, "foo", "foo", 1);

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test:
     * {@link JudgeList#judge(String, String, String, String, String, int)}
     */
    @Test
    void testJudge4() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeList judgeList = new JudgeList(mock(JudgeHandler.class));

        // Act
        judgeList.judge("42", "Problem", "en", "Source", "42", 1);

        // Assert that nothing has changed
        assertFalse(judgeList.abort("Submission"));
        assertTrue(judgeList.checkPriority(1));
    }

    /**
     * Method under test:
     * {@link JudgeList#judge(String, String, String, String, String, int)}
     */
    @Test
    void testJudge5() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     JudgeList.judge
        //     JudgeList.priorities

        // Arrange
        when(judgeHandler.isWorking()).thenReturn(true);
        doNothing().when(judgeHandler)
                .submit(Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any());

        // Act
        judgeList.judge("42", "Problem", "en", "Source", "42", 1);
    }
}
