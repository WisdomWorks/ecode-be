package com.example.codeE.service.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.FileHelper;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.repository.CodeExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;

    @Override
    public List<String> getProblemIds(List<CodeExercise> problems) {
        List<String> result = new ArrayList<>();
        for(CodeExercise problem : problems) {
            result.add(codeExerciseRepository.findById(problem.getExerciseId()).get().getExerciseId());
        }
        return result;
    }

    @Override
    public CodeExercise getProblemById(String problemId) {
        return codeExerciseRepository.findById(problemId).orElseThrow(() -> new RuntimeException("Problem not found"));
    }

    @Override
    public CodeExercise getCodeExerciseById(String exerciseId) {
        return codeExerciseRepository.findById(exerciseId).get();
    }

    @Override
    public CodeExercise createCodeExercise(CodeExercise codeExercise) {
        return codeExerciseRepository.save(codeExercise);
    }

    @Override
    public void createProblemFolder(List<TestCase> testCaseList, String exerciseId) {
        try {
            //Create problem folder
            Path path = Paths.get("/Users/nyan/Documents/CodeE/problems/"+exerciseId);
            Files.createDirectories(path);

            //Create init.yml file
            File initFile = new File("/Users/nyan/Documents/CodeE/problems/"+exerciseId+"/init.yml");
            String initFileContent = Constant.INIT_FILE_TEMPLATE;

            //Create temporary directory
            Path tempDirectory = Files.createTempDirectory("iozip");

            //Create input files and output files
            for(int i=0; i< testCaseList.size(); i++){
                //Write input file
                File inputFile = new File(tempDirectory.toFile(), "io." + (i+1) + ".in");
                FileWriter inputFileWriter = new FileWriter(inputFile);
                BufferedWriter inputFileBufferedWriter = new BufferedWriter(inputFileWriter);

                String input = testCaseList.get(i).getInput();
                inputFileBufferedWriter.write(input);

                inputFileBufferedWriter.close();

                //Write output file
                File outputFile = new File(tempDirectory.toFile(), "io." + (i+1) + ".out");
                FileWriter outputFileWriter = new FileWriter(outputFile);
                BufferedWriter outputFileBufferedWriter = new BufferedWriter(outputFileWriter);

                String output = testCaseList.get(i).getOutput();
                outputFileBufferedWriter.write(output);

                outputFileBufferedWriter.close();

                initFileContent = initFileContent + String.format(Constant.TESTCASE_TEMPLATE, i+1, i+1, testCaseList.get(i).getPoints());
            }

            //Zip iozip directory
            Path zipFilePath = Paths.get("/Users/nyan/Documents/CodeE/problems/"+exerciseId+"/iozip.zip");
            FileHelper.zipFile(zipFilePath, tempDirectory);

            FileWriter initFileWriter = new FileWriter(initFile);
            BufferedWriter initFileBufferedWriter = new BufferedWriter(initFileWriter);
            initFileBufferedWriter.write(initFileContent);
            initFileBufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
