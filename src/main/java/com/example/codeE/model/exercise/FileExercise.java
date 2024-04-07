package com.example.codeE.model.exercise;

import com.example.codeE.request.exercise.file.CreateFileExerciseRequest;
import com.example.codeE.request.exercise.file.UpdateFileExerciseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "FileExercise")
public class FileExercise extends Exercise{
    @Field
    @NotNull(message = "Exercise's question is required")
    private String question;

    public FileExercise(CreateFileExerciseRequest request){
        super(request.getTopicId(), request.getExerciseName(), request.getKey(), request.getStartTime(), request.getEndTime(), request.getDurationTime(), request.getReAttempt(), "file",
                request.getExerciseDescription(), false, new ArrayList<String>());
        this.question = request.getQuestion();
    }

    public FileExercise(String exerciseId, UpdateFileExerciseRequest request, boolean isShowAll, List<String> groups){
        super(exerciseId,
                request.getTopicId(), request.getExerciseName(), request.getKey(), request.getStartTime(), request.getEndTime(), request.getDurationTime(), request.getReAttempt(), "file",
                request.getExerciseDescription(), isShowAll, groups);
        this.question = request.getQuestion();
    }
}
