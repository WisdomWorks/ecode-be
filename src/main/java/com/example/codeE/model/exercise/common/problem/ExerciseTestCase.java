package com.example.codeE.model.exercise.common.problem;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "exercise_testcase")
public class ExerciseTestCase {
    @Id
    private String id;

    // Problem
    @NotNull(message = "Dataset ID is required")
    @Field("dataset_id")
    private String datasetId;

    @NotNull(message = "Order is required")
    @Field("order")
    private Integer order;

    @NotBlank(message = "Type is required")
    @Pattern(regexp = "^C$", message = "Type must be 'C'")
    @Field("type")
    private char type = 'C';

    @Field("input_file")
    @Size(max = 100, message = "Input file name must not exceed 100 characters")
    private String inputFile;

    @Field("output_file")
    @Size(max = 100, message = "Output file name must not exceed 100 characters")
    private String outputFile;

    @Field("generator_args")
    private String generatorArgs;

    @Field("points")
    private Integer points;

    @Field("is_pretest")
    private Boolean isPretest;

    @Field("output_prefix")
    @Min(value = 0, message = "Output prefix must be non-negative")
    private Integer outputPrefix;

    @Field("output_limit")
    @Min(value = 0, message = "Output limit must be non-negative")
    private Integer outputLimit;

    @Field("checker")
    @NotBlank(message = "Checker is required")
    private String checker;

    @Field("checker_args")
    private String checkerArgs;

    @Field("batch_dependencies")
    private String batchDependencies;
}
