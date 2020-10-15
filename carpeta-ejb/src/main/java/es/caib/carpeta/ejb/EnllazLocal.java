
package es.caib.carpeta.ejb;

import es.caib.carpeta.jpa.EnllazIJPAManager;
import es.caib.carpeta.jpa.EnllazJPA;
import es.caib.carpeta.model.dao.IEnllazManager;

import javax.ejb.Local;

@Local
public interface EnllazLocal extends EnllazIJPAManager,IEnllazManager {

 public static final String JNDI_NAME = "java:app/carpeta-ejb/EnllazEJB!es.caib.carpeta.ejb.EnllazLocal";

    public EnllazJPA findByPrimaryKey(Long _ID_);
}
