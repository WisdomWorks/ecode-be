package com.example.codeE.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Constant {
    public static final String UPPER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CHAR = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER_CHAR = "0123456789";
    public static final String SPECIAL_CHAR = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    //time format
    public static final String DATE_TIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String TIME_FORMAT = "HH:mm:ss";

    //file path
    public static final String EXCEL_FILE_PATH = "./excel/";

    //file path in docker container
    public static final String INIT_FILE_TEMPLATE = "archive: iozip.zip\n" + "test_cases:";
    public static final String TESTCASE_TEMPLATE = "\n- {in: io.%s.in, out: io.%s.out, points: %s}";
    public static final List<String> VALID_ROLES = Arrays.asList("student", "teacher", "admin");

    //first param: name
    //second param: username
    //third param: password
    public static final String MAIL_TEMPLATE = """
            Hi, %s,
                        
            This is your account to login CODEE system:
                User name: %s
                Password: %s
                        
            Thank a lot.
                        
            WisdomWorks.""";

    public static final String BRIDGED_HOST = "127.0.0.1";
    public static final int BRIDGED_SPRING_BOOT_PORT = 9998;
    public static final int BRIDGED_JUDGE_PORT = 9999;

    public static final Map<String, String> USER_DISPLAY_CODES = new HashMap<>();
    public static final Map<String, String> SUBMISSION_RESULT = new HashMap<>();
    public static final Map<String, String> STATUS = new HashMap<>();
    public static final Map<String, String> CHECKERS = new HashMap<>();
    public static final int CONTEST_SUBMISSION_PRIORITY = 0;
    public static final int DEFAULT_PRIORITY = 1;
    public static final int REJUDGE_PRIORITY = 2;
    public static final int BATCH_REJUDGE_PRIORITY = 3;
    public static final String SERVER_NAME = "DefaultJudge";
    public static final int PROBLEM_MIN_TIME_LIMIT = 0;  // seconds
    public static final int PROBLEM_MAX_TIME_LIMIT = 60;  // seconds
    public static final int PROBLEM_MIN_MEMORY_LIMIT = 0;  // kilobytes
    public static final int PROBLEM_MAX_MEMORY_LIMIT = 1048576; // kilobytes

    static {
        USER_DISPLAY_CODES.put("AC", "Accepted");
        USER_DISPLAY_CODES.put("WA", "Wrong Answer");
        USER_DISPLAY_CODES.put("SC", "Short Circuited");
        USER_DISPLAY_CODES.put("TLE", "Time Limit Exceeded");
        USER_DISPLAY_CODES.put("MLE", "Memory Limit Exceeded");
        USER_DISPLAY_CODES.put("OLE", "Output Limit Exceeded");
        USER_DISPLAY_CODES.put("IR", "Invalid Return");
        USER_DISPLAY_CODES.put("RTE", "Runtime Error");
        USER_DISPLAY_CODES.put("CE", "Compile Error");
        USER_DISPLAY_CODES.put("IE", "Internal Error (judging server error)");
        USER_DISPLAY_CODES.put("QU", "Queued");
        USER_DISPLAY_CODES.put("P", "Processing");
        USER_DISPLAY_CODES.put("G", "Grading");
        USER_DISPLAY_CODES.put("D", "Completed");
        USER_DISPLAY_CODES.put("AB", "Aborted");
    }

    static {
        SUBMISSION_RESULT.put("AC", "Accepted");
        SUBMISSION_RESULT.put("WA", "Wrong Answer");
        SUBMISSION_RESULT.put("TLE", "Time Limit Exceeded");
        SUBMISSION_RESULT.put("MLE", "Memory Limit Exceeded");
        SUBMISSION_RESULT.put("OLE", "Output Limit Exceeded");
        SUBMISSION_RESULT.put("IR", "Invalid Return");
        SUBMISSION_RESULT.put("RTE", "Runtime Error");
        SUBMISSION_RESULT.put("CE", "Compile Error");
        SUBMISSION_RESULT.put("IE", "Internal Error");
        SUBMISSION_RESULT.put("SC", "Short Circuited");
        SUBMISSION_RESULT.put("AB", "Aborted");
    }

    static {
        STATUS.put("QU", "Queued");
        STATUS.put("P", "Processing");
        STATUS.put("G", "Grading");
        STATUS.put("D", "Completed");
        STATUS.put("IE", "Internal Error");
        STATUS.put("CE", "Compile Error");
        STATUS.put("AB", "Aborted");
    }

    static {
        CHECKERS.put("STANDARD", "standard");
        CHECKERS.put("FLOATS", "floats");
        CHECKERS.put("FLOATS_ABS", "floatsabs");
        CHECKERS.put("FLOATS_REL", "floatsrel");
        CHECKERS.put("RSTRIPPED", "rstripped");
        CHECKERS.put("SORTED", "sorted");
        CHECKERS.put("IDENTICAL", "identical");
        CHECKERS.put("LINE_COUNT", "linecount");
    }


}
