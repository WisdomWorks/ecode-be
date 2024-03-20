package com.example.codeE.model.judge;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Language {
    private String key;
    private String name;
    private String shortName;
    private String commonName;
    private String template;
    private String info;
    private String description;
    private String extension;
}
