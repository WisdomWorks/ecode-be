package com.example.codeE.controller;

import com.example.codeE.helper.ExcelHelper;
import com.example.codeE.model.course.Course;
import com.example.codeE.service.course.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String courseId){
        Course result = courseService.getById(courseId);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOne(@RequestBody Course course) {
        Course result = courseService.createOne(course);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @RequestMapping(value = "/import-courses",method = RequestMethod.POST)
    public ResponseEntity<?> importCoursesByExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }
        boolean importSuccess = this.courseService.importByExcel(file);
        if (importSuccess) {
            return ResponseEntity.ok(Map.of("message", "Course data uploaded and saved to database successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid file format"));
        }
    }

    @PatchMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@PathVariable String courseId, @RequestBody Map<String, Object> updates){
        Course result = courseService.updateById(courseId, updates);
        if(result == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable String courseId) {
        boolean result = courseService.deleteById(courseId);
        if (result) {
            return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
        }
        return ResponseEntity.notFound().build();
    }
}
