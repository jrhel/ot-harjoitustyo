/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

/**
 *
 * @author J
 */
public class RecipeIngredient {
    
    private int id;
    private int recipeId;
    private Ingredient ingredient;
    private String amount;

    public RecipeIngredient(Ingredient ingredient, String amount) {
        this.ingredient = ingredient;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }   
    
    public String getAmount() {
        return amount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }   

    public void setId(int id) {
        this.id = id;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}