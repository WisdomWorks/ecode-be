package com.example.codeE.model.exercise;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exercise {
    @NonNull
    private String exerciseId;
    @NonNull
    private String topicId;
    @NonNull
    private String title;
    private String description;
    @NonNull
    private String question;
    @NonNull
    private Date dateCreate;
    @NonNull
    private Date startTime;
    @NonNull
    private Date dueTo;
    @NonNull
    private ExerciseType type;
    @NonNull
    private Boolean isPublic;
}