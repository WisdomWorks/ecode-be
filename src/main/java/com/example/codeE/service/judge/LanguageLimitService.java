package com.example.codeE.service.judge;

import com.example.codeE.model.exercise.common.LanguageLimit;
import org.springframework.stereotype.Service;

@Service
public interface LanguageLimitService {
    LanguageLimit findByProblemIdAndLanguageId(String problemId, String languageId);
}
