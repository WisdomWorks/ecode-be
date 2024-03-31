package com.example.codeE.model.exercise.common.problem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestCase {
    private List<String> inputs;
    private List<String> outputs;
    private int points;
}
