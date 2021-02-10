package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.AvisService;
import es.caib.carpeta.model.entity.Avis;
import es.caib.carpeta.persistence.AvisJPA;

/**
 * 
 * @author jagarcia
 * 
 */
@Local
public interface AvisLogicaService extends AvisService {
  
    public static final String JNDI_NAME = "java:app/carpeta-logic/AvisLogicaEJB!es.caib.carpeta.logic.AvisLogicaService";
  
    public List<Avis> findAllActive () throws I18NException;
    
    public List<Avis> findActiveByEntidadID (long entidadID) throws I18NException;

    /** Cerca els avisos actius d'un plugin en concret i els ordena de més greu a menys **/
    public List<AvisJPA> findActiveByPluginID (long pluginID) throws I18NException;

    /** Cerca els avisos actius d'un front public en concret i els ordena de més greu a menys **/
    public List<AvisJPA> findActiveAvisos (String codiEntitat, int avisType) throws I18NException;
}

