/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.dtos;

import co.edu.uniandes.csw.recipe.entities.IngredienteEntity;
import co.edu.uniandes.csw.recipe.entities.RecipeEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class RecipeDTO implements  Serializable{
    
    private Long id ; 
    
    private String name;
   
    private Integer anio;
    
    private String tipo;
    
    private String descripcion;

    private List<IngredienteDTO> ing;
    
    public RecipeDTO() {
    }
    
    public RecipeDTO(RecipeEntity recipeEntity)
    {
        if( recipeEntity != null)
        {
            this.id = recipeEntity.getId();
            this.name = recipeEntity.getName();
            this.anio = recipeEntity.getAnio();
            this.descripcion = recipeEntity.getDescripcion();
            this.tipo = recipeEntity.getTipo();
        }
        if(recipeEntity.getIngredientes() != null){
        ing = new ArrayList<>();
            for (IngredienteEntity entityReview : recipeEntity.getIngredientes()) {
                ing.add(new IngredienteDTO(entityReview));
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
     public RecipeEntity toEntity() {
        RecipeEntity recipeEntity = new RecipeEntity();
        recipeEntity.setAnio(this.anio);
        recipeEntity.setDescripcion(this.descripcion);
        recipeEntity.setName(this.name);
        recipeEntity.setTipo(this.tipo);
        recipeEntity.setId(this.id);
        if(recipeEntity.getIngredientes()!= null){
           
        }
        return recipeEntity;
    }

    public Long getId() {
        return id;
    }

    public List<IngredienteDTO> getIng() {
        return ing;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIng(List<IngredienteDTO> ing) {
        this.ing = ing;
    }

   
    
}
