
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.LogCarpetaIJPAManager;
import es.caib.carpeta.jpa.LogCarpetaJPA;
import es.caib.carpeta.model.dao.ILogCarpetaManager;

import javax.ejb.Local;

@Local
public interface LogCarpetaLocal extends LogCarpetaIJPAManager,ILogCarpetaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/LogCarpetaEJB!es.caib.carpeta.ejb.LogCarpetaLocal";

    public LogCarpetaJPA findByPrimaryKey(Long _ID_);
}
