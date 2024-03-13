package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.repository.EssaySubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EssaySubmissionImpl implements EssaySubmissionService{
    @Autowired
    private EssaySubmissionRepository essaySubmissionRepository;

    @Override
    public EssaySubmission createSubmission(EssaySubmission essaySubmission) {
        return this.essaySubmissionRepository.save(essaySubmission);
    }

    @Override
    public EssaySubmission gradeSubmission(String essaySubmissionId, float score) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        EssaySubmission essaySubmission = this.essaySubmissionRepository.findById(essaySubmissionId).get();
        if(essaySubmission == null){
            throw new NoSuchElementException("Essay Submission not found");
        }
        essaySubmission.setScore(score);
        this.essaySubmissionRepository.save(essaySubmission);
        return essaySubmission;
    }
}
