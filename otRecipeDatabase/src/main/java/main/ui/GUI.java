/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ui;


import main.domain.Logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.domain.Recipe;
import main.domain.RecipeIngredient;

/**
 *
 * @author J
 */
public class GUI extends Application {
    
    private Logic logic;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        this.logic = new Logic();        
        this.logic.ensureDatabaseConnection();
        
        stage.setTitle("Your Digital Recipe Book");
        
        updateFrontPage(stage);
        
        stage.show();
    }
    
    public void newRecipeForm(Stage stage) {
        
        Stage recipeStage = new Stage();        
        BorderPane recipeForm = new BorderPane();
        
        HBox nameForm = new HBox();
        nameForm.setSpacing(5);
        Label nameInstruction = new Label("Please, enter the name of the recipe: ");
        nameForm.getChildren().add(nameInstruction);
        TextField nameField = new TextField();
        nameForm.getChildren().add(nameField);                
        nameForm.setAlignment(Pos.CENTER);
        
        VBox ingredientForm = new VBox();
        ingredientForm.setSpacing(5);
        Label ingredientInstruction = new Label("Please enter the required ingredients in the left column,\nand their respective amounts in the right column:");
        ingredientForm.getChildren().add(ingredientInstruction);
        Map<TextField, TextField> ingredientList = new HashMap<>();
        int ingredients = 20;
        while (ingredients > 0) {
            HBox recipeIngredient = new HBox();
            TextField newIngredient = new TextField();
            recipeIngredient.getChildren().add(newIngredient);
            Label comma = new Label(" ; ");
            recipeIngredient.getChildren().add(comma);
            TextField newAmount = new TextField();
            recipeIngredient.getChildren().add(newAmount);
            ingredientForm.getChildren().add(recipeIngredient);            
            ingredientList.put(newIngredient, newAmount);
            ingredients--;
        }
        
        VBox descriptionForm = new VBox();
        descriptionForm.setSpacing(5);
        descriptionForm.setAlignment(Pos.TOP_RIGHT);
        Label descriptionInstruction = new Label("Please type up the instructions for the recipe below:                                                                                                                                                                                                               ");
        descriptionForm.getChildren().add(descriptionInstruction);
        TextArea descriptionArea = new TextArea();
        descriptionArea.setMinHeight(590);
        descriptionArea.setMaxWidth(960);
        descriptionForm.getChildren().add(descriptionArea);
        
        BorderPane bottomPane = new BorderPane();
        HBox sourceForm = new HBox();
        sourceForm.setSpacing(5);
        Label sourceInstruction = new Label("     Enter a source for the recipe if you wish: ");
        sourceForm.getChildren().add(sourceInstruction);
        TextField sourceField = new TextField();
        sourceField.setMinWidth(400);
        sourceForm.getChildren().add(sourceField);
        bottomPane.setLeft(sourceForm);
        
        Button submitRecipeButton = new Button("Save recipe");
        submitRecipeButton.setOnAction((event) -> {
            if (checkRecipeExistance(nameField.getText())) {
                recipeExistsMessage(nameField.getText());
            } else {
                this.logic.handleGraphicRecipe(nameField, ingredientList, descriptionArea, sourceField);
                updateFrontPage(stage);
                ((Node)(event.getSource())).getScene().getWindow().hide();
            }
        });
        bottomPane.setRight(submitRecipeButton);
        descriptionForm.getChildren().add(bottomPane);
        
        recipeForm.setTop(nameForm);
        recipeForm.setLeft(ingredientForm);
        recipeForm.setCenter(descriptionForm);        
                
        Scene recipeScene = new Scene(recipeForm, 1280, 720);
        recipeStage.setScene(recipeScene);
        recipeStage.show();
        
    }
    
    public boolean checkRecipeExistance(String name) {
        boolean exists = false;
        if (this.logic.getRecipePrimaryKey(name) > 0) {
            exists = true;
        }
        return exists;
    }
    
    public void recipeExistsMessage(String name) {
        Stage messageStage = new Stage();
        Label messageLabel = new Label("A recipe already exists with the name \"" + name + "\". Please, change the name of your new recipe.");
        
        messageLabel.setAlignment(Pos.CENTER);
        Scene messageScene = new Scene(messageLabel, 640, 128);   
        messageStage.setScene(messageScene);
        messageStage.show();  
    }
    
    public void searchByName(String name, Stage frontStage, Stage resultStage) {        
        
        updateSearchByNamePage(name, frontStage, resultStage);
        
        resultStage.show();
    }
    
    public void searchByIngredients(List<TextField> ingredients) {
        for (TextField tf: ingredients) {
            System.out.println(tf.getText());
        }
    }
    
    public void showRecipe(String recipeName, Stage stage) {
        Stage recipeStage = new Stage();  
        BorderPane recipeFrame = new BorderPane();
        VBox recipeLayout = new VBox();    
        recipeLayout.setAlignment(Pos.TOP_CENTER);                
        
        Recipe recipe = this.logic.getRecipe(recipeName);
        
        Label name = new Label(recipeName);
        name.setMinHeight(32);
        Label description = new Label(recipe.getDescription());
        description.setAlignment(Pos.TOP_LEFT);
        description.setMinHeight(590);
        description.setMaxWidth(960);
        Label source = new Label("   Source: " + recipe.getSource());  
        
        VBox ingredientList = new VBox();
        ingredientList.setSpacing(5);
        for (RecipeIngredient ri: recipe.getIngredients()) {
            String ingredientName = ri.getIngredient().getName();
            if (0 < this.logic.getRecipePrimaryKey(ingredientName)) {
                Button openRecipeForIngredient = new Button(ingredientName);
                openRecipeForIngredient.setOnAction((event) -> {
                    showRecipe(ingredientName, stage);                    
                });
                Label amountLabel = new Label(", " + ri.getAmount());
                FlowPane ingredientWithButton = new FlowPane();
                ingredientWithButton.getChildren().addAll(openRecipeForIngredient, amountLabel);
                ingredientList.getChildren().add(ingredientWithButton);
            } else {
                Label ingredientLabel = new Label();
                String recipeIngredient = ingredientName + ", " + ri.getAmount();
                ingredientLabel.setText(recipeIngredient);
                ingredientList.getChildren().add(ingredientLabel);
            }
        }
        
        BorderPane ingredientsAndDescription = new BorderPane();
        ingredientsAndDescription.setLeft(ingredientList);
        ingredientsAndDescription.setCenter(description);
        
        recipeLayout.getChildren().add(name);
        recipeLayout.getChildren().add(ingredientsAndDescription);
        
        recipeFrame.setTop(recipeLayout);
        
        Button deleteRecipeButton = new Button("Delete recipe");
        deleteRecipeButton.setOnAction((event) -> {
            this.logic.deleteRecipe(recipeName);
            updateFrontPage(stage);
            // update seacrh stage ?
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });
        BorderPane bottom = new BorderPane();
        bottom.setLeft(source);
        bottom.setRight(deleteRecipeButton);
        recipeFrame.setBottom(bottom);
        
        Scene recipeScene = new Scene(recipeFrame, 1280, 720);
        recipeStage.setScene(recipeScene);
        recipeStage.show();
        
    }
    
    public void addMenu(HBox frontPage, Stage frontStage){
        
        VBox menu = new VBox();
        menu.setSpacing(5);
        
        FlowPane menuFirstLine = new FlowPane();
        Label please = new Label(" Please, ");
        Button newRecipeButton = new Button("add a new recipe");
        newRecipeButton.setOnAction((event) -> {
            newRecipeForm(frontStage);
        });        
        menuFirstLine.getChildren().addAll(please, newRecipeButton);
        menu.getChildren().add(menuFirstLine);
        
        Label menuSecondtLine = new Label(" or search recipies, either ;");
        menu.getChildren().add(menuSecondtLine);
        
        FlowPane menuFourthtLine = new FlowPane();
        Label indent = new Label("     ");
        TextField searchRecipe = new TextField();  
        Label comma = new Label(",");        
        menuFourthtLine.getChildren().addAll(indent, searchRecipe, comma);
        
        FlowPane menuThirdLine = new FlowPane();
        Label space = new Label(" - ");    
        Button searchByNameButton = new Button("by name :");
        searchByNameButton.setOnAction((event) -> {
            Stage resultStage = new Stage();
            searchByName(searchRecipe.getText(), frontStage, resultStage);
        });       
        menuThirdLine.getChildren().addAll(space, searchByNameButton);
        menu.getChildren().add(menuThirdLine);
        menu.getChildren().add(menuFourthtLine);
        
        List<TextField> ingredients = new ArrayList<>();
        FlowPane menuFifthtLine = new FlowPane();
        Label or = new Label(" - ");
        Button searchByIngredientsButton = new Button("by ingredients :");
        searchByIngredientsButton.setOnAction((event) -> {
            searchByIngredients(ingredients);
        });   
        menuFifthtLine.getChildren().addAll(or, searchByIngredientsButton);
        menu.getChildren().add(menuFifthtLine);
        
        int i = 10;
        while (i > 0) {
            FlowPane ingredientLine = new FlowPane();
            Label ingredientSpace = new Label("     ");
            TextField ingredientField = new TextField();
            Label ingredientComma = new Label(",");
            ingredientLine.getChildren().addAll(ingredientSpace, ingredientField, ingredientComma);
            ingredients.add(ingredientField);
            menu.getChildren().add(ingredientLine);
            i--;
        }   
        
        Button resetDatabase = new Button("Reset recipe book");
        resetDatabase.setOnAction((event) -> {
            resetDatabaseMessage();
        });  
        resetDatabase.setAlignment(Pos.BOTTOM_LEFT);
        menu.getChildren().add(resetDatabase);
        
        frontPage.getChildren().add(menu);
    }
    
    public void resetDatabaseMessage() {
        Stage messageStage = new Stage();
        VBox messageLayout = new VBox();
        Label messageLabel = new Label("This will delete every recipe that you have saved, and reset your recipe book to its original (i.e. empty) state. ");
        Label questionLabel = new Label("Are you sure you want to do this? ");
        Button confirm = new Button("Yes");
        confirm.setOnAction((event) -> {
            this.logic.resetDatabase();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });   
        Button cancel = new Button("No");
        cancel.setOnAction((event) -> {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        });  
        HBox bottomRow = new HBox();
        bottomRow.setSpacing(5);
        bottomRow.getChildren().addAll(questionLabel, confirm, cancel);
        bottomRow.setAlignment(Pos.CENTER);
        
        messageLayout.getChildren().addAll(messageLabel, bottomRow);
        messageLayout.setAlignment(Pos.CENTER);
        
        Scene messageScene = new Scene(messageLayout, 640, 128);   
        messageStage.setScene(messageScene);
        messageStage.show();  
    }
    
    public void addRecipeDirectory(HBox recipeDirectory, Stage stage) {
        List<String> firstColumn = new ArrayList<>();
        List<String> secondColumn = new ArrayList<>();
        this.logic.populateRecipeNameColumns("", firstColumn, secondColumn);
        recipeDirectory.getChildren().addAll(addRecipeColumn(firstColumn, stage), addRecipeColumn(secondColumn, stage));
        
    }
    
    public VBox addRecipeColumn(List<String> recipes, Stage stage) {
        VBox recipeColumn = new VBox();
        recipeColumn.setAlignment(Pos.TOP_LEFT);
        recipeColumn.setSpacing(5);
        recipeColumn.setMinWidth(256);
        for (String s: recipes) {
            Button openRecipeButton = new Button(s);
            openRecipeButton.setOnAction((event) -> {
                showRecipe(s, stage);
            });   
            recipeColumn.getChildren().add(openRecipeButton);
        }
        
        return recipeColumn;
    }
    
    public void updateFrontPage(Stage stage) {
        
        HBox frontPage = new HBox();
        frontPage.setSpacing(10);
        
        addMenu(frontPage, stage);
        
        VBox recipeDirectory = new VBox();
        Label pleaseClick = new Label("Click on a recipe in your recipe book to open it:");
        pleaseClick.setMinHeight(40);
        recipeDirectory.getChildren().add(pleaseClick);
        HBox recipeColumns = new HBox();
        addRecipeDirectory(recipeColumns, stage);
        recipeDirectory.getChildren().add(recipeColumns);
        frontPage.getChildren().add(recipeDirectory);
        
        
        Scene scene = new Scene(frontPage, 1280, 720);   
        stage.setScene(scene);
    }
    
    public void updateSearchByNamePage(String name, Stage frontStage, Stage resultStage) {
        
        VBox resultLayout = new VBox();
        resultLayout.setAlignment(Pos.TOP_LEFT);
        resultLayout.setSpacing(10);
        
        Label resultExplanation = new Label("Recipies in your recipe book containing \"" + name + "\":");
        resultLayout.getChildren().add(resultExplanation);
        
        HBox searchResults = new HBox();
        searchResults.setAlignment(Pos.TOP_LEFT);
        List<String> firstColumn = new ArrayList<>();
        List<String> secondColumn = new ArrayList<>();
        this.logic.populateRecipeNameColumns(name, firstColumn, secondColumn);
        searchResults.getChildren().addAll(addRecipeColumn(firstColumn, frontStage), addRecipeColumn(secondColumn, frontStage));
        
        resultLayout.getChildren().add(searchResults);
        Scene resultScene = new Scene(resultLayout, 1280, 720);
        resultStage.setScene(resultScene);
    }
}
