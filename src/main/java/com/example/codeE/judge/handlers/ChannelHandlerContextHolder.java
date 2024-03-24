package com.example.codeE.judge.handlers;

import java.util.concurrent.atomic.AtomicReference;

public class ChannelHandlerContextHolder {
    private static final AtomicReference<JudgeHandler> judgeHandlerRef = new AtomicReference<>();

    public static JudgeHandler getJudgeHandler() {
        return judgeHandlerRef.get();
    }

    public static void setJudgeHandler(JudgeHandler judgeHandler) {
        judgeHandlerRef.set(judgeHandler);
    }
}
