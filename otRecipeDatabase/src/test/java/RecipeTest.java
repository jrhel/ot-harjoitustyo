/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import main.domain.Ingredient;
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
public class RecipeTest {
    
    List<RecipeIngredient> ingredients;
    Ingredient blueberries;
    RecipeIngredient riBlueberries;    
    Recipe recipe;
    
    public RecipeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ingredients = new ArrayList<>();
        blueberries = new Ingredient("Blueberries");
        riBlueberries = new RecipeIngredient(blueberries, "500g");
        ingredients.add(riBlueberries);
        recipe = new Recipe("Blueberry pie", ingredients, "Make blueberry pie. Make lots of it!", "The Big Pie Book");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void newRecipeId() {
        int id = recipe.getIngredientId();
        assertEquals(-1, id);
    }
    
    @Test
    public void newRecipeName() {
        assertEquals("Blueberry pie", recipe.getName());
    }
    
    @Test
    public void newRecipeRecipeIngredients() {
        
    }
    
    @Test
    public void newRecipeDescription() {
        
    }
    
    @Test
    public void newRecipeSource() {
        
    }
}
