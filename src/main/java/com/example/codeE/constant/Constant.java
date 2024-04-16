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
    public static final String TESTCASE_TEMPLATE = "\n- {in: io.%s.in, out: io.%s.out, points: %f}";
    public static final List<String> VALID_ROLES = Arrays.asList("student", "teacher", "admin");
    //first param: name
    //second param: username
    //third param: password
    public static final String MAIL_TEMPLATE = """
            Hi, %s
                        
            This is your account to login CODEE system:
                User name: %s
                Password: %s
                        
            Thank a lot.
                        
            WisdomWorks.""";
    public static final String SEND_OTP_MAIL_TEMPLATE = """
            Hi, %s,
                        
            OTP to reset password: %s
            This OTP will be expired in 3 minute.                   
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

    public static final Map<String, String> LANGUAGE_TEMPLATE = new HashMap<>();

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

    static {
        LANGUAGE_TEMPLATE.put("PY2", "");
        LANGUAGE_TEMPLATE.put("CPP03", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("RUBY18", "");
        LANGUAGE_TEMPLATE.put("PHP", "");
        LANGUAGE_TEMPLATE.put("PERL", "");
        LANGUAGE_TEMPLATE.put("PY3", "");
        LANGUAGE_TEMPLATE.put("C", "#include <stdio.h>\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("PAS", "");
        LANGUAGE_TEMPLATE.put("CPP11", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("MONOCS", "");
        LANGUAGE_TEMPLATE.put("HASK", "");
        LANGUAGE_TEMPLATE.put("GO", "");
        LANGUAGE_TEMPLATE.put("PYPY", "");
        LANGUAGE_TEMPLATE.put("PYPY3", "");
        LANGUAGE_TEMPLATE.put("F95", "");
        LANGUAGE_TEMPLATE.put("NASM", "");
        LANGUAGE_TEMPLATE.put("RUBY", "");
        LANGUAGE_TEMPLATE.put("LUA", "");
        LANGUAGE_TEMPLATE.put("OCAML", "");
        LANGUAGE_TEMPLATE.put("TUR", "");
        LANGUAGE_TEMPLATE.put("JAVA8", "import java.io.*;\r\nimport java.util.*;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n\r\n    }\r\n}");
        LANGUAGE_TEMPLATE.put("V8JS", "/* \r\n * This is a custom version of V8 that adds six functions in order to perform I/O and aid in online judging.\r\n *\r\n * * `print(...)`: similar to Python's `print`, prints all argument separated by space followed by new line.\r\n * * `flush()`: flushes stdout, ensuring everything output by `print()` immediately shows up.\r\n * * `gets()`: similar to the Ruby equivalent, returns one line of input from `stdin`.\r\n * * `read(bytes)`: read `bytes` bytes from stdin as an `ArrayBuffer`.\r\n * * `write(buffer)`: write a typed array, `ArrayBuffer`, or a view of `ArrayBuffer` to stdout.\r\n * * `quit(code)`: exits the program with `code`.\r\n * * You can also assign to the global variable `autoflush` to control whether `print()` flushes.\r\n *\r\n */");
        LANGUAGE_TEMPLATE.put("D", "import std.stdio;\r\n\r\nvoid main() {\r\n\r\n}");
        LANGUAGE_TEMPLATE.put("BF", "");
        LANGUAGE_TEMPLATE.put("OBJC", "");
        LANGUAGE_TEMPLATE.put("CPP14", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("MONOVB", "");
        LANGUAGE_TEMPLATE.put("CLANG", "#include <stdio.h>\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("CLANGX", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("DART", "");
        LANGUAGE_TEMPLATE.put("TCL", "");
        LANGUAGE_TEMPLATE.put("CBL", "");
        LANGUAGE_TEMPLATE.put("MONOFS", "");
        LANGUAGE_TEMPLATE.put("SCM", "");
        LANGUAGE_TEMPLATE.put("ADA", "");
        LANGUAGE_TEMPLATE.put("AWK", "");
        LANGUAGE_TEMPLATE.put("RUST", "#[macro_use] extern crate dmoj;\r\n\r\nfn main() {\r\n    println!(\"Hello, World!\");\r\n}");
        LANGUAGE_TEMPLATE.put("COFFEE", "");
        LANGUAGE_TEMPLATE.put("PRO", "");
        LANGUAGE_TEMPLATE.put("FORTH", "");
        LANGUAGE_TEMPLATE.put("ICK", "");
        LANGUAGE_TEMPLATE.put("TEXT", "");
        LANGUAGE_TEMPLATE.put("SCALA", "// Must be the same name as the problem code\r\nobject problemcode extends App {\r\n    \r\n}");
        LANGUAGE_TEMPLATE.put("SWIFT", "");
        LANGUAGE_TEMPLATE.put("GAS32", "");
        LANGUAGE_TEMPLATE.put("GASARM", "");
        LANGUAGE_TEMPLATE.put("GAS64", "");
        LANGUAGE_TEMPLATE.put("SED", "");
        LANGUAGE_TEMPLATE.put("JAVA", "import java.io.*;\r\nimport java.util.*;\r\n\r\npublic class Main {\r\n    public static void main(String[] args) {\r\n\r\n    }\r\n}");
        LANGUAGE_TEMPLATE.put("NASM64", "");
        LANGUAGE_TEMPLATE.put("RKT", "#lang racket");
        LANGUAGE_TEMPLATE.put("GROOVY", "");
        LANGUAGE_TEMPLATE.put("KOTLIN", "");
        LANGUAGE_TEMPLATE.put("PIKE", "");
        LANGUAGE_TEMPLATE.put("CPP17", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("SBCL", "");
        LANGUAGE_TEMPLATE.put("C11", "#include <stdio.h>\r\n\r\nint main() {\r\n    return 0;\r\n}");
        LANGUAGE_TEMPLATE.put("ZIG", "");
        LANGUAGE_TEMPLATE.put("CPP20", "#include <bits/stdc++.h>\r\n\r\nusing namespace std;\r\n\r\nint main() {\r\n    return 0;\r\n}");
    }

    public static final String PROMPT_ESSAY_TEMPLATE = "You are roleplaying as an exceptional university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a particular assignment.\n" +
            "As a fair and unbiased grader, you will evaluate each submission based solely on the student's demonstrated factual knowledge and problem-solving skills, providing truthful explanations for your assessment. Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution.\n" +
            "In addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\n" +
            "The relevant assignment problem and the student's submitted solution will be provided in the following format:\n" +
            "<PROBLEM>\n" +
            "{Problem data}\n" +
            "</PROBLEM>\n" +
            "\n" +
            "<STUDENT-SUBMISSION>\n" +
            "{Student Submission}\n" +
            "</STUDENT-SUBMISSION>\n" +
            "After reviewing the problem and submission, output your evaluation using this JSON format, with no additional text besides the JSON:\n" +
            "\n" +
            "{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n" +
            "\n" +
            "Here is an example interaction using the specified format:\n" +
            "<PROBLEM>\n" +
            "What is 1 + 1?\n" +
            "</PROBLEM>\n" +
            "\n" +
            "<STUDENT-SUBMISSION>\n" +
            "</STUDENT-SUBMISSION>\n" +
            "output:\n" +
            "\n" +
            "{\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n" +
            "\n" +
            "\n" +
            "input: <PROBLEM>\n" +
            "What is 1 + 1?\n" +
            "</PROBLEM>\n" +
            "\n" +
            "<STUDENT-SUBMISSION>\n" +
            "</STUDENT-SUBMISSION>\n" +
            "output: {\"score\": 0, \"comment\": \"The student did not provide any work or attempt to answer the question, so a score of 0 is warranted. To receive credit, the student needed to supply a numeric answer to the simple addition problem presented.\"}\n" +
            "\n" +
            "input: <PROBLEM>\n" +
            "%s\n" +
            "</PROBLEM>\n" +
            "\n" +
            "<STUDENT-SUBMISSION>\n" +
            "%s\n" +
            "</STUDENT-SUBMISSION>\n" +
            "output: {";

    public static final String PROMPT_CODE_TEMPLATE =
            "You are roleplaying as an exceptional programming university professor teaching at FPT University, a renowned technology institution in Vietnam. It is currently the final examination period, and your task is to grade student submissions for a programming assignment.\n" +
                    "\n" +
                    "As a fair and unbiased grader, you will evaluate each submission based solely on the following:\n" +
                    "\n" +
                    "1. The correctness of the student's solution.\n" +
                    "2. The correctness of the student's test cases' inputs and outputs.\n" +
                    "3. The student's coding style and cleanliness.\n" +
                    "4. The student's demonstrated factual knowledge and problem-solving skills.\n" +
                    "\n" +
                    "Scores range from 0 to 10, with 0 indicating no attempt to solve the problem and 10 representing a perfect solution. In case there is an error message in the Judge message (i.e., Compiler error or Internal Error), the student score is always 0, with the comment being \"Run failed\".\n" +
                    "\n" +
                    "In addition to assigning a numeric score, please provide a clear, detailed comment explaining your reasoning behind the grade. The comment should offer a comprehensive rationale for the score, highlighting both strengths and weaknesses in the student's work as applicable.\n" +
                    "\n" +
                    "The relevant assignment problem, the student's submitted solution, judge message, and test cases will be provided in the following format. After reviewing the problem, submission, judge message, and test cases, output your evaluation using this JSON format with no additional text besides the JSON:\n" +
                    "\n" +
                    "<PROBLEM>\n" +
                    "{Problem description}\n" +
                    "</PROBLEM>\n" +
                    "\n" +
                    "<PROGRAMMING-LANGUAGE>\n" +
                    "{Submission Language}\n" +
                    "</PROGRAMMING-LANGUAGE>\n" +
                    "\n" +
                    "<STUDENT-SUBMISSION>\n" +
                    "{Student's submitted code}\n" +
                    "</STUDENT-SUBMISSION>\n" +
                    "\n" +
                    "<TEST-CASES>\n" +
                    "<CASE-1>\n" +
                    "<INPUT>\n" +
                    "{Test case 1 input}\n" +
                    "</INPUT>\n" +
                    "<STUDENT-OUTPUT>\n" +
                    "{Test case 1 student's output}\n" +
                    "</STUDENT-OUTPUT>\n" +
                    "<CASE-POINT>{Points for test case 1}</CASE-POINT>\n" +
                    "</CASE-1>\n" +
                    "\n" +
                    "<CASE-2>\n" +
                    "<INPUT>\n" +
                    "{Test case 2 input}\n" +
                    "</INPUT>\n" +
                    "<STUDENT-OUTPUT>\n" +
                    "{Test case 2 student's output}\n" +
                    "</STUDENT-OUTPUT>\n" +
                    "<CASE-POINT>{Points for test case 2}</CASE-POINT>\n" +
                    "</CASE-2>\n" +
                    "...\n" +
                    "</TEST-CASES>\n" +
                    "\n" +
                    "GRADE:{\"score\": [numeric score], \"comment\": \"[explanation for assigned score]\"}\n" +
                    "\n" +
                    "Please grade the following submission. Remember to strictly follow the format above.\n" +
                    "\n" +
                    "<PROBLEM>\n" +
                    "Write a Python program that prints a random number from 0 to 10.\n" +
                    "</PROBLEM>\n" +
                    "\n" +
                    "<PROGRAMMING-LANGUAGE>\n" +
                    "PY3\n" +
                    "</PROGRAMMING-LANGUAGE>\n" +
                    "\n" +
                    "<STUDENT-SUBMISSION>\n" +
                    "import random\n" +
                    "random_number = random.randint(0, 10)\n" +
                    "print(random_number)\n" +
                    "</STUDENT-SUBMISSION>\n" +
                    "\n" +
                    "<JUDGE-MESSAGE>\n" +
                    "</JUDGE-MESSAGE>\n" +
                    "\n" +
                    "<TEST-CASES>\n" +
                    "<CASE-1>\n" +
                    "<INPUT>\n" +
                    "</INPUT>\n" +
                    "<STUDENT-OUTPUT>\n" +
                    "3\n" +
                    "</STUDENT-OUTPUT>\n" +
                    "<CASE-POINT>5</CASE-POINT>\n" +
                    "</CASE-1>\n" +
                    "\n" +
                    "<CASE-2>\n" +
                    "<INPUT>\n" +
                    "</INPUT>\n" +
                    "<STUDENT-OUTPUT>\n" +
                    "7\n" +
                    "</STUDENT-OUTPUT>\n" +
                    "<CASE-POINT>5</CASE-POINT>\n" +
                    "</CASE-2>\n" +
                    "</TEST-CASES>\n" +
                    "\n" +
                    "GRADE:{\"score\": 10, \"comment\": \"The student's solution correctly generates and prints a random number between 0 and 10 using the 'random' module in Python. The code is clean and concise, demonstrating a good understanding of generating random numbers within a specified range.\"}\n" +
                    "\n" +
                    "Here is the real problem. Please grade the submission unbiasedly and coherently and output it as a pure JSON string without any markdown formatting.\n" +
                    "\n" +
                    "<PROBLEM>\n" +
                    "%s\n" +
                    "</PROBLEM>\n" +
                    "\n" +
                    "<PROGRAMMING-LANGUAGE>\n" +
                    "%s\n" +
                    "</PROGRAMMING-LANGUAGE>\n" +
                    "\n" +
                    " <STUDENT-SUBMISSION>\n" +
                    "%s\n" +
                    "</STUDENT-SUBMISSION> \n" +
                    "\n" +
                    "<TEST-CASES>\n" +
                    "%s\n" +
                    "</TEST-CASES>\n" +
                    "\n" +
                    "GRADE:{";
}
