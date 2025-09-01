package com.rubix.dti.ZooProject.view;

import com.rubix.dti.ZooProject.model.Animal;
import com.rubix.dti.ZooProject.service.AnimalService;
import com.rubix.dti.ZooProject.utils.validations.ArgValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


@Component
public class CommandLine {

    @Autowired
    private AnimalService animalService;

    private final Scanner scanner;
    private List<Animal> listAnimal;


    public CommandLine(AnimalService animalService) {
        this.animalService = animalService;
        this.scanner = new Scanner(System.in);
    }


    public void init() {
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

    private void registerAnimal() {

        System.out.println("== Register new animal == \n Insert a species: ");
        String species = ArgValidations.validateIfIsEmpty(scanner);

        System.out.println("Insert a animal name (optional): ");
        String name = scanner.nextLine();

        System.out.println("Insert date of birth (yyyy-MM-dd): ");
        Date birthDate = ArgValidations.validateBirth(scanner);

        System.out.println("Insert weight(Kg): ");
        Double weight = ArgValidations.validateWeight(scanner);

        Animal animalToSave = new Animal(name.isEmpty() ? null : name, birthDate , weight, species);
        animalService.createOrUpdateAnimal(animalToSave);
        System.out.println("Animal registered successfully");
    }

    private void listAnimals() {
        //TODO: listar em formato de tabela
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
        Long id = ArgValidations.validateId(scanner, listAnimal);
        animalService.removeAnimal(id);
        System.out.println("Animal with ID " + id + " has been removed.");
    }

    private void editAnimals() {
        listAnimals();
        if (listAnimal.isEmpty()) {
            System.out.println("No animals registered to update.");;
            return;
        }

        System.out.println("Insert the ID of the animal to update: ");
        Long id = ArgValidations.validateId(scanner, listAnimal);
        Animal animal = listAnimal.stream().filter(a -> a.getId().equals(id)).findFirst().get();
        setFieldsAndUpdate(id, animal);

    }

    private void setFieldsAndUpdate(Long id, Animal animal) {
        boolean exit;
        String option;
        do {
            exit = true;
            System.out.printf("O que deseja alterar no animal com ID %d?\n", id);
            showEditFields();
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    System.out.println("== Insert a species: ");
                    animal.setSpecies(ArgValidations.validateIfIsEmpty(scanner));
                    break;
                case "2":
                    System.out.println("== Insert a name: ");
                    String name = scanner.nextLine();
                    animal.setName(name.isEmpty() ? null : name);
                    break;
                case "3":
                    System.out.println("== Insert date of birth (yyyy-MM-dd): ");
                    animal.setDateOfBirth(ArgValidations.validateBirth(scanner));
                    break;
                case "4":
                    System.out.println("== Insert weight(Kg): ");
                    animal.setWeight(ArgValidations.validateWeight(scanner));
                    break;
                case "5":
                    return;
                default: {
                    System.out.println("Invalid option!");
                    exit = false;
                    break;
                }
            }
        } while (!exit);

        animalService.createOrUpdateAnimal(animal);
        System.out.println("Animal with ID " + id + " has been updated.");
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

    private static void showEditFields() {
        System.out.println("== Edit ==");
        System.out.println("1 - Species ");
        System.out.println("2 - Name ");
        System.out.println("3 - Date of Birth ");
        System.out.println("4 - Weight ");
        System.out.println("5 - Exit");
        System.out.print("Choose an option: ");
    }

}
