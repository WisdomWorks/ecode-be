package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.request.exercise.file.CreateFileSubmissionRequest;
import org.springframework.web.multipart.MultipartFile;

public interface FileSubmissionService {
    FileSubmission createSubmission(CreateFileSubmissionRequest request, MultipartFile file);
}
