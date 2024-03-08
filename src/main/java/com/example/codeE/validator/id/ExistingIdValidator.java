package com.example.codeE.validator.id;

import com.example.codeE.repository.*;
import com.example.codeE.request.course.AddStudentToCourseRequest;
import com.example.codeE.request.course.ImportStudentToCourseRequest;
import com.example.codeE.request.course.RemoveStudentFromCourseRequest;
import com.example.codeE.request.course.UpdateCourseRequest;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.request.material.UpdateMaterialRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingIdValidator implements ConstraintValidator<ExistingId, Object> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseStudentRepository courseStudentRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void initialize(ExistingId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        if (object instanceof DeleteExerciseRequest) {
            DeleteExerciseRequest deleteExerciseRequest = (DeleteExerciseRequest) object;
            return exerciseRepository.existsById(deleteExerciseRequest.getExerciseId());
        } else if (object instanceof UpdateUserRequest) {
            UpdateUserRequest updateUserRequest = (UpdateUserRequest) object;
            return userRepository.existsById(updateUserRequest.getUserId());
        } else if (object instanceof UpdateCourseRequest) {
            UpdateCourseRequest updateCourseRequest = (UpdateCourseRequest) object;
            return courseRepository.existsById(updateCourseRequest.getCourseId());
        } else if (object instanceof AddStudentToCourseRequest) {
            AddStudentToCourseRequest addStudentToCourseRequest = (AddStudentToCourseRequest) object;
            return courseRepository.existsById(addStudentToCourseRequest.getCourseId())
                    && userRepository.existsById(addStudentToCourseRequest.getStudentId());
        } else if (object instanceof ImportStudentToCourseRequest) {
            ImportStudentToCourseRequest importStudentToCourseRequest = (ImportStudentToCourseRequest) object;
            return courseRepository.existsById(importStudentToCourseRequest.getCourseId());
        } else if (object instanceof RemoveStudentFromCourseRequest) {
            RemoveStudentFromCourseRequest removeStudentFromCourseRequest = (RemoveStudentFromCourseRequest) object;
            return courseStudentRepository.existsByStudentIdAndCourseId(removeStudentFromCourseRequest.getStudentId(), removeStudentFromCourseRequest.getCourseId()) > 0;
        } else if (object instanceof UpdateMaterialRequest) {
            UpdateMaterialRequest updateMaterialRequest = (UpdateMaterialRequest) object;
            return materialRepository.existsById(updateMaterialRequest.getMaterialId())
                    && topicRepository.existsById(updateMaterialRequest.getTopicId());
        } else if (object instanceof CreateMaterialRequest) {
            CreateMaterialRequest createMaterialRequest = (CreateMaterialRequest) object;
            return topicRepository.existsById(createMaterialRequest.getTopicId());
        }
        return false;
    }
}

