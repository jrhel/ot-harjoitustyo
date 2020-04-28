/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import main.dao.RecipeIngredientDAO;

/**
 *
 * @author J
 */
public class RecipeIngredient {
    
    private Integer recipeId;
    private Ingredient ingredient;
    private String amount;
    private Integer ingredientId;

    public RecipeIngredient(Ingredient ingredient, String amount) {
        this.ingredient = ingredient;
        this.amount = amount;
        this.recipeId = -1;
        this.ingredientId = -1;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }   
    
    public String getAmount() {
        return amount;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }
    
    public void save(RecipeIngredientDAO recipeIngredientDAO) {                
        recipeIngredientDAO.create(this);        
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }    

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }
    
    
}
