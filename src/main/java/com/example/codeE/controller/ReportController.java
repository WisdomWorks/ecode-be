package com.example.codeE.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
@Validated
public class ReportController {
    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getOverviewScoreExerciseReport(@PathVariable String exerciseId){
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
