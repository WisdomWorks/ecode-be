package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;

import java.util.List;

public interface CodeExerciseService {
    List<String> getProblemIds(List<CodeExercise> problems);
    CodeExercise getProblemById(String problemId);
}
