
package es.caib.carpeta.ejb;

import javax.ejb.Local;

import es.caib.carpeta.persistence.EnllazJPA;
import es.caib.carpeta.persistence.EnllazIJPAManager;
import es.caib.carpeta.model.dao.IEnllazManager;

@Local
public interface EnllazLocal extends EnllazIJPAManager,IEnllazManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/EnllazEJB!es.caib.carpeta.ejb.EnllazLocal";

    public EnllazJPA findByPrimaryKey(Long _ID_);
}
