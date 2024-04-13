package com.example.codeE.util;

import com.example.codeE.constant.Constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constant.DATE_TIME_FORMAT_SECOND);

    public static String format(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }
    public static String formatToIso(LocalDateTime dateTime){
        return dateTime.format( DateTimeFormatter.ofPattern(Constant.DATE_TIME_ISO_FORMAT));
    }
}
