package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.code.CodeSubmissionsResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
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
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private GroupService groupService;
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
    public AllSubmissionResponse getCodeSubmissionsByExerciseId(String exerciseId, List<String> groupFilter) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        List<CodeSubmission> submissions = codeSubmissionRepository.findAll();
        var listSubmissions = new ArrayList<SubmissionDetail>();
        for (var item : submissions) {
            if (!item.getSubmissionId().equals("code_submission")){
                if (item.getExerciseId().equals(exerciseId) && !item.isPretested()) {
                    var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                    listSubmissions.add(new SubmissionDetail(student, item));
                }
            }
        }
        var report = this.getOverviewScoreReportByExerciseId(exerciseId, groupFilter);
        List<Group> groups = new ArrayList<>();
        for(var item : exercise.getPublicGroupIds()){
            groups.add(this.groupService.getById(item));
        }
        return new AllSubmissionResponse(exercise,listSubmissions, report, groups);
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
            return null;
    }
    public OverviewScoreReport getOverviewScoreReportByExerciseId(String exerciseId, List<String> groupId) {
        OverviewScoreReport result = new OverviewScoreReport();
        var exercise = exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        ;
        if (exercise.isShowAll()) {
            String courseId = topicRepository.findById(exercise.getTopicId()).orElseThrow(() -> new NoSuchElementException("No topic found")).getCourseId();
            var courseStudents = courseStudentRepository.getAllStudentsInCourse(courseId);
            int AScoreCount = 0, BScoreCount = 0, CScoreCount = 0, NumberSubmission = 0;
            result.setNumberStudent(courseStudents.size());
            for (var item : courseStudents) {
                float score = getScoreStudent(item.getStudentId(), exercise);
                if (score != -1) {
                    NumberSubmission++;
                    if (score < 5)
                        CScoreCount++;
                    else if (score < 8)
                        BScoreCount++;
                    else
                        AScoreCount++;
                }
            }
            result.setAScore(AScoreCount);
            result.setBScore(BScoreCount);
            result.setCScore(CScoreCount);
            result.setNumberSubmission(NumberSubmission);
        } else {
            int AScoreCount = 0, BScoreCount = 0, CScoreCount = 0, NumberSubmission = 0;
            for (String gId : exercise.getPublicGroupIds()) {
                if (groupId.contains(gId)) {
                    var groupStudents = groupStudentRepository.getStudentInGroup(gId);
                    for (var item : groupStudents) {
                        float score = getScoreStudent(item.getUserId(), exercise);
                        if (score != -1) {
                            NumberSubmission++;
                            if (score < 5)
                                CScoreCount++;
                            else if (score < 8)
                                BScoreCount++;
                            else
                                AScoreCount++;
                        }
                    }
                    result.setNumberStudent(result.getNumberStudent() + groupStudents.size());
                }
            }
            result.setAScore(AScoreCount);
            result.setBScore(BScoreCount);
            result.setCScore(CScoreCount);
            result.setNumberSubmission(NumberSubmission);
        }
        return result;
    }

    private float getScoreStudent(String studentId, Exercise exercise) {
        var quiz = this.getLastCodeSubmissionByUserId(exercise.getExerciseId(), studentId);
        if (quiz != null)
            return quiz.getScore();
        else return -1;

    }
}
