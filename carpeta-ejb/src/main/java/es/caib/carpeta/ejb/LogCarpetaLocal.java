
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.LogCarpetaJPA;
import es.caib.carpeta.persistence.LogCarpetaIJPAManager;
import es.caib.carpeta.model.dao.ILogCarpetaManager;

@Local
public interface LogCarpetaLocal extends LogCarpetaIJPAManager,ILogCarpetaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/LogCarpetaEJB!es.caib.carpeta.ejb.LogCarpetaLocal";

    public LogCarpetaJPA findByPrimaryKey(Long _ID_);
}
