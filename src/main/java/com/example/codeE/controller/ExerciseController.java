package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.service.docker.DockerService;
import com.example.codeE.service.exercise.CodeExerciseService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.request.exercise.DeleteExerciseRequest;
import com.example.codeE.service.exercise.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercises")
@Validated
public class ExerciseController {
//    @Autowired
    private DockerService dockerService;
    @Autowired
    private ExerciseService exerciseService;
    @Autowired
    private CodeExerciseService codeExerciseService;

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CodeExercise exercise){
        CodeExercise codeExercise = this.codeExerciseService.createCodeExercise(exercise);
        this.exerciseService.saveExercise((Exercise) codeExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(codeExercise);
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllExerciseByCourseId(@RequestParam String courseId) {
        List<Exercise> exercises = this.exerciseService.getExercisesByCourseId(courseId);
//        List<CodeExercise> codeExercises = new ArrayList<>();
//        for(Exercise exercise: exercises) {
//            switch (exercise.getType()) {
//                case "code":
//                    codeExercises.add(this.codeExerciseService.getCodeExerciseById(exercise.getExerciseId()));
//                    break;
//            }
//        }
        return ResponseEntity.status(HttpStatus.OK).body(exercises);
    }

    @DeleteMapping
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @ModelAttribute DeleteExerciseRequest request) {
        switch (request.getType()) {
            case "code":
                this.codeExerciseService.deleteCodeExerciseById(request.getExerciseId());
                break;
        }
        this.exerciseService.deleteExerciseById(request.getExerciseId());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExercise exercise) {
        return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseService.updateCodeExercise(exercise));
    }


    @PostMapping
    @RequestMapping(value = "runCode", method = RequestMethod.POST)
    public ResponseEntity<?> RunCode(@RequestBody String fileCodeContent){
            //param : fileCodeContent, exerciseId, containerId
            // override fileCodeContent to file
            // run code
        var ContainerId = "42adf8ef9049cd6d5ba4f669b879fb2d5f2f10c27760ec232fe64cfc8ee11980";
        String result = dockerService.RunCode(fileCodeContent, "java", ContainerId );
            // get result from container
            // return result
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
