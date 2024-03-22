package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Override
    public CodeExerciseWBD createCodeExercise(CodeExerciseWBD codeExerciseWBD) {
        return this.codeExerciseRepository.save(codeExerciseWBD);
    }

    @Override
    public CodeExerciseWBD getCodeExerciseById(String exerciseId) {
        return this.codeExerciseRepository.findById(exerciseId).get();
    }

    @Override
    public void deleteCodeExerciseById(String exerciseId) {
        this.codeExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public CodeExerciseWBD updateCodeExercise(CodeExerciseWBD codeExerciseWBD) {
        return this.codeExerciseRepository.save(codeExerciseWBD);
    }
}
