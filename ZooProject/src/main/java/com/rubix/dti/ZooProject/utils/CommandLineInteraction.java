package com.rubix.dti.ZooProject.utils;

import com.rubix.dti.ZooProject.utils.validations.ArgValidations;

public class CommandLineInteraction {

    public static void init() {
        System.out.println("Aplicação console Spring Boot iniciada!");

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.println("Olá, " + nome + "!");
        System.out.print("Digite a idade do animal: ");
        Integer age = ArgValidations.ValidateAge(scanner.nextLine());
        System.out.println("seu idade é " + age + "!");
        scanner.close();
    }

}
