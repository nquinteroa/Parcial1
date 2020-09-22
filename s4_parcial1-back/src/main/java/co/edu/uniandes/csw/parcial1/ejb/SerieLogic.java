/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.ejb;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import co.edu.uniandes.csw.parcial1.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.parcial1.persistence.SeriePersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class SerieLogic {
    
    private static final Logger LOGGER = Logger.getLogger(SerieLogic.class.getName());

    @Inject
    private SeriePersistence  persistence;
    
    
      public SerieEntity createSerie(SerieEntity serieEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO,"Inicia proceso de creacion de una serie");
        validateSerieCreate(serieEntity);
        SerieEntity newSerieEntity = persistence.create(serieEntity);
        LOGGER.log(Level.INFO, "Se termina el proceso de crear una serie");
        return newSerieEntity; 
    }
    public List<SerieEntity> getSeries() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los series");
        List<SerieEntity> lista = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los series");
        return lista;
    }
    
      public Boolean validateNombre(String nombre)
    {
      return !(nombre==null|| nombre.isEmpty());
    }
      
     public Boolean validateRating(Float rating)
     {
         return (rating>=0 && rating<=10);
     }
     
     public Boolean validateCategoria(String category,SerieEntity serieEntity)
     {
         Boolean valido = true;
         if("Infantil".equals(category))
         {
             for (CapituloEntity entityCapitulo : serieEntity.getCapitulos()) {
                  if(!entityCapitulo.getAptoParaMenores())
                  {
                      valido =false;
                  }
            }
         }
             return valido;
     }
    public void validateSerieCreate(SerieEntity serieEntity) throws BusinessLogicException
    {
        if(!validateRating(serieEntity.getRating())) throw new BusinessLogicException("El rating no esta entre 0 y 10");
        if(!validateNombre(serieEntity.getName())) throw new BusinessLogicException("El nombre no puede ser nulo ni vacio");
        if(!validateCategoria(serieEntity.getCategory(),serieEntity)) throw new BusinessLogicException("No se puede crear una serie cuya categoria es infantil si contiene capitulos no aptos para menores de 16 anios");
    }
}
