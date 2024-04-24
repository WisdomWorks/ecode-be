package com.example.codeE.service.judge;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.common.LanguageLimit;
import com.example.codeE.repository.LanguageLimitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {LanguageLimitImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class LanguageLimitImplDiffblueTest {
    @Autowired
    private LanguageLimitImpl languageLimitImpl;

    @MockBean
    private LanguageLimitRepository languageLimitRepository;

    /**
     * Method under test:
     * {@link LanguageLimitImpl#findByProblemIdAndLanguageId(String, String)}
     */
    @Test
    void testFindByProblemIdAndLanguageId() {
        // Arrange
        LanguageLimit languageLimit = new LanguageLimit();
        languageLimit.setLanguageId("en");
        languageLimit.setLanguageLimitId("en");
        languageLimit.setMemoryLimit(1);
        languageLimit.setProblemId("42");
        languageLimit.setTimeLimit(10.0d);
        when(languageLimitRepository.findByProblemIdAndLanguageId(Mockito.<String>any(), Mockito.<String>any()))
                .thenReturn(languageLimit);

        // Act
        LanguageLimit actualFindByProblemIdAndLanguageIdResult = languageLimitImpl.findByProblemIdAndLanguageId("42", "en");

        // Assert
        verify(languageLimitRepository).findByProblemIdAndLanguageId(eq("42"), eq("en"));
        assertSame(languageLimit, actualFindByProblemIdAndLanguageIdResult);
    }
}
