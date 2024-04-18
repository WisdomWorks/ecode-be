package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.request.exercise.code.CodeDetailResponse;
import com.example.codeE.request.exercise.code.UpdateCodeExerciseRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface CodeExerciseService {
    List<String> getProblemIds(List<CodeExercise> problems);
    CodeExercise getProblemById(String problemId);

    CodeExercise getCodeExerciseById(String exerciseId);

    CodeDetailResponse getCodeExerciseDetail(String exerciseId, HttpServletRequest request);

    void createProblemFolder(List<TestCase> testCaseList, String exerciseId);

    CodeExercise createCodeExercise(CodeExercise codeExercise);

    void deleteCodeExercise(String exerciseId);

    CodeExercise updateCodeExercise(String exerciseId, UpdateCodeExerciseRequest updateCodeExerciseRequest);
}
