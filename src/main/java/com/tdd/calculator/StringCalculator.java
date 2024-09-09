package com.tdd.calculator;

import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.startsWith("//")) {
            numbers = handleDifferentDelimiters(numbers);
        } else {
            numbers = numbers.replace("\n", ",");
        }
        List<String> numsList = List.of(numbers.split(","));
        int sum = 0;
        for (String num : numsList) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

    private static String handleDifferentDelimiters(String numbers) {
        int index = numbers.indexOf('\n');
        String delimiter = numbers.substring(2, index);
        numbers = numbers.substring(index + 1);
        numbers = numbers.replace(delimiter, ",");
        return numbers;
    }

}
