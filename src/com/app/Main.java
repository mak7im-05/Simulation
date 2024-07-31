package com.app;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Привет! [З]апустить симуляцию или [н]ет?");
        while (true) {
            char choice = scanner.next().toUpperCase().charAt(0);
            if (choice == 'З') {
                startGameRound();
            } else if (choice == 'Н') {
                System.out.println("Симуляция не запустилась(");
                break;
            } else {
                System.out.println("Введите правильную букву:");
            }
        }


    }

    public static void startGameRound() {
        int width;
        int height;
        while (true) {
            System.out.println("Введите ширину симуляции");
            if (scanner.hasNextInt()) {
                width = scanner.nextInt();
                if(width > 0) {
                    break;
                } else {
                    System.out.println("Введите значение >0");
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите цифру.");
                scanner.next(); // Сброс неправильного ввода
            }
        }
        while (true) {
            System.out.println("Введите высоту симуляции");
            if (scanner.hasNextInt()) {
                height = scanner.nextInt();
                if(height > 0) {
                    break;
                } else {
                    System.out.println("Введите значение >0");
                }
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите цифру.");
                scanner.next(); // Сброс неправильного ввода
            }
        }
        Simulation matrix = new Simulation(width, height);
        matrix.start();
    }
}