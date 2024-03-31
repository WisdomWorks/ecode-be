package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.problem.TestCase;

import java.util.List;

public interface CodeExerciseService {
    List<String> getProblemIds(List<CodeExercise> problems);
    CodeExercise getProblemById(String problemId);

    CodeExercise getCodeExerciseById(String exerciseId);

    void createProblemFolder(List<TestCase> testCaseList, String exerciseId);

    CodeExercise createCodeExercise(CodeExercise codeExercise);
}
