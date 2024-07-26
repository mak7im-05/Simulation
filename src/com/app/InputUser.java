package com.app;

import java.io.IOException;
import java.util.Scanner;

public class InputUser {
    private static final Scanner scanner = new Scanner(System.in);

    public static int inputActions() {
        System.out.println("Выбери действие:");
        System.out.println("1. Сделать одну итерацию");
        System.out.println("2. Запустить Симуляцию(Бесконечный цикл)");
        System.out.println("3. Сгенирировать новую карту");
        System.out.println("0. Выход из Симуялиции");
        System.out.println();
        int x;
        while (true) {
            //Checing for errors


            x = scanner.nextInt();
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
        Thread.sleep(2500);
        return current;
    }

    public static void setupBasicSettingForSimulation() {
        System.out.println("Введите длину поля");
        WorldMap.WIDTH = scanner.nextInt();

        System.out.println("Введите высоту поля");
        WorldMap.HEIGHT = scanner.nextInt();

        System.out.println("Введите кол-во травы");
        WorldMap.GRASS_CNT = scanner.nextInt();

        System.out.println("Введите кол-во камней");
        WorldMap.ROCK_CNT = scanner.nextInt();

        System.out.println("Введите кол-во деревьев");
        WorldMap.TREE_CNT = scanner.nextInt();

        System.out.println("Введите кол-во Хищников");
        WorldMap.PREDATOR_CNT = scanner.nextInt();

        System.out.println("Введите кол-во кроликов");
        WorldMap.HEVIRBORE_CNT = scanner.nextInt();
    }
}
