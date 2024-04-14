package com.example.codeE.request.exercise.file.response;

import com.example.codeE.model.exercise.common.QuizQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExcelResult {
    private List<QuizQuestion> questions;
    private List<Integer> failedRows;
}
