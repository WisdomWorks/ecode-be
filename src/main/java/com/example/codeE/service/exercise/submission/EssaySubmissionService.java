package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;

import java.util.List;

public interface EssaySubmissionService {
    EssaySubmission createSubmission(CreateEssaySubmissionRequest essaySubmission);
    List<EssaySubmission> getEssaySubmissionByExerciseId(String exerciseId);
    AllSubmissionResponse getEssaySubmissionsByExerciseId(String exerciseId, List<String> groupFilter);
    EssaySubmissionsResponse getEssaySubmission(String submissionId);
    List<EssaySubmission> getEssaySubmissionByUserId(String exerciseId, String userId);
    EssaySubmission gradeSubmission(String essaySubmissionId, float score);
    EssaySubmission getLastEssaySubmissionByUserId(String exerciseId, String userId);
}
