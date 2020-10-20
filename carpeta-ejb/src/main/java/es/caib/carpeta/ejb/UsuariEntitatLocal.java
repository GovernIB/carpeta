
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.UsuariEntitatIJPAManager;
import es.caib.carpeta.jpa.UsuariEntitatJPA;
import es.caib.carpeta.model.dao.IUsuariEntitatManager;

import javax.ejb.Local;

@Local
public interface UsuariEntitatLocal extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEntitatEJB!es.caib.carpeta.ejb.UsuariEntitatLocal";

    public UsuariEntitatJPA findByPrimaryKey(Long _ID_);
}
