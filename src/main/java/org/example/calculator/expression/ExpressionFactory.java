package org.example.calculator.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.example.calculator.StringUtils.getFirstLine;

public class ExpressionFactory {
    private static final Map<Predicate<String>, Function<String, Expression>> expressions = new LinkedHashMap<>();

    static {
        expressions.put(e -> !e.startsWith("//"), DefaultDelimiterExpression::new);
        expressions.put(e -> e.matches("//.\n.*"), OneSymbolDelimiterExpression::new);
        expressions.put(e -> getFirstLine(e).matches("^//\\[.*]$"), MultiDelimitersMultiSymbolsExpression::new);
        expressions.put(e -> true, DummyExpression::new);
    }

    public static Expression create(String expression) {
        return expressions.entrySet().stream()
            .filter(e -> e.getKey().test(expression))
            .findFirst()
            .map(Map.Entry::getValue)
            .map(supplier -> supplier.apply(expression))
            .orElseThrow();
    }

    private ExpressionFactory() {
    }
}
