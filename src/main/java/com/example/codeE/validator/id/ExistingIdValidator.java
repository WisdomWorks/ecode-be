package com.example.codeE.validator.id;

import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.UserRepository;
import com.example.codeE.request.exercise.DeleteExerciseRequest;
import com.example.codeE.request.user.CommonUserRequest;
import com.example.codeE.request.user.UpdateUserRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ExistingIdValidator implements ConstraintValidator<ExistingId, Object> {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UserRepository userRepository;

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
        } else if (object instanceof CommonUserRequest) {
            CommonUserRequest getUserRequest = (CommonUserRequest) object;
            return userRepository.existsById(getUserRequest.getUserId());
        }
        return false;
    }
}

