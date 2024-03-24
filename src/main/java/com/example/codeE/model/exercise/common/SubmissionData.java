package com.example.codeE.model.exercise.common;

import jakarta.persistence.Id;
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
@Document(collection = "submission_data")
public class SubmissionData {
    @Id
    private String submissionDataId;
    @Field
    private Double time;
    @Field
    private Double memory;
    @Field("short_circuit")
    private Boolean shortCircuit;
    @Field
    private boolean pretests_only;

    public SubmissionData(Double time, Double memory, Boolean shortCircuit, boolean isPretested) {
        this.time = time;
        this.memory = memory;
        this.shortCircuit = shortCircuit;
        this.pretests_only = isPretested;
    }
}
