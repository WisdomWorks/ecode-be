package com.example.codeE.service.exercise.submission;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizSubmission;
import com.example.codeE.model.exercise.common.QuizAnswers;
import com.example.codeE.model.exercise.common.QuizChoice;
import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.model.group.Group;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.AllSubmissionResponse;
import com.example.codeE.request.exercise.SubmissionDetail;
import com.example.codeE.request.exercise.quiz.QuizSubmissionsResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.report.OverviewScoreReport;
import com.example.codeE.service.group.GroupService;
import com.example.codeE.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizSubmissionImpl implements QuizSubmissionService {
    @Autowired
    private QuizSubmissionRepository quizSubmissionRepository;
    @Autowired
    private QuizQuestionRepository quizQuestionRepository;
    @Autowired
    private QuizAnswersRepository quizAnswersRepository;
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
    public QuizSubmission createSubmission(QuizSubmission quizSubmission) {
        this.userRepository.findById(quizSubmission.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + quizSubmission.getStudentId()));
        this.exerciseRepository.findById(quizSubmission.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found by id: " + quizSubmission.getExerciseId()));
        List<QuizAnswers> quizAnswersList = quizSubmission.getSubmission();
        for(int i=0; i<quizAnswersList.size(); i++){
            QuizAnswers savedQuizAns = this.quizAnswersRepository.save(quizAnswersList.get(i));
            quizAnswersList.get(i).setQuizAnswerId(savedQuizAns.getQuizAnswerId());
        }
        return this.quizSubmissionRepository.save(quizSubmission);
    }

    @Override
    public List<QuizSubmission> getQuizSubmissionByExerciseId(String exerciseId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public AllSubmissionResponse<SubmissionDetail> getQuizSubmissionsByExerciseId(String exerciseId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found"));
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var listSubmissions = new ArrayList<SubmissionDetail>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId)) {
                var student = this.userRepository.findById(item.getStudentId()).orElseThrow(() -> new NoSuchElementException("No student found by id: " + item.getStudentId()));
                List<GroupTopicResponse> returnedGroups = this.userService.getAllGroupsByUserId(student.getUserId());
                listSubmissions.add(new SubmissionDetail(student, item, returnedGroups));
            }
        }
        List<Group> groups = new ArrayList<>();
        for (var item : exercise.getPublicGroupIds()) {
            groups.add(this.groupService.getById(item));
        }
        List<String> groupIds = new ArrayList<>();
        if (!exercise.isShowAll()) {
            for (var group : groups) {
                groupIds.add(group.getGroupId());
            }
        }
        var report = this.getOverviewScoreReportByExerciseId(exerciseId, groupIds);
        return new AllSubmissionResponse<SubmissionDetail>(exercise, listSubmissions, report, exercise.isShowAll() ? null : groups);
    }

    @Override
    public QuizSubmissionsResponse getStudentQuizSubmission(String submissionId) {
        var quiz = this.quizSubmissionRepository.findById(submissionId).orElseThrow(() -> new NoSuchElementException("No Submission found"));
        var user = this.userRepository.findUserByUserId(quiz.getStudentId());
        var exercise = this.exerciseRepository.findById(quiz.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No Exercise found"));
        return new QuizSubmissionsResponse(quiz, user, exercise);
    }

    @Override
    public List<QuizSubmission> getQuizSubmissionByUserId(String exerciseId, String userId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        return result;
    }

    @Override
    public float gradeSubmission(List<QuizAnswers> quizSubmission,List<QuizQuestion> quizExercise) {
        float correctAnsCnt = 0;
        for (QuizAnswers quizAnswers: quizSubmission){
            List<QuizChoice> correctAnswers = this.quizQuestionRepository.findById(quizAnswers.getQuestionId()).get().getAnswers();
            List<QuizChoice> studentAnswers = quizAnswers.getAnswers();
            Collections.sort(correctAnswers);
            Collections.sort(studentAnswers);
            if(correctAnswers.equals(studentAnswers)){
                correctAnsCnt++;
            }
        }
        float questionQuantity = quizExercise.size();
        return 10 * (correctAnsCnt / questionQuantity);
    }

    @Override
    public QuizSubmission getLastQuizSubmissionByUserId(String exerciseId, String userId) {
        List<QuizSubmission> submissions = this.quizSubmissionRepository.findAll();
        var result = new ArrayList<QuizSubmission>();
        for (var item : submissions) {
            if (item.getExerciseId().equals(exerciseId) && item.getStudentId().equals(userId)) {
                result.add(item);
            }
        }
        result.sort(new Comparator<QuizSubmission>() {
            @Override
            public int compare(QuizSubmission o1, QuizSubmission o2) {
                return o1.getDateSubmit().compareTo(o2.getDateSubmit());
            }
        });
        if (!result.isEmpty())
        return result.get(result.size() - 1);
        else return null;
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
        var quiz = this.getLastQuizSubmissionByUserId(exercise.getExerciseId(), studentId);
        if (quiz != null)
            return quiz.getScore();
        else return -1;

    }
}
