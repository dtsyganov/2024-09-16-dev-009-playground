package org.example.calculator.expression;

import java.util.List;

import static org.example.calculator.StringUtils.getLinesButFirst;

public class OneSymbolDelimiterExpression implements Expression {
    private final String content;
    private final List<String> delimiters;

    public OneSymbolDelimiterExpression(String expression) {
        content = getLinesButFirst(expression);
        delimiters = List.of(String.valueOf(expression.charAt(2)));
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
