/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.dtos;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;

/**
 *
 * @author Nicolas Quintero
 * 
 
 */
class CapituloDTO {
    String nombre;
    
    Integer duracion;
    
    String descripcion;
    
    Boolean aptoParaMenores;
    
    public CapituloDTO(){
        
    }
    
    public CapituloDTO(CapituloEntity capituloEntity){
      if (capituloEntity!=null){
          this.duracion = capituloEntity.getDuracion();
          this.nombre = capituloEntity.getNombre();
          this.descripcion = capituloEntity.getDescripcion();
          this.aptoParaMenores = capituloEntity.getAptoParaMenores();
      }
    }
      public CapituloEntity toEntity() {
        CapituloEntity capEntity = new CapituloEntity();
        capEntity.setDuracion(this.duracion);
        capEntity.setNombre(this.nombre);
        capEntity.setDescripcion(this.descripcion);
        capEntity.setAptoParaMenores(this.aptoParaMenores);
        return capEntity;
    }
        
}
