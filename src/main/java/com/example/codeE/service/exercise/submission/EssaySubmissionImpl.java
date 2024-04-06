package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.repository.EssaySubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EssaySubmissionImpl implements EssaySubmissionService{
    @Autowired
    private EssaySubmissionRepository essaySubmissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public EssaySubmission createSubmission(CreateEssaySubmissionRequest essaySubmission) {
        this.userRepository.findById(essaySubmission.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + essaySubmission.getStudentId()));
        this.exerciseRepository.findById(essaySubmission.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + essaySubmission.getExerciseId()));
        var submission = new EssaySubmission(essaySubmission, -1);
        return this.essaySubmissionRepository.save(submission);
    }

    @Override
    public List<EssaySubmission> getEssaySubmissionByExerciseId(String exerciseId) {
        List<EssaySubmission> submissions = this.essaySubmissionRepository.findAll();
        var result = new ArrayList<EssaySubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public List<EssaySubmissionsResponse> getEssaySubmissionsByExerciseId(String exerciseId) {
        List<EssaySubmission> submissions = this.essaySubmissionRepository.findAll();
        var result = new ArrayList<EssaySubmissionsResponse>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                result.add(new EssaySubmissionsResponse(item,student, new Exercise()));
            }
        }
        return result;
    }

    @Override
    public EssaySubmissionsResponse getEssaySubmission(String submissionId) {
        var essay = this.essaySubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No Submission found"));
        var user = this.userRepository.findUserByUserId(essay.getStudentId());
        var exercise = this.exerciseRepository.findById(essay.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No Exercise found"));
        return new EssaySubmissionsResponse(essay, user,exercise);
    }

    @Override
    public List<EssaySubmission> getEssaySubmissionByUserId(String exerciseId, String userId) {
        List<EssaySubmission> submissions = this.essaySubmissionRepository.findAll();
        var result = new ArrayList<EssaySubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        return result;
    }
    @Override
    public EssaySubmission getLastEssaySubmissionByUserId(String exerciseId, String userId) {
        List<EssaySubmission> submissions = this.essaySubmissionRepository.findAll();
        var result = new ArrayList<EssaySubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        result.sort(new Comparator<EssaySubmission>() {
            @Override
            public int compare(EssaySubmission o1, EssaySubmission o2) {
                return o1.getDateSubmit().compareTo(o2.getDateSubmit());
            }
        });
        if (!result.isEmpty())
        return result.get(result.size()-1);
        else return null;
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
