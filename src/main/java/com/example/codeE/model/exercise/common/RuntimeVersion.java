package com.example.codeE.model.exercise.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RuntimeVersion {
    private Language language;
    private Judge judge;
    private String name;
    private String version;
    private int priority;
}
