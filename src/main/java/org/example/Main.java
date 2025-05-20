package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Введите арифметическое выражение (например, 1 + 2):");

            while (true) {
                System.out.println(calc(br.readLine()));
            }
        } catch (Exception e) {
            System.out.println("throws Exception");
        }
    }

    static String calc(String input) {
        String[] parts = input.split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода. Ожидается: 'число оператор число'.");
        }

        int a = parseNumber(parts[0]);
        String operator = parts[1];
        int b = parseNumber(parts[2]);

        return switch (operator) {
            case "+" -> String.valueOf(a + b);
            case "-" -> String.valueOf(a - b);
            case "*" -> String.valueOf(a * b);
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль.");
                }
                yield String.valueOf(a / b);
            }
            default -> throw new IllegalArgumentException("Неверный оператор. Ожидается: +, -, *, /.");
        };
    }

    static int parseNumber(String str) {
        try {
            int number = Integer.parseInt(str);
            if (number < 1 || number > 10) {
                throw new IllegalArgumentException("Число должно быть от 1 до 10 включительно.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Неверный ввод. Ожидается целое число.");
        }
    }
}
