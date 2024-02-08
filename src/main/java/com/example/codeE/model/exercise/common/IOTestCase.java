package com.example.codeE.model.exercise.common;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IOTestCase {
    @Field
    @NotNull(message = "Value is required")
    private String value;

    @Field
    @NotNull(message = "Data type of value is required")
    private String dataType;
}
