
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.jpa.AccesJPA;
import es.caib.carpeta.jpa.AccesIJPAManager;
import es.caib.carpeta.model.dao.IAccesManager;

@Local
public interface AccesLocal extends AccesIJPAManager,IAccesManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/AccesEJB!es.caib.carpeta.ejb.AccesLocal";

    public AccesJPA findByPrimaryKey(Long _ID_);
}
