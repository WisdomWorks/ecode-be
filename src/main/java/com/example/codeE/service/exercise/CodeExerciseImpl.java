package com.example.codeE.service.exercise;

import com.example.codeE.helper.FileStreamHelper;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;
    @Override
    public CodeExercise createCodeExercise(CodeExercise codeExercise) {
        return this.codeExerciseRepository.save(codeExercise);
    }

    @Override
    public CodeExercise getCodeExerciseById(String exerciseId) {
        return this.codeExerciseRepository.findById(exerciseId).get();
    }

    @Override
    public void deleteCodeExerciseById(String exerciseId) {
        this.codeExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public CodeExercise updateCodeExercise(CodeExercise codeExercise) {
        return this.codeExerciseRepository.save(codeExercise);
    }
    @Override
    public String runCodeExercise(String fileCodeContent, String exerciseId, String containerId) {
        //get path file in container 
        String pathfile = "";
        //override file in container 
        if(!FileStreamHelper.OverrideFileContent(pathfile, fileCodeContent)){
            return "cant override file";
        }
        //run code

        // push file in to container
        //run code
        //get result from container 
        return "";
    }
}
