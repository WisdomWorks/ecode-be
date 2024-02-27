package com.example.codeE.service.docker;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.docker.Docker;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

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
        String pathFile = Constant.DOCKER_CONTAINER_FILE_PATH + fileName + ".java";
        docker.replaceFile(containerId, contentFile, pathFile);
        docker.runCmd(containerId, "javac", "./Main.java");
        docker.runCmd(containerId, "javac", "./"+fileName+".java");
        String output = docker.runCmd(containerId, "java", "Main");
        return output;
    }

    @Override
    public Map<Integer, String> submitCode(String containerId, String fileName) {
        String containerFilePath = Constant.DOCKER_CONTAINER_FILE_PATH + fileName + ".java";
        String contentMain = docker.copyFileFromContainer(containerId, containerFilePath);
        String modifiedContent = contentMain.replace("boolean isCodeRunning = true;", "boolean isCodeRunning = false;");
        docker.replaceFile(containerId, modifiedContent, containerFilePath);
        docker.runCmd(containerId, "javac", "./Main.java");
        String[] output = (String[]) docker.runCmd(containerId, "java", "Main").split("\n");

        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        for (int i=0; i<output.length; i++) {
            resultMap.put(i, output[i]);
        }
        return resultMap;
    }
}
