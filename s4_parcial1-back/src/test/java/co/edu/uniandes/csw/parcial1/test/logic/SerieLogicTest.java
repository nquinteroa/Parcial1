/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.test.logic;
import co.edu.uniandes.csw.parcial1.ejb.SerieLogic;
import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.SeriePersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class SerieLogicTest {
    @Inject
    SerieLogic serieLogic;
       @PersistenceContext
    private EntityManager em;
    @Inject
    UserTransaction utx;
    
       private List<SerieEntity> data = new ArrayList<>();
   
   
    
    @Deployment
      public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SerieEntity.class.getPackage())
                .addPackage(SeriePersistence.class.getPackage())
                .addPackage(SerieLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
        @Before
public void configTest() {
    try {
        utx.begin();
        em.joinTransaction();
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
}private void clearData() {
    em.createQuery("delete from SerieEntity").executeUpdate();
}
private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
    for (int i = 0; i < 3; i++) {
        SerieEntity entity = factory.manufacturePojo(SerieEntity.class);
        em.persist(entity);
        data.add(entity);
    }
}

 @Test
public void createSerieTest() throws BusinessLogicException {
    PodamFactory factory = new PodamFactoryImpl();
    SerieEntity newEntity = factory.manufacturePojo(SerieEntity.class);
    float numero = 10/2;
    
    newEntity.setName("One Piece");
    newEntity.setRating(numero);
    newEntity.setCategory("Shonen");
    newEntity.setReleaseYear(1997);
   
    SerieEntity result = serieLogic.createSerie(newEntity);
    Assert.assertNotNull(result);
    
    SerieEntity entity = em.find(SerieEntity.class, result.getId());
       
    Assert.assertEquals(result.getName(), entity.getName());
    Assert.assertEquals(result.getRating(), entity.getRating());
    Assert.assertEquals(result.getCategory(), entity.getCategory());
    Assert.assertEquals(result.getReleaseYear(), entity.getReleaseYear());
    Assert.assertEquals(result.getCapitulos(), entity.getCapitulos());
}
    @Test(expected = BusinessLogicException.class)
public void createSerieConCategoriaInvalido() throws BusinessLogicException {
    PodamFactory factory = new PodamFactoryImpl();
    SerieEntity newEntity = factory.manufacturePojo(SerieEntity.class);
    float numero = 10/2 ;
    
    newEntity.setName("One Piece");
    newEntity.setRating(numero);
    newEntity.setCategory("Infantil");
    newEntity.setReleaseYear(1997);
    CapituloEntity capitulo = factory.manufacturePojo(CapituloEntity.class);
    capitulo.setAptoParaMenores(false);
    List<CapituloEntity> capitulos = new ArrayList<>();
    capitulos.add(capitulo);
    
    newEntity.setCapitulos(capitulos);
    
    SerieEntity serieCreada = serieLogic.createSerie(newEntity);
} 
}
