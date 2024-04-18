package com.example.codeE.service.exercise;
import com.example.codeE.model.exercise.FileExercise;
import com.example.codeE.request.exercise.file.UpdateFileExerciseRequest;
import com.example.codeE.request.exercise.file.response.FileDetailResponse;

public interface FileExerciseService {
    FileExercise createFileExercise(FileExercise request);
    FileDetailResponse getFileExerciseDetail(String exerciseId);
    FileExercise updateFileExercise(String exerciseId, UpdateFileExerciseRequest updateRequest);
    void deleteFileExerciseById(String exerciseId);
}
