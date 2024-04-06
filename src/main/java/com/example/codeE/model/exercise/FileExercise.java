package com.example.codeE.model.exercise;

import com.example.codeE.model.exercise.common.QuizQuestion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "FileExercise")
public class FileExercise extends Exercise{
    public FileExercise(String topicId, String exerciseName, String key, Date startTime, Date endTime, int durationTime, int reAttempt, String type, String exerciseDescription, boolean isShowAll, List<String> publicGroupIds) {
        super(topicId, exerciseName, key, startTime, endTime, durationTime, reAttempt, type, exerciseDescription, isShowAll, publicGroupIds);
    }
}
