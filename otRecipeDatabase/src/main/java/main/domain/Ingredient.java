/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

/**
 * This class contains the necessary variables and methods for handling an Ingredient
 */
public class Ingredient {
    
    private int id;
    private String name;

    public Ingredient(String name) {
        this.name = name;
        this.id = -2;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }   

    public void setId(int id) {
        this.id = id;
    }    

    public void setName(String name) {
        this.name = name;
    }
}
