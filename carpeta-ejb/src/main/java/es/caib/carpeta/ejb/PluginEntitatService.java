
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PluginEntitatJPA;
import es.caib.carpeta.persistence.PluginEntitatIJPAManager;
import es.caib.carpeta.model.dao.IPluginEntitatManager;

@Local
public interface PluginEntitatService extends PluginEntitatIJPAManager,IPluginEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEntitatEJB!es.caib.carpeta.ejb.PluginEntitatService";

    public PluginEntitatJPA findByPrimaryKey(Long _ID_);
}
