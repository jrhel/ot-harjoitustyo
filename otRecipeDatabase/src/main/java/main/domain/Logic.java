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
 * This class is the application logic. It handles operations for the GUI, and commands the applications DAO's and provides them with
 * the parameters they need to handle database operations for the application logic. 
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
     * @param ingredient The "Ingredient" which is to be saved to the database
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
     *
     * @param   name   The name of the recipe which is to be saved.
     * @param   ingredients   The list of "RecipeIngredients" for the recipe.
     * @param   description   The description of, or instructions for making, the recipe.
     * @param   source   The source of the recipe. 
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
    
    /**
     * This method takes the necessary steps to save the "RecipeIngredients" of a recipe for future use
     *
     * @param   recipe   The Recipe of the RecipeIngredients are to be saved.
     */   
    public void saveListOfRecipeIngredients(Recipe recipe) {
        for (RecipeIngredient recipeIngredient: recipe.getIngredients()) {
            recipeIngredient.setRecipeId(recipe.getId());
            int ingredientPrimaryKey = saveIngredient(recipeIngredient.getIngredient());
            this.recipeIngredientDAO.create(recipeIngredient);
        }
    }
    
    /**
     * This method takes the used objects, from the GUI, for getting input for a new recipe
     * and extracts the required information from them to be able to call the method saveRecipe().
     *
     * @param   nameField   A "NameField" containing the name of the recipe which is to be saved.
     * @param   ingredientList   A "Map"-object containing a 1) "TextFields" containing the names of th ingredients required for a recipe,
     *          2), another "TextFields" containing their corresponding amounts.
     * @param   descriptionArea   A "TextArea" containing the description of, or instructions for making, the recipe.
     * @param   sourceField   A "TextField" containing the source of the recipe. 
     */   
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
    
    /**
     * This method fetches the necessary information from the database to construct a "Recipe", and returns that recipe.
     *
     * @param   recipeName   The name of the Recipe to be fetched and returned.
     * 
     * @return the Recipe to be fetched
     */ 
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
    
    /**
     * This method fetches all the "Recipes" in the database, whose name contains a given string,
     * sorts the matching names in alphabetical order, and populates two List-objects containing strings with their names 
     * so that the first half of the names go in the first list and the other half in the other list.
     *
     * @param   input   The string that the recipe's names will be compared to.
     * @param   firstColumn   The first list to be populated.
     * @param   secondColumn    The second list to be populated.
     */   
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
    
    /**
     * This method fetches all the "Recipes" in the database.
     * 
     * @return all the Recipes in the database
     */ 
    public List<Recipe> listRecipies() {
        List<Recipe> recipies = recipeDAO.list();
        for (Recipe r: recipies) {
            Integer ingredientID = ingredientDAO.getPrimaryKey(r.getName());
            r.setIngredientId(ingredientID);
        }
        return recipies;
    } 
    
    /**
     * This method fetches all the "Ingredients" in the database.
     * 
     * @return all the Recipes in the database
     */ 
    public List<Ingredient> listIngredients() {
        return ingredientDAO.list();
    }
    
    /**
     * This method fetches the primary key form the database for a "Recipe" with a given name.
     * 
     * @param   recipeName   The name of the recipe for which the primary key is to be obtained.
     * 
     * @return all the primary key for the Recipe with the given name
     */ 
    public Integer getRecipePrimaryKey(String recipeName) {
        return recipeDAO.getPrimaryKey(recipeName);
    }
    
    public void ensureDatabaseConnection() {
        
        ingredientDAO.ensureTableExists();
        recipeDAO.ensureTableExists();
        recipeIngredientDAO.ensureTableExists();        
    }
    
    /**
     * This method takes the required steps to reset the applications entire database to its original state
     */ 
    public void resetDatabase() {
        
        recipeIngredientDAO.resetTable();
        recipeDAO.resetTable();
        ingredientDAO.resetTable();
    }
    
    /**
     * This method takes the required steps to delete a given recipe from the database 
     * 
     * @param   recipeName   The name of the recipe which is to be deleted.
     */ 
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
