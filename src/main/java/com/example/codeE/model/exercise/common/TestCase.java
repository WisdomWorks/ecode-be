package com.example.codeE.model.exercise.common;

import com.example.codeE.validator.datatype.DataTypeChecking;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Testcase")
@DataTypeChecking(targetClasses = {TestCase.class})
public class TestCase {
    @Id
    private String testcaseId;

    @Field
    @NotNull(message = "Testcase must have at least 1 input value")
    private List<IOTestCase> inputs;

    @Field
    @NotNull(message = "Output of testcase is required")
    private IOTestCase output;

    public TestCase(List<IOTestCase> inputs, IOTestCase output) {
        this.inputs = inputs;
        this.output = output;
    }
}
