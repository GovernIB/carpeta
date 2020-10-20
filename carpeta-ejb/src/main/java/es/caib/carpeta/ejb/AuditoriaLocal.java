
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.AuditoriaIJPAManager;
import es.caib.carpeta.jpa.AuditoriaJPA;
import es.caib.carpeta.model.dao.IAuditoriaManager;

import javax.ejb.Local;

@Local
public interface AuditoriaLocal extends AuditoriaIJPAManager,IAuditoriaManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AuditoriaEJB!es.caib.carpeta.ejb.AuditoriaLocal";

    public AuditoriaJPA findByPrimaryKey(Long _ID_);
}
