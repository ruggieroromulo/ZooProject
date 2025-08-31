package com.rubix.dti.ZooProject.utils.validations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class ArgValidations {

    public static Date validateBirth(Scanner scanner){
        boolean retry;
        Date date= null;
        do{
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateStr = scanner.nextLine();

                date = formatter.parse(dateStr);
                retry=false;
            }
            catch (Exception e) {
                System.out.println("Data de nascimento não é uma data correta, por favor insira no formato yyyy-MM-dd");
                retry=true;
            }

        } while(retry);
        return date;
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
