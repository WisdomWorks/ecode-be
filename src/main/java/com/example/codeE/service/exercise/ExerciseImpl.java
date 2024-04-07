package com.example.codeE.service.exercise;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.*;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.*;
import com.example.codeE.request.exercise.AllStudentSubmissionResponse;
import com.example.codeE.request.exercise.CreatePermissionExerciseRequest;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.exercise.ExerciseStudentResponse;
import com.example.codeE.request.exercise.file.response.FilePreviewResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import com.example.codeE.request.user.StudentSubmissionInformation;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.exercise.submission.EssaySubmissionService;
import com.example.codeE.service.exercise.submission.FileSubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ExerciseImpl implements ExerciseService{
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private EssayExerciseRepository essayExerciseRepository;
    @Autowired
    private QuizExerciseRepository quizExerciseRepository;
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Autowired
    private FileExerciseRepository fileExerciseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;
    @Autowired
    private CodeSubmissionService codeSubmissionService;
    @Autowired
    private EssaySubmissionService essaySubmissionService;
    @Autowired
    private QuizSubmissionService quizSubmissionService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private FileSubmissionService fileSubmissionService;

    @Override
    public Exercise saveQuizExercise(QuizExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveEsayExercise(EssayExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveCodeExercise(CodeExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveFileExercise(FileExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public ExerciseStudentResponse getPreviewExercise(String exerciseId, String studentId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        var available = this.isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt());
        return new ExerciseStudentResponse(exercise, available);
    }
    @Override
    public FilePreviewResponse getFilePreviewExercise(String exerciseId, String studentId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        var available = this.isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt());
        var fileExercise = this.fileExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No file exercise found with ID: " + exerciseId));
        return new FilePreviewResponse(fileExercise,fileExercise.getQuestion(), available);
    }

    @Override
    public List<ExerciseResponse> getExercisesByCourseId(String courseId) {
        List<String> topicIdList = new ArrayList<>();
        for (Topic topic : this.topicRepository.findAll()) {
            System.out.println(topic.getTopicId());
            if (topic.getCourseId().equals(courseId)) topicIdList.add(topic.getTopicId());
        }
        List<ExerciseResponse> exercises = new ArrayList<>();
        for (String id : topicIdList) {
            exercises.addAll(this.getExercisesByTopicId(id));
        }
        for (int i = 0; i < exercises.size(); i++) {
            switch (exercises.get(i).getType()) {
                case "essay" -> {
                    var submission = this.essaySubmissionService.getEssaySubmissionByExerciseId(exercises.get(i).getExerciseId());
                    List<StudentSubmissionInformation> submissionResponse = new ArrayList<>();
                    for (var sub : submission) {
                        var userInfor = this.userRepository.findById(sub.getStudentId()).get();
                        submissionResponse.add(new StudentSubmissionInformation(userInfor.getUserId(), userInfor.getName(), sub.getDateSubmit(), sub.getScore(), sub.isReviewable()));
                    }
                    exercises.get(i).setStudents(submissionResponse);
                }
                case "quiz" -> {
                    var submission = this.quizSubmissionService.getQuizSubmissionByExerciseId(exercises.get(i).getExerciseId());
                    List<StudentSubmissionInformation> submissionResponse = new ArrayList<>();
                    for (var sub : submission) {
                        var userInfor = this.userRepository.findById(sub.getStudentId()).get();
                        submissionResponse.add(new StudentSubmissionInformation(userInfor.getUserId(), userInfor.getName(), sub.getDateSubmit(), sub.getScore(), sub.isReviewable()));
                    }
                    exercises.get(i).setStudents(submissionResponse);
                }
                //code exercise here
            }
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(String exerciseId) {
        return this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
    }

    @Override
    public Exercise getDetailExercise(String exerciseId, String key, String studentId) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        if (!exercise.getKey().equals(key))
            throw new IllegalArgumentException("Invalid enrollment key. Please double-check and try again.");
        else if (!isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt()))
            throw new DataIntegrityViolationException("You have already submitted the exercise the maximum number of times allowed.");
        else if (exercise.getKey().equals(key) && isReTemp(exercise.getExerciseId(), studentId, exercise.getType(), exercise.getReAttempt()))
            return exercise;
        else
            throw new IllegalArgumentException("Failed to retrieve exercise information.");
    }

    private boolean isReTemp(String exerciseId, String userId, String type, int reTemp) {
        switch (type) {
            case "quiz" -> {
                var quizSubmission = this.quizSubmissionService.getQuizSubmissionByUserId(exerciseId, userId);
                if (quizSubmission.isEmpty() || quizSubmission.size() < reTemp)
                    return true;
            }
            case "essay" -> {
                var quizSubmission = this.essaySubmissionService.getEssaySubmissionByUserId(exerciseId, userId);
                if (quizSubmission.isEmpty() || quizSubmission.size() < reTemp)
                    return true;
            }
            case "code" -> {
                var codeSubmission = this.codeSubmissionService.getCodeSubmissionByUserId(exerciseId, userId);
                if (codeSubmission.isEmpty() || codeSubmission.size() < reTemp)
                    return true;
            }
            case "file" -> {
                var fileSubmission = this.fileSubmissionService.getFileSubmissionByUserId(exerciseId, userId);
                if (fileSubmission.isEmpty() || fileSubmission.size() < reTemp)
                    return true;
            }
        }
        return false;
    }
    @Override
    public void deleteExerciseById(String exerciseId) {
        this.exerciseRepository.deleteById(exerciseId);
    }
    @Override
    public ExerciseResponse modifiedPermission(CreatePermissionExerciseRequest request){
        try{
            var exercise = this.exerciseRepository.findById(request.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + request.getExerciseId()));
            exercise.setPublicGroupIds(request.getGroupIds());
            exercise.setShowAll(request.isShowAll());
            this.exerciseRepository.save(exercise);
            switch (exercise.getType()) {
                case "code" -> {
                    var code = this.codeExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No code exercise found with ID: " + request.getExerciseId()));
                    code.setPublicGroupIds(request.getGroupIds());
                    code.setShowAll(request.isShowAll());
                    codeExerciseRepository.save(code);
                }
                case "quiz" -> {
                    var quiz = this.quizExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No quiz exercise found with ID: " + request.getExerciseId()));
                    quiz.setPublicGroupIds(request.getGroupIds());
                    quiz.setShowAll(request.isShowAll());
                    quizExerciseRepository.save(quiz);
                }
                case "essay" -> {
                    var essay = this.essayExerciseRepository.findById(exercise.getExerciseId()).orElseThrow(() -> new NoSuchElementException("No essay exercise found with ID: " + request.getExerciseId()));
                    essay.setPublicGroupIds(request.getGroupIds());
                    essay.setShowAll(request.isShowAll());
                    essayExerciseRepository.save(essay);
                }
            }
            var groupResponse = new ArrayList<GroupTopicResponse>();
            for(var item: exercise.getPublicGroupIds()){
                var group = this.groupRepository.findById(item).get();
                groupResponse.add(new GroupTopicResponse(group.getGroupName(), group.getGroupId()));
            }
            return new ExerciseResponse(exercise, groupResponse);
        }catch (Exception e){
            throw new RuntimeException("Something wrong when change permission.");
        }
    }

    @Override
    public List<AllStudentSubmissionResponse> getAllStudentSubmission(String courseId, String userId) {
        this.userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No user found"));
        List<AllStudentSubmissionResponse> responses = new ArrayList<>();
        try {
            List<String> topicIdList = new ArrayList<>();
            for (Topic topic : this.topicRepository.findAll()) {
                if (topic.getCourseId().equals(courseId)) topicIdList.add(topic.getTopicId());
            }
            List<ExerciseResponse> exercises = new ArrayList<>();
            for (String id : topicIdList) {
                exercises.addAll(this.getExercisesByTopicId(id));
            }
            for(var item: exercises){
                switch (item.getType()) {
                    case "essay" -> {
                        var essay = this.essaySubmissionService.getLastEssaySubmissionByUserId(item.getExerciseId(), userId);
                        if (essay != null)
                            responses.add(new AllStudentSubmissionResponse(essay, item.getExerciseName()));
                    }case "code" -> {
                        var code = this.codeSubmissionService.getLastCodeSubmissionByUserId(item.getExerciseId(), userId);
                        if (code != null)
                            responses.add(new AllStudentSubmissionResponse(code, item.getExerciseName()));
                    }case "quiz" -> {
                        var quiz = this.quizSubmissionService.getLastQuizSubmissionByUserId(item.getExerciseId(), userId);
                        if (quiz != null)
                            responses.add(new AllStudentSubmissionResponse(quiz, item.getExerciseName()));
                    }
                }
            }
        }
        catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
        return responses;
    }

    @Override
    public List<ExerciseResponse> getExercisesByTopicId(String topicId) {
        try {
            List<Exercise> exercises = this.exerciseRepository.findAll();
            List<ExerciseResponse> result = new ArrayList<>();
            for (var item : exercises) {
                if (item.getTopicId().equals(topicId)) {
                    result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                }
            }
            return result;
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
    }

    @Override
    public List<ExerciseResponse> getExercisesByUserId(String topicId, String userId) {
        try {
            List<Exercise> exercises = this.exerciseRepository.findAll();
            List<ExerciseResponse> result = new ArrayList<>();
            for (var item : exercises) {
                if (item.isShowAll()) {
                    if (item.getTopicId().equals(topicId)) {
                        result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                    }
                } else {
                    if (item.getTopicId().equals(topicId)) {
                        for (String g : item.getPublicGroupIds()) {
                            var check = this.groupStudentRepository.isStudentInGroup(userId, g);
                            if (check != null) {
                                result.add(new ExerciseResponse(item, getGroupResponse(item.getPublicGroupIds())));
                                break;
                            }
                        }
                    }
                }
            }
            return result;
        } catch (Exception e) {
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Something wrong when get exercise in this topic");
        }
    }

    private List<GroupTopicResponse> getGroupResponse(List<String> groupIds) {
        List<GroupTopicResponse> groupTopicResponses = new ArrayList<>();
        for (String g : groupIds) {
            var group = this.groupRepository.findById(g).get();
            groupTopicResponses.add(new GroupTopicResponse(group.getGroupName(), group.getGroupId()));
        }
        return groupTopicResponses;
    }

}
