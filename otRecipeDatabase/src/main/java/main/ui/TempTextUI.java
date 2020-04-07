/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.ui;

import java.util.Scanner;
import main.domain.Logic;

/**
 *
 * @author J
 */
public class TempTextUI {
    
    private Logic logic;
    private Scanner kbInput;
    

    public TempTextUI(Scanner kbInput) {
        this.kbInput = kbInput;
        logic = new Logic();
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
            System.out.println("Please, type a command:");
            String userCommand = kbInput.nextLine();
            String command = userCommand.trim().toLowerCase();            
            
            if (command.equals("burn")) {
                this.logic.burnDatabase();
            } else if (command.equals("test")) {
                this.logic.testDatabase();
            } else if (command.equals("quit")) {
                userUndecided = false;
            } else {
                System.out.println("Try again. ");
            }
        }
        
    }
    
    
    
}