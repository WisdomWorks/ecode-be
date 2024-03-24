package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.QuizQuestion;
import com.example.codeE.request.exercise.quiz.CreateQuizExerciseRequest;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public QuizExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, int durationTime, int reAttempt, String type, boolean isShowAll, List<String> publicGroupIds, List<QuizQuestion> questions) {
        super(topicId, exerciseName, key, startTime, endTime, durationTime, reAttempt, type, isShowAll, publicGroupIds);
        this.questions = questions;
    }
    public QuizExercise(CreateQuizExerciseRequest request){
        super(request.getTopicId(),
                request.getExerciseName(),
                request.getExerciseName(),
                request.getStartTime(),
                request.getEndTime(),
                request.getDurationTime(),
                request.getReAttempt(),
                "quiz",
                true,
                new ArrayList<String>());
        this.questions = request.getQuestions();
    }
}
