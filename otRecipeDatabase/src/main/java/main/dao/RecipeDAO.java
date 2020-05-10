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
import main.domain.RecipeIngredient;

/**
 * This class is a Data Access Object offering an interface between the class "Recipe" and the database table "Recipe"
 */
public class RecipeDAO implements DAO<Recipe, Integer> {
    
    public RecipeDAO() {
        
    }    
    
    /**
     * This method creates an entry in the database table "Recipe".
     *
     * @param   recipe   The recipe to be entered into the database.
     */   
    @Override
    public void create(Recipe recipe) {
        
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
    
    /**
     * This method fetches and returns a specific "Recipe" from the database.
     *
     * @param   key   The primary key of the "Recipe" which is to be obtained.
     * 
     * @return a Recipe
     */
    public Recipe read(Integer key) {
        
        List<RecipeIngredient> ingredients = new ArrayList<>();
        Recipe recipe = new Recipe("", ingredients, "", "");
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM Recipe WHERE id = ?");
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                recipe.setId(resultSet.getInt("id"));
                recipe.setIngredientId(resultSet.getInt("ingredient_id"));
                recipe.setName(resultSet.getString("name"));
                recipe.setDescription(resultSet.getString("description"));
                recipe.setSource(resultSet.getString("source"));                
            }
            
            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeDAO.read() failed for primary key(" + key + "): " + e);
        }
        
        return recipe;
    }
    
    /**
     * This method deletes an entry in the database table "Recipe".
     *
     * @param   key   The primary key for the entry to be deleted from the database.
     */
    @Override
    public void delete(Integer key) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("DELETE FROM Recipe WHERE id = ?");
            statement.setInt(1, key);
            statement.execute();
            
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeDAO.delete() failed: " + e);
        }
        
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
            
            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeDAO.list() failed: " + e);
        }
        
        return recipies;
    }
    
    /**
     * This method makes sure that there is a table "Recipe" in the correct form for the application to use.
     */
    public void ensureTableExists() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            String createRecipeTable = "CREATE TABLE IF NOT EXISTS Recipe (id INTEGER AUTO_INCREMENT PRIMARY KEY, ingredient_id INTEGER, name VARCHAR(128), description CLOB, source VARCHAR(1024), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
            databaseConnection.prepareStatement(createRecipeTable).executeUpdate();   
            
            databaseConnection.close();            
            
        } catch (Exception e) {
        // For testing purposes:
            System.out.println("\nERROR in Main > ensureDatabaseConnection():\n" + e + "\n");
        }     
    }
    
    /**
     * This method resets the table "Recipe".
     */
    public void resetTable() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            databaseConnection.prepareStatement("DROP TABLE Recipe IF EXISTS;").executeUpdate();
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS Recipe (id INTEGER AUTO_INCREMENT PRIMARY KEY, ingredient_id INTEGER, name VARCHAR(128), description CLOB, source VARCHAR(1024), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
            
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("Could not reset Recipe table: " + e);
        }        
    }
    
    /**
     * This method returns the primary key of an entry in the database table "Recipe".
     *
     * @param   recipeName   The name of the Recipe of which the primary key is to be obtained.
     * 
     * @return the primary key for the Recipe, or "-1" if no such recipe has been entered into the database
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
            System.out.println("RecipeDAO.getPrimaryKey(" + recipeName + ") failed: " + e);
        }
        
        return recipeId;
    }
}
