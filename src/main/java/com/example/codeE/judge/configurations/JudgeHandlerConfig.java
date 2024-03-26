package com.example.codeE.judge.configurations;

import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.service.exercise.CodeExerciseService;
import com.example.codeE.service.exercise.common.RuntimeVersionService;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.LanguageLimitService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class JudgeHandlerConfig {
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JudgeHandler judgeHandler(RuntimeVersionService runtimeVersionService,
                                     CodeSubmissionRepository codeSubmissionRepository,
                                     CodeSubmissionService codeSubmissionService,
                                     CodeExerciseService codeExerciseService,
                                     LanguageLimitService languageLimitService,
                                     SubmissionTestCaseService submissionTestCaseService) {
        JudgeHandler judgeHandler = new JudgeHandler();
        judgeHandler.setRuntimeVersionService(runtimeVersionService);
        judgeHandler.setCodeSubmissionRepository(codeSubmissionRepository);
        judgeHandler.setCodeSubmissionService(codeSubmissionService);
        judgeHandler.setCodeExerciseService(codeExerciseService);
        judgeHandler.setLanguageLimitService(languageLimitService);
        judgeHandler.setSubmissionTestCaseService(submissionTestCaseService);
        return judgeHandler;
    }
}
