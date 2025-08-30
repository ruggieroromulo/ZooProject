package com.rubix.dti.ZooProject.utils.validations;

public class ArgValidations {

    public static Integer validateAge(String arg){
        int age = 0;
        try {
            age = Integer.parseInt(arg);
            if (age < 0 || age > 150) {
                System.out.println("Incorrect age, please re-enter");
            }
        }catch (Exception e){
            System.out.println("Idade não é um número correto");

        }finally {
            return age;
        }
    }

//    public static Integer ValidateName(String arg){
//        String name;
//        try {
//            if (name.length() = 0 {
//                System.out.println("Incorrect name, please re-enter");
//            }
//        }catch (Exception e){
//            System.out.println("Idade não é um número correto");
//
//        }finally {
//            return name;
//        }
//    }

}
