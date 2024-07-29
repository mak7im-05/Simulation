package com.app;

import java.io.IOException;
import java.util.Scanner;

public class InputUser {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputActions() {
        System.out.println("Выбери действие:");
        System.out.println("1. Сделать одну итерацию");
        System.out.println("2. Запустить Симуляцию (Бесконечный цикл)");
        System.out.println("3. Сгенерировать новую карту");
        System.out.println("0. Выход из Симуляции");
        System.out.println();

        int x;
        while (true) {
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
                if (x == 1 || x == 2 || x == 3 || x == 0) {
                    break;
                } else {
                    System.out.println("Введите цифры, обозначающие одно из действий:");
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите цифру.");
                scanner.next(); // Сброс неправильного ввода
            }

            System.out.println("1. Сделать одну итерацию");
            System.out.println("2. Запустить Симуляцию (Бесконечный цикл)");
            System.out.println("3. Сгенерировать новую карту");
            System.out.println("0. Выход из Симуляции");
            System.out.println();
        }

        return x;
    }

    public static int inputInSimulation(int current) throws IOException, InterruptedException {
        if (System.in.available() > 0) {
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                if (x >= 0 && x < 4) {
                    return x;
                }
            } else {
                System.out.println("Неправильный ввод. Введите целое число.");
                scanner.next();
            }
        }
        Thread.sleep(2000);
        return current;
    }
}
