/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cp.edu.uniandes.csw.recipe.ejb;

import co.edu.uniandes.csw.recipe.entities.IngredienteEntity;
import co.edu.uniandes.csw.recipe.entities.RecipeEntity;
import co.edu.uniandes.csw.recipe.entities.persistence.RecipePersistence;
import co.edu.uniandes.csw.recipe.exceptions.BusinessLogicException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author mr.sosa
 */
@Stateless
public class RecipeLogic {
    @Inject 
    RecipePersistence persistence;
    
    public RecipeEntity createRecipe(RecipeEntity recipeEntity)throws BusinessLogicException{
        Date fecha = new Date();
        if(recipeEntity.getName() == null || recipeEntity.getName().equals("")){
            throw new BusinessLogicException("el nombre es inválido");
        }
        if((recipeEntity.getAnio()+ 10) < fecha.getYear()){
            throw new BusinessLogicException("el año es 10 años menor al actual");
        }
        if(recipeEntity.getTipo().equals("Sin gluten")){
            if(recipeEntity.getIngredientes() != null){
                List<IngredienteEntity> lista = recipeEntity.getIngredientes();
                for(int i = 0; i < lista.size(); i++){
                    IngredienteEntity ing = lista.get(i);
                    if(ing.isGluten()){
                        throw new BusinessLogicException("los ingredientes tienen gluten");
                    }
                }
            }
        }
        if(recipeEntity.getTipo().equals("Baja en grasa")){
            if(recipeEntity.getIngredientes() != null){
                List<IngredienteEntity> lista = recipeEntity.getIngredientes();
                int total = 0;
                for(int i = 0; i < lista.size(); i++){
                    total = total + lista.get(i).getCalorias();
                }
                if(total > 1200){
                    throw new BusinessLogicException("las calorias superan los 1200");
                }
            }
        }
        persistence.create(recipeEntity);
        return recipeEntity;
    }
}
