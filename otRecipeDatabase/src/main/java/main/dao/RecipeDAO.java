/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import main.domain.Recipe;

/**
 * This class is a Data Access Object offering an interface between the class "Recipe" and the database table "Recipe"
 */
public class RecipeDAO implements DAO<Recipe, Integer>{
    
    public RecipeDAO() {
        
    }    
    
    /**
     * This method creates an entry in the database table "Recipe".
     *
     * @param   recipe   The recipe to be entered into the database.
     */   
    @Override
    public void create(Recipe recipe) {
        
        System.out.println("");
        System.out.println("SAVING RECIPE TO DB");
        System.out.println("IngredientID = " + recipe.getIngredientId());
        System.out.println("Name = " + recipe.getName());
        System.out.println("Dexcription = " + recipe.getDescription());
        System.out.println("Source = " + recipe.getSource());
        System.out.println("");
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO Recipe (ingredient_id, name, description, source) VALUES (?, ?, ?, ?)");
            statement.setInt(1, recipe.getIngredientId());
            statement.setString(2, recipe.getName());
            statement.setString(3, recipe.getDescription());
            statement.setString(4, recipe.getSource());
            statement.executeLargeUpdate();
            
            statement.close();
            databaseConnection.close();        
        
        } catch (Exception e) {
            System.out.println("RecipeDAO.create() failed for (" + recipe.getName() + "): " + e);
        }
        
    }

    @Override
    public Recipe read(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe update(Recipe object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * This method makes, and returns, a list of all the recipes in the database.
     *
     * @return a list of all the recipes in the database 
     */ 
    @Override
    public List<Recipe> list() {
        
        List<Recipe> recipies = new ArrayList<>();
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM Recipe");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Integer ingredientId = resultSet.getInt("ingredient_id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String source = resultSet.getString("source");
                
                Recipe recipe = new Recipe(name, null, description, source);
                recipies.add(recipe);
            }
            
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeDAO.list() failed: " + e);
        }
        
        return recipies;
    }
    
    /**
     * This method returns the primary key of an entry in the database table "Recipe".
     *
     * @param   recipeName   The name of the Recipe of which the primary key is to be obtained.
     * 
     * @return the primary key for the Recipe
     */
    public Integer getPrimaryKey(String recipeName) {
        
        Integer recipeId = -1;
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT id FROM Recipe WHERE name = ?");
            statement.setString(1, recipeName);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                recipeId = resultSet.getInt("id");
            }
            
            // Free up resources
            statement.close();
            resultSet.close();
            databaseConnection.close();        
        
        } catch (Exception e) {
            System.out.println("RecipeDAO.getPrimaryKey(" + recipeName +") failed: " + e);
        }
        
        return recipeId;
    }
}
