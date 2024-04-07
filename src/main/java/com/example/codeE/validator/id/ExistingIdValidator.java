package com.example.codeE.validator.id;

import com.example.codeE.repository.*;
import com.example.codeE.request.course.*;
import com.example.codeE.request.exercise.essay.CreateEssayExerciseRequest;
import com.example.codeE.request.exercise.file.CreateFileExerciseRequest;
import com.example.codeE.request.material.CreateMaterialRequest;
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
        if (object instanceof UpdateUserRequest updateUserRequest) {
            return userRepository.existsById(updateUserRequest.getUserId());
        } else if (object instanceof UpdateCourseRequest updateCourseRequest) {
            return courseRepository.existsById(updateCourseRequest.getCourseId());
        } else if (object instanceof AddStudentToCourseRequest addStudentToCourseRequest) {
            return courseRepository.existsById(addStudentToCourseRequest.getCourseId());
        } else if (object instanceof ImportStudentToCourseRequest importStudentToCourseRequest) {
            return courseRepository.existsById(importStudentToCourseRequest.getCourseId());
        } else if (object instanceof RemoveStudentFromCourseRequest removeStudentFromCourseRequest) {
            return courseStudentRepository.existsByStudentIdAndCourseId(removeStudentFromCourseRequest.getStudentId(), removeStudentFromCourseRequest.getCourseId()) > 0;
        } else if (object instanceof UpdateMaterialRequest updateMaterialRequest) {
            return materialRepository.existsById(updateMaterialRequest.getMaterialId());
        } else if (object instanceof CreateMaterialRequest createMaterialRequest) {
            return topicRepository.existsById(createMaterialRequest.getTopicId());
        } else if (object instanceof CreateEssayExerciseRequest createEssayExerciseRequest) {
            return topicRepository.existsById(createEssayExerciseRequest.getTopicId());
        } else if (object instanceof UpdateStudentsToCourseRequest updateStudentsToCourseRequest){
            return courseRepository.existsById(updateStudentsToCourseRequest.getCourseId()) &&
                    updateStudentsToCourseRequest.getStudentIds().stream().allMatch(studentId -> userRepository.existsById(studentId));
        } else if (object instanceof CreateFileExerciseRequest createFileExerciseRequest) {
            return topicRepository.existsById(createFileExerciseRequest.getTopicId());
        }
        return false;
    }
}

