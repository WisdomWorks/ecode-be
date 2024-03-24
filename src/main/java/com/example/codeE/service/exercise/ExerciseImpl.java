package com.example.codeE.service.exercise;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.QuizExercise;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.repository.GroupStudentRepository;
import com.example.codeE.request.exercise.ExerciseResponse;
import com.example.codeE.request.group.GroupTopicResponse;
import jakarta.ws.rs.NotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExerciseImpl implements ExerciseService{
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupStudentRepository groupStudentRepository;

    @Override
    public Exercise saveQuizExercise(QuizExercise exercise) {
        var questions = exercise.getQuestions();
        for (var question : questions) {
            for (var choice : question.getChoices()) {
                for (var answer : question.getAnswers()) {
                    if (Objects.equals(choice.getChoiceId(), answer.getChoiceId())){
                        choice.setChoiceId(UUID.randomUUID().toString());
                        answer.setChoiceId(choice.getChoiceId());
                    }
                }
                choice.setChoiceId(UUID.randomUUID().toString());
            }
        }
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public Exercise saveEsayExercise(EssayExercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getExercisesByCourseId(String courseId) {
//        List<String> topicIdList = new ArrayList<>();
//        for(TopicGetResponse topic: this.topicService.getAllTopicsByCourseId(courseId)) {
//            topicIdList.add(topic.getTopicId());
//        }
//        List<Exercise> exercises = new ArrayList<>();
//        for(String id: topicIdList){
//            for(Exercise exc: this.exerciseRepository.findAll()){
//                if (exc.getTopicId().equals(id)) {
//                    exercises.add(exc);
//                }
//            }
//        }
//        return exercises;
        throw new NotSupportedException("Api not support");
    }

    @Override
    public Exercise getExerciseById(String exerciseId) {
        return this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
    }

    @Override
    public Exercise getDetailExercise(String exerciseId, String key) {
        var exercise = this.exerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise found with ID: " + exerciseId));
        if(exercise.getKey().equals(key)){
            return exercise;
        }else
            throw new IllegalArgumentException("Wrong key to enroll exercise");
    }

    @Override
    public void deleteExerciseById(String exerciseId) {
        this.exerciseRepository.deleteById(exerciseId);
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
                        List<GroupTopicResponse> groupTopicResponses = new ArrayList<>();
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
