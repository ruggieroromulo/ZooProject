    package com.rubix.dti.ZooProject.view;

    import com.rubix.dti.ZooProject.model.Animal;
    import com.rubix.dti.ZooProject.service.AnimalService;
    import com.rubix.dti.ZooProject.utils.validations.ArgValidations;
    import org.apache.logging.log4j.LogManager;
    import org.apache.logging.log4j.Logger;
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
                    case "3": findAnimalById(); break;
                    case "4": editAnimals(); break;
                    case "5": removeAnimal(); break;
                    case "6": exit = true; scanner.close(); break;
                    default: System.out.println("Invalid option!"); break;
                }
            }
        }

        private void registerAnimal() {

            System.out.println();
            System.out.println("== REGISTER NEW ANIMAL == \n Insert a species: ");
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
            listAnimal = animalService.listAnimals();

            if (!ArgValidations.validateIfExistAnimals(listAnimal)) {
                System.out.println("Use the ‘Register Animal’ option to add new animals.");
                return;
            }
            System.out.println();
            System.out.println("\n=== LIST OF ANIMALS ===");
            System.out.println("Total number of registered animals: " + listAnimal.size());
            System.out.println();

            // Formato de tabela
            System.out.printf("%-5s %-20s %-20s %-12s %-20s%n",
                    "ID", "SPECIES", "NAME", "BIRTH", "WEIGHT (KG)");
            System.out.println("─".repeat(75));

            listAnimal.forEach(animal -> {
                String name = animal.getName() != null ? animal.getName() : "No name";
                String birthDate = new SimpleDateFormat("dd/MM/yyyy").format(animal.getDateOfBirth());
                String weight = String.format("%.2f", animal.getWeight());

                System.out.printf("%-5d %-20s %-20s %-12s %-20s%n",
                        animal.getId(),
                        animal.getSpecies(),
                        name,
                        birthDate,
                        weight);
            });

            System.out.println("─".repeat(75));
        }

        private void findAnimalById() {
            listAnimal = animalService.listAnimals();
            if (!ArgValidations.validateIfExistAnimals(listAnimal)) {
                return;
            }
            System.out.println();
            System.out.println("\n=== FIND BY ID ===");
            System.out.println("Insert the ID of the animal to find: ");
            Long id = ArgValidations.validateId(scanner, listAnimal);

            Animal animal = listAnimal.stream().filter(a -> a.getId().equals(id)).findFirst().get();
            String name = animal.getName() != null ? animal.getName() : "No name";
            String birthDate = new SimpleDateFormat("dd/MM/yyyy").format(animal.getDateOfBirth());
            String weight = String.format("%.2f", animal.getWeight());

            System.out.println("\n=== ANIMAL DETAILS ===");
            System.out.printf("ID: %d%n", animal.getId());
            System.out.printf("Species: %s%n", animal.getSpecies());
            System.out.printf("Name: %s%n", name);
            System.out.printf("Date of Birth: %s%n", birthDate);
            System.out.printf("Weight (Kg): %s%n", weight);
        }

        private void removeAnimal() {
            listAnimals();
            if (listAnimal.isEmpty()) {
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
                return;
            }
            System.out.println();
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
                System.out.printf("What do you want to change in the animal with ID %d?\n", id);
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
            System.out.println();//<br> to separate the menu from previous output
            System.out.println("== ZOO MANAGEMENT ==");
            System.out.println("1 - Register new animal");
            System.out.println("2 - List all animals");
            System.out.println("3 - Find animal by ID");
            System.out.println("4 - Edit animal");
            System.out.println("5 - Delete animal");
            System.out.println("6 - Exit");
            System.out.print("Choose an option: ");
        }

        private static void showEditFields() {
            System.out.println("== EDIT ==");
            System.out.println("1 - Species ");
            System.out.println("2 - Name (optional)");
            System.out.println("3 - Date of Birth ");
            System.out.println("4 - Weight ");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");
        }

    }
