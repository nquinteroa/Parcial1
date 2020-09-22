/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.entities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicolas Quintero Acevedo 201913972
 */
@Entity
public class CapituloEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne
    SerieEntity serieEntity;
    
    String nombre;
    
    Integer duracion;
    
    String descripcion;
    
    Boolean aptoParaMenores;
    
    public CapituloEntity()
    {
    }

    public SerieEntity getSerieEntity() {
        return serieEntity;
    }

    public void setSerieEntity(SerieEntity serieEntity) {
        this.serieEntity = serieEntity;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getAptoParaMenores() {
        return aptoParaMenores;
    }

    public void setAptoParaMenores(Boolean aptoParaMenores) {
        this.aptoParaMenores = aptoParaMenores;
    }
    
    
}
