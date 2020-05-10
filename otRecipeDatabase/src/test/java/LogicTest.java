/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.domain.Ingredient;
import main.domain.Logic;
import main.domain.Recipe;
import main.domain.RecipeIngredient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author J
 */
public class LogicTest {
    
    Logic logic;
    
    public LogicTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        logic = new Logic();
        logic.resetDatabase();
        
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("Olives");
        ingredient.setId(10000);
        RecipeIngredient olives = new RecipeIngredient(ingredient, "50g");
        recipeIngredients.add(olives);        
        String recipeName = "Olive plate";
        logic.saveRecipe(recipeName, recipeIngredients, "Put them on a plate", "-");
        
        List<RecipeIngredient> ingredientsBlueberryPie = new ArrayList<>();
        Ingredient blueberries = new Ingredient("Blueberries");        
        RecipeIngredient rIBlueberries = new RecipeIngredient(blueberries, "500g");  
        ingredientsBlueberryPie.add(rIBlueberries);
        Ingredient bPieDough = new Ingredient("Blueberry pie dough");
        RecipeIngredient rIbPieDough = new RecipeIngredient(bPieDough, "1");
        ingredientsBlueberryPie.add(rIbPieDough);
        logic.saveRecipe("Blueberry pie", ingredientsBlueberryPie, "Make blueberry pie. Make lots of it!", "The Big Pie Book");
        
        List<RecipeIngredient> ingredientsStrawberryPie = new ArrayList<>();
        Ingredient strawberries = new Ingredient("Strawberries");        
        RecipeIngredient rIStrawberries = new RecipeIngredient(blueberries, "0,7kg");  
        ingredientsStrawberryPie.add(rIStrawberries);
        Ingredient sPieDough = new Ingredient("Strawberry pie dough");
        RecipeIngredient rIsPieDough = new RecipeIngredient(bPieDough, "450g");
        ingredientsBlueberryPie.add(rIbPieDough);
        logic.saveRecipe("Strawberry pie", ingredientsStrawberryPie, "Grab a bowl, mix berreis, and make a dough. Then bake!", "The Big Pie Book 2");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void columnsGetPopulated() {
                
        String input = "";
        List<String> firstColumn = new ArrayList<>();
        List<String> secondColumn = new ArrayList<>();
        logic.populateRecipeNameColumns(input, firstColumn, secondColumn);
        
        boolean blueBoolean = false;
        boolean strawBoolean = false;
        boolean oliveBoolean = false;
        
        for (String sfc: firstColumn) {
            if (sfc.equals("Strawberry pie")) {
                strawBoolean = true;
            }
            if (sfc.equals("Blueberry pie")) {
                blueBoolean = true;
            }
            if (sfc.equals("Olive plate")) {
                oliveBoolean = true;
            }
        }
        
        for (String ssc: secondColumn) {
            if (ssc.equals("Strawberry pie")) {
                strawBoolean = true;
            }
            if (ssc.equals("Blueberry pie")) {
                blueBoolean = true;
            }
            if (ssc.equals("Olive plate")) {
                oliveBoolean = true;
            }
        }
        
        boolean success = false;
        if (blueBoolean) {
            if (strawBoolean) {
                if (oliveBoolean) {
                    success = true;
                }
            }
        }
        
        assertEquals(true, success); 
    }
    
    @Test
    public void columnsOnlyPopulateInput() {
        
        String input = "pie";
        List<String> firstColumn = new ArrayList<>();
        List<String> secondColumn = new ArrayList<>();
        logic.populateRecipeNameColumns(input, firstColumn, secondColumn);
        
        boolean blueBoolean = false;
        boolean strawBoolean = false;
        boolean oliveBoolean = true;
        
        for (String sfc: firstColumn) {
            if (sfc.equals("Strawberry pie")) {
                strawBoolean = true;
            }
            if (sfc.equals("Blueberry pie")) {
                blueBoolean = true;
            }
            if (sfc.equals("Olive plate")) {
                oliveBoolean = false;
            }
        }
        
        for (String ssc: secondColumn) {
            if (ssc.equals("Strawberry pie")) {
                strawBoolean = true;
            }
            if (ssc.equals("Blueberry pie")) {
                blueBoolean = true;
            }
            if (ssc.equals("Olive plate")) {
                oliveBoolean = false;
            }
        }
        
        boolean success = false;
        if (blueBoolean) {
            if (strawBoolean) {
                if (oliveBoolean) {
                    success = true;
                }
            }
        }
        
        assertEquals(true, success); 
    }
    
    @Test
    public void logicCanListRecipes(){
        
        int ingredientId = logic.getRecipePrimaryKey("Strawberry pie");
        boolean success = false;
        List<Recipe> recipies = logic.listRecipies();
        for (Recipe r: recipies) {
            if (r.getName().equals("Strawberry pie")) {
                success = true;
            }
        }
        
        assertEquals(true, success); 
    }
    
    
}
