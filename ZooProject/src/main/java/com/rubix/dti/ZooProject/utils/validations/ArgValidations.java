package com.rubix.dti.ZooProject.utils.validations;

import com.rubix.dti.ZooProject.exception.AnimalIdNotFound;
import com.rubix.dti.ZooProject.exception.FutureDateException;
import com.rubix.dti.ZooProject.exception.IntervalNumberException;
import com.rubix.dti.ZooProject.model.Animal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ArgValidations {

    public static Date validateBirth(Scanner scanner) {
        boolean retry;
        Date date = null;
        do {
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                formatter.setLenient(false);
                String dateStr = scanner.nextLine();

                date = formatter.parse(dateStr);

                if (date.after(new Date())) {
                    throw new FutureDateException();
                }
                retry = false;
            } catch (FutureDateException ex) {
                System.out.println(ex.getMessage());
                retry = true;
            } catch (Exception e) {
                System.out.println("The date of birth is not correct. Please enter it in the following format: yyyy-MM-dd");
                retry = true;
            }

        } while (retry);
        return date;
    }

    public static Double validateWeight(Scanner scanner) {
        boolean retry;
        Double weight = null;
        do {
            try {
                String weightStr = scanner.nextLine();
                weight = Double.parseDouble(weightStr);

                if (weight <= 0 || weight > 6000) {
                    throw new IntervalNumberException();
                }


                retry = false;
            } catch (IntervalNumberException ex) {
                System.out.println(ex.getMessage());
                retry = true;
            } catch (Exception e) {
                System.out.println("Weight is not a valid number, please enter a number.");
                retry = true;
            }

        } while (retry);
        return weight;
    }

    public static String validateIfIsEmpty(Scanner scanner) {
        boolean retry;
        String input = null;
        do {
            try {
                String inputStr = scanner.nextLine();
                if (inputStr == null || inputStr.trim().isEmpty()) {
                    throw new IllegalArgumentException();
                }
                input = inputStr;
                retry = false;
            } catch (Exception e) {
                System.out.println("Field cannot be empty. Please re-enter: ");
                retry = true;
            }

        } while (retry);
        return input;
    }
    public static Long validateId(Scanner scanner, List<Animal> listAnimal) {
        boolean retry;
        Long input = null;
        do {
            try {
                String inputStr = scanner.nextLine();
                input = Long.parseLong(inputStr);

                Long finalInput = input;
                boolean idExists = listAnimal.stream().anyMatch(animal -> animal.getId().equals(finalInput));
                if (!idExists) {
                    throw new AnimalIdNotFound();
                }
                retry = false;
            }
            catch (AnimalIdNotFound ex) {
                System.out.println(ex.getMessage());
                retry = true;
            }
            catch (Exception e) {
                System.out.println("ID must be a number. Please re-enter: ");
                retry = true;
            }

        } while (retry);
        return input;
    }

    public static boolean validateIfExistAnimals(List<Animal> listAnimal) {
        if (listAnimal.isEmpty()) {
            System.out.println("No animals registered.");
            return false;
        }
        return true;
    }
}
