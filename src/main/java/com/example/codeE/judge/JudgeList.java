package com.example.codeE.judge;

import com.example.codeE.constant.JudgePriority;
import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

class PriorityMarker {
    int priority;

    PriorityMarker(int priority) {
        this.priority = priority;
    }
}

public class JudgeList {
    private final int priorities = 4;
    LinkedList<Object> queue = new LinkedList<>();
    List<PriorityMarker> priority = new ArrayList<>();

    JudgeHandler judge; //????
    HashMap<String, Object> nodeMap = new HashMap<>();
    ReentrantLock lock = new ReentrantLock();

    public JudgeList() {
        for (int i = 0; i < priorities; i++) {
            this.priority.add(new PriorityMarker(i));
        }
    }

    public void handleFreeJudge(){
        this.lock.lock();
        try {
            Object node = this.queue.getFirst();
            int priority = 0;
            while (node != null) {
                if (node instanceof PriorityMarker) {
                    priority = ((PriorityMarker) node).priority + 1;
                } else if (priority >= JudgePriority.REJUDGE_PRIORITY
                        && !judge.isWorking() && !judge.isDisabled())
                {
                    return;
                } else {
                    String submissionId = ((CodeSubmission) node).getSubmissionId();
                    String problemId = ((CodeSubmission) node).getExerciseId();
                    String languageKey = ((CodeSubmission) node).getLanguageId();
                    String source = ((CodeSubmission) node).getSource();
                    try {
                        this.judge.submit(submissionId, problemId, languageKey, source);
                    } catch (Exception e) {
//                            System.out.println("Failed to dispatch " + id + " (" + problem + ", " + language + ") to " + judge.getName());
                        return;
                    }
//                        System.out.println("Dispatched queued submission " + id + ": " + judge.getName());
                    this.queue.remove(node);
                    this.nodeMap.remove(submissionId);
                    break;
                }
                node = this.queue.getFirst();
            }
        } finally {
            this.lock.unlock();
        }

    }

    public void register() {
        lock.lock();
        try {
            this.disconnect(true);
            this.handleFreeJudge();
        } finally {
            lock.unlock();
        }
    }

    public void disconnect(boolean force) {
        this.lock.lock();
        try {
            Judge j = new Judge();
            j.disconnect(force);
        } finally {
            this.lock.unlock();
        }
    }

    public void updateProblems() {
        this.lock.lock();
        try {
            this.handleFreeJudge();
        } finally {
            this.lock.unlock();
        }
    }

    public void updateDisableJudge(boolean isDisabled) {
        this.lock.lock();
        try {
            this.judge.setDisabled(isDisabled);
        } finally {
            this.lock.unlock();
        }
    }

    public void onJudgeFree() {
        System.out.println("Judge available after grading: " + judge.getName());
        this.lock.lock();
        try {
            this.judge.setWorking(null);
            this.handleFreeJudge();
        } finally {
            this.lock.unlock();
        }
    }

    public boolean abort(String submission) {
        System.out.println("Abort request: " + submission);
        this.lock.lock();
        try {
            try {
                this.queue.remove(this.nodeMap.get(submission));
                this.nodeMap.remove(submission);
            } catch (Exception e2) {
                return false;
            }
        } finally {
            this.lock.unlock();
        }
        return false;
    }

    public boolean checkPriority(int priority) {
        return 0 <= priority && priority < this.priorities;
    }

    public void judge(String submissionId, String problem, String language, String source, String judgeId, int priority) {
        this.lock.lock();
        try {
            if (this.nodeMap.containsKey(submissionId)) {
                return;
            }
            if (!judge.isWorking() && !judge.isDisabled()) {
                try {
                    this.judge.submit(submissionId, problem, language, source);
                } catch (Exception e) {
                    System.out.println("Failed to dispatch " + submissionId + " (" + problem + ", " + language + ") to " + judge.getName());
                }
            } else {
                CodeSubmission submission = new CodeSubmission(submissionId, problem, language, source, judgeId);
                this.queue.add(priority, submission);
                this.nodeMap.put(submissionId, submission);
                System.out.println("Queued submission: " + submissionId);
            }
        } finally {
            this.lock.unlock();
        }
    }
}
