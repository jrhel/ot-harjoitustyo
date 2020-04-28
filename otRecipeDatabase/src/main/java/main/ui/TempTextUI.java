/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.domain.Ingredient;
import main.domain.Logic;
import main.domain.Recipe;
import main.domain.RecipeIngredient;

/**
 *
 * @author J
 */
public class TempTextUI {
    
    private Logic logic;
    private Scanner kbInput;
    

    public TempTextUI() {
        kbInput = new Scanner(System.in);
        logic = new Logic();
    }
    
    public void start() {
        
        String instructions = "\nTo add a new recipe, type \"add\",\n"
                            + "to open a recipe, type \"open\",\n"
                            + "to list all the recipies in the database, type \"list\",\n"
                            + "to edit a recipe, type \"edit\", \n"
                            + "to delete a recipe, type \"delete\",\n"
                            + "to quit, type \"quit\".\n"; 
        
        boolean userUndecided = true;
        while (userUndecided) {
            
            System.out.println(instructions);
            System.out.println("Please, type a command:");
            String userCommand = kbInput.nextLine();
            String command = userCommand.trim().toLowerCase();            
            
            if (command.equals("burn")) {
                this.logic.resetDatabase();
            } else if (command.equals("test")) {
                this.logic.testDatabase();
            } else if (command.equals("quit")) {
                userUndecided = false;
            } else if (command.equals("add")) {
                newRecipe();
            } else if (command.equals("list")){
                List<Recipe> recipies = this.logic.listRecipies();
                for(Recipe r: recipies) {
                    System.out.println("");
                    Integer ingredientID = r.getIngredientId();
                    String name = r.getName();
                    String description = r.getDescription();
                    String source = r.getSource();
                    String summary = "ingredientID: " + ingredientID + ", name: " + name + ", description: \n   " + description + "\nsource: " + source;
                    System.out.println(summary);
                }
            } else {
                System.out.println("Try again. ");
            }
        }
        
    }
    
    private void newRecipe() {
        System.out.println("\nPlease, enter the name of the recipe:");
        String recipeName = kbInput.nextLine();

        List<String> ingredients = new ArrayList<>();
        String ingredient = "";
        while (true) {
            String one = "\nPlease type an ingredient and an amout separated by a comma, e.g. ";
            String two = "\"Minced ginger, 1 tsp\"";
            String three = ", or just Enter to continue:";
            System.out.println(one+two+three);
            ingredient = kbInput.nextLine();
            if (ingredient.equals("")) {
                break;
            } else {
                ingredients.add(ingredient);
            }
        }

        List<RecipeIngredient> recipeIngredients = new ArrayList<>();
        for (String i: ingredients) {
            String[] parts = i.split(",");
            String ingredientName = parts[0];
            String ingredientAmount = parts[1];
            Ingredient newIngredient = new Ingredient(ingredientName);
            RecipeIngredient newRecipeIngredient = new RecipeIngredient(newIngredient, ingredientAmount);
            recipeIngredients.add(newRecipeIngredient);
        }

        System.out.println("Please enter the instructions for the recipe:");
        String description = kbInput.nextLine();

        System.out.println("Finally, enter a source if you wish:");
        String source = kbInput.nextLine();

        this.logic.newRecipe(recipeName, recipeIngredients, description, source);
    }
    
}