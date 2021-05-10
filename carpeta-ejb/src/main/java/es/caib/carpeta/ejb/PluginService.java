
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.persistence.PluginIJPAManager;
import es.caib.carpeta.model.dao.IPluginManager;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEJB!es.caib.carpeta.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);
}
