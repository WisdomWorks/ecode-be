package com.example.codeE.controller;

import com.example.codeE.request.course.*;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.courseStudent.CourseStudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseStudentService courseStudentService;


    @GetMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String courseId){
        return ResponseEntity.ok(courseService.getById(courseId));
    }
    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOne(@Valid @RequestBody CreateCourseRequest course) {
        var result = courseService.createOne(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    @RequestMapping(value = "/import-courses",method = RequestMethod.POST)
    public ResponseEntity<?> importCoursesByExcel(@Valid @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }

        return this.courseService.importByExcel(file);
    }

    @PutMapping
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<?> updateById(@Valid @RequestBody UpdateCourseRequest updates){
        courseService.updateById(updates.getCourseId(), updates);
        CourseResponse courseResponse = courseService.getById(updates.getCourseId());
        return ResponseEntity.status(HttpStatus.OK).body(courseResponse);
    }

    @PutMapping
    @RequestMapping(value = "students", method = RequestMethod.PUT)
    public ResponseEntity<?> updateStudentsInCourse(@Valid @RequestBody UpdateStudentsToCourseRequest request) {
        courseStudentService.updateStudentsInCourse(request);
        CourseResponse course = courseService.getById(request.getCourseId());
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }

    @DeleteMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @PathVariable String courseId) {
        courseService.deleteById(courseId);
        return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
    }

    // Course - Student api
    @PostMapping
    @RequestMapping(value = "student", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentToCourse(@Valid @RequestBody AddStudentToCourseRequest request) {
        var result = courseStudentService.addStudentToCourse(request);
        if(result == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add student into course");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    @RequestMapping(value = "import-students", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentsToCourse(@Valid @ModelAttribute ImportStudentToCourseRequest request) {
        if (request.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }
        List<Integer> result = courseStudentService.importStudentsToCourse(request);
        if (result.isEmpty()) {
            return ResponseEntity.ok(Map.of("message", "Import students to course successfully"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("failedRows", result));
    }

    @DeleteMapping
    @RequestMapping(value = "student", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @RequestBody RemoveStudentFromCourseRequest request) {
        courseStudentService.deleteStudentInCourse(request);
        return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
    }

    @PostMapping
    @RequestMapping(value = "enrollment", method = RequestMethod.POST)
    public ResponseEntity<?> enrollUserToCourse(@Valid @RequestBody CourseEnrollmentRequest request){
        var response = this.courseService.enrollStudentToCourse(request);
        return switch (response.getStatus()) {
            case 404 -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            case 409 -> ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            case 201 -> ResponseEntity.status(HttpStatus.CREATED).body(response);
            case 400 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            default -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        };
    }
    @DeleteMapping
    @RequestMapping(value = "unenrollment", method = RequestMethod.DELETE)
    public ResponseEntity<?> unEnrollUserInCourse(@Valid @RequestParam String userId, @RequestParam String courseId){
        this.courseService.unEnrollUserInCourse(userId, courseId);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("message","UnEnroll successful"));
    }
    @GetMapping
    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCourseByUserId(@Valid @PathVariable String userId){
        return ResponseEntity.status(HttpStatus.OK).body(this.courseService.getCourseByUserId(userId));
    }
}
