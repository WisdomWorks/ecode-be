package com.example.codeE.judge.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.service.exercise.CodeExerciseImpl;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.common.RuntimeVersionImpl;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseImpl;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionImpl;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.LanguageLimitService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JudgeHandlerConfig.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JudgeHandlerConfigDiffblueTest {
    @Autowired
    private JudgeHandlerConfig judgeHandlerConfig;

    @MockBean
    private LanguageLimitService languageLimitService;

    /**
     * Method under test:
     * {@link JudgeHandlerConfig#judgeHandler(RuntimeVersionService, CodeSubmissionService, CodeExerciseService, LanguageLimitService, SubmissionTestCaseService)}
     */
    @Test
    void testJudgeHandler() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        JudgeHandlerConfig judgeHandlerConfig = new JudgeHandlerConfig();
        RuntimeVersionImpl runtimeVersionService = new RuntimeVersionImpl();
        CodeSubmissionImpl codeSubmissionService = new CodeSubmissionImpl();
        CodeExerciseImpl codeExerciseService = new CodeExerciseImpl();
        LanguageLimitService languageLimitService = mock(LanguageLimitService.class);
        SubmissionTestCaseImpl submissionTestCaseService = new SubmissionTestCaseImpl();

        // Act
        JudgeHandler actualJudgeHandlerResult = judgeHandlerConfig.judgeHandler(runtimeVersionService,
                codeSubmissionService, codeExerciseService, languageLimitService, submissionTestCaseService);

        // Assert
        Judge judge = actualJudgeHandlerResult.getJudge();
        assertEquals("100/Base64/characters/ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/ABCDEFGHIJKLMN",
                judge.getAuthKey());
        assertEquals("DefaultJudge", judge.getName());
        assertEquals("ExampleJudge", judge.getJudgeId());
        assertNull(judge.getDescription());
        assertEquals(13, actualJudgeHandlerResult.getHandlers().size());
        assertFalse(judge.getIsBlocked());
        assertFalse(judge.getIsDisabled());
        assertFalse(judge.getOnline());
        assertSame(codeExerciseService, actualJudgeHandlerResult.getCodeExerciseService());
        assertSame(runtimeVersionService, actualJudgeHandlerResult.getRuntimeVersionService());
        assertSame(submissionTestCaseService, actualJudgeHandlerResult.getSubmissionTestCaseService());
        assertSame(codeSubmissionService, actualJudgeHandlerResult.getCodeSubmissionService());
        assertSame(languageLimitService, actualJudgeHandlerResult.getLanguageLimitService());
    }

    /**
     * Method under test:
     * {@link JudgeHandlerConfig#judgeHandler(RuntimeVersionService, CodeSubmissionService, CodeExerciseService, LanguageLimitService, SubmissionTestCaseService)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testJudgeHandler2() {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'judgeHandler': Unsatisfied dependency expressed through field 'runtimeVersionService': No qualifying bean of type 'com.example.codeE.service.exercise.common.RuntimeVersionService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
        //       at com.example.codeE.judge.configurations.JudgeHandlerConfig$$SpringCGLIB$$0.judgeHandler(<generated>)
        //   org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.example.codeE.service.exercise.common.RuntimeVersionService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
        //       at com.example.codeE.judge.configurations.JudgeHandlerConfig$$SpringCGLIB$$0.judgeHandler(<generated>)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        RuntimeVersionImpl runtimeVersionService = new RuntimeVersionImpl();
        CodeSubmissionImpl codeSubmissionService = new CodeSubmissionImpl();
        CodeExerciseImpl codeExerciseService = new CodeExerciseImpl();
        LanguageLimitService languageLimitService2 = mock(LanguageLimitService.class);

        // Act
        judgeHandlerConfig.judgeHandler(runtimeVersionService, codeSubmissionService, codeExerciseService,
                languageLimitService2, new SubmissionTestCaseImpl());
    }
}
