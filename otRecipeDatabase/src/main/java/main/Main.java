/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

import main.ui.TempTextUI;

/**
 *
 * @author J
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
        
        // 1.   Ensure database is set up
        ensureDatabaseConnection();             
        
        // 2.   Instantiating other objects required for UI
        Scanner kbInput = new Scanner(System.in);
                
        /* 3.   Instantiate and launch UI
                    This is a temporary text based UI. 
                    The final version of the application is meant to be replaced by a GUI 
        */
        TempTextUI textUI = new TempTextUI(kbInput);
        textUI.start();
        
        
    }
    
    private static void ensureDatabaseConnection() {
        
        // A)   1.   Create connection to database
        try (Connection databaseConnection = DriverManager.getConnection("jdbc:h2:./recipeDatabase", "sa", "")) {
        
        //      2.   Make sure database has proper tables
            String createRecipeTable = "CREATE TABLE IF NOT EXISTS Recipe (id INTEGER, name VARCHAR(128), description CLOB, source VARCHAR(1024), PRIMARY KEY (id));";
            databaseConnection.prepareStatement(createRecipeTable).executeUpdate();
            
            String createIngredientTable = "CREATE TABLE IF NOT EXISTS Ingredient (id INTEGER, name VARCHAR(64), recipe_id INTEGER, PRIMARY KEY (id), FOREIGN KEY (recipe_id) REFERENCES Recipe(id));";            
            databaseConnection.prepareStatement(createIngredientTable).executeUpdate();
            
            String createRecipeIngredientTable = "CREATE TABLE IF NOT EXISTS RecipeIngredient (id INTEGER, recipe_id INTEGER, amount VARCHAR(64), ingredient_id INTEGER, PRIMARY KEY (id), FOREIGN KEY (recipe_id) REFERENCES Recipe(id), FOREIGN KEY (ingredient_id) REFERENCES Ingredient(id));";
            databaseConnection.prepareStatement(createRecipeIngredientTable).executeUpdate();
            
            
            databaseConnection.close();
            
        } catch (Exception e) {
        // For testing purposes:
            System.out.println("\nERROR in Main > ensureDatabaseConnection():\n" + e + "\n");
        }
        
    }
    
    
    
    
    
}
