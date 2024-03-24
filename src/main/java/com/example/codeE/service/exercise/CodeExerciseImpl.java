package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;

    @Override
    public List<String> getProblemIds(List<CodeExercise> problems) {
        List<String> result = new ArrayList<>();
        for(CodeExercise problem : problems) {
            result.add(codeExerciseRepository.findById(problem.getCode()).get().getCode());
        }
        return result;
    }
}
