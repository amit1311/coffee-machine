package com.dunzo.coffeemachine;

import com.dunzo.coffeemachine.model.Beverages;
import com.dunzo.coffeemachine.model.Ingredients;
import com.dunzo.coffeemachine.service.Refill;
import com.dunzo.coffeemachine.utility.Drink;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CoffeeMachineAppImpl {

    HashMap<String, Ingredients> totalItemsQuantity;

    public void getOutput(String file) throws IOException, ParseException {

        Object obj = new JSONParser().parse(new FileReader(file));
        JSONObject jsonObject = (JSONObject) obj;

        JSONObject machine = (JSONObject) jsonObject.get("machine");
        JSONObject outlets = (JSONObject) machine.get("outlets");
        double outlet = (long) outlets.get("count_n");

        //Adding ingredients
        JSONObject Inventory = (JSONObject) machine.get("total_items_quantity");
        Iterator<Map.Entry> items = Inventory.entrySet().iterator();

        totalItemsQuantity = new HashMap<>();
        while (items.hasNext()) {
            Map.Entry pair = items.next();
            String name = (String) pair.getKey();
            double quantity = ((Number) pair.getValue()).doubleValue();
            Ingredients ing = new Ingredients(name, quantity);
            totalItemsQuantity.put(name, ing);
        }


        //Adding beverages
        JSONObject beverages = (JSONObject) machine.get("beverages");
        Iterator<Map.Entry> bitr = beverages.entrySet().iterator();
        ArrayList<Beverages> beverage = new ArrayList<Beverages>();
        while (bitr.hasNext()) {
            Map.Entry pair = bitr.next();
            String drink = (String) pair.getKey();
            ArrayList<Ingredients> ingts = new ArrayList<Ingredients>();
            JSONObject ingred = (JSONObject) beverages.get(drink);
            Iterator<Map.Entry> itr = ingred.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry pair1 = itr.next();
                String name = (String) pair1.getKey();
                double quantity = ((Number) pair1.getValue()).doubleValue();
                Ingredients ing = new Ingredients(name, quantity);
                ingts.add(ing);
            }
            Beverages b = new Beverages(drink, ingts);
            beverage.add(b);

            //If N beverages are parsed, we produce the drink
            if (beverage.size() == outlet) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int s = 0; s < outlet; ++s) {
                    Beverages b1 = beverage.get(s);
                    Drink drink1 = new Drink();
                    drink1.getDrink(b1, totalItemsQuantity);
                }
                beverage = new ArrayList<Beverages>();
            }
        }

        if (beverage.size() > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int s = 0; s < beverage.size(); ++s) {
                Beverages b1 = beverage.get(s);
                Drink drink = new Drink();
                drink.getDrink(b1, totalItemsQuantity);
            }
        }

    }

    //Calls the function to refill ingredients
    public void addIngredients() {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Ingredients> totalItemsQuantity = getIngredients();
        Refill add = new Refill();
        add.addIngredients(totalItemsQuantity);
        Iterator<Map.Entry<String, Ingredients>> it = totalItemsQuantity.entrySet().iterator();

    }

    //Returns the totalItemsQuantity
    public HashMap<String, Ingredients> getIngredients() {
        return totalItemsQuantity;
    }

}
