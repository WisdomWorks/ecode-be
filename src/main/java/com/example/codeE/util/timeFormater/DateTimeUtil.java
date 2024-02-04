package com.example.codeE.util.timeFormater;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
}
