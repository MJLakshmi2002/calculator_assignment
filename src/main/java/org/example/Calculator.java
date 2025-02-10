package org.example;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    public double calculate(String expression) {
        try {
            return evaluate(expression);
        } catch (Exception e) {
            System.out.println("Invalid expression! Please check your input.");
            return Double.NaN;
        }
    }

    private double evaluate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.matches("-?\\d+(\\.\\d+)?")) { // Check if it's a number
                numbers.push(Double.parseDouble(token));
            } else if (token.equals("(")) {
                operators.push('(');
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop(); // Pop the opening parenthesis
                } else {
                    System.out.println("Mismatched parentheses!");
                    return Double.NaN;
                }
            } else if (isOperator(token.charAt(0))) { // Check if it's an operator
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
                }
                operators.push(token.charAt(0));
            } else {
                System.out.println("Invalid token: " + token);
                return Double.NaN;
            }
        }

        while (!operators.isEmpty()) {
            numbers.push(applyOperation(operators.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private boolean isOperator(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '%' || op == '^';
    }

    private int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            case '^' -> 3; // Exponentiation has the highest precedence
            default -> -1;
        };
    }

    private double applyOperation(char op, double b, double a) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> b == 0 ? Double.NaN : a / b;
            case '%' -> b == 0 ? Double.NaN : a % b;
            case '^' -> Math.pow(a, b);
            default -> Double.NaN;
        };
    }
}


