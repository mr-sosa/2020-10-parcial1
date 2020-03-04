/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.entities;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author mr.sosa
 */
@Entity
public class RecipeEntity extends BaseEntity{

        
    private String name;
   
    private Integer anio;
    
    private String tipo;
    
    private String descripcion;
    
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

    @PodamExclude
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<IngredienteEntity> ingredientes;

    public List<IngredienteEntity> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<IngredienteEntity> ingredientes) {
        this.ingredientes = ingredientes;
    }
    
}


