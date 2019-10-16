package com.curso.especprofandroid.util;

public class StringUtils {

    public static final String EMPTY = "";

    public static String trimEnd(String text) {
        return text.replaceFirst("\\s+$", "");
    }

    public static String trimStart(String value) {
        return value.replaceFirst("^\\s+", "");
    }

    public static Boolean isEmpty(String value) {
        return value == null || value.equals("");
    }
}
