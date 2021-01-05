package es.caib.carpeta.back.utils;

import es.caib.carpeta.commons.utils.Configuracio;
import es.caib.carpeta.commons.utils.Constants;

import java.util.Properties;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

/**
 * 
 * @author anadal
 * 
 */
public class CarpetaPluginsManager implements Constants {

	protected static Logger log = Logger.getLogger(CarpetaPluginsManager.class);

	public static final String USERINFORMATION_PLUGIN_KEY = CARPETA_PROPERTY_BASE + "userinformationplugin";

	public static IUserInformationPlugin userInformationPlugin = null;

	public static IUserInformationPlugin getUserInformationPluginInstance() throws EJBException { 
		if (userInformationPlugin == null) {
		    
		    Properties props = Configuracio.getFilesProperties();

			String className = props.getProperty(USERINFORMATION_PLUGIN_KEY);
			Object pluginInstance;
			try {
			    pluginInstance = PluginsManager.instancePluginByClassName(className, CARPETA_PROPERTY_BASE, props);
			    
			    if (pluginInstance == null) {
			      log.error("pluginInstance RETORNAT és null ");
			    }
			    
            } catch (Exception th) {
                String msg = "Error no controlat instanciant Plugin de userInformation: " + th.getMessage();
                log.error(msg, th);
                pluginInstance = null;
                throw new EJBException("plugin.donotinstantiateplugin", th);
            }
			
			if (pluginInstance == null) {
				throw new EJBException("plugin.donotinstantiateplugin");
			}
			userInformationPlugin = (IUserInformationPlugin) pluginInstance;
		}
		return userInformationPlugin;
	}

}
