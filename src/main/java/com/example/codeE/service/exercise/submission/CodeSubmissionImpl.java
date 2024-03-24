package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.repository.CodeSubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeSubmissionImpl implements CodeSubmissionService{
    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;

    @Override
    public CodeSubmission checkStatusAndUpdate(CodeSubmission codeSubmission) {
        CodeSubmission submission = codeSubmissionRepository.findById(codeSubmission.getSubmissionId()).get();
        if (!submission.getStatus().equals("P") && !submission.getStatus().equals("G")) {
            return codeSubmissionRepository.save(codeSubmission);
        }
        return null;
    }

    @Override
    public void updateStatusAndResult(String submissionId, String status, String result) {
        CodeSubmission submission = codeSubmissionRepository.findById(submissionId).get();
        submission.setStatus(status);
        submission.setResult(result);
        if (status.equals("AB") && result.equals("AB")) {
            submission.setScore(0.0f);
        }
        codeSubmissionRepository.save(submission);
    }

    @Override
    public void updateStatusAndResultBySubmissionIdAndStatus(String submissionId, String searchedStatus, String status, String result) {
        CodeSubmission submission = codeSubmissionRepository.findById(submissionId).get();
        if (submission.getStatus().equals(searchedStatus)) {
            submission.setStatus(status);
            submission.setResult(result);
            codeSubmissionRepository.save(submission);
        }
    }
}
