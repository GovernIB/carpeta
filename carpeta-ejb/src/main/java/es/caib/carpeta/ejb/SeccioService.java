
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.SeccioJPA;
import es.caib.carpeta.persistence.SeccioIJPAManager;
import es.caib.carpeta.model.dao.ISeccioManager;

@Local
public interface SeccioService extends SeccioIJPAManager,ISeccioManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/SeccioEJB!es.caib.carpeta.ejb.SeccioService";

    public SeccioJPA findByPrimaryKey(Long _ID_);
}
