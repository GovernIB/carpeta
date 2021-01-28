
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.UsuariEntitatJPA;
import es.caib.carpeta.persistence.UsuariEntitatIJPAManager;
import es.caib.carpeta.model.dao.IUsuariEntitatManager;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEntitatEJB!es.caib.carpeta.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
