package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.repository.EssaySubmissionRepository;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EssaySubmissionImpl implements EssaySubmissionService{
    @Autowired
    private EssaySubmissionRepository essaySubmissionRepository;

    @Override
    public EssaySubmission createSubmission(CreateEssaySubmissionRequest essaySubmission) {
        var submission = new EssaySubmission(essaySubmission, 0);
        return this.essaySubmissionRepository.save(submission);
    }

    @Override
    public EssaySubmission gradeSubmission(String essaySubmissionId, float score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        EssaySubmission essaySubmission = this.essaySubmissionRepository.findById(essaySubmissionId).orElseThrow(() -> new NoSuchElementException("No Exercise found by Id: " + essaySubmissionId));
        essaySubmission.setScore(score);
        this.essaySubmissionRepository.save(essaySubmission);
        return essaySubmission;
    }
}
