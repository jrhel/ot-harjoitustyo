/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import main.domain.Ingredient;

/**
 * This class is a Data Access Object offering an interface between the class "Ingredient" and the database table "Ingredient"
 */
public class IngredientDAO implements DAO<Ingredient, Integer> {
    
    /**
     * This method creates an entry in the database table "Ingredient".
     *
     * @param   ingredient   The Ingredient to be saved to the database.
     */
    @Override
    public void create(Ingredient ingredient) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO Ingredient (name) VALUES (?)");
            statement.setString(1, ingredient.getName());            
            statement.executeLargeUpdate();
            
            statement.close();
            databaseConnection.close();   
            
        } catch (Exception e) {
            System.out.println("IngredientDAO.create() failed for (" + ingredient.getName() + "): " + e);
        }
    }

    @Override
    public Ingredient read(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient update(Ingredient object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ingredient> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method returns the primary key of an entry in the database table "Ingredient".
     *
     * @param   ingredientName   The name of the Ingredient of which the primary key is to be obtained.
     * 
     * @return the primary key for the Ingredient
     */    
    public Integer getPrimaryKey(String ingredientName) {
        
        Integer ingredientId = -1;
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT id FROM Ingredient WHERE name = ?");
            statement.setString(1, ingredientName);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ingredientId = resultSet.getInt("id");
            }
            
            // Free up resources
            statement.close();
            resultSet.close();
            databaseConnection.close();        
        
        } catch (Exception e) {
            System.out.println("IngredientDAO.getPrimaryKey(" + ingredientName + ") failed: " + e);
        }
        
        return ingredientId;
    }

}