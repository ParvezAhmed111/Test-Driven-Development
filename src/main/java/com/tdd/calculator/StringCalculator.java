package com.tdd.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.tdd.constant.Constants.NEGATIVE_NUMS_NOT_ALLOWED;

public class StringCalculator {

    private int calledCount = 0;

    public int add(String numbers) {
        calledCount++;
        if (numbers.isEmpty()) return 0;
        if (numbers.startsWith("//")) {
            numbers = handleDifferentDelimiters(numbers);
        } else {
            numbers = numbers.replace("\n", ",");
        }
        List<String> numsList = List.of(numbers.split(","));
        StringBuilder negativeNums= new StringBuilder();

        int sum = 0;
        sum = calculateSum(numsList, negativeNums, sum);
        if (!negativeNums.isEmpty()) {
            throw new IllegalArgumentException(NEGATIVE_NUMS_NOT_ALLOWED + negativeNums);
        }
        return sum;
    }

    private static int calculateSum(List<String> numsList, StringBuilder negativeNums, int sum) {
        for (String num : numsList) {
            int number= Integer.parseInt(num);
            if (number<0) {
                if (!negativeNums.isEmpty()) negativeNums.append(",");
                negativeNums.append(num);
            }
            else if (number<=1000) sum += number;
        }
        return sum;
    }

    private static String handleDifferentDelimiters(String numbers) {
        int index = numbers.indexOf('\n');
        String delimiterPart = numbers.substring(2, index);
        numbers = numbers.substring(index + 1);

        // extracted all the unique delimiters and stored in list
        List<String> delimitersList = extractDelimiters(delimiterPart);
        for (String delimiter : delimitersList) {
            numbers = numbers.replace(delimiter, ",");
        }
        return numbers;
    }

    private static List<String> extractDelimiters(String delimiterPart) {
        List<String> delimiters = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        StringBuilder currentDelimiter = new StringBuilder();
        if(delimiterPart.startsWith("[") && delimiterPart.contains("]")) {
            // case 1: if delimiter is wrapped in brackets (i.e., [***])
            extractDelimitersWrappedInBrackets(delimiterPart, stack, delimiters, currentDelimiter);
        }
        else delimiters.add(delimiterPart); // case 2: if delimiter is not wrapped in brackets (i.e. //;\n)
        return delimiters;
    }

    private static void extractDelimitersWrappedInBrackets(String delimiterPart, Stack<Character> stack, List<String> delimiters, StringBuilder currentDelimiter) {
        for(char c : delimiterPart.toCharArray()) {
            if(c=='[') {
                stack.push(c);
            } else if (c==']' && !stack.isEmpty()) {
                    stack.pop();
                    delimiters.add(currentDelimiter.toString());
                    currentDelimiter.setLength(0);
            } else if(!stack.isEmpty()){
                    currentDelimiter.append(c);
            }
        }
    }

    public int getCalledCount() {
        return calledCount;
    }
}
