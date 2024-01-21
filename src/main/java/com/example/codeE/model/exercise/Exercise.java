package com.example.codeE.model.exercise;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Exercise {
    @NonNull
    private String exerciseId;
    @NonNull
    private String topicId;
    @NonNull
    private String name;
    private String description;
    @NonNull
    private String question;
    @NonNull
    private String answer;
    @NonNull
    private String password;
    @NonNull
    private Date dateCreate;
    @NonNull
    private Date startTime;
    @NonNull
    private Date dueTo;
    @NonNull
    private ExerciseType Type;
    @NonNull
    private Boolean isPublic;
}
