package com.example.codeE.request.exercise.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunCodeRequest {
    private String containerId;
    private String contentFile;
    private String fileName;
}
