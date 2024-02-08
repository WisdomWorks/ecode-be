package com.example.codeE.model.exercise.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestCase {
    @Field
    @NotNull(message = "Testcase must have at least 1 input value")
    private List<IOTestCase> inputs;

    @Field
    @NotNull(message = "Output of testcase is required")
    private IOTestCase output;
}
