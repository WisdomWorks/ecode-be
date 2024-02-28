package com.example.codeE.request.exercise.code;

import com.example.codeE.model.exercise.common.IOTestCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RunCodeRequest {
    private String containerId;
    private String contentFile;
    private String fileName;
    private List<IOTestCase> inputs;
}
