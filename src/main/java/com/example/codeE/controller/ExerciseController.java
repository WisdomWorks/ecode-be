package com.example.codeE.controller;

import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.MSExercise;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.service.exercise.CodeExerciseImpl;
import com.example.codeE.service.exercise.MSExerciseImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exercises")
@Validated
public class ExerciseController {
    @Autowired
    private CodeExerciseImpl codeExerciseImpl;

    @Autowired
    private MSExerciseImpl msExerciseImpl;

    @PostMapping
    @RequestMapping(value = "code",method = RequestMethod.POST)
    public ResponseEntity<?> createCodeExercise(@Valid @RequestBody CodeExercise exercise){
        CodeExercise codeExercise = this.codeExerciseImpl.createCodeExercise(exercise);
        MSExercise msExercise = new MSExercise(codeExercise.getExerciseId(), codeExercise.getTopicId());
        this.msExerciseImpl.saveExerciseToMySql(msExercise);
        return ResponseEntity.status(HttpStatus.CREATED).body(codeExercise);
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllExerciseByCourseId(@RequestParam String courseId) {
        List<MSExercise> exerciseIdList = this.msExerciseImpl.getAllExercisesByCourseId(courseId);
        List<CodeExercise> exerciseList = new ArrayList<>();
        for (MSExercise msExercise: exerciseIdList) {
            CodeExercise codeExercise = this.codeExerciseImpl.getCodeExerciseById(msExercise.getExerciseId());
        }
        return ResponseEntity.status(HttpStatus.OK).body(exerciseList);
    }

    @DeleteMapping
    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteExerciseById(@Valid @ModelAttribute DeleteExerciseRequest request) {
        switch (request.getType()) {
            case "code":
                this.codeExerciseImpl.deleteCodeExerciseById(request.getExerciseId());
                break;
        }
        this.msExerciseImpl.deleteExerciseInMySql(request.getExerciseId());
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("success", true));
    }

    @PutMapping
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCodeExercise(@Valid @RequestBody CodeExercise exercise) {
        return ResponseEntity.status(HttpStatus.OK).body(this.codeExerciseImpl.updateCodeExercise(exercise));
    }
}
