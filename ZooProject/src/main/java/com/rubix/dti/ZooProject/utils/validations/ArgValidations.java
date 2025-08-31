package com.rubix.dti.ZooProject.utils.validations;

import com.rubix.dti.ZooProject.exception.FutureDateException;

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
                formatter.setLenient(false);
                String dateStr = scanner.nextLine();

                date = formatter.parse(dateStr);

                if(date.after(new Date())){
                    throw new FutureDateException();
                }
                retry=false;
            }
            catch (FutureDateException ex) {
                System.out.println(ex.getMessage());
                retry=true;
            }
            catch (Exception e) {
                System.out.println("Data de nascimento não é uma data correta, por favor insira no formato yyyy-MM-dd");
                retry=true;
            }

        } while(retry);
        return date;
    }

    public static Double validateWeight(Scanner scanner){
        boolean retry;
        Double weight= null;
        do{
            try {
                String weightStr = scanner.nextLine();
                 weight = Double.parseDouble(weightStr);

                retry=false;
            }
            catch (Exception e) {
                System.out.println("Peso não é uma numero válido, por favor insira um número.");
                retry=true;
            }

        } while(retry);
        return weight;
    }

     public static String validateIfIsEmpty(Scanner scanner){
        boolean retry;
        String input= null;
        do{
            try {
                String inputStr = scanner.nextLine();
                if (inputStr == null || inputStr.trim().isEmpty()) {
                    throw new IllegalArgumentException();
                }
                input = inputStr;
                retry=false;
            }
            catch (Exception e) {
                System.out.println("Campo não pode ser vazio. Digite novamente: ");
                retry=true;
            }

        } while(retry);
        return input;
    }

}
