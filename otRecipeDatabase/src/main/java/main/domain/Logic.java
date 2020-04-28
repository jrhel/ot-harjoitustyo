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
import java.util.List;
import main.dao.IngredientDAO;
import main.dao.RecipeDAO;
import main.dao.RecipeIngredientDAO;

/**
 *
 * @author J
 */
public class Logic {
    
    private RecipeDAO recipeDAO;
    private RecipeIngredientDAO recipeIngredientDAO;
    private IngredientDAO ingredientDAO;

    public Logic() {
                
        ingredientDAO = new IngredientDAO();  
        recipeDAO = new RecipeDAO();        
        recipeIngredientDAO = new RecipeIngredientDAO();
    }
    
    public void newRecipe(String name, List<RecipeIngredient> ingredients, String description, String source) {        
        Recipe recipe = new Recipe(name, ingredients, description, source);
        recipe.save(ingredientDAO, recipeDAO, recipeIngredientDAO);
    }
    
    public List<Recipe> listRecipies() {
        List<Recipe> recipies = recipeDAO.list();
        for (Recipe r: recipies) {
            Integer ingredientID = ingredientDAO.getPrimaryKey(r.getName());
            r.setIngredientId(ingredientID);
        }
        return recipies;
    }
    
    // Just for temporary testing:
    public static void testDatabase() {
        
        boolean testInUse = false;
        if (testInUse) {
        
            try (Connection conn = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {

                PreparedStatement statement = conn.prepareStatement("INSERT INTO Recipe (name, description, source) VALUES (?, ?, ?)");
                statement.setString(1, "Blueberry pie");
                statement.setString(2, "Make blueberry pie! Make lots of it!");
                statement.setString(3, "The Big Blueberry Book");
                statement.executeLargeUpdate();

                statement = conn.prepareStatement("SELECT * FROM Recipe");
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String description = resultSet.getString("description");
                    String source = resultSet.getString("source");
                    String rivi = id + ", " + name + ", " + description + ", " + source;
                    System.out.println(rivi);
                } 


                statement = conn.prepareStatement("INSERT INTO Ingredient (name) VALUES (?)");
                statement.setString(1, "Mandelmassa");
                statement.executeLargeUpdate();

                statement = conn.prepareStatement("SELECT * FROM Ingredient");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String recipeID = resultSet.getString("recipe_id");
                    String rivi = id + ", " + name + ", " + recipeID ;
                    System.out.println(rivi);
                } 

                statement = conn.prepareStatement("INSERT INTO RecipeIngredient (amount) VALUES (?)");
                statement.setString(1, "1 tsk");
                statement.executeLargeUpdate();

                statement = conn.prepareStatement("SELECT * FROM RecipeIngredient");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String recipeID = resultSet.getString("recipe_id");
                    String description = resultSet.getString("amount");
                    String ingredientID = resultSet.getString("ingredient_id");
                    String rivi = id + ", " + recipeID + ", " + description + ", " + ingredientID;
                    System.out.println(rivi);
                }   

                conn.close();

            } catch (Exception e) {
                System.out.println("\nERROR in testDatabase():\n" + e + "\n"); 
            }
        }            
    }
    
    // Current method helps in testing, but final build should contain possibility to delete database/start over. 
    // That user experience might be quite different than current implementation.
    public static void resetDatabase() {
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            databaseConnection.prepareStatement("DROP TABLE Recipe IF EXISTS;").executeUpdate();
            databaseConnection.prepareStatement("DROP TABLE Ingredient IF EXISTS;").executeUpdate();
            databaseConnection.prepareStatement("DROP TABLE RecipeIngredient IF EXISTS;").executeUpdate();
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS Ingredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, name VARCHAR(128));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
               
            String createRecipeTable = "CREATE TABLE IF NOT EXISTS Recipe (id INTEGER AUTO_INCREMENT PRIMARY KEY, ingredient_id INTEGER, name VARCHAR(128), description CLOB, source VARCHAR(1024), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
            databaseConnection.prepareStatement(createRecipeTable).executeUpdate();           
            
            String createRecipeIngredientTable = "CREATE TABLE IF NOT EXISTS RecipeIngredient (id INTEGER AUTO_INCREMENT PRIMARY KEY, recipe_id INTEGER, amount VARCHAR(64), ingredient_id INTEGER, FOREIGN KEY (recipe_id) REFERENCES Recipe(id), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
            databaseConnection.prepareStatement(createRecipeIngredientTable).executeUpdate();
            
            databaseConnection.close();
            
            System.out.println("\nNew & empty database, yay!\n");
            
        } catch (Exception e) {
            System.out.println("\nNo new database for you. woomp woomp:\n" + e + "\n");
        }
    }
    
    public Integer getRecipePrimaryKey(String recipeName) {
        return recipeDAO.getPrimaryKey(recipeName);
    }
    
}
