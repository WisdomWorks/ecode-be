package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;

public interface EssaySubmissionService {
    EssaySubmission createSubmission(EssaySubmission essaySubmission);

    EssaySubmission gradeSubmission(String essaySubmissionId, float score);
}
