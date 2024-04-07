package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import com.example.codeE.request.exercise.file.response.FileSubmissionsResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileSubmissionService {
    FileSubmission createSubmission(CreateFileSubmissionRequest request, MultipartFile file);
    List<FileSubmissionsResponse> getFileSubmissionsByExerciseId(String exerciseId);
    FileSubmissionsResponse getFileSubmissionById(String submissionId);
    List<FileSubmission> getFileSubmissionByUserId(String exerciseId, String userId);
}
