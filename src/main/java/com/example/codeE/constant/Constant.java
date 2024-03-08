package com.example.codeE.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String UPPER_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CHAR = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBER_CHAR = "0123456789";
    public static final String SPECIAL_CHAR = "!@#$%^&*()_+-=[]{}|;:,.<>?";

    //time format
    public static final String DATE_TIME_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss.SSSSSS";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //file path
    public static final String EXCEL_FILE_PATH = "./excel/";

    //file path in docker container
    public static final String DOCKER_CONTAINER_FILE_PATH = "app/student-submission/";

    public static final List<String> VALID_ROLES = Arrays.asList("student", "teacher", "admin", "");

}
