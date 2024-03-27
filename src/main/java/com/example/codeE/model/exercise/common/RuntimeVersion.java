package com.example.codeE.model.exercise.common;

import jakarta.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "runtime_version")
public class RuntimeVersion {
    @Id
    private String runtimeVersionId;

    @NotNull(message = "Language ID is required")
    @Field("language_id")
    private String languageId;

    private String judgeId;

    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name must not exceed 64 characters")
    @Field("name")
    private String name;

    @Field("version")
    @Size(max = 64, message = "Version must not exceed 64 characters")
    private String version;
//
//    @Field("priority")
//    private int priority;
}
