package com.example.codeE.service.docker;

<<<<<<< Updated upstream
public interface DockerService {
=======
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.IOTestCase;

import java.util.List;
import java.util.Map;

public interface DockerService {
    String createContainer(String dockerfilePath, CodeExercise codeExercise);
    String runCode(String containerId, String contentFile, String fileName, List<IOTestCase> inputs, CodeExercise codeExercise);
    Map<Integer, String> submitCode(String containerId, String fileName);
>>>>>>> Stashed changes
    String RunCode(String containerId, String fileContent, String exerciseId);
}
