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
 *
 * @author J
 */
public class IngredientDAO implements DAO<Ingredient, Integer> {

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
    public Ingredient read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ingredient update(Ingredient object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ingredient> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
            System.out.println("IngredientDAO.getPrimaryKey(" + ingredientName +") failed: " + e);
        }
        
        return ingredientId;
    }

}