package com.dunzo.coffeemachine.model;

import java.util.ArrayList;

public class Beverages {
    private String name;
    private ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();

    public Beverages(String name, ArrayList<Ingredients> ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
