package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

import es.caib.carpeta.ejb.EntitatLocal;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.model.entity.Entitat;


/**
 * 
 * @author jagarcia
 *
 */
@Local
public interface EntitatLogicaLocal extends EntitatLocal {
	
    public static final String JNDI_NAME = "java:app/carpeta-logic/EntitatLogicaEJB!es.caib.carpeta.logic.EntitatLogicaLocal";
    
    public void deleteFull(Entitat entitat, boolean deleteFiles) throws I18NException;

    public EntitatJPA findByCodi(String codiEntitat) throws I18NException;

    public EntitatJPA findByCodiDir3(String codiDir3Entitat) throws I18NException;

}
