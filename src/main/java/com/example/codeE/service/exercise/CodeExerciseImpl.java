package com.example.codeE.service.exercise;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.CloudStorageHelper;
import com.example.codeE.helper.FileHelper;
import com.example.codeE.model.exercise.CodeExercise;
import com.example.codeE.model.exercise.common.SessionExercise;
import com.example.codeE.model.exercise.common.problem.TestCase;
import com.example.codeE.repository.CodeExerciseRepository;
import com.example.codeE.repository.ExerciseRepository;
import com.example.codeE.repository.SessionExerciseRepository;
import com.example.codeE.request.exercise.code.CodeDetailResponse;
import com.example.codeE.request.exercise.code.UpdateCodeExerciseRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CodeExerciseImpl implements CodeExerciseService{
    @Autowired
    private CodeExerciseRepository codeExerciseRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private CloudStorageHelper cloudStorageHelper;

    @Autowired
    private SessionExerciseRepository sessionExerciseRepository;
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
    public CodeDetailResponse getCodeExerciseDetail(String exerciseId, HttpServletRequest request) {
        CodeExercise codeExercise = codeExerciseRepository.findById(exerciseId).get();
        // Get only TestCase with 0 points
        List<TestCase> testCases = codeExercise.getTestCases();
        List<TestCase> pretestCases = new ArrayList<>();
        for(TestCase testCase : testCases) {
            if(testCase.getPoints() == 0) {
                pretestCases.add(testCase);
            }
        }
        codeExercise.setTestCases(pretestCases);
        var sessionlist = this.sessionExerciseRepository.findAll();
        SessionExercise session = getSessionExercise(request, sessionlist);
        Date timeStart = new Date();
        try {
            var timeString = session.getTimeStart();
            SimpleDateFormat sdf = new SimpleDateFormat(Constant.DATE_TIME_ISO_FORMAT);
            timeStart = sdf.parse(timeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new CodeDetailResponse(codeExercise, timeStart);
    }
    private static SessionExercise getSessionExercise(HttpServletRequest request, List<SessionExercise> sessionlist) {
        SessionExercise session = new SessionExercise();
        String loginId = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("LoginSessionId".equals(cookie.getName())) {
                    loginId = cookie.getValue();
                }
            }
        }
        for (var item : sessionlist) {
            if (item.getLoginId().equals(loginId)) {
                session = item;
            }
        }
        return session;
    }
    @Override
    public CodeExercise createCodeExercise(CodeExercise codeExercise) {
        return codeExerciseRepository.save(codeExercise);
    }

    @Override
    public void deleteCodeExercise(String exerciseId) {
        codeExerciseRepository.deleteById(exerciseId);
    }

    @Override
    public CodeExercise updateCodeExercise(String exerciseId, UpdateCodeExerciseRequest updateCodeExerciseRequest) {
        CodeExercise codeExercise = codeExerciseRepository.findById(exerciseId).get();
        codeExercise.setExerciseName(updateCodeExerciseRequest.getExerciseName());
        codeExercise.setTopicId(updateCodeExerciseRequest.getTopicId());
        codeExercise.setKey(updateCodeExerciseRequest.getKey());
        codeExercise.setStartTime(updateCodeExerciseRequest.getStartTime());
        codeExercise.setEndTime(updateCodeExerciseRequest.getEndTime());
        codeExercise.setDurationTime(updateCodeExerciseRequest.getDurationTime());
        codeExercise.setShowAll(codeExercise.isShowAll());
        codeExercise.setReAttempt(updateCodeExerciseRequest.getReAttempt());
        codeExercise.setDescription(updateCodeExerciseRequest.getDescription());
        codeExercise.setAllowedLanguageIds(updateCodeExerciseRequest.getAllowedLanguageIds());
        codeExercise.setPoints(updateCodeExerciseRequest.getPoints());
        codeExercise.setMemoryLimit(Constant.PROBLEM_MAX_MEMORY_LIMIT);
        codeExercise.setTimeLimit((double) Constant.PROBLEM_MAX_TIME_LIMIT);
        codeExercise.setTemplate(updateCodeExerciseRequest.getTemplate());
        codeExercise.setType("code");
        codeExercise.setPublicGroupIds(codeExercise.getPublicGroupIds());
        codeExercise.setTestCases(updateCodeExerciseRequest.getTestCases());
        codeExercise.setUsingAiGrading(updateCodeExerciseRequest.isUsingAiGrading());
        this.exerciseRepository.save(codeExercise);
        return codeExerciseRepository.save(codeExercise);
    }

    @Override
    public void createProblemFolder(List<TestCase> testCaseList, String exerciseId) {
        try {
            String store = "problems/" + exerciseId + "/";

            //Create init.yml file
            File initFile = File.createTempFile("init", ".yml");
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
            Path zipFilePath = Files.createTempFile("iozip", ".zip");
            FileHelper.zipFile(zipFilePath, tempDirectory);

            FileWriter initFileWriter = new FileWriter(initFile);
            BufferedWriter initFileBufferedWriter = new BufferedWriter(initFileWriter);
            initFileBufferedWriter.write(initFileContent);
            initFileBufferedWriter.close();

            cloudStorageHelper.uploadFile(FileHelper.convertFileToMultiPartFile(initFile, "init.yml"), store);
            cloudStorageHelper.uploadFile(FileHelper.convertFileToMultiPartFile(zipFilePath.toFile(), "iozip.zip"), store);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
