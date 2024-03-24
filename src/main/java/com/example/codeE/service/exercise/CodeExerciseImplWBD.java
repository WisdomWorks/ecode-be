package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import com.example.codeE.repository.CodeExerciseRepositoryWBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeExerciseImplWBD implements CodeExerciseServiceWBD {
    @Autowired
    private CodeExerciseRepositoryWBD codeExerciseRepositoryWBD;
    @Override
    public CodeExerciseWBD createCodeExercise(CodeExerciseWBD codeExerciseWBD) {
        return this.codeExerciseRepositoryWBD.save(codeExerciseWBD);
    }

    @Override
    public CodeExerciseWBD getCodeExerciseById(String exerciseId) {
        return this.codeExerciseRepositoryWBD.findById(exerciseId).get();
    }

    @Override
    public void deleteCodeExerciseById(String exerciseId) {
        this.codeExerciseRepositoryWBD.deleteById(exerciseId);
    }

    @Override
    public CodeExerciseWBD updateCodeExercise(CodeExerciseWBD codeExerciseWBD) {
        return this.codeExerciseRepositoryWBD.save(codeExerciseWBD);
    }
}
