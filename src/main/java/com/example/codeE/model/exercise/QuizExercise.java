package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.QuizQuestion;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "QuizExercise")
public class QuizExercise extends Exercise {
    @Field
    @NotNull(message = "Exercise's questions is required")
    private List<QuizQuestion> questions;

    public QuizExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime,Date openedTime, Date closedTime, int reAttempt, String type, List<String> publicGroupIds, List<QuizQuestion> questions) {
        super(topicId, exerciseName, key, startTime, endTime,openedTime, closedTime, reAttempt,  type, publicGroupIds);
        this.questions = questions;
    }
}
