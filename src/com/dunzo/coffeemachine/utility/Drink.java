package com.dunzo.coffeemachine.utility;

import com.dunzo.coffeemachine.indicator.Indicator;
import com.dunzo.coffeemachine.model.Beverages;
import com.dunzo.coffeemachine.model.Ingredients;

import java.util.ArrayList;
import java.util.HashMap;

public class Drink {
    public void getDrink(Beverages beverages, HashMap<String, Ingredients> totalItemsQuantity) {
        ArrayList<Ingredients> ingredients = beverages.getIngredients();

        //Makes Drinks for the user
        for (Ingredients ingredient : ingredients) {
            if (totalItemsQuantity.containsKey(ingredient.getName())) {
                if (ingredient.getQuantity() > totalItemsQuantity.get(ingredient
                        .getName()).getQuantity()) {
                    System.out.println(beverages.getName() + " cannot be prepared because "
                            + ingredient.getName() + " is not sufficient");
                    return;
                }
            } else {
                System.out.println(beverages.getName() + " cannot be prepared because "
                        + ingredient.getName() + " is not available");
                return;
            }
        }


        for (Ingredients ingredient : ingredients) {
            double val = totalItemsQuantity.get(ingredient.getName())
                    .getQuantity() - ingredient.getQuantity();
            totalItemsQuantity.get(ingredient.getName()).setQuantity(val);
        }
        System.out.println(beverages.getName() + " is prepared");

        Indicator indicator = new Indicator();
        indicator.checkIngredients(totalItemsQuantity);
    }
}
