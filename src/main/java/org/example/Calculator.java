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

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.print("Enter an expression (e.g., 10 + 5 * 2): ");
        String expression = scn.nextLine();

        double result = calc.calculate(expression);
        if (!Double.isNaN(result)) {
            System.out.println("Result: " + result);
        }

        scn.close();
    }
}

