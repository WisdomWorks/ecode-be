package com.example.codeE.helper;

import com.example.codeE.model.exercise.Exercise;
import com.example.codeE.util.timeFormater.DateTimeUtil;
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
            exercise.setCreatedDate(DateTimeUtil.format(LocalDateTime.now()));
            exercise.setUpdatedDate(DateTimeUtil.format(LocalDateTime.now()));
        }
    }
}
