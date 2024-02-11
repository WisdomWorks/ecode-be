package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.MSExercise;
import com.example.codeE.repository.MSExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MSExerciseImpl implements MSExerciseService {
    @Autowired
    private MSExerciseRepository msExerciseRepository;

    @Override
    public MSExercise saveExerciseToMySql(MSExercise msExercise) {
        return this.msExerciseRepository.save(msExercise);
    }

    @Override
    public List<MSExercise> getAllExercisesByCourseId(String courseId) {
        return this.msExerciseRepository.getAllExercisesByCourseId(courseId);
    }
}
