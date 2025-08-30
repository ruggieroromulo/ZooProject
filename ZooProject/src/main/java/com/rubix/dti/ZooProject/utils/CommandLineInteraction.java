package com.rubix.dti.ZooProject.utils;

import com.rubix.dti.ZooProject.utils.validations.ArgValidations;

import java.text.MessageFormat;

public class CommandLineInteraction {

    public static void init() {
        System.out.println("Aplicação console Spring Boot iniciada!");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Enter your name: ");


        String nome = scanner.nextLine();
        String mensage = MessageFormat.format("Hello, {0}! What do you want to do? \n 1-Register a new animal \n 2-Edit animal \n 3-List all registered animals \n 4-Delete animal ", nome);
        System.out.println(mensage);


//        System.out.print("Digite a idade do animal: ");
//        Integer age = ArgValidations.validateAge(scanner.nextLine());
//        System.out.println("seu idade é " + age + "!");
//        scanner.close();
    }


    public static void verifyThatTheUserWants(){

    }

}
