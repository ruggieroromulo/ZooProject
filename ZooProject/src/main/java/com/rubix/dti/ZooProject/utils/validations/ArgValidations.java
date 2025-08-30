package com.rubix.dti.ZooProject.utils.validations;

public class ArgValidations {

    public static Integer ValidateAge(String arg){
        int age = 0;
        try {
            age = Integer.parseInt(arg);
            if (age < 0 || age > 100) {
                System.out.println("Idade incorreta");
            }
        }catch (Exception e){
            System.out.println("Idade não é um número correto");

        }finally {
            return age;
        }
    }


}
