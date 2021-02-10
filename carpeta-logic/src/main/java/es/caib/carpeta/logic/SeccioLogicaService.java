package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

import es.caib.carpeta.ejb.SeccioService;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.persistence.SeccioJPA;

import java.util.List;

/**
 * Created by Fundació BIT.
 *
 * @author anadal
 */
@Local
public interface SeccioLogicaService extends SeccioService {

    public static final String JNDI_NAME = "java:app/carpeta-logic/SeccioLogicaEJB!es.caib.carpeta.logic.SeccioLogicaService";

    
    public SeccioJPA findByPrimaryKey(Long _ID_);
    
    public List<Seccio> findByEntity(long entitatID, Long seccioPareID) throws I18NException;

}
