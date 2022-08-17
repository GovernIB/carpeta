
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.PluginJPA;
import es.caib.carpeta.persistence.PluginIJPAManager;
import es.caib.carpeta.model.dao.IPluginManager;

import es.caib.carpeta.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/PluginEJB!es.caib.carpeta.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Plugin instance, FitxerService fitxerEjb) throws I18NException;
}
