package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.repository.CodeSubmissionRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.code.CodeSubmissionsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CodeSubmissionImpl implements CodeSubmissionService{
    @Autowired
    private CodeSubmissionRepository codeSubmissionRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private UserRepository userRepository;

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
    
    @Override
    public CodeSubmission getCodeSubmissionById(String id) {
        return codeSubmissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Submission found"));
    }

    @Override
    public CodeSubmissionsResponse getCodeSubmissionResponseById(String id) {
        var code = codeSubmissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("No Submission found"));
        var user = this.userRepository.findUserByUserId(code.getStudentId());
        var exercise = this.exerciseRepository.findById(code.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No Exercise found"));
        return new CodeSubmissionsResponse(code, user,exercise);
    }

    @Override
    public CodeSubmission updateCodeSubmission(CodeSubmission codeSubmission) {
        return codeSubmissionRepository.save(codeSubmission);
    }

    @Override
    public CodeSubmission saveCodeSubmission(CodeSubmission codeSubmission) {
        return codeSubmissionRepository.save(codeSubmission);
    }

    @Override
    public List<CodeSubmissionsResponse> getCodeSubmissionsByExerciseId(String exerciseId) {
        List<CodeSubmission> submissions = codeSubmissionRepository.findAll();
        var result = new ArrayList<CodeSubmissionsResponse>();
        for (var item : submissions) {
            if (!item.getSubmissionId().equals("code_submission")){
                if (item.getExerciseId().equals(exerciseId) && !item.isPretested()) {
                    var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                    result.add(new CodeSubmissionsResponse(item, student, new Exercise()));
                }
            }
        }
        return result;
    }

    @Override
    public List<CodeSubmission> getCodeSubmissionByUserId(String exerciseId, String userId) {
        List<CodeSubmission> submissions = codeSubmissionRepository.findAll();
        var result = new ArrayList<CodeSubmission>();
        for (var item : submissions) {
            if (!item.getSubmissionId().equals("code_submission")){
                if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId) && !item.isPretested()) {
                    result.add(item);
                }
            }
        }
        return result;
    }

    @Override
    public CodeSubmission getLastCodeSubmissionByUserId(String exerciseId, String userId) {
        List<CodeSubmission> submissions = codeSubmissionRepository.findAll();
        var result = new ArrayList<CodeSubmission>();
        for (var item : submissions) {
            if (!item.getSubmissionId().equals("code_submission")){
                if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId) && !item.isPretested()) {
                    result.add(item);
                }
            }
        }
        result.sort(new Comparator<CodeSubmission>() {
            @Override
            public int compare(CodeSubmission o1, CodeSubmission o2) {
                return o1.getDateSubmit().compareTo(o2.getDateSubmit());
            }
        });
        if (!result.isEmpty())
        return result.get(result.size()-1);
        else
            return new CodeSubmission();
    }
}
