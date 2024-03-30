package com.example.codeE.judge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.codeE.judge.handlers.JudgeHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JudgeList.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class JudgeListDiffblueTest {
    @MockBean
    private JudgeHandler judgeHandler;

    @Autowired
    private JudgeList judgeList;

    /**
     * Method under test: {@link JudgeList#abort(String)}
     */
    @Test
    void testAbort() {
        // Arrange, Act and Assert
        assertFalse(judgeList.abort("Submission"));
    }

    /**
     * Method under test: {@link JudgeList#checkPriority(int)}
     */
    @Test
    void testCheckPriority() {
        // Arrange, Act and Assert
        assertTrue(judgeList.checkPriority(1));
        assertFalse(judgeList.checkPriority(4));
        assertFalse(judgeList.checkPriority(-1));
    }
}
