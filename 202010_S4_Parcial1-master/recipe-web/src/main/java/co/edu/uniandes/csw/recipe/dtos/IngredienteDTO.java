/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.dtos;

import co.edu.uniandes.csw.recipe.entities.IngredienteEntity;
import java.io.Serializable;

/**
 *
 * @author mr.sosa
 */
public class IngredienteDTO implements  Serializable{
    private Long id;
    private String name;
    private Integer proporcion;
    private Integer calorias;
    private boolean gluten;
    private RecipeDTO recipe;

    public IngredienteDTO(){
    }
    
    public IngredienteDTO(IngredienteEntity entity)
    {
        if( entity != null)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.calorias = entity.getCalorias();
            this.proporcion = entity.getProporcion();
            this.gluten = entity.isGluten();
            if(entity.getRecipe() != null){
                this.recipe = new RecipeDTO(entity.getRecipe());
            }else{
                this.recipe = null;
            }
            
        }
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Integer getProporcion() {
        return proporcion;
    }

    public Integer getCalorias() {
        return calorias;
    }

    public boolean isGluten() {
        return gluten;
    }

    public RecipeDTO getRecipe() {
        return recipe;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProporcion(Integer proporcion) {
        this.proporcion = proporcion;
    }

    public void setCalorias(Integer calorias) {
        this.calorias = calorias;
    }

    public void setGluten(boolean gluten) {
        this.gluten = gluten;
    }

    public void setRecipe(RecipeDTO recipe) {
        this.recipe = recipe;
    }
    
    public IngredienteEntity toEntity() {
        IngredienteEntity entity = new IngredienteEntity();
        entity.setProporcion(this.proporcion);
        entity.setCalorias(this.calorias);
        entity.setName(this.name);
        entity.setGluten(this.gluten);
        entity.setId(this.id);
        if(this.recipe != null){
            entity.setRecipe(this.recipe.toEntity());
        }
        return entity;
    }
}
