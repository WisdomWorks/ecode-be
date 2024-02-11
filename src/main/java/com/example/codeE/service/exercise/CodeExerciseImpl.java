package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Override
    public CodeExercise createCodeExercise(CodeExercise codeExercise) {
        return codeExerciseRepository.save(codeExercise);
    }

    @Override
    public Optional<CodeExercise> getCodeExerciseById(String exerciseId) {
        return codeExerciseRepository.findById(exerciseId);
    }
}
