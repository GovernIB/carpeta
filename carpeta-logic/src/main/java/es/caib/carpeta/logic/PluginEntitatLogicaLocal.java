package es.caib.carpeta.logic;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.carpeta.ejb.PluginEntitatLocal;
import es.caib.carpeta.jpa.PluginEntitatJPA;

/**
 * 
 * @author jagarcia
 *
 */
@Local
public interface PluginEntitatLogicaLocal extends PluginEntitatLocal  {
	
	public static final String JNDI_NAME = "java:app/carpeta-logic/PluginEntitatLogicaEJB!es.caib.carpeta.logic.PluginEntitatLogicaLocal";

	public List<PluginEntitatJPA> findAllByEntitatId(@NotNull long codiEntitat) throws I18NException;

	public List<Long> getPluginsEntitat(String codiEntitat, boolean actiu) throws I18NException;
	
	public List<Long> getAllPluginsByEntitat(String codiEntitat) throws I18NException;

}

