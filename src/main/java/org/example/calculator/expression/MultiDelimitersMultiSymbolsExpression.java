package org.example.calculator.expression;

import java.util.List;
import java.util.regex.Pattern;

import static org.example.calculator.StringUtils.getFirstLine;
import static org.example.calculator.StringUtils.getLinesButFirst;

public class MultiDelimitersMultiSymbolsExpression implements Expression {
    private final String content;
    private final List<String> delimiters;

    public MultiDelimitersMultiSymbolsExpression(String expression) {
        delimiters = extractDelimiters(getFirstLine(expression).substring(2));
        content = getLinesButFirst(expression);
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<String> getDelimiters() {
        return delimiters;
    }

    private static List<String> extractDelimiters(String rawDelimiter) {
        return Pattern.compile("\\[([^\\[]*)]").matcher(rawDelimiter)
            .results()
            .map(m -> m.group(1))
            .toList();
    }
}
