package org.example.calculator;

public class StringUtils {

    public static String getFirstLine(String input) {
        return input.split("\n", 2)[0];
    }

    public static String getLinesButFirst(String input) {
        return input.split("\n", 2)[1];
    }

    private StringUtils() {
    }
}
