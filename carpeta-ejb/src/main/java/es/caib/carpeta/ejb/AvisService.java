
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.AvisIJPAManager;
import es.caib.carpeta.model.dao.IAvisManager;

@Local
public interface AvisService extends AvisIJPAManager,IAvisManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/AvisEJB!es.caib.carpeta.ejb.AvisService";

    public AvisJPA findByPrimaryKey(Long _ID_);
}
