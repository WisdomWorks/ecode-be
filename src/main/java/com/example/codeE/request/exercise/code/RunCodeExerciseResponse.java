package com.example.codeE.request.exercise.code;

import com.example.codeE.model.exercise.common.SubmissionTestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RunCodeExerciseResponse {
    String status;
    String message;
    List<SubmissionTestCase> testCases;
}
