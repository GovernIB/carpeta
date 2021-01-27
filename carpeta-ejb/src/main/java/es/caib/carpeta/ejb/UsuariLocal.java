
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.UsuariJPA;
import es.caib.carpeta.persistence.UsuariIJPAManager;
import es.caib.carpeta.model.dao.IUsuariManager;

@Local
public interface UsuariLocal extends UsuariIJPAManager,IUsuariManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEJB!es.caib.carpeta.ejb.UsuariLocal";

    public UsuariJPA findByPrimaryKey(Long _ID_);
}
