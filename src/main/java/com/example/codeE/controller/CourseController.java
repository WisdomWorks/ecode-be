package com.example.codeE.controller;

import com.example.codeE.model.course.Course;
import com.example.codeE.model.course.CourseStudent;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.service.course.CourseService;
import com.example.codeE.service.courseStudent.CourseStudentService;
import com.example.codeE.service.user.UserService;
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
        return ResponseEntity.ok(courseService.getById(courseId));
    }

    @GetMapping
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(courseService.getAll());
    }

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createOne(@RequestBody Course course) {
        Course result = courseService.createOne(course);
        if(result == null){
            return ResponseEntity.status(HttpStatus.CREATED).body("Failed to create new course");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
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
    public ResponseEntity<?> deleteById(@PathVariable String courseId) {
        boolean result = courseService.deleteById(courseId);
        if (result) {
            return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No course found with ID:" + courseId);
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
        boolean importSuccess = courseStudentService.importStudentsToCourse(request);
        if (importSuccess) {
            return ResponseEntity.ok(Map.of("message", "Import students to course successfully"));
        }

        return ResponseEntity.badRequest().body(Map.of("error", "There is something wrong with the excel file. Please try again!"));
    }

    @DeleteMapping
    @RequestMapping(value = "student", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@Valid @RequestBody RemoveStudentFromCourseRequest request) {
        boolean result = courseStudentService.deleteStudentInCourse(request);
        if (result) {
            return ResponseEntity.ok(Map.of("message" , "Delete course successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Delete fail");
    }
}
