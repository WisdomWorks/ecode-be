package com.example.codeE.request.exercise.file.response;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.FileSubmission;
import com.example.codeE.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileSubmissionsResponse {
    private FileSubmission submissions;
    private User student;
    private Exercise exercise;
}
