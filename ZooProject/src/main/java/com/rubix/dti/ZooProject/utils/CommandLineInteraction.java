package com.rubix.dti.ZooProject.utils;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.service.AnimalService;
import com.rubix.dti.ZooProject.utils.validations.ArgValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Component
public class CommandLineInteraction {

    @Autowired
    private AnimalService animalService;

    private final Scanner scanner;
    private List<Animal> listAnimal;


    public CommandLineInteraction(AnimalService animalService) {
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }


    public void init() throws ParseException {

        boolean exit = false;

        while (!exit) {
            showMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1": registerAnimal(); break;
                case "2": listAnimals(); break;
                case "3": editAnimals(); break;
                case "4": removeAnimal(); break;
                case "5": exit = true; scanner.close(); break;
                default: System.out.println("Invalid option!"); break;
            }
        }

        System.out.println("Aplicação finalizada.");
    }

    private void registerAnimal() throws ParseException {

        System.out.println("== Register new animal == \n Insert a species: ");
        String species = ArgValidations.validateIfIsEmpty(scanner);

        System.out.println("Insert a animal name (opitional): ");
        String name = scanner.nextLine();

        System.out.println("Insert date of birth (yyyy-MM-dd): ");
        Date birthDate = ArgValidations.validateBirth(scanner);

        System.out.println("Insert weight(Kg): ");
        Double weight = ArgValidations.validateWeight(scanner);

        Animal animalToSave = new Animal(name.isEmpty() ? null : name, birthDate , weight, species);
        Animal animalReturned = animalService.createAnimal(animalToSave);
        System.out.println("Animal registered: " + animalReturned.getName());
    }

    private void listAnimals() {
        listAnimal = animalService.listAnimals();
        listAnimal.forEach(animal -> {
                System.out.println("ID: " + animal.getId());
                System.out.println("Species: " + animal.getSpecies());
                System.out.println("Name: " + (animal.getName() != null ? animal.getName() : "No name"));
                System.out.println("Date of Birth: " + new SimpleDateFormat("yyyy-MM-dd").format(animal.getDateOfBirth()));
                System.out.println("Weight: " + animal.getWeight() + " Kg");
                System.out.println("-------------------------");
            });

    }

    private void removeAnimal() {
        listAnimals();
        if (listAnimal.isEmpty()) {
            System.out.println("No animals registered to delete.");;
            return;
        }

        System.out.println("Insert the ID of the animal to delete: ");
        Long id = ArgValidations.deleteIdValidation(scanner, listAnimal);
        animalService.removeAnimal(id);
        System.out.println("Animal with ID " + id + " has been removed.");
    }

    private static void editAnimals() {

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
