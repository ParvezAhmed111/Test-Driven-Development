package com.tdd.calculator;

import java.util.List;
import static com.tdd.constant.Constants.NEGATIVE_NUMS_NOT_ALLOWED;

public class StringCalculator {

    private int calledCount = 0;

    public int add(String numbers) {
        calledCount++;
        if (numbers.isEmpty()) {
            return 0;
        }
        if (numbers.startsWith("//")) {
            numbers = handleDifferentDelimiters(numbers);
        } else {
            numbers = numbers.replace("\n", ",");
        }
        List<String> numsList = List.of(numbers.split(","));
        StringBuilder negativeNums= new StringBuilder();

        int sum = 0;
        for (String num : numsList) {
            int number= Integer.parseInt(num);
            if (number<0) {
                if (!negativeNums.isEmpty()) negativeNums.append(",");
                negativeNums.append(num);
            }
            else if (number<=1000) sum += number;
        }
        if (!negativeNums.isEmpty()) {
            throw new IllegalArgumentException(NEGATIVE_NUMS_NOT_ALLOWED + negativeNums);
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

    public int getCalledCount() {
        return calledCount;
    }
}
