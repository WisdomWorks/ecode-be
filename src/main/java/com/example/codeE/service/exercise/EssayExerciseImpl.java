package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.EssayExercise;
import com.example.codeE.repository.EssayExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EssayExerciseImpl implements EssayExerciseService{
    @Autowired
    private EssayExerciseRepository essayExerciseRepository;

    @Override
    public EssayExercise createEssayExercise(EssayExercise essayExercise) {
        return this.essayExerciseRepository.save(essayExercise);
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
