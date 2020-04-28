/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import main.dao.IngredientDAO;

/**
 * This class contains the necessary variables and methods for handling an Ingredient
 */
public class Ingredient {
    
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    /**
     * This method passes an Ingredient to a DAO to be saved to a database, and returns the primary key for the database entry.
     *
     * @param   ingredientDAO   The DAO which handles the required database operations.
     * 
     * @return the primary key for the created database entry
     */  
    public Integer save(IngredientDAO ingredientDAO) {
        Integer id = -1;
        
        ingredientDAO.create(this);
        id = ingredientDAO.getPrimaryKey(this.name);
        
        return id;
    }
    
}
