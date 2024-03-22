package com.example.codeE.model.judge;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity
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
