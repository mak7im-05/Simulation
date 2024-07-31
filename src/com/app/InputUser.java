package com.app;

import java.io.IOException;
import java.util.Scanner;

public final class InputUser {
    private static final Scanner scanner = new Scanner(System.in);

    private InputUser() {
    }

    public static int inputActions() {
        System.out.println("Выбери действие:");
        System.out.println("1. Сделать одну итерацию");
        System.out.println("2. Запустить Симуляцию (Бесконечный цикл)");
        System.out.println("3. Сгенерировать новую карту");
        System.out.println("0. Выход из Симуляции");
        System.out.println();

        int choice;
        while (true) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2 || choice == 3 || choice == 0) {
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

        return choice;
    }

    public static int inputInSimulation(int current) throws IOException, InterruptedException {
        if (System.in.available() > 0) {
            if (scanner.hasNextInt()) {
                int choise = scanner.nextInt();
                if (choise >= 0 && choise < 4) {
                    return choise;
                }
            } else {
                System.out.println("Неправильный ввод. Введите целое число.");
                scanner.next();
            }
        }
        return current;
    }
}
