/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import main.dao.IngredientDAO;
import main.dao.RecipeDAO;
import main.dao.RecipeIngredientDAO;

/**
 *
 * @author J
 */
public class Logic {
    
    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private IngredientDAO ingredientDAO;

    public Logic() {
                
        this.ingredientDAO = new IngredientDAO();  
        this.recipeDAO = new RecipeDAO();        
        this.recipeIngredientDAO = new RecipeIngredientDAO();
    }
    
    /**
     * This method creates creates and saves an Ingredient with a given name to the database, 
     * updates its id with the primary key from the database, and returns this.
     * 
     * @param name The name of the Ingredient which is to be created
     * 
     * @return the primary key of the created Ingredient
     */
    public int saveIngredient(Ingredient ingredient) {
        
        int primaryKey = this.ingredientDAO.getPrimaryKey(ingredient.getName());
        if (primaryKey > 0) {
            ingredient.setId(primaryKey);
            return primaryKey;
        } else  {
            this.ingredientDAO.create(ingredient); 
            primaryKey = this.ingredientDAO.getPrimaryKey(ingredient.getName());
            ingredient.setId(primaryKey);
            return primaryKey;
        }
    }    
    
    /**
     * This method takes the necessary steps to save a recipe for future use
     */
    public void saveRecipe(String name, List<RecipeIngredient> ingredients, String description, String source) {   
        //  1.  Create a recipe
        Recipe recipe = new Recipe(name, ingredients, description, source);
        
        /*  2.  Make recipe an Ingredient, save the Ingredient to the database, & set the Ingredients primary key for the recipe
                (This is because every recipe is a potential ingredient for another recipe.)  */
        Ingredient recipeAsIngredient = new Ingredient(name);
        int ingredientPrimaryKey = saveIngredient(recipeAsIngredient);
        recipe.setIngredientId(ingredientPrimaryKey);
        
        //  3.  Save the recipe to the database, and update its id with the primary key from the database
        this.recipeDAO.create(recipe);
        int primaryKey = this.recipeDAO.getPrimaryKey(name);    
        recipe.setId(primaryKey);
        
        //  4.  Save the RecipeIngredients tot he database
        saveListOfRecipeIngredients(recipe);
    }
    
    public void saveListOfRecipeIngredients(Recipe recipe) {
        for (RecipeIngredient recipeIngredient: recipe.getIngredients()) {
            recipeIngredient.setRecipeId(recipe.getId());
            int ingredientPrimaryKey = saveIngredient(recipeIngredient.getIngredient());
            this.recipeIngredientDAO.create(recipeIngredient);
        }
    }
    
    public void handleGraphicRecipe(TextField nameField, Map<TextField, TextField> ingredientList, TextArea descriptionArea, TextField sourceField) {
        String name = nameField.getText();
        List<RecipeIngredient> ingredients = new ArrayList<>();
        for (TextField tf: ingredientList.keySet()) {
            String ingredient = tf.getText();
            if (!ingredient.equals("")) {
                String amount = ingredientList.get(tf).getText();
                Ingredient newIngredient = new Ingredient(ingredient);
                RecipeIngredient newRecipeIngredient = new RecipeIngredient(newIngredient, amount);
                ingredients.add(newRecipeIngredient);
            }            
        }
        String description = descriptionArea.getText();
        String source = sourceField.getText();
        saveRecipe(name, ingredients, description, source);
    }
    
    public Recipe getRecipe(String recipeName) {
        
        int primaryKey = recipeDAO.getPrimaryKey(recipeName);
        Recipe recipe = recipeDAO.read(primaryKey);   
        recipe.setIngredients(recipeIngredientDAO.getIngredientsForRecipe(primaryKey));
        for (RecipeIngredient ri: recipe.getIngredients()) {
            Integer ingredientId = recipeIngredientDAO.getIngredientId(ri.getId());
            ri.setIngredient(ingredientDAO.read(ingredientId));
        }
        return recipe;
    }
    
    public void populateRecipeNameColumns(String input, List<String> firstColumn, List<String> secondColumn) {
        List<String> recipeNames = new ArrayList();
        for (Recipe r: recipeDAO.list()) {
            if (r.getName().contains(input)) {
                recipeNames.add(r.getName());
            }
        }
        if (recipeNames.size() > 0) {
            Collections.sort(recipeNames);
            int half = recipeNames.size() / 2;
            for (int n = 0; n < half; n++) {
                firstColumn.add(recipeNames.get(n));
            }
            while (half < recipeNames.size()) {
                secondColumn.add(recipeNames.get(half));
                half++;
            }
        }
    }
    
    public List<Recipe> listRecipies() {
        List<Recipe> recipies = recipeDAO.list();
        for (Recipe r: recipies) {
            Integer ingredientID = ingredientDAO.getPrimaryKey(r.getName());
            r.setIngredientId(ingredientID);
        }
        return recipies;
    } 
    
    public List<Ingredient> listIngredients() {
        return ingredientDAO.list();
    }
    
    public Integer getRecipePrimaryKey(String recipeName) {
        return recipeDAO.getPrimaryKey(recipeName);
    }
    
    public void ensureDatabaseConnection() {
        
        ingredientDAO.ensureTableExists();
        recipeDAO.ensureTableExists();
        recipeIngredientDAO.ensureTableExists();        
    }
    
    // Current method helps in testing, but final build should contain possibility to delete database/start over. 
    // That user experience might be quite different than current implementation.
    public void resetDatabase() {
        
        recipeIngredientDAO.resetTable();
        recipeDAO.resetTable();
        ingredientDAO.resetTable();
    }
    
    public void deleteRecipe(String recipeName) {
        int primaryKey = recipeDAO.getPrimaryKey(recipeName);
        List<RecipeIngredient> ingredients = recipeIngredientDAO.getIngredientsForRecipe(primaryKey);   
        for (RecipeIngredient ri: ingredients) {
            recipeIngredientDAO.delete(ri.getId());
        }
        
        recipeDAO.delete(primaryKey);     
        
        int IngredientId = ingredientDAO.getPrimaryKey(recipeName);
        if (0 > recipeIngredientDAO.getPrimaryKey(IngredientId)) {
            ingredientDAO.delete(IngredientId);
        }        
    }
}
