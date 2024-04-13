package com.example.codeE.helper;

import com.example.codeE.constant.Constant;
import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.model.exercise.Submission;
import com.example.codeE.util.DateTimeUtil;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuditingMongoEventListener extends AbstractMongoEventListener<Object> {

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Object> event) {
        Object source = event.getSource();

        if (source instanceof Exercise) {
            Exercise exercise = (Exercise) source;
            exercise.setCreatedDate(DateTimeUtil.formatToIso(LocalDateTime.now()));
            exercise.setUpdatedDate(DateTimeUtil.formatToIso(LocalDateTime.now()));
        }

        if (source instanceof Submission) {
            Submission submission = (Submission) source;
            submission.setDateSubmit(DateTimeUtil.formatToIso(LocalDateTime.now()));
            submission.setDateGrade(DateTimeUtil.formatToIso(LocalDateTime.now()));
        }
    }
}
