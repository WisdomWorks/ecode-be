package com.example.codeE.service.judge;

import com.example.codeE.constant.Constant;
import com.example.codeE.helper.ZlibCompression;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.example.codeE.service.exercise.common.SubmissionTestCaseService;
import com.example.codeE.service.exercise.submission.CodeSubmissionService;
import com.example.codeE.util.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.zip.DataFormatException;

public class JudgeImpl implements JudgeService{
    public static final Gson gson = new Gson();
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private CodeSubmissionService codeSubmissionService;

    @Autowired
    private SubmissionTestCaseService submissionTestCaseService;

    @Override
    public Object judgeRequest(Object packet, boolean reply) {
        try(Socket socket = new Socket(Constant.BRIDGED_HOST, Constant.BRIDGED_SPRING_BOOT_PORT)){
            // Convert to JSON
            String output = gson.toJson(packet);
            // Compress the JSON
            byte[] compressedOutput = ZlibCompression.zlibify(output);

            // Send the compressed data through the socket
            DataOutputStream writer = new DataOutputStream(socket.getOutputStream());
            writer.writeInt(compressedOutput.length);
            writer.write(compressedOutput);
            writer.flush();

            // If reply is true, read the response from the socket
            if (reply) {
                // Read the response from the socket
                DataInputStream reader = new DataInputStream(socket.getInputStream());
                int length = reader.readInt();
                byte[] data = new byte[length];
                reader.readFully(data);

                // If the data is empty, throw an exception
                if (data.length == 0) {
                    throw new IOException("Judge did not respond");
                }

                // Decompress the data
                String decompressedInput;
                try {
                    decompressedInput = ZlibCompression.dezlibify(data);
                } catch (DataFormatException e) {
                    throw new IOException("Failed to decompress data", e);
                }

                // Return the decompressed data
                return gson.fromJson(decompressedInput, Object.class);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean judgeSubmission(CodeSubmission submission, boolean rejudge) {
        CodeSubmission updates = new CodeSubmission();
        updates.setTime(null);
        updates.setMemory(null);
        updates.setScore(null);
        updates.setResult(null);
        updates.setCasePoints((float) 0);
        updates.setCaseTotal((float) 0);
        updates.setError(null);
        updates.setDateGrade(rejudge ? DateTimeUtil.format(LocalDateTime.now()) : null);
        updates.setStatus("QU");
        updates.setPretested(true);

        int priority = Constant.DEFAULT_PRIORITY;

        // If the submission status is not updated, return false
        if (codeSubmissionService.checkStatusAndUpdate(updates) == null) {
            return false;
        }

        // Delete all test cases for the submission
        submissionTestCaseService.deleteAllTcBySubmissionId(submission.getSubmissionId());

        // Create a packet to send to the judge
        ObjectNode packet = JsonNodeFactory.instance.objectNode();
        packet.put("name", "submission-request");
        packet.put("submission-id", submission.getSubmissionId());
        packet.put("problem-id", submission.getExerciseId());
        packet.put("language", submission.getLanguageId());
//        packet.put("source", submission.get());
        packet.put("judge-id", submission.getJudgedOn());
        packet.put("priority", rejudge ? Constant.REJUDGE_PRIORITY : priority);

        boolean success = false;

        try{
            String response = (String) judgeRequest(mapper.writeValueAsString(packet), true);

            // If the response is not valid, return false
            ObjectNode node = (ObjectNode) mapper.readTree(response);
            if (!node.get("name").asText().equals("submission-received") || !node.get("submission-id").asText().equals(submission.getSubmissionId())) {
                codeSubmissionService.updateStatusAndResult(submission.getSubmissionId(), "IE", "IE");
            } //????
            // If the response is valid, return true
            success = true;
        } catch (Exception e) {
            System.out.println("Failed to send request to judge");
            // If the request fails, update the submission status to IE
            codeSubmissionService.updateStatusAndResult(submission.getSubmissionId(), "IE", "IE");
        }
        return success;
    }

    @Override
    public void disconnectJudge(Judge judge, boolean force) {
        // Create a packet to send to the judge
        ObjectNode packet = JsonNodeFactory.instance.objectNode();
        packet.put("name", "disconnect-judge");
        packet.put("judge-id", judge.getName());
        packet.put("force", force);

        // Send the packet to the judge
        try {
            judgeRequest(mapper.writeValueAsString(packet), false);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateDisableJudge(Judge judge) {
        // Create a packet to send to the judge
        ObjectNode packet = JsonNodeFactory.instance.objectNode();
        packet.put("name", "disable-judge");
        packet.put("judge-id", judge.getName());
        packet.put("is-disabled", judge.isDisabled());

        // Send the packet to the judge
        try {
            judgeRequest(mapper.writeValueAsString(packet), true);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void abortSubmission(CodeSubmission submission) {
        // If the submission status is D, return
        if (submission.getStatus().equals("D")) {
            return;
        }

        // Create a packet to send to the judge
        ObjectNode packet = JsonNodeFactory.instance.objectNode();
        packet.put("name", "terminate-submission");
        packet.put("submission-id", submission.getSubmissionId());

        // Send the packet to the judge
        String response = null;
        try {
            response = (String) judgeRequest(mapper.writeValueAsString(packet), true);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        ObjectNode node = null;
        try {
            node = (ObjectNode) mapper.readTree(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // If the judge was not aborted, update the submission status to AB
        if (!node.get("judge-aborted").asBoolean()) {
            codeSubmissionService.updateStatusAndResult(submission.getSubmissionId(), "AB", "AB");
        }
    }
}
