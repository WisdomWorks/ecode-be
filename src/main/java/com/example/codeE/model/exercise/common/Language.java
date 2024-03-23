package com.example.codeE.model.exercise.common;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "language")
public class Language {
    @Id
    private String languageId;

    @NotBlank(message = "Language key is required")
    @Size(max = 10, message = "Language key must not exceed 10 characters")
    @Indexed(unique = true)
    @Field("key")
    private String key;

    @NotBlank(message = "Language name is required")
    @Size(max = 50, message = "Language name must not exceed 50 characters")
    @Field("name")
    private String name;

    @Size(max = 20, message = "Short name must not exceed 20 characters")
    @Field("short_name")
    private String shortName;

    @NotBlank(message = "Common name is required")
    @Size(max = 20, message = "Common name must not exceed 20 characters")
    @Field("common_name")
    private String commonName;

    @Field("template")
    private String template;

    @Field("description")
    private String description;

    @NotBlank(message = "Extension is required")
    @Size(max = 10, message = "Extension must not exceed 10 characters")
    @Pattern(regexp = "^\\.\\w+$", message = "Invalid extension format. It should start with a dot followed by alphanumeric characters.")
    @Field("extension")
    private String extension;
}
