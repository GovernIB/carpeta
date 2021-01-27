package es.caib.carpeta.logic;

import java.util.Set;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.EnllazService;
import es.caib.carpeta.model.entity.Enllaz;

/**
 * 
 * @author jagarcia
 *
 */
@Local
public interface EnllazLogicaService extends EnllazService{
	
	public static final String JNDI_NAME = "java:app/carpeta-logic/EnllazLogicaEJB!es.caib.carpeta.logic.EnllazLogicaService";
	
	public Set<Long> deleteFull(Enllaz enllaz, boolean deleteFiles) throws I18NException;

}
