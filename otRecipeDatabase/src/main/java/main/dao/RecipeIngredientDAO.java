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
import main.domain.Recipe;
import main.domain.RecipeIngredient;

/**
 * This class is a Data Access Object offering an interface between the class "RecipeIngredient" and the database table "RecipeIngredient"
 */
public class RecipeIngredientDAO implements DAO<RecipeIngredient, Integer> {
    
    /**
     * This method creates an entry in the database table "RecipeIngredient".
     *
     * @param   ingredient   The "RecipeIngredient" to be saved to the database.
     */
    @Override
    public void create(RecipeIngredient ingredient) {
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            PreparedStatement statement = databaseConnection.prepareStatement("INSERT INTO RecipeIngredient (recipe_id, amount, ingredient_id) VALUES (?, ?, ?)");
            statement.setInt(1, ingredient.getRecipeId());
            statement.setString(2, ingredient.getAmount());
            statement.setInt(3, ingredient.getIngredient().getId());
            statement.executeLargeUpdate();
            
            statement.close();
            databaseConnection.close(); 
            
        } catch (Exception e) {
            System.out.println("RecipeIgredientDAO.create() failed for (" + ingredient.getIngredient().getName() + "): " + e);
        }
    }

    @Override
    public RecipeIngredient read(Integer key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RecipeIngredient update(RecipeIngredient ingredient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("DELETE FROM RecipeIngredient WHERE id = ?");
            statement.setInt(1, key);
            statement.execute();
            
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeDAO.delete() failed: " + e);
        }
    }

    @Override
    public List<RecipeIngredient> list() {
        List<RecipeIngredient> recipies = new ArrayList<>();
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM RecipeIngredient");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer recipe_id = resultSet.getInt("recipe_id");
                String amount = resultSet.getString("amount");
                Integer ingredient_id = resultSet.getInt("ingredient_id");
                
                RecipeIngredient recipe = new RecipeIngredient(null, amount);
                recipe.setId(id);
                recipe.setRecipeId(recipe_id);
                
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
    
    public void ensureTableExists() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            String createRecipeIngredientTable = "CREATE TABLE IF NOT EXISTS RecipeIngredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, recipe_id INTEGER, amount VARCHAR(64), ingredient_id INTEGER, FOREIGN KEY (recipe_id) REFERENCES Recipe(id), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
            databaseConnection.prepareStatement(createRecipeIngredientTable).executeUpdate(); 
            
            databaseConnection.close();            
            
        } catch (Exception e) {
        // For testing purposes:
            System.out.println("\nERROR in Main > ensureDatabaseConnection():\n" + e + "\n");
        }     
    }
    
    public void resetTable() {
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            databaseConnection.prepareStatement("DROP TABLE RecipeIngredient IF EXISTS;").executeUpdate();
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS RecipeIngredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, recipe_id INTEGER, amount VARCHAR(64), ingredient_id INTEGER, FOREIGN KEY (recipe_id) REFERENCES Recipe(id), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
            
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("Could not reset RecipeIngredient table: " + e);
        }        
    }
    
    public List<RecipeIngredient> getIngredientsForRecipe (Integer recipeKey) {
        
        List<RecipeIngredient> ingredients = new ArrayList<>();
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM RecipeIngredient WHERE recipe_id = ?");
            statement.setInt(1, recipeKey);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String amount = resultSet.getString("amount");
                RecipeIngredient ingredient = new RecipeIngredient(null, amount);
                ingredient.setRecipeId(recipeKey);
                ingredient.setId(id);
                ingredients.add(ingredient);
            }
            
            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeIngredientDAO.list() failed: " + e);
        }
        
        return ingredients;
    }
    
    public Integer getIngredientId(Integer primaryKey) {
        
        Integer ingredientId = -1;
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT ingredient_id FROM RecipeIngredient WHERE id = ?");
            statement.setInt(1, primaryKey);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                ingredientId = resultSet.getInt("ingredient_id");
            }    

            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("getIngredientId() failed: " + e);
        }
        
        return ingredientId;
    }

    @Override
    public String toString() {
        
        String s = "";
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT * FROM RecipeIngredient");
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int recipeId = resultSet.getInt("recipe_id");
                String amount = resultSet.getString("amount");
                int ingredientId = resultSet.getInt("ingredient_id");
                s = id + ", " + recipeId + ", " + amount + ", " + ingredientId + "\n";
            }    
            
            resultSet.close();
            statement.close();
            databaseConnection.close();
            
        } catch (Exception e) {
            System.out.println("RecipeIngredientDAO.toString() failed: " + e);
        }
        
        return s;
    }
    
    public Integer getPrimaryKey(Integer ingredientId) {
        
        Integer recipeId = -1;
        
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            
            PreparedStatement statement = databaseConnection.prepareStatement("SELECT id FROM RecipeIngredient WHERE ingredient_id = ?");
            statement.setInt(1, ingredientId);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                recipeId = resultSet.getInt("id");
            }
            
            // Free up resources
            statement.close();
            resultSet.close();
            databaseConnection.close();        
        
        } catch (Exception e) {
            System.out.println("Oho: " + e);
        }
        
        return recipeId;
    }
    
}
