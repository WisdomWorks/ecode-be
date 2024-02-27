package com.example.codeE.service.docker;

import com.example.codeE.docker.DockerHelper;
import com.example.codeE.service.exercise.CodeExerciseService;

public class DockerImpl  implements DockerService{
    private CodeExerciseService codeExerciseService;
    private DockerHelper docker = new DockerHelper();
    private String currentDirectory = System.getProperty("user.dir");
    @Override
    public String RunCode(String containerId, String fileContent, String exerciseId) {
        var language = codeExerciseService.getCodeExerciseById(exerciseId).getLanguage();
        String output = "" ;
        switch (language.toLowerCase()){
            case "java":
                output = docker.runCmd(containerId, "maven","run");
                break;
            default:
                return "";
        }
        return output;
    }
}
