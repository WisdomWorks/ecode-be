package com.example.codeE.controller;

import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.service.judge.ClientService;
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

@RestController
@RequestMapping("/judge")
public class JudgeController {

    @Autowired
    private CodeSubmissionService codeSubmissionService;

    @PostMapping
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> exampleRq() throws IOException {
        CodeSubmission submission = new CodeSubmission();
        submission.setSubmissionId("1");
        submission.setExerciseId("1");
        submission.setLanguageId("C");
        submission.setSource("#include <stdio.h>\nint main() { printf(\"Hello, World!\"); return 0; }");

        CodeSubmission savedSubmission = codeSubmissionService.updateCodeSubmission(submission);

        savedSubmission.judge(false, false);

        return ResponseEntity.status(200).build();
    }
}
