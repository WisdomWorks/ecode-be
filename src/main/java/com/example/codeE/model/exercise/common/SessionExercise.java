package com.example.codeE.model.exercise.common;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "session_exercise")
public class SessionExercise {
    @Id
    private String sessionId;
    @NotBlank
    @Field("login_id")
    private String loginId;
    @NotBlank
    @Field("student_id")
    private String studentId;
    @NotBlank
    @Field("exercise_id")
    private String exerciseId;
    @NotBlank
    @Field("time_start")
    private String timeStart;
    @NotBlank
    @Field("user_urgent")
    private String userUrgent;
    public SessionExercise(String loginId,String studentId, String exerciseId, String timeStart, String userUrgent){
        this.loginId =loginId;
        this.studentId = studentId;
        this.exerciseId = exerciseId;
        this.timeStart = timeStart;
        this.userUrgent = userUrgent;
    }
}
