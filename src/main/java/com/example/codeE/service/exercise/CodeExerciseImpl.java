package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Override
    public CodeExercise createCodeExercise(CodeExercise codeExercise) {
        return this.codeExerciseRepository.save(codeExercise);
    }

    @Override
    public Optional<CodeExercise> getCodeExerciseById(String exerciseId) {
        return this.codeExerciseRepository.findById(exerciseId);
    }

    @Override
    public void deleteCodeExerciseById(String exerciseId) {
        this.codeExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public CodeExercise updateCodeExercise(CodeExercise codeExercise) {
        return this.codeExerciseRepository.save(codeExercise);
    }
}
