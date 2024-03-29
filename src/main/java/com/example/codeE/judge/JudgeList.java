package com.example.codeE.judge;

import com.example.codeE.constant.JudgePriority;
import com.example.codeE.judge.configurations.JudgeHandlerVariables;
import com.example.codeE.judge.configurations.JudgeListVariables;
import com.example.codeE.judge.configurations.PriorityMarker;
import com.example.codeE.judge.handlers.JudgeHandler;
import com.example.codeE.model.exercise.CodeSubmission;
import com.example.codeE.model.exercise.common.Judge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;



@Component
public class JudgeList {
    private final int priorities = 4;



    private final JudgeHandler judge;


    @Autowired
    public JudgeList(JudgeHandler judge) {
        this.judge = judge;
    }

    public void handleFreeJudge(){
        JudgeListVariables.lock.lock();
        ListIterator<Object> iterator = JudgeListVariables.queue.listIterator();
        try {
            Object node = null;
            if (iterator.hasNext()) {
                node = iterator.next();
            }
            int priority = 0;
            while (node != null) {
                if (node instanceof PriorityMarker) {
                    priority = ((PriorityMarker) node).priority + 1;
                } else if (priority >= JudgePriority.REJUDGE_PRIORITY
                        && !judge.isWorking() && !JudgeHandlerVariables.isDisabled)
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
                    JudgeListVariables.queue.remove(node);
                    JudgeListVariables.nodeMap.remove(submissionId);
                    break;
                }
                node = iterator.hasNext() ? iterator.next() : null;
            }
        } finally {
            JudgeListVariables.lock.unlock();
        }

    }

    public void register() {
        JudgeListVariables.lock.lock();
        try {
            this.disconnect(true);
            this.handleFreeJudge();
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }

    public void disconnect(boolean force) {
        JudgeListVariables.lock.lock();
        try {
            Judge j = new Judge();
            j.disconnect(force);
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }

    public void updateProblems() {
        JudgeListVariables.lock.lock();
        try {
            this.handleFreeJudge();
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }

    public void updateDisableJudge(boolean isDisabled) {
        JudgeListVariables.lock.lock();
        try {
            JudgeHandlerVariables.isDisabled = isDisabled;
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }

    public void onJudgeFree() {
        System.out.println("Judge available after grading: " + JudgeHandlerVariables.name);
        JudgeListVariables.lock.lock();
        try {
            JudgeHandlerVariables.working = null;
            this.handleFreeJudge();
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }

    public boolean abort(String submission) {
        System.out.println("Abort request: " + submission);
        JudgeListVariables.lock.lock();
        try {
            try {
                JudgeListVariables.queue.remove(JudgeListVariables.nodeMap.get(submission));
                JudgeListVariables.nodeMap.remove(submission);
            } catch (Exception e2) {
                return false;
            }
        } finally {
            JudgeListVariables.lock.unlock();
        }
        return false;
    }

    public boolean checkPriority(int priority) {
        return 0 <= priority && priority < this.priorities;
    }

    public void judge(String submissionId, String problem, String language, String source, String judgeId, int priority) {
        JudgeListVariables.lock.lock();
        try {
            if (JudgeListVariables.nodeMap.containsKey(submissionId)) {
                return;
            }
            if (!judge.isWorking() && !JudgeHandlerVariables.isDisabled) {
                try {
                    this.judge.submit(submissionId, problem, language, source);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Failed to dispatch " + submissionId + " (" + problem + ", " + language + ") to " + JudgeHandlerVariables.name);
                }
            } else {
                CodeSubmission submission = new CodeSubmission(submissionId, problem, language, source, judgeId);
                JudgeListVariables.queue.add(priority, submission);
                JudgeListVariables.nodeMap.put(submissionId, submission);
                System.out.println("Queued submission: " + submissionId);
            }
        } finally {
            JudgeListVariables.lock.unlock();
        }
    }
}
