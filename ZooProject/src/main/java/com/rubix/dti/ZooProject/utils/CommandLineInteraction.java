package com.rubix.dti.ZooProject.utils;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class CommandLineInteraction {

    @Autowired
    private AnimalService animalService;


    public CommandLineInteraction(AnimalService animalService) {
        this.animalService = animalService;
    }


    public void init() throws ParseException {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            showMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1": registerAnimal(); break;
                case "2": listAnimals(); break;
                case "3": editAnimals(); break;
                case "4": removeAnimal(); break;
                case "6": exit = true; break;
                default: System.out.println("Invalid option!"); break;
            }
            System.out.println();
        }

        scanner.close();
        System.out.println("Aplicação finalizada.");
    }

    private void registerAnimal() throws ParseException {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("== Register new animal == \n Insert a name: ");
        String name = scanner.nextLine();

        System.out.println("Insert date of birth (yyyy-MM-dd): ");
        String dateStr = scanner.nextLine();
        java.util.Date date = formatter.parse(dateStr);

        System.out.println("Insert weight: ");
        String weightStr = scanner.nextLine();
        double weight = Double.parseDouble(weightStr);



        scanner.close();

        Animal animalToSave = new Animal(name, date , weight);


        Animal animalReturned = animalService.createAnimal(animalToSave);
        System.out.println("Animal registered: " + animalReturned.getName());
    }

    private static void removeAnimal() {

    }

    private static void editAnimals() {

    }

    private static void listAnimals() {

    }

    private static void showMenu() {
        System.out.println("== Zoo management ==");
        System.out.println("1 - Register new animal");
        System.out.println("2 - List all animals");
        System.out.println("3 - Edit animal");
        System.out.println("4 - Delete animal");
        System.out.println("5 - Exit");
        System.out.print("Choose an option: ");
    }





}
