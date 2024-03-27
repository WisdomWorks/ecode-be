package com.example.codeE.service.judge;

import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

public interface JudgeService {
    Object judgeRequest(Object packet, boolean reply);

    boolean judgeSubmission(CodeSubmission submission, boolean rejudge);

    public void disconnectJudge(Judge judge, boolean force);

    public void updateDisableJudge(Judge judge);

    public void abortSubmission(CodeSubmission submission);
}
