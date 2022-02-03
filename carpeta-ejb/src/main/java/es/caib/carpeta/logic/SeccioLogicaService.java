package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

import es.caib.carpeta.ejb.SeccioService;
import es.caib.carpeta.model.entity.Seccio;
import es.caib.carpeta.persistence.SeccioJPA;

import java.util.List;
import java.util.Set;

/**
 * Created by Fundació BIT.
 *
 * @author anadal
 */
@Local
public interface SeccioLogicaService extends SeccioService {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/SeccioLogicaEJB!es.caib.carpeta.logic.SeccioLogicaService";

    
    public SeccioJPA findByPrimaryKey(Long _ID_);
    
    public SeccioJPA findByContext(String context) throws I18NException;
    
    public List<Seccio> findByEntity(long entitatID, Long seccioPareID) throws I18NException;
    

    /**
     * Esborra secció de BD, fitxer de BD i fitxer físic. 
     * @param seccio
     * @throws I18NException
     */
    public void deleteFull(Seccio seccio) throws I18NException;
    

    /**
     * Esborra secció de BD i fitxer de BD (No esborra fitxer físic)
     * @param seccio
     * @return
     * @throws I18NException
     */
    public Set<Long> deleteFullRecursive(Seccio seccio) throws I18NException;

}
