package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;

import java.util.List;

public interface EssaySubmissionService {
    EssaySubmission createSubmission(CreateEssaySubmissionRequest essaySubmission);
    List<EssaySubmission> getEssaySubmissionByExerciseId(String exerciseId);
    EssaySubmission getEssayQuizSubmission(String submissionId);
    List<EssaySubmission> getEssaySubmissionByUserId(String exerciseId, String userId);
    EssaySubmission gradeSubmission(String essaySubmissionId, float score);
}
