package com.example.codeE.model.exercise.common;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import static com.example.codeE.constant.Constant.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "language_limit")
public class LanguageLimit {
    @Id
    private String languageLimitId;

    @Field("problemId")
    private String problemId;

    @Field("languageId")
    private String languageId;

    @Field("time_limit")
    @Min(value = PROBLEM_MIN_TIME_LIMIT)
    @Max(value = PROBLEM_MAX_TIME_LIMIT)
    private Float timeLimit;

    @Field("memory_limit")
    @Min(value = PROBLEM_MIN_MEMORY_LIMIT)
    @Max(value = PROBLEM_MAX_MEMORY_LIMIT)
    private Integer memoryLimit;
}
