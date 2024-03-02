package com.example.codeE.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHelper {
    private static final Logger logger = LoggerFactory.getLogger(LoggerHelper.class);

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logDebug(String message) {
        logger.debug(message);
    }

    public static void logWarning(String message) {
        logger.warn(message);
    }
}