/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.entities.persistence;

import co.edu.uniandes.csw.recipe.entities.RecipeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mr.sosa
 */
@Stateless
public class RecipePersistence {
    
    @PersistenceContext(unitName = "RecipePU")
    protected EntityManager em;
    
    public RecipeEntity create(RecipeEntity recipeEntity){
        em.persist(recipeEntity);
        return recipeEntity;
    }

}
