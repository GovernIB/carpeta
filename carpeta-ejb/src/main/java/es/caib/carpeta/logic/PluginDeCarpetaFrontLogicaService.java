package es.caib.carpeta.logic;

import javax.ejb.Local;

import es.caib.carpeta.pluginsib.carpetafront.api.ICarpetaFrontPlugin;
import es.caib.carpeta.pluginsib.carpetafront.api.TitlesInfo;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginDeCarpetaFrontLogicaService extends AbstractPluginLogicaService<ICarpetaFrontPlugin> {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginDeCarpetaFrontLogicaEJB!es.caib.carpeta.logic.PluginDeCarpetaFrontLogicaService";

    public TitlesInfo getTitlesInfo(long pluginID);

}
