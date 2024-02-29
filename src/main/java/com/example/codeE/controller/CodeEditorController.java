package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.request.exercise.code.RunCodeRequest;
import com.example.codeE.request.exercise.code.SubmitCodeRequest;
import com.example.codeE.service.docker.DockerService;
import com.example.codeE.service.exercise.CodeExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    private CodeExerciseService codeExerciseService;

    @PostMapping
    @RequestMapping(value = "{exerciseId}/check-exercise-key", method = RequestMethod.POST)
    public ResponseEntity<?> checkCodeExerciseKey(@RequestBody String key, @PathVariable String exerciseId) {
        CodeExercise codeExercise = this.codeExerciseService.getCodeExerciseById(exerciseId);
        //codeExercise.getKey().equals(key)
        if(key.equals("key")){
            switch (codeExercise.getLanguage()) {
                case "java":
                    String containerId = dockerService.createContainer("Dockerfile.java", codeExercise);
                    return ResponseEntity.status(HttpStatus.OK).body(Map.of("containerId", containerId));
            }
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("wrong key");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
    }

     @PutMapping
     @RequestMapping(value = "{exerciseId}/run", method = RequestMethod.PUT)
     public ResponseEntity<?> runCode(@RequestBody RunCodeRequest request, @PathVariable String exerciseId) {
         CodeExercise codeExercise = this.codeExerciseService.getCodeExerciseById(exerciseId);
         String log = dockerService.runCode(
                 request.getContainerId(),
                 request.getContentFile(),
                 request.getFileName(),
                 request.getInputs(),
                 codeExercise);
         return ResponseEntity.status(HttpStatus.OK).body(Map.of("log", log));
     }

    @PostMapping
    @RequestMapping(value = "{exerciseId}/submit", method = RequestMethod.POST)
    public ResponseEntity<?> submitCodeExercise(@RequestBody SubmitCodeRequest request) {
        Map<Integer, String> result = dockerService.submitCode(request.getContainerId(), "Main");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
