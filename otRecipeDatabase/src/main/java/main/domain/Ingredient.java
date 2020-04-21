/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.domain;

import main.dao.IngredientDAO;

/**
 *
 * @author J
 */
public class Ingredient {
    
    private String name;

    public Ingredient(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public Integer save(IngredientDAO ingredientDAO) {
        Integer id = -1;
        
        ingredientDAO.create(this);
        id = ingredientDAO.getPrimaryKey(this.name);
        
        return id;
    }
    
}
