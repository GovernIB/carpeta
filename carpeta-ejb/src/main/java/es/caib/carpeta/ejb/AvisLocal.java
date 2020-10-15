
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.AvisIJPAManager;
import es.caib.carpeta.jpa.AvisJPA;
import es.caib.carpeta.model.dao.IAvisManager;

import javax.ejb.Local;

@Local
public interface AvisLocal extends AvisIJPAManager,IAvisManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AvisEJB!es.caib.carpeta.ejb.AvisLocal";

    public AvisJPA findByPrimaryKey(Long _ID_);
}
