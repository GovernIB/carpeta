package es.caib.carpeta.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.UsuariLocal;
import es.caib.carpeta.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UsuariLogicaLocal extends UsuariLocal  {
	
	public static final String JNDI_NAME = "java:app/carpeta-logic/UsuariLogicaEJB!es.caib.carpeta.logic.UsuariLogicaLocal";

	
	
	public UsuariJPA crearUsuari(UsuariJPA usuario) throws I18NException, javax.ejb.EJBException;
	
	
	public UsuariJPA findByUsername(String username) throws I18NException;
	
	/*
	public UsuariJPA getUserInfoFromUserInformation(String username) throws javax.ejb.EJBException;
	
	public UsuariJPA userInfo2UsuariJPA(UserInfo info);
*/
}

