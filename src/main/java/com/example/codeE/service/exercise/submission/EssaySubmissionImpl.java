package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.essay.CreateEssaySubmissionRequest;
import com.example.codeE.request.exercise.essay.EssaySubmissionsResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;
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
    private UserService userService;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseStudentRepository courseStudentRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private GroupService groupService;
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
    public AllSubmissionResponse<SubmissionDetail> getEssaySubmissionsByExerciseId(String exerciseId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        List<EssaySubmission> submissions = this.essaySubmissionRepository.findAll();
        var listSubmissions = new ArrayList<SubmissionDetail>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                List<GroupTopicResponse> returnedGroups = this.userService.getAllGroupsByUserId(student.getUserId());
                listSubmissions.add(new SubmissionDetail(student, item, returnedGroups));
            }
        }
        List<Group> groups = new ArrayList<>();
        for(var item : exercise.getPublicGroupIds()){
            groups.add(this.groupService.getById(item));
        }
        List<String> groupIds = new ArrayList<>();
        if (!exercise.isShowAll()) {
            for (var group : groups) {
                groupIds.add(group.getGroupId());
            }
        }
        var report = this.getOverviewScoreReportByExerciseId(exerciseId, groupIds);
        return new AllSubmissionResponse<SubmissionDetail>(exercise,listSubmissions, report, exercise.isShowAll() ? null : groups);
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
    public EssaySubmission gradeSubmission(String essaySubmissionId, float score, String comment) {
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException("Score must be between 0 and 10");
        }
        EssaySubmission essaySubmission = this.essaySubmissionRepository.findById(essaySubmissionId).orElseThrow(() -> new NoSuchElementException("No Exercise found by Id: " + essaySubmissionId));
        essaySubmission.setScore(score);
        essaySubmission.setTeacherComment(comment);
        this.essaySubmissionRepository.save(essaySubmission);
        return essaySubmission;
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
            List<String> hasGetSubmission = new ArrayList<>();
            int AScoreCount = 0, BScoreCount = 0, CScoreCount = 0, NumberSubmission = 0;
            for (String gId : exercise.getPublicGroupIds()) {
                if (groupId.contains(gId)) {
                    var groupStudents = groupStudentRepository.getStudentInGroup(gId);
                    for (var item : groupStudents) {
                        if (!hasGetSubmission.contains(item.getUserId())) {
                            hasGetSubmission.add(item.getUserId());
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
        var quiz = this.getLastEssaySubmissionByUserId(exercise.getExerciseId(), studentId);
        if (quiz != null)
            return quiz.getScore();
        else return -1;

    }
}
