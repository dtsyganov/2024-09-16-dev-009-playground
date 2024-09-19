package org.example.calculator.expression;

import java.util.List;

public class DummyExpression implements Expression {

    public DummyExpression(String expression) {
    }

    @Override
    public String getContent() {
        throw new IllegalArgumentException("Expression is not supported");
    }

    @Override
    public List<String> getDelimiters() {
        throw new IllegalArgumentException("Expression is not supported");
    }
}
