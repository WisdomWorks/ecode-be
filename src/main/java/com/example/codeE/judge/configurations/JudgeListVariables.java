package com.example.codeE.judge.configurations;

import com.example.codeE.judge.configurations.PriorityMarker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class JudgeListVariables {
    public static LinkedList<Object> queue = new LinkedList<>(
            List.of(new PriorityMarker(0), new PriorityMarker(1), new PriorityMarker(2), new PriorityMarker(3)));
    public static List<PriorityMarker> priority = new ArrayList<>();

    public static HashMap<String, Object> nodeMap = new HashMap<>();
    public static ReentrantLock lock = new ReentrantLock();
}
