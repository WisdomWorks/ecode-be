package com.example.codeE.service.exercise.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.codeE.model.exercise.common.RuntimeVersion;
import com.example.codeE.repository.RuntimeVersionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RuntimeVersionImpl.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RuntimeVersionImplDiffblueTest {
    @Autowired
    private RuntimeVersionImpl runtimeVersionImpl;

    @MockBean
    private RuntimeVersionRepository runtimeVersionRepository;

    /**
     * Method under test:
     * {@link RuntimeVersionImpl#saveRuntimeVersion(RuntimeVersion)}
     */
    @Test
    void testSaveRuntimeVersion() {
        // Arrange
        RuntimeVersion runtimeVersion = new RuntimeVersion();
        runtimeVersion.setJudgeId("42");
        runtimeVersion.setLanguageId("en");
        runtimeVersion.setName("Name");
        runtimeVersion.setPriority(1);
        runtimeVersion.setRuntimeVersionId("42");
        runtimeVersion.setVersion("1.0.2");
        when(runtimeVersionRepository.save(Mockito.<RuntimeVersion>any())).thenReturn(runtimeVersion);

        RuntimeVersion runtimeVersion2 = new RuntimeVersion();
        runtimeVersion2.setJudgeId("42");
        runtimeVersion2.setLanguageId("en");
        runtimeVersion2.setName("Name");
        runtimeVersion2.setPriority(1);
        runtimeVersion2.setRuntimeVersionId("42");
        runtimeVersion2.setVersion("1.0.2");

        // Act
        runtimeVersionImpl.saveRuntimeVersion(runtimeVersion2);

        // Assert that nothing has changed
        verify(runtimeVersionRepository).save(isA(RuntimeVersion.class));
        assertEquals("1.0.2", runtimeVersion2.getVersion());
        assertEquals("42", runtimeVersion2.getJudgeId());
        assertEquals("42", runtimeVersion2.getRuntimeVersionId());
        assertEquals("Name", runtimeVersion2.getName());
        assertEquals("en", runtimeVersion2.getLanguageId());
        assertEquals(1, runtimeVersion2.getPriority());
    }

    /**
     * Method under test: {@link RuntimeVersionImpl#deleteAllRuntimeVersion()}
     */
    @Test
    void testDeleteAllRuntimeVersion() {
        // Arrange
        doNothing().when(runtimeVersionRepository).deleteAll();

        // Act
        runtimeVersionImpl.deleteAllRuntimeVersion();

        // Assert that nothing has changed
        verify(runtimeVersionRepository).deleteAll();
    }
}
