package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.AvisLocal;
import es.caib.carpeta.jpa.AvisJPA;

/**
 * 
 * @author jagarcia
 * 
 */
@Local
public interface AvisLogicaLocal extends AvisLocal{
  
    public static final String JNDI_NAME = "java:app/carpeta-logic/AvisLogicaEJB!es.caib.carpeta.logic.AvisLogicaLocal";
  
    public List<AvisJPA> findAllActive () throws I18NException;
    
    public List<AvisJPA> findActiveByEntidadID (long entidadID) throws I18NException;

    /** Cerca els avisos actius d'un plugin en concret i els ordena de més greu a menys **/
    public List<AvisJPA> findActiveByPluginID (long pluginID) throws I18NException;

}

