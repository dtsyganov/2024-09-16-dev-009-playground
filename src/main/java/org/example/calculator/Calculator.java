package org.example.calculator;

import org.example.calculator.expression.Expression;
import org.example.calculator.expression.ExpressionFactory;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Calculator {
    public int add(String expressionString) {
        if (expressionString.isEmpty()) {
            return 0;
        }

        final Expression expression = ExpressionFactory.create(expressionString);


        int[] numbers = expression.numbers();
        validateNumbers(numbers);
        return Arrays.stream(numbers).sum();
    }

    private void validateNumbers(int[] numbers) {
        String negativeNumbersList = Arrays.stream(numbers)
            .filter(number -> number < 0)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(", "));

        if (!negativeNumbersList.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers are not allowed: " + negativeNumbersList);
        }
    }
}
