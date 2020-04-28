/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import main.dao.IngredientDAO;
import main.dao.RecipeDAO;
import main.dao.RecipeIngredientDAO;
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
public class DatabaseTest {
    
    Logic logic;
    
    public DatabaseTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void databaseAutoIncrementsPrimaryKeyForRecipie() {
        logic.resetDatabase();
        
        List<RecipeIngredient> ingredientsBlueberryPie = new ArrayList<>();
        Ingredient blueberries = new Ingredient("Blueberries");        
        RecipeIngredient rIBlueberries = new RecipeIngredient(blueberries, "500g");  
        ingredientsBlueberryPie.add(rIBlueberries);
        Ingredient bPieDough = new Ingredient("Blueberry pie dough");
        RecipeIngredient rIbPieDough = new RecipeIngredient(bPieDough, "1");
        ingredientsBlueberryPie.add(rIbPieDough);
        logic.newRecipe("Blueberry pie", ingredientsBlueberryPie, "Make blueberry pie. Make lots of it!", "The Big Pie Book");
        
        List<RecipeIngredient> ingredientsStrawberryPie = new ArrayList<>();
        Ingredient strawberries = new Ingredient("Blueberries");        
        RecipeIngredient rIStrawberries = new RecipeIngredient(blueberries, "0,7kg");  
        ingredientsStrawberryPie.add(rIStrawberries);
        Ingredient sPieDough = new Ingredient("Strawberry pie dough");
        RecipeIngredient rIsPieDough = new RecipeIngredient(bPieDough, "450g");
        ingredientsBlueberryPie.add(rIbPieDough);
        logic.newRecipe("Strawberry pie", ingredientsStrawberryPie, "Grab a bowl, mix berreis, and make a dough. Then bake!", "The Big Pie Book 2");
        
        int integerOfID = logic.getRecipePrimaryKey("Strawberry pie");
        assertEquals(2, integerOfID);
        
    }
}
