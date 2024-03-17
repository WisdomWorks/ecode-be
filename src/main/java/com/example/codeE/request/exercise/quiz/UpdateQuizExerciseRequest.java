package com.example.codeE.request.exercise.quiz;

import com.example.codeE.model.exercise.common.QuizQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuizExerciseRequest {
    private String topicId;

    private String exerciseName;

    private String key;

    private Date startTime;

    private Date endTime;

    private List<String> publicGroupIds;

    private List<QuizQuestion> questions;

    @Override
    public String toString() {
        return "UpdateQuizExerciseRequest{" +
                "topicId='" + topicId + '\'' +
                ", exerciseName='" + exerciseName + '\'' +
                ", key='" + key + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", publicGroupIds=" + publicGroupIds +
                ", questions=" + questions +
                '}';
    }
}
