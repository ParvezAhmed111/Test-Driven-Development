package com.tdd.calculator;

import java.util.List;

public class StringCalculator {
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        numbers = numbers.replace("\n", ",");
        List<String> numsList = List.of(numbers.split(","));
        int sum = 0;
        for (String num : numsList) {
            sum += Integer.parseInt(num);
        }
        return sum;
    }

}
