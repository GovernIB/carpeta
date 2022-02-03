package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

import es.caib.carpeta.ejb.EntitatService;
import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.model.entity.Entitat;


/**
 * 
 * @author jagarcia
 * @author anadal
 *
 */
@Local
public interface EntitatLogicaService extends EntitatService {
	
    public static final String JNDI_NAME = "java:app/carpeta-ejb/EntitatLogicaEJB!es.caib.carpeta.logic.EntitatLogicaService";
    
    public void deleteFull(Entitat entitat) throws I18NException;

    public EntitatJPA findByCodi(String codiEntitat) throws I18NException;

    public EntitatJPA findByCodiDir3(String codiDir3Entitat) throws I18NException;

}
