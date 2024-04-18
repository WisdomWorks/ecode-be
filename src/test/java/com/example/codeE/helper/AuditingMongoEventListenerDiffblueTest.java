package com.example.codeE.helper;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.example.codeE.model.exercise.CodeExercise;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuditingMongoEventListener.class})
@ExtendWith(SpringExtension.class)
class AuditingMongoEventListenerDiffblueTest {
    @Autowired
    private AuditingMongoEventListener auditingMongoEventListener;

    /**
     * Method under test:
     * {@link AuditingMongoEventListener#onBeforeConvert(BeforeConvertEvent)}
     */
    @Test
    void testOnBeforeConvert() {
        // Arrange
        CodeExercise codeExercise = mock(CodeExercise.class);
        doNothing().when(codeExercise).setCreatedDate(Mockito.<String>any());
        doNothing().when(codeExercise).setUpdatedDate(Mockito.<String>any());

        // Act
        auditingMongoEventListener.onBeforeConvert(new BeforeConvertEvent<>(codeExercise, "Collection Name"));

        // Assert that nothing has changed
        verify(codeExercise).setCreatedDate(eq("1970-01-01T00:00:00Z"));
        verify(codeExercise).setUpdatedDate(eq("1970-01-01T00:00:00Z"));
    }
}
