package com.dunzo.coffeemachine.indicator;

import com.dunzo.coffeemachine.model.Ingredients;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Indicator {
    public void checkIngredients(HashMap<String, Ingredients> totalItem) {
        Iterator<Map.Entry<String, Ingredients>> it = totalItem.entrySet().iterator();

        //Will indicate which all ingredients are running low
        while (it.hasNext()) {
            HashMap.Entry<String, Ingredients> item = it.next();
            if (item.getValue().getQuantity() < 30) {
                System.out.println();
                System.out.println("Warning!!!" + item.getValue().getName() + " is running low!!!");
            }
        }
    }
}
