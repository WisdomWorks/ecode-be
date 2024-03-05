package com.example.codeE.controller;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.material.Material;
import com.example.codeE.request.course.CreateCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.material.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/materials")
@Validated
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    @RequestMapping(value = "{materialId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String materialId){
        return ResponseEntity.ok(materialService.getById(materialId));
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(materialService.getAll());
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOne(@RequestBody CreateMaterialRequest request) {
        Material result = materialService.createOne(request);
        if(result == null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Failed to create new material");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@Valid @RequestBody UpdateMaterialRequest updates){
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.updateById(updates));
    }

    @DeleteMapping
    @RequestMapping(value = "{materialId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable String materialId) {
        boolean result = materialService.deleteById(materialId);
        if (result) {
            return ResponseEntity.ok(Map.of("message" , "Delete material successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No material found with ID:" + materialId);
    }
}
