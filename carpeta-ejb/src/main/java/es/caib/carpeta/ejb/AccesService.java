
package es.caib.carpeta.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.carpeta.persistence.AccesJPA;
import es.caib.carpeta.persistence.AccesIJPAManager;
import es.caib.carpeta.model.dao.IAccesManager;

@Local
public interface AccesService extends AccesIJPAManager,IAccesManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AccesEJB!es.caib.carpeta.ejb.AccesService";

    public AccesJPA findByPrimaryKey(Long _ID_);
}
