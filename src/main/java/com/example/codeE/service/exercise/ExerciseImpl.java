package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.topic.Topic;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExerciseImpl implements ExerciseService{
    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private TopicService topicService;

    @Override
    public Exercise saveExercise(Exercise exercise) {
        return this.exerciseRepository.save(exercise);
    }

    @Override
    public List<Exercise> getExercisesByCourseId(String courseId) {
        List<String> topicIdList = new ArrayList<>();
        for(Topic topic: this.topicService.getAllTopicsByCourseId(courseId)) {
            topicIdList.add(topic.getTopicId());
        }
        List<Exercise> exercises = new ArrayList<>();
        for(String id: topicIdList){
            for(Exercise exc: this.exerciseRepository.findAll()){
                if (exc.getTopicId().equals(id)) {
                    exercises.add(exc);
                }
            }
        }
        return exercises;
    }

    @Override
    public Exercise getExerciseById(String exerciseId) {
        return this.exerciseRepository.findById(exerciseId).get();
    }

    @Override
    public void deleteExerciseById(String exerciseId) {
        this.exerciseRepository.deleteById(exerciseId);
    }


}
