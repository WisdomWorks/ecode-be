package com.example.codeE.controller;

import com.example.codeE.request.exercise.code.RunCodeRequest;
import com.example.codeE.request.exercise.code.SubmitCodeRequest;
import com.example.codeE.service.docker.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/exercises")
public class CodeEditorController {
    @Autowired
    private DockerService dockerService;

    @PostMapping
    @RequestMapping(value = "{exerciseId}/check-key", method = RequestMethod.POST)
    public ResponseEntity<?> checkCodeExerciseKey(@RequestBody String key) {
        //create container if the entered key is true
        String containerId = dockerService.createContainer("Dockerfile.java");
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("containerId", containerId));
    }

    @PutMapping
    @RequestMapping(value = "{exerciseId}/run", method = RequestMethod.PUT)
    public ResponseEntity<?> runCode(@RequestBody RunCodeRequest request) {
        String log = dockerService.runCode(request.getContainerId(), request.getContentFile(), request.getFileName());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("log", log));
    }

    @PostMapping
    @RequestMapping(value = "{exerciseId}/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitCodeExercise(@RequestBody SubmitCodeRequest request) {
        Map<Integer, String> result = dockerService.submitCode(request.getContainerId(), "Main");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
