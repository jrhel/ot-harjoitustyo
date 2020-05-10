/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import java.util.List;

/**
 * This class contains the necessary variables and methods for handling a Recipe
 */
public class Recipe {
    
    private int id;
    private int ingredientId;
    private String name;    
    private List<RecipeIngredient> ingredients;
    private String description;
    private String source;

    public Recipe(String name, List<RecipeIngredient> ingredients, String description, String source) {
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.source = source;
        this.id = -1;
        this.ingredientId = -1;
    }
    
    public String getName() {
        return name;
    }

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }    
       
    public String getDescription() {
        return description;
    }

    public String getSource() {
        return source;
    }   

    public int getIngredientId() {
        return ingredientId;
    }

    public int getId() {
        return id;
    }    
     
    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }     

    public void setId(int id) {
        this.id = id;
    }    

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}
