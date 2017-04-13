package com.isacademy.jjdd1.czterystrony;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainMenu {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainMenu.class);

    public static void main(String[] args) {
        System.out.println("Witaj w analizatorze funduszy inwestycyjnych. Wybierz co chcesz zrobić:");
        System.out.println("[1]. Rozpocznij");
        System.out.println("[0]. Wyjdź");
        Scanner answer = new Scanner(System.in);
        int start = answer.nextInt();
        LOGGER.info("Users choice: " + start);
        switch (start) {
            case 1:
                MenuOfFunds toMenuOfFunds = new MenuOfFunds();
                break;
            case 0:
                System.out.println("Miłego dnia!");
                break;
            default:
                System.out.println("Błędny wybór. Spróbuj ponownie.");
                new MainMenu();
        }
    }
}