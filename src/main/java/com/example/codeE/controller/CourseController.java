package com.example.codeE.controller;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.*;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.courseStudent.CourseStudentService;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courses")
@Validated
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseStudentService courseStudentService;

    @GetMapping
    @RequestMapping(value = "{courseId}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable String courseId){
//        var course =
//        var teacherInCourse =
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
        boolean importSuccess = this.courseService.importByExcel(file);
        if (importSuccess) {
            return ResponseEntity.ok(Map.of("message", "Course data uploaded and saved to database successfully"));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "Invalid file format"));
    }

    @PatchMapping
    @RequestMapping(value = "", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateById(@Valid @RequestBody UpdateCourseRequest updates){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.updateById(updates.getCourseId(), updates));
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
        CourseStudent result = courseStudentService.addStudentToCourse(request);
        if(result == null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Failed to add student into course");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping
    @RequestMapping(value = "import-students", method = RequestMethod.POST)
    public ResponseEntity<?> addStudentsToCourse(@Valid @ModelAttribute ImportStudentToCourseRequest request) {
        if (request.getFile().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "File is empty"));
        }
        List<String> result = courseStudentService.importStudentsToCourse(request);
        if (result == null) {
            return ResponseEntity.ok(Map.of("message", "Import students to course successfully"));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fail to add these students in course: " + result);
    }

    @DeleteMapping
    @RequestMapping(value = "student", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @RequestBody RemoveStudentFromCourseRequest request) {
        courseStudentService.deleteStudentInCourse(request);
        return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
    }
}
