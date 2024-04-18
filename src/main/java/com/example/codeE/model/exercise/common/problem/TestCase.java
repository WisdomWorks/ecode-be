package com.example.codeE.model.exercise.common.problem;

import org.springframework.data.annotation.Id;
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
@Document(collection = "exercise_testcase")
public class TestCase {
    @Id
    private String testcaseId;

    @Field
    private String exerciseId;

    @Field
    private String input;

    @Field
    private String output;

    @Field
    private Double points;
}
