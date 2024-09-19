package org.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void emptyStringShouldBe0() {
        int result = calculator.add("");

        assertEquals(0, result);
    }

    @Test
    void one0ShouldBeThe0() {
        int result = calculator.add("0");

        assertEquals(0, result);
    }

    @Test
    void one1ShouldBeThe1() {
        int result = calculator.add("1");

        assertEquals(1, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "2"})
    void oneNumberShouldBeNumber(String number) {
        int result = calculator.add(number);

        assertEquals(Integer.parseInt(number), result);
    }

    @Test
    void twoNumbersShouldBeSum() {
        int result = calculator.add("1,2");

        assertEquals(3, result);
    }

    @Test
    void threeNumbersShouldBeSum() {
        int result = calculator.add("0,1,2");

        assertEquals(3, result);
    }

    @Test
    void tenNumbersShouldBeSum() {
        int result = calculator.add("0,1,2,1,2,0,1,1,1,1");

        assertEquals(10, result);
    }

    @Test
    void twoNumbersWithNewLineShouldBeSum() {
        int result = calculator.add("1\n2");

        assertEquals(3, result);
    }

    @Test
    void threeNumbersWithNewLineShouldBeSum() {
        int result = calculator.add("0\n1,2");

        assertEquals(3, result);
    }

    @Test
    void incorrectInputEmptyNumberShouldThrow() {
        assertThrows(
            NumberFormatException.class,
            () -> calculator.add("1,\\n")
        );
    }

    @Test
    void incorrectInputEmptyNumberShouldThrow2() {
        assertThrows(
            NumberFormatException.class,
            () -> calculator.add("1,,2")
        );
    }

    @Test
    void useCustomDelimiter() {
        int result = calculator.add("//;\n1;2");

        assertEquals(3, result);
    }

    @Test
    void singleNegativeNumberAreNotAllowed() {
        var e = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("-2")
        );

        assertTrue(e.getMessage().contains("-2"));
    }

    @Test
    void negativeNumbersAreNotAllowed() {
        var e = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("1,-2,3,-4")
        );

        assertTrue(e.getMessage().contains("-4"));
        assertTrue(e.getMessage().contains("-2"));
    }

    @Test
    void numberGraterThan1000ShouldBeIgnored() {
        int result = calculator.add("1001,2");

        assertEquals(2, result);
    }

    @Test
    void number1000ShouldBeUsed() {
        int result = calculator.add("1000,2");

        assertEquals(1002, result);
    }

    @Test
    void customDelimiterOfAnyLength() {
        int result = calculator.add("//[***]\n1***2***3");

        assertEquals(6, result);
    }

    @Test
    void multipleCustomDelimiters() {
        int result = calculator.add("//[*][%]\n1*2%3");

        assertEquals(6, result);
    }

    @Test
    void incorrectExpression() {
        assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("//[*][%\n1*2%3")
        );
    }
}
