package org.example;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        Calculator calc = new Calculator();

        while(true) {
            System.out.print("Enter an expression (e.g., 10 + 5 * 2): ");
            String expression = scn.nextLine();

            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Exiting calculator. Goodbye!");
                break;
            }

            double result = calc.calculate(expression);
            if (!Double.isNaN(result)) {
                System.out.println("Result: " + result);
            }
        }
        scn.close();
    }

}