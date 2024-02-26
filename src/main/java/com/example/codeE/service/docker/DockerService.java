package com.example.codeE.service.docker;

public interface DockerService {
    String createContainer(String dockerfilePath);
    String runCode(String containerId, String contentFile, String fileName);
}
