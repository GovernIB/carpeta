
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.jpa.PluginEntitatJPA;
import es.caib.carpeta.jpa.PluginEntitatIJPAManager;
import es.caib.carpeta.model.dao.IPluginEntitatManager;

@Local
public interface PluginEntitatLocal extends PluginEntitatIJPAManager,IPluginEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEntitatEJB!es.caib.carpeta.ejb.PluginEntitatLocal";

    public PluginEntitatJPA findByPrimaryKey(Long _ID_);
}
