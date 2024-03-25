package com.example.codeE.service.judge;

import com.example.codeE.model.exercise.common.LanguageLimit;
import com.example.codeE.repository.LanguageLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LanguageLimitImpl implements LanguageLimitService{
    @Autowired
    private LanguageLimitRepository languageLimitRepository;

    @Override
    public LanguageLimit findByProblemIdAndLanguageId(String problemId, String languageId) {
        return languageLimitRepository.findByProblemIdAndLanguageId(problemId, languageId);
    }
}
