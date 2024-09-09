package com.tdd.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    @Test
    public void testEmptyString() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(1, calculator.add("1"));
    }

    @Test
    public void testTwoNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    void testMultipleNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    void testNewLineBetweenNumbers() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    void testDifferentDelimiter() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    void testNegativeNumbers() {
        StringCalculator calculator = new StringCalculator();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> calculator.add("1,-2,3,-4"));
        assertEquals("negatives not allowed -2,-4", exception.getMessage());
    }

    @Test
    void testGetCalledCount() {
        StringCalculator calculator = new StringCalculator();
        calculator.add("1,2,3");
        calculator.add("2,4");
        assertEquals(2, calculator.getCalledCount());
    }

    @Test
    void testIgnoreNumbersBiggerThan1000() {
        StringCalculator calculator = new StringCalculator();
        assertEquals(2, calculator.add("2,1001,1003"));
        assertEquals(2, calculator.add("1001,2"));
        assertEquals(2000, calculator.add("1000,1000"));
    }
}

