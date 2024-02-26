package com.example.codeE.service.docker;

import com.example.codeE.model.docker.Docker;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class DockerImpl implements DockerService {
    private Docker docker = new Docker();
    private String currentDirectory = System.getProperty("user.dir");
    @Override
    public String createContainer(String dockerfilePath) {
        String imageId = docker.buildDockerImage(new File(currentDirectory, dockerfilePath));
        String containerId = docker.createContainer(imageId);
        return containerId;
    }

    @Override
    public String runCode(String containerId, String contentFile, String fileName) {
        String pathFile = "app/student-submission/" + fileName + ".java";
        docker.replaceFile(containerId, contentFile, pathFile);
        docker.runCmd(containerId, "javac", "./Main.java");
        docker.runCmd(containerId, "javac", "./"+fileName+".java");
        String output = docker.runCmd(containerId, "java", "Main");
        return output;
    }
}
