package com.example.codeE.service.docker;

public interface DockerService {
    String RunCode(String containerId, String fileContent, String exerciseId);
}
