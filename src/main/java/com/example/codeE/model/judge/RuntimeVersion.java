package com.example.codeE.model.judge;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RuntimeVersion {
    private Language language;
    private Judge judge;
    private String name;
    private String version;
    private int priority;
}
