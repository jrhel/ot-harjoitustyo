/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import java.util.List;
import main.dao.IngredientDAO;
import main.dao.RecipeDAO;
import main.dao.RecipeIngredientDAO;

/**
 *
 * @author J
 */
public class Recipe {
    
    private Integer ingredientId;
    private String name;    
    private List<RecipeIngredient> ingredients;
    private String description;
    private String source;

    public Recipe(String name, List<RecipeIngredient> ingredients, String description, String source) {
        this.ingredientId = -1;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
        this.source = source;
    }

    public Integer getIngredientId() {
        return ingredientId;
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

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }
    
    
        
    // Take all necessary steps to save a recipe in accordance with how it may be used in the app
    public void save(IngredientDAO ingredientDAO, RecipeDAO recipeDAO, RecipeIngredientDAO recipeIngredientDAO) {
        
        // 1.   Save recipe as an Ingredient to the database & get primary key for saved Ingredient    
        Ingredient recipeAsIngredient = new Ingredient(this.name);
        ingredientId = recipeAsIngredient.save(ingredientDAO);
        
        // 2.   Save the recipe to the database
        recipeDAO.create(this);
        
        // 3.   Save all recipe specific "ResipeIngredients" to the database
        Integer recipeId = recipeDAO.getPrimaryKey(this.name);
        
        for (RecipeIngredient recipeIngredient: ingredients) {
            recipeIngredient.setRecipeId(recipeId);
            recipeIngredient.setIngredientId(ingredientId);
            recipeIngredient.save(recipeIngredientDAO);
        }
    }
    
}
