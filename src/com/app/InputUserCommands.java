package com.app;

import java.io.IOException;
import java.util.Scanner;

public class InputUserCommands {
    public static Scanner scanner = new Scanner(System.in);

    public static int input() {
        System.out.println("Выбери действие:");
        System.out.println("1. Сделать одну итерацию");
        System.out.println("2. Запустить Симуляцию(Бесконечный цикл)");
        System.out.println("3. Сгенирировать новую карту");
        System.out.println("0. Выход из Симуялиции");
        System.out.println();
        int x;
        while (true) {
            //Checing for errors
            while (true) {
                if (scanner.hasNextInt()) {
                    x = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Это не число! Введите число!");
                }
            }
            if (x == 1 || x == 2 || x == 3 || x == 0) break;

            System.out.println("Введите цифры обозначающее одно из действий:");
            System.out.println("1. Сделать одну итерацию");
            System.out.println("2. Запустить Симуляцию(Бесконечный цикл)");
            System.out.println("3. Сгенирировать новую карту");
            System.out.println("0. Выход из Симуялиции");
            System.out.println();
        }
        return x;
    }

    public static int inputInSimulation(int current) throws IOException, InterruptedException {
        if (System.in.available() > 0) {
            if (scanner.hasNextInt()) {
                int x = scanner.nextInt();
                if (x >= 0 && x < 4) return x;
            } else {
                System.out.println("Неправильный ввод. Введите целое число.");
                scanner.next(); // очистка неверного ввода
            }
        }
        Thread.sleep(1000);
        return current;
    }
}
