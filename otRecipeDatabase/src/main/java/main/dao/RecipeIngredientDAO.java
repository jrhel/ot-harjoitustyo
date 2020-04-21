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
 *
 * @author J
 */
public class RecipeIngredientDAO implements DAO<RecipeIngredient, Integer>{

    @Override
    public void create(RecipeIngredient ingredient) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
        } catch (Exception e) {
            System.out.println("RecipeIgredientDAO.create() failed for (" + ingredient.getIngredientId() + "): " + e);
        }
    }

    @Override
    public RecipeIngredient read(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecipeIngredient update(RecipeIngredient object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<RecipeIngredient> list() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
