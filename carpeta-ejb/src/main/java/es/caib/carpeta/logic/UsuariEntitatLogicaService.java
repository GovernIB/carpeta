package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.UsuariEntitatService;
import es.caib.carpeta.persistence.UsuariEntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UsuariEntitatLogicaService extends UsuariEntitatService  {
	
	public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEntitatLogicaEJB!es.caib.carpeta.logic.UsuariEntitatLogicaService";

	public List<UsuariEntitatJPA> findAllByUsuariId(@NotNull long usuarioID) throws I18NException;
	
	public List<UsuariEntitatJPA> findAllByUsuariIdWithEntitat(@NotNull long usuarioID) throws I18NException;

}

