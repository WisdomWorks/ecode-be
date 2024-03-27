package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;

public interface EssaySubmissionService {
    EssaySubmission createSubmission(CreateEssaySubmissionRequest essaySubmission);

    EssaySubmission gradeSubmission(String essaySubmissionId, float score);
}
