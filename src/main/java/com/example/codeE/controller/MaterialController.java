package com.example.codeE.controller;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.material.Material;
import com.example.codeE.request.material.CreateMaterialRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.service.material.MaterialService;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/materials")
@Validated
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    @RequestMapping(value = "{materialId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String materialId) {
        return ResponseEntity.ok(materialService.getById(materialId));
    }

    @GetMapping
    @RequestMapping(value = "topic/{topicId}", method = RequestMethod.GET)
    public ResponseEntity<?> getMaterialsByTopicId(@PathVariable String topicId) {
        return ResponseEntity.ok(materialService.getAllByTopicId(topicId));
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createOne(@Valid @ModelAttribute CreateMaterialRequest request, @RequestPart(required = false) MultipartFile file) {
        if (request.getMaterialType().equals("file")) {
            if (file == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Please upload your material file"));
        }
        if (request.getMaterialType().equals("string"))
            if (request.getUrl() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", "Please enter link material"));
        Material result = materialService.CreateMaterial(request, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateByMaterialAndTopicId(@Valid @RequestBody UpdateMaterialRequest updates) {
        return ResponseEntity.status(HttpStatus.CREATED).body(materialService.updateById(updates));
    }

    @DeleteMapping
    @RequestMapping(value = "{materialId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @PathVariable String materialId) {
        materialService.deleteById(materialId);
        return ResponseEntity.ok(Map.of("message", "Delete material successfully"));
    }

//    @GetMapping
//    @RequestMapping(value="/view", method = RequestMethod.GET)
//    public ResponseEntity<?> getPublicMaterials(@RequestParam String materialId){
//        return ResponseEntity.ok(this.materialService.getAllGroupsByMaterialId(materialId));
//    }

    @PostMapping
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public ResponseEntity<?> addViewPermission(@RequestParam String materialId, @RequestParam List<String> groupIds, @RequestParam boolean isShowAll) {
        if (this.materialService.addViewPermission(materialId, groupIds, isShowAll))
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Grant permission for material successful"));
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("massage", "Can not grant permission for this material"));
    }

    @DeleteMapping
    @RequestMapping(value = "/view", method = RequestMethod.DELETE)
    public ResponseEntity<?> removeViewPermission(@RequestParam String materialId, @RequestParam List<String> groupIds) {
        return ResponseEntity.ok(this.materialService.removeViewPermission(materialId, groupIds));
    }
}
