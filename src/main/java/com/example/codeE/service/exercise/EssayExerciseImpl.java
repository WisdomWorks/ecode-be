package com.example.codeE.service.exercise;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.repository.EssayExerciseRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import com.example.codeE.request.exercise.essay.EssayDetailResponse;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EssayExerciseImpl implements EssayExerciseService{
    @Autowired
    private EssayExerciseRepository essayExerciseRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Override
    public EssayExercise createEssayExercise(EssayExercise essayExercise) {
        try{
            for(String groupId : essayExercise.getPublicGroupIds()){
                this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No Group found with ID: " + groupId));
            }
            if (essayExercise.getReAttempt() <= 0){
                essayExercise.setReAttempt(1);
            }
            return this.essayExerciseRepository.save(essayExercise);
        }catch (Exception e){
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Can not save essay exercise");
        }
    }

    @Override
    public EssayExercise getEssayExerciseById(String exerciseId) {
        return this.essayExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise essay found by Id: "+ exerciseId));
    }
    @Override
    public EssayDetailResponse getEssayExerciseDetail(String exerciseId) {
        var exercise = this.essayExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise essay found by Id: "+ exerciseId));
        return new EssayDetailResponse(exercise);
    }
    @Override
    public void deleteEssayExerciseById(String exerciseId) {
        this.essayExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public EssayExercise updateEssayExercise(String exerciseId, UpdateEssayExerciseRequest updateRequest) {
        try{
            EssayExercise essayExercise = this.essayExerciseRepository.findById(exerciseId).orElseThrow(() -> new NoSuchElementException("No exercise essay found by Id: " + exerciseId));
            var updateExercise = new EssayExercise(updateRequest, essayExercise.isShowAll(), essayExercise.getPublicGroupIds());
            this.exerciseRepository.save(updateExercise);
            return this.essayExerciseRepository.save(updateExercise);
        }catch (RuntimeException e){
            throw new RuntimeException("Something wrong when update essay exercise");
        }
    }
}
