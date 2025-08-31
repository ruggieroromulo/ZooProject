package com.rubix.dti.ZooProject.utils;

public class CommandLineInteraction {


    public static void init() {
        System.out.println("Aplicação console Spring Boot iniciada!");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter your name: ");
        String nome = scanner.nextLine();

        boolean exit = false;

        while (!exit) {
            showMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1": registerAnimal(); break;
                case "2": listAnimals(); break;
                case "3": updateAnimal(); break;
                case "4": removeAnimal(); break;
                case "6": exit = true; break;
                default: System.out.println("Invalid option!"); break;
            }
            System.out.println();
        }

        System.out.println("Aplicação finalizada.");
        scanner.close();
    }

    private static void removeAnimal() {

    }

    private static void updateAnimal() {

    }

    private static void listAnimals() {

    }

    private static void showMenu() {
        System.out.println("== Zoo management ==");
        System.out.println("1 - Register new animal");
        System.out.println("2 - List all animals");
        System.out.println("3 - Update animal");
        System.out.println("4 - Delete animal");
        System.out.println("6 - Exit");
        System.out.print("Choose an option: ");
    }

    private static void registerAnimal() {
    }



}
