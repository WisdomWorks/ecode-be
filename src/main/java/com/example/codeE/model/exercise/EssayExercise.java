package com.example.codeE.model.exercise;

import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.essay.UpdateEssayExerciseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "EssayExercise")
public class EssayExercise extends Exercise{
    @Field
    @NotNull(message = "Exercise's question is required")
    private String question;

    public EssayExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, String type, List<String> publicGroupIds, String question) {
        super(topicId, exerciseName, key, startTime, endTime, type, publicGroupIds);
        this.question = question;
    }

    public EssayExercise(CreateEssayExerciseRequest request) {
        super(request.getTopicId(), request.getExerciseName(), request.getKey(), request.getStartTime(), request.getEndTime(), request.getDurationTime(), request.getReAttempt(), "essay", false,
                new ArrayList<String>());
        this.question = request.getQuestion();
    }
    public EssayExercise(UpdateEssayExerciseRequest request, boolean isShowAll, List<String> groups){
        super(request.getTopicId(), request.getExerciseName(), request.getKey(), request.getStartTime(), request.getEndTime(), request.getDurationTime(), request.getReAttempt(), "essay", isShowAll,
                groups);
        this.question = request.getQuestion();
    }
}
