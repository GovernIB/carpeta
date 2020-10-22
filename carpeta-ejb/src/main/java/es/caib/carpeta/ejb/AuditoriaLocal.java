
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.jpa.AuditoriaJPA;
import es.caib.carpeta.jpa.AuditoriaIJPAManager;
import es.caib.carpeta.model.dao.IAuditoriaManager;

@Local
public interface AuditoriaLocal extends AuditoriaIJPAManager,IAuditoriaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AuditoriaEJB!es.caib.carpeta.ejb.AuditoriaLocal";

    public AuditoriaJPA findByPrimaryKey(Long _ID_);
}
