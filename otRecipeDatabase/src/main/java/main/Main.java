/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Scanner;

import main.ui.TempTextUI;

/**
 *
 * @author J
 */
public class Main {
    
    public static void main(String[] args) throws Exception{
        
        Scanner kbInput = new Scanner(System.in);
        
        TempTextUI textUI = new TempTextUI(kbInput);
        textUI.start();
        
        
    }
    
}
