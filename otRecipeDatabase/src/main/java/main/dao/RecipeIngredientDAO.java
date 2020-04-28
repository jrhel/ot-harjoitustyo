/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import main.domain.Recipe;
import main.domain.RecipeIngredient;

/**
 * This class is a Data Access Object offering an interface between the class "RecipeIngredient" and the database table "RecipeIngredient"
 */
public class RecipeIngredientDAO implements DAO<RecipeIngredient, Integer>{
    
    /**
     * This method creates an entry in the database table "RecipeIngredient".
     *
     * @param   ingredient   The "RecipeIngredient" to be saved to the database.
     */
    @Override
    public void create(RecipeIngredient ingredient) {
        
        System.out.println("");
        System.out.println("SAVING RECIPEingredient TO DB");
        System.out.println("RecipeID = " + ingredient.getRecipeId());
        System.out.println("Name = " + ingredient.getIngredient().getName());
        System.out.println("Amount: " + ingredient.getAmount());
        System.out.println("IngredientID = " + ingredient.getIngredientId());
        System.out.println("");
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
        } catch (Exception e) {
            System.out.println("RecipeIgredientDAO.create() failed for (" + ingredient.getIngredientId() + "): " + e);
        }
    }

    @Override
    public RecipeIngredient read(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecipeIngredient update(RecipeIngredient object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecipeIngredient> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
