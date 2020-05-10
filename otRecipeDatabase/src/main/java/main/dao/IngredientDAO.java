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
import java.util.ArrayList;
import java.util.List;
import main.domain.Ingredient;

/**
 * This class is a Data Access Object offering an interface between the class "Ingredient" and the database table "Ingredient"
 */
public class IngredientDAO implements DAO<Ingredient, Integer> {
    
    /**
     * This method creates an entry in the database table "Ingredient", if the entry does not already exist.
     *
     * @param   ingredient   The Ingredient to be saved to the database.
     */
    @Override
    public void create(Ingredient ingredient) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            // Check if ingredient already exists in the database            
            if (getPrimaryKey(ingredient.getName()) < 0) {
                
                // If such an Ingredient does not exist in the database, it will be created
                PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO Ingredient (name) VALUES (?)");
                statement.setString(1, ingredient.getName());            
                statement.executeLargeUpdate();
            
                statement.close();
            }
            
            databaseConnection.close();   
            
        } catch (Exception e) {
            System.out.println("IngredientDAO.create() failed for (" + ingredient.getName() + "): " + e);
        }
    }

    @Override
    public Ingredient read(Integer key) {
        
        Ingredient ingredient = new Ingredient("");
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM Ingredient WHERE id = ?");
            statement.setInt(1, key);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ingredient.setId(resultSet.getInt("id"));
                ingredient.setName(resultSet.getString("name"));
            }
            
            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("IngredientDAO.read() failed: " + e);
        }
        
        return ingredient;
    }

    @Override
    public Ingredient update(Ingredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("DELETE FROM Ingredient WHERE id = ?");
            statement.setInt(1, key);
            statement.execute();
            
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("IngredientDAO.delete() failed: " + e);
        }
    }

    @Override
    public List<Ingredient> list() {
        
        List<Ingredient> ingredients = new ArrayList<>();
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM Ingredient");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Ingredient ingredient = new Ingredient(name);
                ingredient.setId(id);
                ingredients.add(ingredient);
            }
            
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("IngredientDAO.list() failed: " + e);
        }
        
        return ingredients;
    }
    
    public void ensureTableExists() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS Ingredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(128));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
            
            databaseConnection.close();            
            
        } catch (Exception e) {
        // For testing purposes:
            System.out.println("\nERROR in Main > ensureDatabaseConnection():\n" + e + "\n");
        }     
    }
    
    public void resetTable() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            databaseConnection.prepareStatement("DROP TABLE Ingredient IF EXISTS;").executeUpdate();
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS Ingredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(128));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
            
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("Could not reset Ingredient table: " + e);
        }        
    }
    
    /**
     * This method returns the primary key of an entry in the database table "Ingredient".
     *
     * @param   ingredientName   The name of the Ingredient of which the primary key is to be obtained.
     * 
     * @return the primary key for the Ingredient, or "-1" if no such ingredient has been entered into the database
     */    
    public Integer getPrimaryKey(String ingredientName) {
        
        int ingredientId = -1;
        
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