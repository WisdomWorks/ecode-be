package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.request.exercise.code.CodeSubmissionsResponse;

import java.util.List;

public interface CodeSubmissionService {
    public void updateStatusAndResultBySubmissionIdAndStatus(String submissionId, String searchedStatus, String status, String result);
    public CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission);
    public void updateStatusAndResult(String submissionId, String status, String result);
    public CodeSubmission getCodeSubmissionById(String id);
    CodeSubmissionsResponse getCodeSubmissionResponseById(String id);
    public CodeSubmission updateCodeSubmission(CodeSubmission codeSubmission);
    public CodeSubmission saveCodeSubmission(CodeSubmission codeSubmission);
    public List<CodeSubmissionsResponse> getCodeSubmissionsByExerciseId(String exerciseId);
    public List<CodeSubmission> getCodeSubmissionByUserId(String exerciseId, String userId);
    public CodeSubmission getLastCodeSubmissionByUserId(String exerciseId, String userId);
}
