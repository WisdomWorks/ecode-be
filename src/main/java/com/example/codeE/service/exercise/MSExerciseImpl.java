package com.example.codeE.service.exercise;

import com.example.codeE.model.exercise.MSExercise;
import com.example.codeE.repository.MSExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MSExerciseImpl implements MSExerciseService {
    @Autowired
    private MSExerciseRepository msExerciseRepository;

    @Override
    public MSExercise saveExerciseToMySql(MSExercise msExercise) {
        return this.msExerciseRepository.save(msExercise);
    }
}
