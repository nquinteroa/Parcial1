/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.parcial1.dtos;

import co.edu.uniandes.csw.parcial1.entities.CapituloEntity;
import co.edu.uniandes.csw.parcial1.entities.SerieEntity;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Nicolas Quintero
 */
public class SerieDetailDTO extends SerieDTO{
    
     private List<CapituloDTO> capitulo;
    
    public SerieDetailDTO()
    {
        super();
    }
    
     /**
     * Crea un objeto PaseadorDetailDTO a partir de un objeto AuthorEntity
     * incluyendo los atributos de PaseadorDTO.
     *
     *
     * @param serieEntity
     * @param paseadorEntity
     */
    public SerieDetailDTO(SerieEntity serieEntity) {
        super(serieEntity);
        if (serieEntity != null) {
            capitulo = new ArrayList<>();
            for (CapituloEntity entityCapitulo : serieEntity.getCapitulos()) {
                capitulo.add(new CapituloDTO(entityCapitulo));
            }       
        }
    }
    
    /**
     * Convierte un objeto PaseadorDetailDTO a PaseadorEntity incluyendo los
     * atributos de AuthorDTO.
     *
     * @return Nueva objeto PaseadorEntity.
     *
     */
     
    @Override
    public SerieEntity toEntity() {
        SerieEntity serieEntity = super.toEntity();
        if (capitulo != null) {
            List<CapituloEntity> capituloEntity = new ArrayList<>();
            for (CapituloDTO dtoCapitulo : capitulo) {
                capituloEntity.add(dtoCapitulo.toEntity());
            }
            serieEntity.setCapitulos(capituloEntity);
        }
        return serieEntity;
    }
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
