package com.example.codeE.service.exercise;

import com.example.codeE.helper.LoggerHelper;
import com.example.codeE.model.exercise.FileExercise;
import com.example.codeE.repository.FileExerciseRepository;
import com.example.codeE.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class FileExerciseImpl implements FileExerciseService{
    @Autowired
    private FileExerciseRepository fileExerciseRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public FileExercise createFileExercise(FileExercise fileExercise) {
        try{
            for(String groupId : fileExercise.getPublicGroupIds()){
                this.groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No Group found with ID: " + groupId));
            }
            if (fileExercise.getReAttempt() <= 0){
                fileExercise.setReAttempt(1);
            }
            return this.fileExerciseRepository.save(fileExercise);
        }catch (Exception e){
            LoggerHelper.logError(e.getMessage());
            throw new RuntimeException("Can not save file exercise");
        }
    }
}
