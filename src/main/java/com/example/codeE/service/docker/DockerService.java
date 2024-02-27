package com.example.codeE.service.docker;

import java.util.Map;

public interface DockerService {
    String createContainer(String dockerfilePath);
    String runCode(String containerId, String contentFile, String fileName);
    Map<Integer, String> submitCode(String containerId, String fileName);
}
