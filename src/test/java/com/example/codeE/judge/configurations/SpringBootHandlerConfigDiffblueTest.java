package com.example.codeE.judge.configurations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import com.example.codeE.judge.JudgeList;
import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.judge.handlers.SpringBootHandler;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.LanguageLimitService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SpringBootHandlerConfig.class, JudgeList.class, JudgeHandler.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SpringBootHandlerConfigDiffblueTest {
    @MockBean
    private CodeExerciseService codeExerciseService;

    @MockBean
    private CodeSubmissionService codeSubmissionService;

    @Autowired
    private JudgeList judgeList;

    @MockBean
    private LanguageLimitService languageLimitService;

    @MockBean
    private RuntimeVersionService runtimeVersionService;

    @Autowired
    private SpringBootHandlerConfig springBootHandlerConfig;

    @MockBean
    private SubmissionTestCaseService submissionTestCaseService;

    /**
     * Method under test:
     * {@link SpringBootHandlerConfig#springBootHandler(JudgeList)}
     */
    @Test
    void testSpringBootHandler() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        SpringBootHandlerConfig springBootHandlerConfig = new SpringBootHandlerConfig();
        JudgeList judges = new JudgeList(new JudgeHandler());

        // Act
        SpringBootHandler actualSpringBootHandlerResult = springBootHandlerConfig.springBootHandler(judges);

        // Assert
        assertFalse(judges.abort("Submission"));
        assertTrue(judges.checkPriority(1));
        assertTrue(actualSpringBootHandlerResult.isSharable());
    }

    /**
     * Method under test:
     * {@link SpringBootHandlerConfig#springBootHandler(JudgeList)}
     */
    @Test
    void testSpringBootHandler2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        SpringBootHandlerConfig springBootHandlerConfig = new SpringBootHandlerConfig();
        JudgeList judges = new JudgeList(mock(JudgeHandler.class));

        // Act
        SpringBootHandler actualSpringBootHandlerResult = springBootHandlerConfig.springBootHandler(judges);

        // Assert
        assertFalse(judges.abort("Submission"));
        assertTrue(judges.checkPriority(1));
        assertTrue(actualSpringBootHandlerResult.isSharable());
    }

    /**
     * Method under test:
     * {@link SpringBootHandlerConfig#springBootHandler(JudgeList)}
     */
    @Test
    void testSpringBootHandler3() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Missing observers.
        //   Diffblue Cover was unable to create an assertion.
        //   Add getters for the following fields or make them package-private:
        //     SpringBootHandler.judges
        //     SpringBootHandler.methodMap
        //     ChannelHandlerAdapter.added
        //     JudgeList.judge
        //     JudgeList.priorities

        // Arrange and Act
        springBootHandlerConfig.springBootHandler(judgeList);
    }
}
