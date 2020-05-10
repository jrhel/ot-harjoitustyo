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
        logic.resetDatabase();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void recipeIngredientIsSavedToDatabase() {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        
        Ingredient ingredient = new Ingredient("Olives");
        ingredient.setId(10000);
        RecipeIngredient olives = new RecipeIngredient(ingredient, "50g");
        recipeIngredients.add(olives);
        
        String recipeName = "Olive plate";
        Recipe recipe = new Recipe(recipeName, recipeIngredients, "Put them on a plate", "-");        
        logic.saveListOfRecipeIngredients(recipe);       
        
        recipe = logic.getRecipe(recipeName);
        boolean saved = false;
        for (RecipeIngredient ri: recipe.getIngredients()) {
            if (ri.getIngredient().getName().equals("Olives")) {
                if (ri.getAmount().equals("50g")) {
                    saved = true;
                }
            }
        }
        
        assertEquals(true, saved);
    }
    
    @Test
    public void recipeIngredientCanBeDeletedFromDatabase() {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        
        Ingredient ingredient = new Ingredient("Olives");
        ingredient.setId(10000);
        RecipeIngredient olives = new RecipeIngredient(ingredient, "50g");
        recipeIngredients.add(olives);
        
        String recipeName = "Olive plate";
        Recipe recipe = new Recipe(recipeName, recipeIngredients, "Put them on a plate", "-");        
        logic.saveListOfRecipeIngredients(recipe);       
        
        Integer recipeId = logic.getRecipePrimaryKey(recipeName);
        
        logic.deleteRecipe(recipeName);
        
        List<RecipeIngredient> recipeIngresAfterDeletion = logic.getRecipe(recipeName).getIngredients();
        
        assertEquals(0, recipeIngresAfterDeletion.size());
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
        logic.saveRecipe("Blueberry pie", ingredientsBlueberryPie, "Make blueberry pie. Make lots of it!", "The Big Pie Book");
        
        List<RecipeIngredient> ingredientsStrawberryPie = new ArrayList<>();
        Ingredient strawberries = new Ingredient("Strawberries");        
        RecipeIngredient rIStrawberries = new RecipeIngredient(blueberries, "0,7kg");  
        ingredientsStrawberryPie.add(rIStrawberries);
        Ingredient sPieDough = new Ingredient("Strawberry pie dough");
        RecipeIngredient rIsPieDough = new RecipeIngredient(bPieDough, "450g");
        ingredientsBlueberryPie.add(rIbPieDough);
        logic.saveRecipe("Strawberry pie", ingredientsStrawberryPie, "Grab a bowl, mix berreis, and make a dough. Then bake!", "The Big Pie Book 2");
        
        int integerOfID = logic.getRecipePrimaryKey("Strawberry pie");
        assertEquals(2, integerOfID);        
    }
    
    @Test
    public void databaseCanbeResetted() {
        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        
        Ingredient strawberries = new Ingredient("Strawberries");
        RecipeIngredient riSb = new RecipeIngredient(strawberries, "100g");        
        recipeIngredients.add(riSb);
        
        Ingredient blueberries = new Ingredient("Blueberries");
        RecipeIngredient riBb = new RecipeIngredient(blueberries, "100g");
        recipeIngredients.add(riBb);        
        
        String recipeName = "Berry mix";
        logic.saveRecipe(recipeName, recipeIngredients, "Just mix it.", "n/a");
        logic.resetDatabase();
        
        int primaryKey = logic.getRecipePrimaryKey(recipeName);
        
        assertEquals(-1, primaryKey);  
    }
}
