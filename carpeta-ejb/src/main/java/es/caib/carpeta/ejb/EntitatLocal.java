
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.EntitatIJPAManager;
import es.caib.carpeta.jpa.EntitatJPA;
import es.caib.carpeta.model.dao.IEntitatManager;

import javax.ejb.Local;

@Local
public interface EntitatLocal extends EntitatIJPAManager,IEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/EntitatEJB!es.caib.carpeta.ejb.EntitatLocal";

    public EntitatJPA findByPrimaryKey(Long _ID_);
}
