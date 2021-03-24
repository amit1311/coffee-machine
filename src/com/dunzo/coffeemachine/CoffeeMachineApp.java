package com.dunzo.coffeemachine;

import java.util.Scanner;

public class CoffeeMachineApp {
    private static String FILE_PATH = "C:\\Users\\Ari\\Desktop\\coffee-machine\\src\\resources\\input2.json";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        CoffeeMachineAppImpl coffeeMachineApp = new CoffeeMachineAppImpl();
        try {
            //Processing N drinks at a time
            coffeeMachineApp.getOutput(FILE_PATH);

            //Check whether the user needs to refill or not
            System.out.println("\nEnter 'Y' to Refill Ingredients, press any other Button to Exit: ");
            String s = sc.next();
            if (s.equals("Y") || s.contentEquals("y")) {
                coffeeMachineApp.addIngredients();
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
