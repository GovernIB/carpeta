package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.UsuariEntitatLocal;
import es.caib.carpeta.jpa.UsuariEntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UsuariEntitatLogicaLocal extends UsuariEntitatLocal  {
	
	public static final String JNDI_NAME = "java:app/carpeta-logic/UsuariEntitatLogicaEJB!es.caib.carpeta.logic.UsuariEntitatLogicaLocal";

	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException;
	
	public List<UsuariEntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException;

}

