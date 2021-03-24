package com.dunzo.coffeemachine.service;

import com.dunzo.coffeemachine.model.Ingredients;

import java.util.HashMap;
import java.util.Scanner;

public class Refill {
    public void addIngredients(HashMap<String, Ingredients> inventory) {
        //Refilling Ingredients
        String response;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Ingredient Name : ");
            String name = sc.next();
            System.out.print("Ingredient Quantity : ");
            double q = sc.nextDouble();
            System.out.println();
            if (q < 0) {
                System.out.println("Refill!!! Ingredient Quantity is Negative");
                return;
            }
            try {
                double val = inventory.get(name).getQuantity();
                inventory.get(name).setQuantity(val + q);
                System.out.println("Successfully Refilled " + name +
                        ", Net Quantity " + inventory.get(name).getQuantity());
            } catch (Exception e) {
                System.out.println(name + " does not exist in the inventory");
            }
            System.out.println("Do you want to Refill another item? Press 'Y' to continue or other key to exit");
            response = sc.next();
        } while (response.equals("Y") || response.contentEquals("y"));
        sc.close();
    }
}
