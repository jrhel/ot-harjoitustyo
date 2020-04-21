/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.dao;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import main.domain.Recipe;

/**
 *
 * @author J
 */
public class RecipeDAO implements DAO<Recipe, Integer>{
    
    public RecipeDAO() {
        
    }    
    
    @Override
    public void create(Recipe recipe) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO Recipe (ingredient_id, name, description, source) VALUES (?, ?, ?)");
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
    public Recipe read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Recipe update(Recipe object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Recipe> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
