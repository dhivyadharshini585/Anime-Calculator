package com.calculator.service;

import java.util.Stack;

public class CalculatorService {

    public double calculate(String expression) {

        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;

        while (i < expression.length()) {

            char ch = expression.charAt(i);

            // Ignore spaces
            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            }

            // Read numbers (including decimals)
            if (Character.isDigit(ch) || ch == '.') {

                StringBuilder number = new StringBuilder();

                while (i < expression.length()
                        && (Character.isDigit(expression.charAt(i))
                        || expression.charAt(i) == '.')) {

                    number.append(expression.charAt(i));
                    i++;
                }

                numbers.push(Double.parseDouble(number.toString()));
                continue;
            }

            // Operators
            if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {

                while (!operators.isEmpty()
                        && precedence(operators.peek()) >= precedence(ch)) {

                    if (numbers.size() < 2) {
                        throw new IllegalArgumentException("Invalid Expression");
                    }

                    double b = numbers.pop();
                    double a = numbers.pop();
                    char op = operators.pop();

                    numbers.push(applyOperation(a, b, op));
                }

                operators.push(ch);
                i++;
                continue;
            }

            // Invalid character
            throw new IllegalArgumentException("Invalid character: " + ch);
        }

        while (!operators.isEmpty()) {

            if (numbers.size() < 2) {
                throw new IllegalArgumentException("Invalid Expression");
            }

            double b = numbers.pop();
            double a = numbers.pop();
            char op = operators.pop();

            numbers.push(applyOperation(a, b, op));
        }

        if (numbers.size() != 1) {
            throw new IllegalArgumentException("Invalid Expression");
        }

        return numbers.pop();
    }

    private int precedence(char operator) {

        if (operator == '+' || operator == '-') {
            return 1;
        }

        if (operator == '*' || operator == '/') {
            return 2;
        }

        return 0;
    }

    private double applyOperation(double a, double b, char operator) {

        switch (operator) {

            case '+':
                return a + b;

            case '-':
                return a - b;

            case '*':
                return a * b;

            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return a / b;

            default:
                throw new IllegalArgumentException("Invalid operator");
        }
    }
}