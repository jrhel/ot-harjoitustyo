/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ui;

import java.util.Scanner;

/**
 *
 * @author J
 */
public class TempTextUI {
    
    private Scanner kbInput;

    public TempTextUI(Scanner kbInput) {
        this.kbInput = kbInput;
    }
    
    public void start() {
        
        String instructions = "To add a new recipe, type \"add\",\n"
                            + "to open a recipe, type \"open\",\n"
                            + "to list all the recipies in the database, type \"list\",\n"
                            + "to edit a recipe, type \"edit\", \n"
                            + "to delete a recipe, type \"delete\",\n"
                            + "to quit, type \"quit\".\n"; 
        
        boolean userUndecided = true;
        while (userUndecided) {
            
            System.out.println(instructions);
            String userCommand = kbInput.nextLine();
            String command = userCommand.trim().toLowerCase();
            
        }
        
    }
}