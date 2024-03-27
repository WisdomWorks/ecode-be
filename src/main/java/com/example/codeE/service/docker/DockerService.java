package com.example.codeE.service.docker;

import com.example.codeE.model.exercise.CodeExerciseWBD;
import com.example.codeE.model.exercise.common.IOTestCase;

import java.util.List;
import java.util.Map;

public interface DockerService {
    String createContainer(String dockerfilePath, CodeExerciseWBD codeExerciseWBD);
    String runCode(String containerId, String contentFile, String fileName, List<IOTestCase> inputs, CodeExerciseWBD codeExerciseWBD);
    Map<Integer, String> submitCode(String containerId, String fileName);
}
