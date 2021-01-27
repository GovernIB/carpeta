
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.EntitatJPA;
import es.caib.carpeta.persistence.EntitatIJPAManager;
import es.caib.carpeta.model.dao.IEntitatManager;

@Local
public interface EntitatLocal extends EntitatIJPAManager,IEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/EntitatEJB!es.caib.carpeta.ejb.EntitatLocal";

    public EntitatJPA findByPrimaryKey(Long _ID_);
}
