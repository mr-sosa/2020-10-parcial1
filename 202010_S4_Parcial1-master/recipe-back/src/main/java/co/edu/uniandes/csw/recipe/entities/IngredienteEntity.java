/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mr.sosa
 */
@Entity
public class IngredienteEntity extends BaseEntity implements Serializable {
    
    private String name;
    private Integer proporcion;
    private Integer calorias;
    private boolean gluten;
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private RecipeEntity recipe;
    
    public IngredienteEntity(){
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

    public RecipeEntity getRecipe() {
        return recipe;
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

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }
    
    
}
