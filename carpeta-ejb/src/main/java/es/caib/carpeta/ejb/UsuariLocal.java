
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.UsuariIJPAManager;
import es.caib.carpeta.jpa.UsuariJPA;
import es.caib.carpeta.model.dao.IUsuariManager;

import javax.ejb.Local;

@Local
public interface UsuariLocal extends UsuariIJPAManager,IUsuariManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/UsuariEJB!es.caib.carpeta.ejb.UsuariLocal";

    public UsuariJPA findByPrimaryKey(Long _ID_);
}
