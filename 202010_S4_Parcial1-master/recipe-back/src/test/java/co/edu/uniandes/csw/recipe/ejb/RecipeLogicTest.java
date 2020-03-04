/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.recipe.ejb;

import co.edu.uniandes.csw.recipe.entities.RecipeEntity;
import co.edu.uniandes.csw.recipe.entities.persistence.RecipePersistence;
import co.edu.uniandes.csw.recipe.exceptions.BusinessLogicException;
import cp.edu.uniandes.csw.recipe.ejb.RecipeLogic;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author mr.sosa
 */
@RunWith(Arquillian.class)
public class RecipeLogicTest {
    
    @Inject
    private RecipeLogic logic;
     @PersistenceContext
    private EntityManager em;
     
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UserTransaction utx;

    private List<RecipeEntity> data = new ArrayList<RecipeEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecipeEntity.class.getPackage())
                .addPackage(RecipePersistence.class.getPackage())
                .addPackage(RecipeLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from RecipeEntity").executeUpdate();
        em.createQuery("delete from IngredienteEntity").executeUpdate();
    }
    
    private void insertData(){
        for (int i = 0; i < 3; i++) {
            RecipeEntity entity = factory.manufacturePojo(RecipeEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createRecipeTest() throws BusinessLogicException{
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setAnio(2015);
        
        RecipeEntity result = logic.createRecipe(newEntity);
        Assert.assertNotNull(result);
        RecipeEntity entity = em.find(RecipeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
    @Test
    public void createRecipeTestConAnioDiferente() throws BusinessLogicException{
        RecipeEntity newEntity = factory.manufacturePojo(RecipeEntity.class);
        newEntity.setAnio(2000);
        
        RecipeEntity result = logic.createRecipe(newEntity);
        Assert.assertNotNull(result);
        RecipeEntity entity = em.find(RecipeEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getName(), entity.getName());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
}
