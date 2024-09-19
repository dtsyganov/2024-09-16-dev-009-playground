package org.example.calculator.expression;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public interface Expression {
    String getContent();

    List<String> getDelimiters();

    default int[] numbers() {
        try (Scanner scanner = new Scanner(getContent())) {
            scanner.useDelimiter(Pattern.compile(prepareRegExp(getDelimiters())));
            return scanner.tokens()
                .mapToInt(Integer::valueOf)
                .filter(number -> number <= 1000)
                .toArray();
        } catch (NoSuchElementException exception) {
            throw new NumberFormatException();
        }
    }

    private static String prepareRegExp(List<String> d) {
        return d.stream()
            .map(t -> wrapWithBrackets(regExpEscape(t)))
            .collect(Collectors.joining("|"));
    }

    private static String wrapWithBrackets(String t) {
        return "(" + t + ")";
    }

    private static String regExpEscape(String t) {
        return t.replaceAll("([*\\[.?\\\\+|])", "\\\\$1");
    }
}
