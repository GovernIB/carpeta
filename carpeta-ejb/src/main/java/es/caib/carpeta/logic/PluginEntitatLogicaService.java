package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.PluginEntitatService;
import es.caib.carpeta.persistence.PluginEntitatJPA;

/**
 * 
 * @author jagarcia
 *
 */
@Local
public interface PluginEntitatLogicaService extends PluginEntitatService  {
	
	public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEntitatLogicaEJB!es.caib.carpeta.logic.PluginEntitatLogicaService";

	public List<PluginEntitatJPA> findAllByEntitatId(@NotNull long codiEntitat) throws I18NException;

	public List<Long> getPluginsEntitat(String codiEntitat, boolean actiu, Long seccioID) throws I18NException;
	
	public List<Long> getAllPluginsByEntitat(String codiEntitat) throws I18NException;

}

