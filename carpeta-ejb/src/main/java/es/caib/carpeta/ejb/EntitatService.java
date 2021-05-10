
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.EntitatIJPAManager;
import es.caib.carpeta.model.dao.IEntitatManager;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/carpeta-ejb/EntitatEJB!es.caib.carpeta.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(Long _ID_);
}
