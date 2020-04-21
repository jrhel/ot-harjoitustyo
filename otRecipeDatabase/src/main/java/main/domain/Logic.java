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
    
    // Just for temporary testing:
    public static void testDatabase() {
        
        boolean testInUse = false;
        if (testInUse) {
        
            try (Connection conn = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {

                /*
                String createRecipeTable = "CREATE TABLE IF NOT EXISTS Recipe (id INTEGER, name VARCHAR(128), description CLOB, source VARCHAR(1024), PRIMARY KEY (id));";
                conn.prepareStatement(createRecipeTable).executeUpdate();

                String createIngredientTable = "CREATE TABLE IF NOT EXISTS Ingredient (id INTEGER, name VARCHAR(64), recipe_id INTEGER, PRIMARY KEY (id), FOREIGN KEY (recipe_id) REFERENCES Recipe(id));";            
                conn.prepareStatement(createIngredientTable).executeUpdate();

                String createRecipeIngredientTable = "CREATE TABLE IF NOT EXISTS RecipeIngredient (id INTEGER, recipe_id INTEGER, amount VARCHAR(64), ingredient_id INTEGER, PRIMARY KEY (id), FOREIGN KEY (recipe_id) REFERENCES Recipe(id), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
                conn.prepareStatement(createRecipeIngredientTable).executeUpdate();
                */

                PreparedStatement statement = conn.prepareStatement("INSERT INTO Recipe (name, description, source) VALUES (?, ?, ?)");
                // statement.setInt(1, 0); 
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
                // statement.setInt(1, 0); 
                statement.setString(1, "Mandelmassa");
                // statement.setInt(2, 0); 
                statement.executeLargeUpdate();

                statement = conn.prepareStatement("SELECT * FROM Ingredient");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String recipe_id = resultSet.getString("recipe_id");
                    String rivi = id + ", " + name + ", " + recipe_id ;
                    System.out.println(rivi);
                } 

                statement = conn.prepareStatement("INSERT INTO RecipeIngredient (amount) VALUES (?)");
                // statement.setInt(1, 0); 
                // statement.setInt(1, 0);
                statement.setString(1, "1 tsk");
                // statement.setInt(3, 0);
                statement.executeLargeUpdate();

                statement = conn.prepareStatement("SELECT * FROM RecipeIngredient");
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String id = resultSet.getString("id");
                    String recipe_id = resultSet.getString("recipe_id");
                    String description = resultSet.getString("amount");
                    String ingredient_id = resultSet.getString("ingredient_id");
                    String rivi = id + ", " + recipe_id + ", " + description + ", " + ingredient_id;
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
    public static void burnDatabase() {
        try (Connection conn = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
            conn.prepareStatement("DROP TABLE Recipe IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE Ingredient IF EXISTS;").executeUpdate();
            conn.prepareStatement("DROP TABLE RecipeIngredient IF EXISTS;").executeUpdate();
            
            conn.close();
            
            System.out.println("\nBLAZE OF GLORY!\n");
            
        } catch (Exception e) {
            System.out.println("\nThe fire went out. woomp woomp:\n" + e + "\n");
        }
    }
    
}
