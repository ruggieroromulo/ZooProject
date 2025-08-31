package com.rubix.dti.ZooProject.utils;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CommandLineInteraction {

    @Autowired
    private AnimalService animalService;


    public CommandLineInteraction(AnimalService animalService) {
        this.animalService = animalService;
    }


    public void init() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
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

    private void registerAnimal() {

        Animal animalToSave = new Animal("Leo", new java.util.Date(), 5);


        Animal animalReturned = animalService.createAnimal(animalToSave);
        System.out.println("Animal registered: " + animalReturned.getName());
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





}
