package com.example.codeE.judge.configurations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class JudgeHandlerVariables {
    public static Thread pingThread;
    public static CountDownLatch stopPing = new CountDownLatch(1);
    public static Map<String, Double> problems = new HashMap<>();
    public static HashMap<String, List<List<Object>>> executors = new HashMap<>();
    public static String working;
    public static String name;
    public static boolean isDisabled = false;
    public static String judgeAddress;
    public static int timeout;
}
