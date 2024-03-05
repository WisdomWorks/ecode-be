package com.example.codeE.service.docker;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.FileHelper;
import com.example.codeE.helper.DockerHelper;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.IOTestCase;
import com.example.codeE.model.exercise.common.TestCase;
import com.example.codeE.service.exercise.common.TestcaseService;
import com.example.codeE.util.TestcaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DockerImpl implements DockerService {
    private DockerHelper dockerHelper = new DockerHelper();
    private String currentDirectory = System.getProperty("user.dir");

    @Autowired
    private TestcaseService testcaseService;

    @Override
    public String createContainer(String dockerfilePath, CodeExercise codeExercise) {
        // Create image
        String imageId = dockerHelper.buildDockerImage(new File(currentDirectory, dockerfilePath));

        // Create container
        String containerId = dockerHelper.createContainer(imageId);

        // Push template exercise of teacher to docker
        String fileName = "JavaClass.java";
        String pathFile = Constant.DOCKER_CONTAINER_FILE_PATH + fileName;
        dockerHelper.replaceFile(containerId, codeExercise.getTemplate(), pathFile);

        // Config Main file in docker
        List<TestCase> testCases = new ArrayList<>();
        for(String id: codeExercise.getTestcases()){
            testCases.add(this.testcaseService.getTestcaseById(id));
        }
        Map<String, String> enteredInputs = new HashMap<>();
        enteredInputs.put("inputs", "{}");
        enteredInputs.put("inputTypes", "{}");

        dockerHelper.replaceFile(
                containerId,
                FileHelper.replaceTemplateFile(
                        "./template/java/MainTemplate.txt",
                        "\"" + codeExercise.getFunctionName() + "\"",
                        "true",
                        TestcaseUtil.convertTestcaseList(testCases),
                        enteredInputs),
                Constant.DOCKER_CONTAINER_FILE_PATH + "Main.java");

        return containerId;
    }

    @Override
    public String runCode(String containerId,
                          String contentFile,
                          String fileName,
                          List<IOTestCase> inputs,
                          CodeExercise codeExercise) {
        String pathFile = Constant.DOCKER_CONTAINER_FILE_PATH + fileName + ".java";
        dockerHelper.replaceFile(containerId, contentFile, pathFile);

        // Config Main file in docker
        List<TestCase> testCases = new ArrayList<>();
        for(String id: codeExercise.getTestcases()){
            testCases.add(this.testcaseService.getTestcaseById(id));
        }

        dockerHelper.replaceFile(
                containerId,
                FileHelper.replaceTemplateFile(
                        "./template/java/MainTemplate.txt",
                        "\"" + codeExercise.getFunctionName() + "\"",
                        "true",
                        TestcaseUtil.convertTestcaseList(testCases),
                        TestcaseUtil.convertStudentInputsToMap(inputs)),
                Constant.DOCKER_CONTAINER_FILE_PATH + "Main.java");

        dockerHelper.runCmd(containerId, "javac", "./Main.java");
        dockerHelper.runCmd(containerId, "javac", "./"+fileName+".java");
        String output = dockerHelper.runCmd(containerId, "java", "Main");
        return output;
    }

    @Override
    public Map<Integer, String> submitCode(String containerId, String fileName) {
        String containerFilePath = Constant.DOCKER_CONTAINER_FILE_PATH + fileName + ".java";
        String contentMain = dockerHelper.copyFileFromContainer(containerId, containerFilePath);
        String modifiedContent = contentMain.replace("boolean isCodeRunning = true;", "boolean isCodeRunning = false;");
        dockerHelper.replaceFile(containerId, modifiedContent, containerFilePath);
        dockerHelper.runCmd(containerId, "javac", "./Main.java");
        String[] output = (String[]) dockerHelper.runCmd(containerId, "java", "Main").split("\n");

        Map<Integer, String> resultMap = new HashMap<Integer, String>();
        for (int i=0; i<output.length; i++) {
            resultMap.put(i, output[i]);
        }
        return resultMap;
    }
}
