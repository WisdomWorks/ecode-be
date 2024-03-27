package com.example.codeE.controller;

import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.ClientService;
import com.example.codeE.service.judge.JudgeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private CodeSubmissionService codeSubmissionService;

    @Autowired
    private JudgeService judgeService;

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> exampleRq() throws IOException {
        CodeSubmission submission = new CodeSubmission(judgeService);
        submission.setExerciseId("6603897fdd74386f36d28783");
        submission.setTime(2.0);
        submission.setMemory(1);
        submission.setLanguageId("C");
        submission.setSource("#include <stdio.h>\nint main() { printf(\"Hello, World!\"); return 0; }");
//        submission.setStudentId("id");
//        submission.setError("error");
//        submission.setJudgedOn("judgedOn");
//        submission.setPretested(true);
        submission.setLockedAfter(LocalDateTime.now().plusHours(1));
//        submission.setCasePoints(1.0f);
//        submission.setCaseTotal(1.0f);
//        submission.setCurrentTestcase(1);
//        submission.setStatus("status"); //here
//        submission.setResult("result");


//        CodeSubmission savedSubmission = codeSubmissionService.updateCodeSubmission(submission);
        CodeSubmission savedSubmission = codeSubmissionService.saveCodeSubmission(submission);
        savedSubmission.judge(false, false);

        return ResponseEntity.status(200).build();
    }
}
