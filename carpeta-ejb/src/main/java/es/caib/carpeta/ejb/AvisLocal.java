
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.AvisJPA;
import es.caib.carpeta.persistence.AvisIJPAManager;
import es.caib.carpeta.model.dao.IAvisManager;

@Local
public interface AvisLocal extends AvisIJPAManager,IAvisManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AvisEJB!es.caib.carpeta.ejb.AvisLocal";

    public AvisJPA findByPrimaryKey(Long _ID_);
}
