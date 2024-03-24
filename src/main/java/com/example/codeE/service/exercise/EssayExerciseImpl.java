package com.example.codeE.service.exercise;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.repository.EssayExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EssayExerciseImpl implements EssayExerciseService{
    @Autowired
    private EssayExerciseRepository essayExerciseRepository;
    @Autowired
    private GroupRepository groupRepository;
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
        return this.essayExerciseRepository.findById(exerciseId).get();
    }

    @Override
    public void deleteEssayExerciseById(String exerciseId) {
        this.essayExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public EssayExercise updateEssayExercise(EssayExercise essayExercise) {
        return this.essayExerciseRepository.save(essayExercise);
    }
}
