package com.example.codeE.request.exercise.essay;

import com.example.codeE.model.exercise.EssaySubmission;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EssaySubmissionsResponse {
    private EssaySubmission submissions;
    private User student;
    private Exercise exercise;
}
