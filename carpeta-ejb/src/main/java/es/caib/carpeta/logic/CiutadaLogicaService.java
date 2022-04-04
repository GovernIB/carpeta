package es.caib.carpeta.logic;

import org.fundaciobit.genapp.common.i18n.I18NException;

import javax.ejb.Local;

import es.caib.carpeta.ejb.CiutadaService;
import es.caib.carpeta.model.entity.Ciutada;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface CiutadaLogicaService extends CiutadaService  {
	
	public static final String JNDI_NAME = "java:app/carpeta-ejb/CiutadaLogicaEJB!es.caib.carpeta.logic.CiutadaLogicaService";

	public Ciutada createPublic(Ciutada ciutada) throws I18NException;
	
	public void updatePublic(Ciutada ciutada) throws I18NException;

}

