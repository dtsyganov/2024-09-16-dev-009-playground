package org.example.calculator.expression;

import java.util.List;

public class DefaultDelimiterExpression implements Expression {
    private final String content;
    private final List<String> delimiters = List.of(",", "\n");

    public DefaultDelimiterExpression(String expression) {
        content = expression;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public List<String> getDelimiters() {
        return delimiters;
    }
}
