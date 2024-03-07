package com.example.codeE.controller;

import com.example.codeE.model.material.Material;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.material.MaterialService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    @RequestMapping(value = "{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getMaterialsByTopicId(@PathVariable String topicId){
        return ResponseEntity.ok(materialService.getAllByTopicId(topicId));
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(materialService.getAll());
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOne(@Valid @RequestBody CreateMaterialRequest request) {
        Material result = materialService.createOne(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateByMaterialAndTopicId(@Valid @RequestBody UpdateMaterialRequest updates){
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.updateById(updates));
    }

    @DeleteMapping
    @RequestMapping(value = "{materialId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @PathVariable String materialId) {
        materialService.deleteById(materialId);
        return ResponseEntity.ok(Map.of("message" , "Delete material successfully"));
    }

    @GetMapping
    @RequestMapping(value="/view", method = RequestMethod.GET)
    public ResponseEntity<?> getPublicGroups(@RequestParam String materialId){
        return ResponseEntity.ok(this.materialService.getAllGroupsByMaterialId(materialId));
    }

    @PostMapping
    @RequestMapping(value="/view", method = RequestMethod.POST)
    public ResponseEntity<?> addViewPermission(@RequestParam String materialId, @RequestParam List<String> groupIds){
        return ResponseEntity.ok(this.materialService.addViewPermission(materialId, groupIds));
    }

    @DeleteMapping
    @RequestMapping(value="/view", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeViewPermission(@RequestParam String materialId, @RequestParam List<String> groupIds){
        return ResponseEntity.ok(this.materialService.removeViewPermission(materialId, groupIds));
    }
}
