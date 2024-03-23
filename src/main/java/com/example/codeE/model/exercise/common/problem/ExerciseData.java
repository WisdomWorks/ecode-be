package com.example.codeE.model.exercise.common.problem;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Document(collection = "exercise_data")
public class ExerciseData {
    @Id
    private String id;

    @NotNull(message = "Exercise ID is required")
    @Field("exercise_id")
    private String problemId;

//    @Field("zipfile")
//    private String zipfile;

    @Field("generator")
    private String generator;

    @Field("output_prefix")
    @Min(value = 0, message = "Output prefix must be non-negative")
    private int outputPrefix;

    @Field("output_limit")
    @Min(value = 0, message = "Output limit must be non-negative")
    private int outputLimit;

    @Field("feedback")
    @Size(max = 1000, message = "Feedback must not exceed 1000 characters")
    private String feedback;

    @Field("checker")
    @NotBlank(message = "Checker is required")
    private String checker;

    @Field("unicode")
    private boolean unicode;

    @Field("nobigmath")
    private boolean nobigmath;

    @Field("checker_args")
    private String checkerArgs;
}
